package rob.netflix2app.Screen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rob.netflix2app.R;
import rob.netflix2app.RoomDatabase.DatabaseViewModel;
import rob.netflix2app.RoomDatabase.MySingleton_Post_DB;
import rob.netflix2app.RoomDatabase.PostObj;


public class HomeFragment extends Fragment implements RecyclerViewClickInterface {

    private static final String TAG = HomeFragment.class.getSimpleName();
    View view;
    RecyclerView recyclerViewHome;
    List<TwitOfHome> lstTweet;
    RecyclerViewAdapter recyclerViewAdapterHome;
    DatabaseViewModel databaseViewModel;
    CommentRetweetLikeShare_Class commentRetweetLikeShare_class;
    LinearLayout linear_layout_clickable_tweet_post;
    NavController navControllerHome;

    //initial Variable
    int toggleOfLikeImageView = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        //init Database
        databaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);


        commentRetweetLikeShare_class = new CommentRetweetLikeShare_Class();
        initialRecycler(view);
        insertCommentLikeRetweet();
        return view;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lstTweet = new ArrayList<>();
        lstTweet.add(new TwitOfHome("Mario ", "Hallo guys, Today weather were so cold, if you like come to enjoy", R.drawable.fran1));
        lstTweet.add(new TwitOfHome("Matt Bro", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweet.add(new TwitOfHome("Sherry lovely", "Man, let's going Party", R.drawable.fran3));
        lstTweet.add(new TwitOfHome("New York Visit", "My Love, Now in New York", R.drawable.fran4));
    }

    private void initialRecycler(View view) {
        recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        recyclerViewAdapterHome = new RecyclerViewAdapter(getContext(), lstTweet, this);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHome.setAdapter(recyclerViewAdapterHome);

        linear_layout_clickable_tweet_post = view.findViewById(R.id.linear_layout_clickable_in_row_tweet_post);
    }


    @Override
    public void onItemCommentClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemCommentClickInterface", getContext());
    }

    @Override
    public void onItemRetweetClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemRetweetClickInterface", getContext());
    }

    @Override
    public void onItemLinearLayoutClickInterface(int position, String usernameOfTweet, String textOfTweet, int imageProfileOfUser) {
        commentRetweetLikeShare_class.toastFunction("onItemLinearLayoutClickInterface", getContext());
        //replace (jump fragment to another Fragment) layout and transfer data to another fragment


        try {
            if (!TextUtils.isEmpty(usernameOfTweet) && !TextUtils.isEmpty(textOfTweet)) {
                Bundle dataBundle = new Bundle();
                dataBundle.putString("usernameOfTweetKey", usernameOfTweet);
                dataBundle.putString("textOfTweetKey", textOfTweet);
                dataBundle.putInt("imageProfileOfUser", imageProfileOfUser);
                ViewDetailTweetPostFragment viewDetailTweetPostFragment = new ViewDetailTweetPostFragment();
                viewDetailTweetPostFragment.setArguments(dataBundle);

                getParentFragment().getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        viewDetailTweetPostFragment).commit();
            }
        } catch (Exception e) {
            commentRetweetLikeShare_class.toastFunction("Tweet isn't Avalible", getActivity().getApplicationContext());
        }


    }


    @Override
    public void onItemLikeClickInterface(int position) {

        if (toggleOfLikeImageView == 0) {
            commentRetweetLikeShare_class.toastFunction("onItemRetweetClickInterface Liked : " + position, getContext());

            //(int bioId, String messagePost, Integer like)
            PostObj postObj = new PostObj(1);
            InsertPostAsyncTask insertPostAsyncTask = new InsertPostAsyncTask();
            insertPostAsyncTask.execute(postObj);
            toggleOfLikeImageView = 1;
        } else if (toggleOfLikeImageView == 1) {
            commentRetweetLikeShare_class.toastFunction("onItemRetweetClickInterface Unliked : " + position, getContext());
            toggleOfLikeImageView = 0;
        }

    }

    @Override
    public void onItemShareClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemShareClickInterface", getContext());
        commentRetweetLikeShare_class.shareTweetToAnotherApp(view);
    }


    public void insertCommentLikeRetweet() {


        Log.i(TAG, "onChanged: empty");
        LiveData<List<PostObj>> tweetPostList = MySingleton_Post_DB.getInstance(getContext())
                .databasePost_dao()
                .getAllTweetPost();

        tweetPostList.observe(HomeFragment.this, new Observer<List<PostObj>>() {
            @Override
            public void onChanged(List<PostObj> postObjs) {
                if (postObjs != null) {
                    for (int i = 0; i < postObjs.size(); i++) {
                        Log.i(TAG, "Liked: " + postObjs.get(i).getLike().toString());
                    }
                } else {
                    Log.i(TAG, "onChanged: empty");
                }
            }
        });
    }


    class InsertPostAsyncTask extends AsyncTask<PostObj, Void, Void> {

        @Override
        protected Void doInBackground(PostObj... postObjs) {
            MySingleton_Post_DB.getInstance(getContext())
                    .databasePost_dao()
                    .insert(postObjs[0]);

            //commentRetweetLikeShare_class.toastFunction("Inserted", getContext());
            return null;
        }
    }


}