package rob.netflix2app.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DatabasePost_Dao {
    @Insert
    void insert(PostObj postObj);

    @Query("SELECT * FROM postobj")
    List<PostObj> getAllTweetPost();
    //LiveData<List<PostObj>> getAllTweetPost();

    @Update
    void updateLike(PostObj postObj);

    @Query("SELECT * FROM postobj WHERE uid LIKE :uid LIMIT 1")
    PostObj findById(int uid);




}
