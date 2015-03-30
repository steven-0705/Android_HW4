package casuals.filthy.playmaker;

/**
 * Created by Shane on 3/19/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class FragmentPageAdapter extends FragmentPagerAdapter {
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        switch (arg0) {
            case 0:
                return new GroupFragment();
            case 1:
                return new EventsFragment();
            case 2:
                return  new LeaderboardFragment();
            default:
                break;
        }
        return null;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }
}
