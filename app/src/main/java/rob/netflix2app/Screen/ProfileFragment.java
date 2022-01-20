package rob.netflix2app.Screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import rob.netflix2app.R;


public class ProfileFragment extends Fragment {

    TabLayout tab_layout_profile;
    ViewPagerAdapter view_pager_profile_Adapter;
    ViewPager view_pager_profile;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_profile, container, false);
        tab_layout_profile = view.findViewById(R.id.tab_layout_profile);
        view_pager_profile = view.findViewById(R.id.view_pager_profile);
        view_pager_profile_Adapter = new ViewPagerAdapter(getChildFragmentManager());

        view_pager_profile_Adapter.AddFragment(new NotificationsFragment(), "Tweets");
        view_pager_profile_Adapter.AddFragment(new NotificationsFragment(), "Tweet & replies");
        view_pager_profile_Adapter.AddFragment(new NotificationsFragment(), "Media");
        view_pager_profile_Adapter.AddFragment(new NotificationsFragment(), "Likes");

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