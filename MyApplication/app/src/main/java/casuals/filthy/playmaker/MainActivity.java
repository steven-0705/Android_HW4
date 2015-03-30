package casuals.filthy.playmaker;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener{
    ActionBar actionbar;
    ViewPager viewpager;
    FragmentPageAdapter ft;


    private static CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());
        actionbar = getActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayUseLogoEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowHomeEnabled(false);

        viewpager.setAdapter(ft);
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionbar.addTab(actionbar.newTab().setIcon(R.drawable.group_icon).setText("Group").setTabListener(this));

        actionbar.addTab(actionbar.newTab().setIcon(R.drawable.event_icon).setText("Events").setTabListener(this));
        actionbar.addTab(actionbar.newTab().setIcon(R.drawable.leaderboard_icon).setText("Leader Board").setTabListener(this));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                actionbar.setSelectedNavigationItem(arg0);

            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub


            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }



    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub


    }
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        viewpager.setCurrentItem(tab.getPosition());


    }
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub


    }



    }









