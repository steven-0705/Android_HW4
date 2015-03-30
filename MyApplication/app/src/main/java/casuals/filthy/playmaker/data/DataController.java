package casuals.filthy.playmaker.data;

/**
 * Created by Chris on 3/26/2015.
 */
public class DataController {

    public static final String ServletURL = "http://playmaker-app.appspot.com/hello";
    private static DataController singleton = null;

    // Object data
    private long userID = 0; // 0 will be reserved sever-side for invalid requests

    private DataController(long id) {
        userID = id;
    }

    public static DataController getDataController(long id) {
        if (singleton == null) {
            synchronized (DataController.class) {
                if (singleton == null)
                    singleton = new DataController(id);
            }
        }

        return singleton;
    }






}
