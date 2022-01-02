package rob.netflix2app.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DatabasePost_Dao {
    @Insert
    void insert(PostObj postObj);
}
