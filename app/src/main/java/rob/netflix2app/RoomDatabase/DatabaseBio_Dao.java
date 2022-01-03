package rob.netflix2app.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabaseBio_Dao {
    @Insert
    void insertBio(BioObj bioObj);

    @Query("SELECT * FROM bioobj")
    LiveData<List<BioObj>> getAllUsers();

    @Query("SELECT * FROM bioobj WHERE user_name LIKE :username AND password LIKE :password LIMIT 1")
    LiveData<BioObj> findUserByNamePass(String username, String password);



}
