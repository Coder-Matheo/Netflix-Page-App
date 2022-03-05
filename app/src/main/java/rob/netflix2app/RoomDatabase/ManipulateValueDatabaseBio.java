package rob.netflix2app.RoomDatabase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ManipulateValueDatabaseBio {
    private static final String TAG = ManipulateValueDatabaseBio.class.getSimpleName();
    Context context;
    //int bioId, String messagePost, Integer like
    public ManipulateValueDatabaseBio(Context context1){
        this.context = context1;
    }

    public Boolean insert_single_bio(String name, String messageTweet, String password) throws ExecutionException, InterruptedException {

        Toast.makeText(context, "Bio Created", Toast.LENGTH_SHORT).show();

        ExecutorService executorInsert = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                MySingletonRoom_Bio_DB.getInstance(context)
                        .databaseBio_dao()
                        .insertBio(new BioObj("Matthew","123aside", "4123"));
                return true;
            }
        };
        Future<Boolean> future = executorInsert.submit(callable);
        executorInsert.shutdown();
        Boolean inserted = future.get();
        return inserted;
    }




    public List<String> getAllBio()
            throws ExecutionException, InterruptedException {
        ArrayList<String> returnBioList = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<ArrayList<String>> callable = new Callable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() {
                LiveData<List<BioObj>> bioList = MySingletonRoom_Bio_DB.getInstance(context)
                        .databaseBio_dao()
                        .getAllUsers();

                for (int i = 0; i < bioList.getValue().size(); i++){
                    returnBioList.add(bioList.getValue().get(i).getUserName());
                    Log.i(TAG, "call: "+ bioList);
                }
                return returnBioList;
            }
        };
        Future<ArrayList<String>> future = executor.submit(callable);
        // future.get() returns 2 or raises an exception if the thread dies, so safer
        executor.shutdown();
        List s = future.get();
        return s;
    }

}
