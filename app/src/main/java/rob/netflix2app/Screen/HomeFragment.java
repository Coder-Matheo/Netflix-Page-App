package rob.netflix2app.Screen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
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
    List<TwitOfHome> lstTwit;
    RecyclerViewAdapter recyclerViewAdapterHome;

    DatabaseViewModel databaseViewModel;

    CommentRetweetLikeShare_Class commentRetweetLikeShare_class;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        //init Database
        commentRetweetLikeShare_class = new CommentRetweetLikeShare_Class();
        initialRecycler(view);

        return view;
    }


    private void initialRecycler(View view) {
        recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        recyclerViewAdapterHome = new RecyclerViewAdapter(getContext(), lstTwit, this);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHome.setAdapter(recyclerViewAdapterHome);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lstTwit = new ArrayList<>();
        lstTwit.add(new TwitOfHome("Mario ", "Hallo guys, Today weather were so cold, if you like come to enjoy", R.drawable.fran1));
        lstTwit.add(new TwitOfHome("Matt Bro", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTwit.add(new TwitOfHome("Sherry lovely", "Man, let's going Party", R.drawable.fran3));
        lstTwit.add(new TwitOfHome("New York Visit", "My Love, Now in New York", R.drawable.fran4));
    }


    @Override
    public void onItemCommentClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemCommentClickInterface", getContext());
    }

    @Override
    public void onItemShareClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemShareClickInterface", getContext());
        commentRetweetLikeShare_class.shareTweetToAnotherApp(view);
    }

    @Override
    public void onItemLikeClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemRetweetClickInterface", getContext());
    }

    @Override
    public void onItemRetweetClickInterface(int position) {
        commentRetweetLikeShare_class.toastFunction("onItemRetweetClickInterface", getContext());
    }


    public void insertCommentLikeRetweet(){

    }




    class InsertAsyncTask extends AsyncTask<PostObj, Void, Void> {

        @Override
        protected Void doInBackground(PostObj... postObjs) {
            MySingleton_Post_DB.getInstance(getContext())
                    .databasePost_dao()
                    .insert(postObjs[0]);

            commentRetweetLikeShare_class.toastFunction("Inserted", getContext());
            return null;
        }
    }


}