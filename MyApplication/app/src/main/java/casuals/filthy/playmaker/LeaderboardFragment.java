package casuals.filthy.playmaker;

/**
 * Created by Shane on 3/19/2015.
 */
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class LeaderboardFragment extends ListFragment {

    List<HashMap<String,String>>  Entries = LeaderboardEntries.ITEMS;
    String[] from = {LeaderboardEntries.KEY_ICON, LeaderboardEntries.KEY_NAME, LeaderboardEntries.KEY_PLACE};
    int[] to = {R.id.item_icon, R.id.item_textName, R.id.item_textPlace};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View leaderboardView =  super.onCreateView(inflater, container, savedInstanceState);

        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), Entries , R.layout.leaderboard_item_view, from, to);

        setListAdapter(adapter);

        return leaderboardView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        View header = getLayoutInflater(savedInstanceState).inflate(R.layout.leaderboard_view, null);
        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(getListView());
        setHasOptionsMenu(true);
        getListView().addHeaderView(header);
        Spinner spin = (Spinner) getListView().findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> categories = new ArrayList<String>();
        categories.add("soccer");
        categories.add("baseball");
        categories.add("football");
        categories.add("Basketball");
        categories.add("LAN PARTY");
        categories.add("Quidditch");
        ArrayAdapter<String> dataAdapater = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,categories);
        dataAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapater);
        getListView().setDividerHeight(10);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        if(position == 0)
        {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.leaderboard_popout, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }});
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

    }


}