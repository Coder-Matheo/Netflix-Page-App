package rob.netflix2app.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BioObj.class}, version = 1)
public abstract class MySingletonRoom_Bio_DB extends RoomDatabase {

    private static final String BIO_DB = "BIO_DB";
    public abstract DatabaseBio_Dao databaseBio_dao();
    private static volatile MySingletonRoom_Bio_DB INSTANCE;


    public static MySingletonRoom_Bio_DB getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (MySingletonRoom_Bio_DB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MySingletonRoom_Bio_DB.class, BIO_DB).build();
                }
            }
        }
        return INSTANCE;
    }
}
