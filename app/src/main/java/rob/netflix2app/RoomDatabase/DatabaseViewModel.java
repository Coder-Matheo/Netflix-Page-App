package rob.netflix2app.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {
    MySingletonRoom_Bio_DB mySingleton_database;
    MySingletonRoom_Post_DB mySingleton_Room_post_db;


    public DatabaseViewModel(@NonNull Application application) {
        super(application);

        mySingleton_database = MySingletonRoom_Bio_DB.getInstance(application.getApplicationContext());
        mySingleton_Room_post_db = MySingletonRoom_Post_DB.getInstance(application.getApplicationContext());

    }

    public LiveData<List<BioObj>> getAllUserBioQuery(){
        return mySingleton_database.databaseBio_dao().getAllUsers();
    }

    public LiveData<BioObj> findUserByNamePassBioQuery(String username, String password){
        return mySingleton_database.databaseBio_dao().findUserByNamePass(username, password);
    }

    public LiveData<List<PostObj>> getAllTweetPostQuery(){
        return (LiveData<List<PostObj>>) mySingleton_Room_post_db.databasePost_dao().getAllTweetPost();
    }



}
