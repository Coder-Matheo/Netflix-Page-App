package rob.netflix2app.Screen;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import rob.netflix2app.R;


public class DisplayViewFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    public DisplayViewFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_view, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        //getSupportFragmentManager
        //Add Fragment Here
        viewPagerAdapter.AddFragment(new HomeFragment(),getString(R.string.home_tab_text));
        viewPagerAdapter.AddFragment(new ProfileFragment(),"Call");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_bolt);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_article);



        //Remove shadow From the action
        //ActionBar action = getSupportActionBar();
        //action.setElevation(0);

        return view;
    }
}