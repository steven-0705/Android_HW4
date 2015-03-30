package data;

import com.googlecode.objectify.annotation.*;

import java.util.Date;

/**
 * Created by Chris on 3/29/2015.
 */

@Entity
public abstract class DataObject {

    @Id public long id;
    public String name;
    public long dateCreated = System.currentTimeMillis();
    public long createdByUser;

    public DataObject() {
        id = 0;
    }

}
