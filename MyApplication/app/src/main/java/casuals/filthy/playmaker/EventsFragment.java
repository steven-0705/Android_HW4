package casuals.filthy.playmaker;

/**
 * Created by Shane on 3/19/2015.
 */
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class EventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View eventView = inflater.inflate(R.layout.events, container,false);
        // TODO Auto-generated method stub
        Spinner dropdown = (Spinner)eventView.findViewById(R.id.spinner1);
        String[] items = new String[]{"Basketball", "Baseball", "LAN Party", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);

        //View dateView = inflater.inflate(R.layout.abc_popup_menu_item_layout, container, false);
        final Button button = (Button) eventView.findViewById(R.id.button1);
        final EditText editText = (EditText) eventView.findViewById(R.id.edittext1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                int mYear = currentDate.get(Calendar.YEAR);
                int mMonth = currentDate.get(Calendar.MONTH);
                int mDay = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog DatePicker;
                DatePicker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        // TODO Auto-generated method stub
                        month = month + 1;
                        button.setText("" + month + "/" + day + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                DatePicker.setTitle("Select Date");
                DatePicker.show();
            }
        });
        return eventView;
    }
}