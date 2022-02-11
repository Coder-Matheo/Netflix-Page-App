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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    List<TwitOfHome> lstTweet = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapterHome;
    DatabaseViewModel databaseViewModel;
    CommentRetweetLikeShare_Class commentRetweetLikeShare_class;
    LinearLayout linear_layout_clickable_tweet_post;
    NavController navControllerHome;
    FloatingActionButton button_include;
    BottomSheetDialog bottomSheetDialogWriteTweet;

    //initial Variable
    int toggleOfLikeImageView = 0;

    public HomeFragment(List<TwitOfHome> lstTweet_) {
        this.lstTweet = lstTweet_;

    }

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



    private void initialRecycler(View view) {
        recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        recyclerViewAdapterHome = new RecyclerViewAdapter(getContext(), this.lstTweet, this);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHome.setAdapter(recyclerViewAdapterHome);
        button_include = view.findViewById(R.id.floating_action_id);

        button_include.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "include Button", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(view.getContext(), WriteLoadImageTweet_Fragment.class));

                getParentFragment().getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WriteLoadImageTweet_Fragment()).commit();


                bottomSheetDialogWriteTweet = new BottomSheetDialog(view.getContext());
                bottomSheetDialogWriteTweet.setContentView(R.layout.bottom_sheet_dialog_write_tweet);
                bottomSheetDialogWriteTweet.setCanceledOnTouchOutside(false);

                //bottomSheetDialogWriteTweet.show();


            }
        });

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

        tweetPostList.observe(getViewLifecycleOwner(), new Observer<List<PostObj>>() {
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