package casuals.filthy.playmaker;

/**
 * Created by Shane on 3/19/2015.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment {
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View groupView = inflater.inflate(R.layout.group, container,false);
        Bundle args = getArguments();
        ((CheckBox) groupView.findViewById(R.id.groupCheckBox1)).setText("Basketball");
        ((CheckBox) groupView.findViewById(R.id.groupCheckBox2)).setText("Baseball");
        ((CheckBox) groupView.findViewById(R.id.groupCheckBox3)).setText("LAN Party");
        ((TextView) groupView.findViewById(R.id.notification1)).setText("Variable Notification1");
        ((TextView) groupView.findViewById(R.id.notification2)).setText("Variable Notification2");
        // TODO Auto-generated method stub
        return groupView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(getView());
        setHasOptionsMenu(true);
        CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
        checkBox1 = (CheckBox) getView().findViewById(R.id.groupCheckBox1);
        checkBox2 = (CheckBox) getView().findViewById(R.id.groupCheckBox2);
        checkBox3 = (CheckBox) getView().findViewById(R.id.groupCheckBox3);
        checkBox4 = (CheckBox) getView().findViewById(R.id.groupCheckBox4);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(getActivity(),
                            "You Have selected Basketball", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(getActivity(),
                            "You Have selected Baseball", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(getActivity(),
                            "You Have selected LAN PARTY", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            EditText blah = (EditText) getView().findViewById(R.id.groupOther);
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    //Creating the instance of PopupMenu
                    PopupMenu popup = new PopupMenu(getActivity(), blah);
                    popup.getMenu().add("Soccer");
                    popup.getMenu().add("Football");
                    popup.getMenu().add("Quidditch");
                    popup.getMenu().add("Other");
                    popup.show();

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            if(!item.getTitle().equals("Other")){
                                blah.setText(item.getTitle());
                                return true;
                            }
                            blah.setEnabled(true);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(blah,0);
                            return false;
                        }
                    });


                }
                else{
                    blah.setEnabled(false);
                    blah.setText(null);
                }
            }
        });


    }

}