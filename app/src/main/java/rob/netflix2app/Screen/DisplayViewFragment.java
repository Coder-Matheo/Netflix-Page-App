package rob.netflix2app.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rob.netflix2app.R;
import rob.netflix2app.RoomDatabase.ManipulateValueDatabaseBio;
import rob.netflix2app.RoomDatabase.ManipulateValueDatabasePost;


public class DisplayViewFragment extends Fragment {

    private static final String TAG = DisplayViewFragment.class.getSimpleName();
    private TabLayout tab_layout_display_view;
    private ViewPagerAdapter view_pager_display_Adapter;
    private ViewPager view_pager_display_view;
    ManipulateValueDatabasePost manipulateValueDatabasePost;
    ManipulateValueDatabaseBio manipulateValueDatabaseBio;
    List<TwitOfHome> lstTweet;
    List<TwitOfHome> lstTweetlike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_view, container, false);
        tab_layout_display_view = view.findViewById(R.id.tab_layout_display_view);
        view_pager_display_view = view.findViewById(R.id.view_pager_display_view);
        view_pager_display_Adapter = new ViewPagerAdapter(getChildFragmentManager());

        manipulateValueDatabasePost = new ManipulateValueDatabasePost(getContext().getApplicationContext());

        //getSupportFragmentManager
        //Add Fragment Here
        lstTweet = new ArrayList<>();
        try {
            List<String> getValueFromDatabase = manipulateValueDatabasePost.getAllPostTweet();
            for (int i = 0; i < getValueFromDatabase.size(); i++){
                Log.i(TAG, "onCreateView: "+ getValueFromDatabase.get(i));
                lstTweet.add(new TwitOfHome("Mario ", getValueFromDatabase.get(i), R.drawable.fran1));
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        lstTweet.add(new TwitOfHome("Matt Bro", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweet.add(new TwitOfHome("Sherry lovely", "Man, let's going Party", R.drawable.fran3));
        lstTweet.add(new TwitOfHome("New York Visit", "My Love, Now in New York", R.drawable.fran4));

        lstTweetlike = new ArrayList<>();
        lstTweetlike.add(new TwitOfHome("Matthew ", "Hallo guys, Today weather were so cold, if you like come to enjoy", R.drawable.fran1));
        lstTweetlike.add(new TwitOfHome("Matthew", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweetlike.add(new TwitOfHome("Matthew", "Man, let's going Party", R.drawable.fran3));
        lstTweetlike.add(new TwitOfHome("Matthew", "My Love, Now in New York", R.drawable.fran4));


        view_pager_display_Adapter.AddFragment(new HomeFragment(lstTweet),getString(R.string.home_tab_text));
        view_pager_display_Adapter.AddFragment(new HomeFragment(lstTweetlike),"Call");


        view_pager_display_view.setAdapter(view_pager_display_Adapter);
        tab_layout_display_view.setupWithViewPager(view_pager_display_view);

        //tab_layout_display_view.getTabAt(0).setIcon(R.drawable.ic_bolt);
        //tab_layout_display_view.getTabAt(1).setIcon(R.drawable.ic_article);



        //Remove shadow From the action
        //ActionBar action = getSupportActionBar();
        //action.setElevation(0);

        return view;
    }
}