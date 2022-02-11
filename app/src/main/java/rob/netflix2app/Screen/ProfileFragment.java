package rob.netflix2app.Screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import rob.netflix2app.R;


public class ProfileFragment extends Fragment {

    TabLayout tab_layout_profile;
    ViewPagerAdapter view_pager_profile_Adapter;
    ViewPager view_pager_profile;
    List<TwitOfHome> lstTweetliked;
    List<TwitOfHome> lstTweetReplied;
    List<TwitOfHome> lstTweetMedia;
    List<TwitOfHome> lstTweetTweets;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_profile, container, false);
        tab_layout_profile = view.findViewById(R.id.tab_layout_profile);
        view_pager_profile = view.findViewById(R.id.view_pager_profile);
        view_pager_profile_Adapter = new ViewPagerAdapter(getChildFragmentManager());


        lstTweetliked = new ArrayList<>();
        lstTweetliked.add(new TwitOfHome("Mario ", "Hallo guys, Today weather were so cold, if you like come to enjoy", R.drawable.fran1));
        lstTweetliked.add(new TwitOfHome("Matt Bro", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweetliked.add(new TwitOfHome("Sherry lovely", "Man, let's going Party", R.drawable.fran3));
        lstTweetliked.add(new TwitOfHome("New York Visit", "My Love, Now in New York", R.drawable.fran4));

        lstTweetReplied = new ArrayList<>();
        lstTweetReplied.add(new TwitOfHome("Matthew ", "Hallo guys, Today weather were so cold, if you like come to enjoy", R.drawable.fran1));
        lstTweetReplied.add(new TwitOfHome("Matthew", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweetReplied.add(new TwitOfHome("Matthew", "Man, let's going Party", R.drawable.fran3));
        lstTweetReplied.add(new TwitOfHome("Matthew", "My Love, Now in New York", R.drawable.fran4));

        lstTweetMedia = new ArrayList<>();
        lstTweetMedia.add(new TwitOfHome("Ali Dinarvand ", "Hallo guys, Today weather were so cold", R.drawable.fran1));
        lstTweetMedia.add(new TwitOfHome("Ali Dinarvand", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweetMedia.add(new TwitOfHome("Ali Dinarvand", "Man, let's going Party", R.drawable.fran3));
        lstTweetMedia.add(new TwitOfHome("Ali Dinarvand", "My Love", R.drawable.fran4));

        lstTweetTweets = new ArrayList<>();
        lstTweetTweets.add(new TwitOfHome("Ali Dinarvand ", "Hallo guys, Today weather were so cold", R.drawable.fran1));
        lstTweetTweets.add(new TwitOfHome("Ali Dinarvand", "Hi Bro, we go tomorrow to football", R.drawable.fran2));
        lstTweetTweets.add(new TwitOfHome("Ali Dinarvand", "Man, let's going Party", R.drawable.fran3));


        view_pager_profile_Adapter.AddFragment(new HomeFragment(lstTweetTweets), "Tweets");
        view_pager_profile_Adapter.AddFragment(new HomeFragment(lstTweetReplied), "Tweet & replies");
        view_pager_profile_Adapter.AddFragment(new HomeFragment(lstTweetMedia), "Media");
        view_pager_profile_Adapter.AddFragment(new HomeFragment(lstTweetliked), "Likes");

        view_pager_profile.setAdapter(view_pager_profile_Adapter);
        tab_layout_profile.setupWithViewPager(view_pager_profile);



        return view;
    }


    //Hide Default Toolbar Fragment
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}