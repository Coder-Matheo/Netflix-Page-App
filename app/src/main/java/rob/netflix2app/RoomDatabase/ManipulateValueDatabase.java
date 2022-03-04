package rob.netflix2app.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ManipulateValueDatabase{
    private static final String TAG = ManipulateValueDatabase.class.getSimpleName();
    Context context;
    //int bioId, String messagePost, Integer like
    public ManipulateValueDatabase(Context context1){
        this.context = context1;

    }


    public void insert_single_tweet(int bioId, String messageTweet, Integer like) throws ExecutionException, InterruptedException {
        PostObj postObj = new PostObj(bioId, messageTweet, like);
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        insertAsyncTask.execute(postObj);
        Toast.makeText(context, "Tweet Posted", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "insert_single_tweet: "+getAllPostTweet());
    }

    public List<String> getAllPostTweet1(){
        ArrayList<String> returnTweet = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                List<PostObj> tweetPostList = MySingletonRoom_Post_DB.getInstance(context)
                .databasePost_dao()
                .getAllTweetPost();

                for (int i = 0; i < tweetPostList.size(); i++){
                    Log.i(TAG, "run: " + tweetPostList.get(i).getMessagePost());
                    returnTweet.add(tweetPostList.get(i).getMessagePost());
                }
            }

        });
        thread.start();

        return returnTweet;
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
                    Log.i(TAG, "runner: " + tweetPostList.get(i).getMessagePost());
                    returnTweetList.add(tweetPostList.get(i).getMessagePost());
                }
                return returnTweetList;
            }
        };
        Future<ArrayList<String>> future = executor.submit(callable);
        // future.get() returns 2 or raises an exception if the thread dies, so safer
        executor.shutdown();
        List s = future.get();
        Log.i(TAG, "test: "+s);
        return s;
    }



    public class InsertAsyncTask extends AsyncTask<PostObj, Void, Void> {

        @Override
        protected Void doInBackground(PostObj... postObjs) {
            MySingletonRoom_Post_DB.getInstance(context)
                    .databasePost_dao()
                    .insert(postObjs[0]);
            return null;
        }
    }

}
