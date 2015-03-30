package data.metadata;

import com.googlecode.objectify.annotation.Subclass;

/**
 * Created by Chris on 3/29/2015.
 */
@Subclass
public class UserIdMgr extends MetaDataObject {

    // reserve 0 for null
    public static final long META_ID = 12345;

    private long nextId = 1;

    public UserIdMgr () {
        data_id = META_ID;
    }

    public long getNextId() {
        long next = nextId;
        increment();
        return next;
    }

    public long peakNextId() {
        return nextId;
    }

    public void increment() {
        nextId++;
        modified = true;
    }

}
