package rob.netflix2app.Screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import rob.netflix2app.R;


public class ViewDetailTweetPostFragment extends Fragment {


    private static final String TAG = ViewDetailTweetPostFragment.class.getSimpleName();

    TextView text_text_view_of_detail_tweet_item;
    TextView username_text_view_of_detail_tweet_item;
    ImageView image_profile_of_detail_tweet_item;
    ImageView backspace_button_in_image_view_of_detail_tweet_item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_detail_tweet_post, container, false);




        String usernameOfTweetText = getArguments().getString("usernameOfTweetKey");
        String textOfTweetText = getArguments().getString("textOfTweetKey");
        int imageProfileOfUser = getArguments().getInt("imageProfileOfUser");



        text_text_view_of_detail_tweet_item = view.findViewById(R.id.text_text_view_of_detail_tweet_item);
        text_text_view_of_detail_tweet_item.setText(textOfTweetText);

        username_text_view_of_detail_tweet_item = view.findViewById(R.id.username_text_view_of_detail_tweet_item);
        username_text_view_of_detail_tweet_item.setText(usernameOfTweetText);

        image_profile_of_detail_tweet_item = view.findViewById(R.id.image_profile_of_detail_tweet_item);
        image_profile_of_detail_tweet_item.setImageResource(imageProfileOfUser);

        backspace_button_in_image_view_of_detail_tweet_item = view.findViewById(R.id.backspace_button_in_image_view_of_detail_tweet_item);

        backspaceButtonInImageViewOfDetailTweetItemFunction();

        return view;
    }

    private void backspaceButtonInImageViewOfDetailTweetItemFunction() {
        backspace_button_in_image_view_of_detail_tweet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity().getApplicationContext(), "Backspace Button: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Hide and Show Toolbar in this Fragment
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