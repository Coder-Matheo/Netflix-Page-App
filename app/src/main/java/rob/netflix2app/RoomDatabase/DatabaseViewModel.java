package rob.netflix2app.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class DatabaseViewModel extends AndroidViewModel {
    MySingleton_Bio_DB mySingleton_database;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);

        mySingleton_database = MySingleton_Bio_DB.getInstance(application.getApplicationContext());
    }


}
