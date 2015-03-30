package data;

import com.googlecode.objectify.annotation.Subclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 3/29/2015.
 */
@Subclass
public class UserData extends DataObject{

    public String email;
    public List<Map<String, String>> groups;

    private UserData() {};

    public UserData(String email, String name, long user_id) {
        this.id = user_id;
        this.name = name;
        this.email = email;

        groups = new ArrayList<Map<String, String>>();
    }


}
