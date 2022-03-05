package rob.netflix2app.RoomDatabase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ManipulateValueDatabasePost {
    private static final String TAG = ManipulateValueDatabasePost.class.getSimpleName();
    Context context;
    //int bioId, String messagePost, Integer like
    public ManipulateValueDatabasePost(Context context1){
        this.context = context1;
    }

    public Boolean insert_single_tweet(int bioId, String messageTweet, Integer like) throws ExecutionException, InterruptedException {

        Toast.makeText(context, "Tweet Posted", Toast.LENGTH_SHORT).show();

        ExecutorService executorInsert = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                MySingletonRoom_Post_DB.getInstance(context)
                        .databasePost_dao()
                        .insert(new PostObj(1,messageTweet, 4));
                return true;
            }
        };
        Future<Boolean> future = executorInsert.submit(callable);
        executorInsert.shutdown();
        Boolean inserted = future.get();
        return inserted;
    }




    public List<String> getAllPostTweet()
            throws ExecutionException, InterruptedException {
        ArrayList<String> returnTweetList = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<ArrayList<String>> callable = new Callable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() {
                List<PostObj> tweetPostList = MySingletonRoom_Post_DB.getInstance(context)
                        .databasePost_dao()
                        .getAllTweetPost();

                for (int i = 0; i < tweetPostList.size(); i++){
                    returnTweetList.add(tweetPostList.get(i).getMessagePost());
                }
                return returnTweetList;
            }
        };
        Future<ArrayList<String>> future = executor.submit(callable);
        // future.get() returns 2 or raises an exception if the thread dies, so safer
        executor.shutdown();
        List s = future.get();
        return s;
    }

    public void updateLikePost(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PostObj postLikeId = MySingletonRoom_Post_DB.getInstance(context.getApplicationContext())
                        .databasePost_dao()
                        .findById(id);

                Log.i(TAG, "run: "+ postLikeId);
                if (postLikeId != null){
                    postLikeId.setLike(1);

                    MySingletonRoom_Post_DB.getInstance(context.getApplicationContext())
                            .databasePost_dao()
                            .updateLike(new PostObj(49));

                }

            }
        }).start();
    }




}
