package com.example.steven.homework_4;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private String API_KEY = "AIzaSyCs6-RKKF9uy_x8WXGnWIYu5Fj5zt6gD4w";
    private ArrayList<Double> latitude;
    private ArrayList<Double> longitude;
    private ArrayList<String> lat;
    private ArrayList<String> lng;
    private ArrayList<String> markerNames;
    private boolean done;

    public void sendAddress(View view){
        final EditText editText = (EditText) findViewById(R.id.edit_message);
        latitude = new ArrayList<Double>();
        longitude = new ArrayList<Double>();
        lat = new ArrayList<String>();
        lng = new ArrayList<String>();
        markerNames = new ArrayList<String>();
        done = false;
        //Log.println(10, "Log", request);
        Thread t = new Thread(new Runnable(){
            public void run (){
                String address = editText.getText().toString();
                address = address.replace(' ','+');
                String request = "https://maps.googleapis.com/maps/api/geocode/json?address=";
                request += address;
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(request);
                HttpResponse response = null;
                try{
                    response = client.execute(get);

                    String json = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObj = new JSONObject(json);
                    Log.i("JSON String =", jsonObj.toString());
                    String status = jsonObj.getString("status").toString();
                    Log.i("Status =", status);

                    if(status.equalsIgnoreCase("OK")){
                        JSONArray results = jsonObj.getJSONArray("results");
                        for(int k = 0; k < results.length(); k += 1){
                            JSONObject array = results.getJSONObject(k);
                            if(array.has("formatted_address")){
                                markerNames.add(array.getString("formatted_address").toString());
                            }
                            if(array.has("geometry")) {
                                lat.add(array.getJSONObject("geometry").getJSONObject("location").getString("lat").toString());
                                lng.add(array.getJSONObject("geometry").getJSONObject("location").getString("lng").toString());
                            }
                        }
                    }
                }
                catch(IOException e){
                    e.printStackTrace();
                  }
                catch(JSONException e){
                    e.printStackTrace();
                }
                if((lat.get(0) != null) && (lng.get(0) != null)){
                    latitude.add(Double.parseDouble(lat.get(0)));
                    longitude.add(Double.parseDouble(lng.get(0)));
                }

                request = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
                request += latitude.get(0)+","+longitude.get(0)+"&radius=3000&types=gas_station&key="+API_KEY;
                get = new HttpGet(request);
                try{
                    response = client.execute(get);

                    String json = EntityUtils.toString(response.getEntity());
                    Log.i("JSON", json);
                    JSONObject jsonObj = new JSONObject(json);
                    String status = jsonObj.getString("status").toString();
                    if(status.equalsIgnoreCase("OK")){
                        JSONArray results = jsonObj.getJSONArray("results");
                        for(int k = 0; k < results.length(); k += 1){
                            JSONObject array = results.getJSONObject(k);
                            if(array.has("name")){
                                markerNames.add(array.getString("name").toString());
                            }
                            if(array.has("geometry")){
                                lat.add(array.getJSONObject("geometry").getJSONObject("location").getString("lat").toString());
                                lng.add(array.getJSONObject("geometry").getJSONObject("location").getString("lng").toString());
                            }
                        }
                    }
                    for(int k = 1; k < markerNames.size(); k += 1){
                        if((lat.get(k) != null) && (lng.get(k) != null)){
                            latitude.add(Double.parseDouble(lat.get(k)));
                            longitude.add(Double.parseDouble(lng.get(k)));
                        }
                    }
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                done = true;
                //map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            }
        });
        t.start();
        while(!done){}
        Log.i("Lat =", latitude+"");
        Log.i("Lng =", longitude+"");
        if((lat.get(0) != null) && (lng.get(0) != null)){
            LatLng latlng = new LatLng(latitude.get(0), longitude.get(0));
            map.clear();
            map.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            map.moveCamera(CameraUpdateFactory.zoomTo(12));
            Marker marker = map.addMarker(new MarkerOptions().position(latlng).title(markerNames.get(0)));
            marker.showInfoWindow();
            for(int k = 1; k < markerNames.size(); k += 1){
                if((lat.get(k) != null) && (lng.get(k) != null)){
                    latlng = new LatLng(latitude.get(k), longitude.get(k));
                    map.addMarker(new MarkerOptions().position(latlng).title(markerNames.get(k)));
                }
            }
        }
    }

    public void normalMap(View view){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void satelliteMap(View view){
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #map} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #map} is not null.
     */
    private void setUpMap() {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
