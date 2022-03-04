package rob.netflix2app.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PostObj.class}, version = 1)
public abstract class MySingletonRoom_Post_DB extends RoomDatabase {

    private static final String POST_DB = "POST_DB";
    private static volatile MySingletonRoom_Post_DB INSTANCE;
    public abstract DatabasePost_Dao databasePost_dao();


    public static MySingletonRoom_Post_DB getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (MySingletonRoom_Post_DB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonRoom_Post_DB.class, POST_DB).build();

                }
            }
        }
        return INSTANCE;
    }
}
