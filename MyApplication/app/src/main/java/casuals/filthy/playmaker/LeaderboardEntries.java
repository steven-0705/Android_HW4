package casuals.filthy.playmaker;

/**
 * Created by Shane on 3/23/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderboardEntries {

    /**
     * An array of sample (dummy) items.
     */
    public static List<HashMap<String,String>> ITEMS = new ArrayList<HashMap<String,String>>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public final static String KEY_ICON = "icon";
    public final static String KEY_NAME = "name";
    public final static String KEY_PLACE = "place";

//    public static Map<String, LeaderboardEntry> ITEM_MAP = new HashMap<String, LeaderboardEntry>();
    public static ArrayList<HashMap<String, String>> mLeaderboardEntries =
            new ArrayList<HashMap<String, String>>();


//    // Array of strings storing country names
//    String[] countries = new String[] {
//            "India",
//            "Pakistan",
//            "Sri Lanka",
//            "China",
//            "Bangladesh",
//            "Nepal",
//            "Afghanistan",
//            "North Korea",
//            "South Korea",
//            "Japan"
//    };
//
//    // Array of integers points to images stored in /res/drawable/
//    int[] flags = new int[]{
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//            R.drawable.leaderboard_icon,
//    };
//
//    // Array of strings to store currencies
//    String[] currency = new String[]{
//            "Indian Rupee",
//            "Pakistani Rupee",
//            "Sri Lankan Rupee",
//            "Renminbi",
//            "Bangladeshi Taka",
//            "Nepalese Rupee",
//            "Afghani",
//            "North Korean Won",
//            "South Korean Won",
//            "Japanese Yen"
//    };
    static {
        // Add 3 sample items.
        addItem("1", "Name 1", "1st Place", R.drawable.first_place, "user1");
        addItem("2", "Name 2", "2nd Place",R.drawable.second_place,"user2");
        addItem("3", "Name 3", "3rd Place",R.drawable.third_place,"user3");
        addItem("4", "Name 4", "4th Place",R.drawable.std_ribbon, "user1");
        addItem("5", "Name 5", "5th Place",R.drawable.std_ribbon,"user2");
        addItem("6", "Name 6", "6th Place",R.drawable.std_ribbon,"user3");
        addItem("7", "Name 7", "7th Place",R.drawable.std_ribbon, "user1");
        addItem("8", "Name 8", "8th Place",R.drawable.std_ribbon,"user2");
        addItem("9", "Name 9", "9th Place",R.drawable.std_ribbon,"user3");
        addItem("10", "Name 10", "10th Place",R.drawable.std_ribbon, "user1");
        addItem("11", "Name 11", "11th Place",R.drawable.std_ribbon,"user2");
        addItem("12", "Name 12", "12th Place",R.drawable.std_ribbon,"user3");
        addItem("13", "Name 13", "13th Place",R.drawable.std_ribbon, "user1");
        addItem("14", "Name 14", "14th Place",R.drawable.std_ribbon,"user2");
        addItem("15", "Name 15", "15th Place",R.drawable.std_ribbon,"user3");

    }

    private static void addItem(String id, String name, String place, int iconId, String content) {


        HashMap<String, String> leaderboardEntry = new HashMap<String, String>();
        leaderboardEntry.put(KEY_NAME, name);
        leaderboardEntry.put(KEY_PLACE, place);
        leaderboardEntry.put(KEY_ICON, String.valueOf(iconId));

        ITEMS.add(leaderboardEntry);
    }


}