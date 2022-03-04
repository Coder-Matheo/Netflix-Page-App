package rob.netflix2app.RoomDatabase;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabasePost_Dao {
    @Insert
    void insert(PostObj postObj);

    @Query("SELECT * FROM postobj")
    List<PostObj> getAllTweetPost();
    //LiveData<List<PostObj>> getAllTweetPost();



}
