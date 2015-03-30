package data.metadata;

import com.googlecode.objectify.annotation.*;

/**
 * Created by Chris on 3/29/2015.
 */
@Entity
public abstract class MetaDataObject {

    //public static final long META_ID = 12345;

    // Only supposed to be one of every meta-data object
    @Id long data_id;
    protected long version;
    protected boolean modified = false;

    public long getVersion() {
        return version;
    }

    public boolean isModified() {
        return modified;
    }

}
