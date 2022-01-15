package rob.netflix2app.Screen;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rob.netflix2app.R;


public class HomeFragment extends Fragment implements RecyclerViewClickInterface{

    private static final String TAG = HomeFragment.class.getSimpleName();
    View view;
    RecyclerView recyclerViewHome;
    List<TwitOfHome> lstTwit;
    RecyclerViewAdapter recyclerViewAdapterHome;
    CommentRetweetLikeShare_Class commentRetweetLikeShare_class = new CommentRetweetLikeShare_Class();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        recyclerViewAdapterHome = new RecyclerViewAdapter(getContext(), lstTwit, this);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHome.setAdapter(recyclerViewAdapterHome);
        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        
        lstTwit = new ArrayList<>();
        lstTwit.add(new TwitOfHome("Franfurt 1", "(+12, +32)", R.drawable.fran1));
        lstTwit.add(new TwitOfHome("Franfurt 2", "(+17, +82)", R.drawable.fran2));
        lstTwit.add(new TwitOfHome("Franfurt 3", "(+120, +90)", R.drawable.fran3));
        lstTwit.add(new TwitOfHome("Franfurt 4", "(+28, +320)", R.drawable.fran4));
    }


    @Override
    public void onItemCommentClickInterface(int position) {
        Toast.makeText(getContext(), "Clickable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemShareClickInterface(int position) {
        Toast.makeText(getContext(), "Clickable2", Toast.LENGTH_SHORT).show();
        commentRetweetLikeShare_class.shareTweetToAnotherApp(view);
    }

    @Override
    public void onItemLikeClickInterface(int position) {
        Toast.makeText(getContext(), "Clickable3", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemRetweetClickInterface(int position) {
        Toast.makeText(getContext(), "Clickable4", Toast.LENGTH_SHORT).show();
    }


}