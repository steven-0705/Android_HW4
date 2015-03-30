package data.metadata;

import com.googlecode.objectify.annotation.Subclass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 3/29/2015.
 */
@Subclass
public class UserLookup extends MetaDataObject {

    public static final long META_ID = 51232;
    private Map<String, Long> lookup;

    public UserLookup() {
        data_id = META_ID;
        lookup = new HashMap<String, Long>();
    }

    /**
     *
     * @param email
     * @return returns the id of the user, returns -1 if user not found
     */
    public long getUserId(String email) {
        Long id = lookup.get(email);
        return (id == null) ? -1 : id;
    }

    /**
     *
     * @param email
     * @param id
     * @return true if the lookup did not contain user, false if it already did
     */
    public boolean addUser(String email, long id) {
        if (lookup.containsKey(email))
            return false;

        lookup.put(email, id);
        modified = true;
        return true;
    }
}
