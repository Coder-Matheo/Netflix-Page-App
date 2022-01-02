package rob.netflix2app.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DatabaseBio_Dao {
    @Insert
    void insertBio(BioObj bioObj);

}
