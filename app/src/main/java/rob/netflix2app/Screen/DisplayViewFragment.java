package rob.netflix2app.Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import rob.netflix2app.R;


public class DisplayViewFragment extends Fragment {

    private TabLayout tab_layout_display_view;
    private ViewPagerAdapter view_pager_display_Adapter;
    private ViewPager view_pager_display_view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_view, container, false);
        tab_layout_display_view = view.findViewById(R.id.tab_layout_display_view);
        view_pager_display_view = view.findViewById(R.id.view_pager_display_view);
        view_pager_display_Adapter = new ViewPagerAdapter(getChildFragmentManager());
        //getSupportFragmentManager
        //Add Fragment Here
        view_pager_display_Adapter.AddFragment(new HomeFragment(),getString(R.string.home_tab_text));
        view_pager_display_Adapter.AddFragment(new NotificationsFragment(),"Call");


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