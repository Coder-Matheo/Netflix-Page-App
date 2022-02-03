package rob.netflix2app.Screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import rob.netflix2app.R;


public class ViewDetailTweetPostFragment extends Fragment{


    private static final String TAG = ViewDetailTweetPostFragment.class.getSimpleName();

    TextView text_text_view_of_detail_tweet_item;
    TextView username_text_view_of_detail_tweet_item;
    ImageView image_profile_of_detail_tweet_item;
    ImageView backspace_button_in_image_view_of_detail_tweet_item;
    ImageView more_option_image_view_of_detail_tweet_item;
    LinearLayout dynamic_linear_layout_write_detail_tweet;
    Button button_mention_edit_text_in_detail_tweet;
    EditText mention_edit_text_in_detail_tweet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_detail_tweet_post, container, false);

        //Get value from HomeFragment as Argument
        String usernameOfTweetText = getArguments().getString("usernameOfTweetKey");
        String textOfTweetText = getArguments().getString("textOfTweetKey");
        int imageProfileOfUser = getArguments().getInt("imageProfileOfUser");


        text_text_view_of_detail_tweet_item = view.findViewById(R.id.text_text_view_of_detail_tweet_item);
        text_text_view_of_detail_tweet_item.setText(textOfTweetText);

        username_text_view_of_detail_tweet_item = view.findViewById(R.id.username_text_view_of_detail_tweet_item);
        username_text_view_of_detail_tweet_item.setText(usernameOfTweetText);

        image_profile_of_detail_tweet_item = view.findViewById(R.id.image_profile_of_detail_tweet_item);
        image_profile_of_detail_tweet_item.setImageResource(imageProfileOfUser);
        //More Optional Popup
        more_option_image_view_of_detail_tweet_item = view.findViewById(R.id.more_option_image_view_of_detail_tweet_item);

        backspace_button_in_image_view_of_detail_tweet_item = view.findViewById(R.id.backspace_button_in_image_view_of_detail_tweet_item);


        dynamic_linear_layout_write_detail_tweet = view.findViewById(R.id.dynamic_linear_layout_write_detail_tweet);
        button_mention_edit_text_in_detail_tweet = view.findViewById(R.id.button_mention_edit_text_in_detail_tweet);
        mention_edit_text_in_detail_tweet = view.findViewById(R.id.mention_edit_text_in_detail_tweet);

        backspaceButtonInImageViewOfDetailTweetItemFunction();
        moreOptionImageViewOfDetailTweetItemFunction(view);

        //Write mention, in detail Tweet
        button_mention_edit_text_in_detail_tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMentionDynamicLayout();
            }
        });


        return view;
    }


    private void backspaceButtonInImageViewOfDetailTweetItemFunction() {
        backspace_button_in_image_view_of_detail_tweet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity().getApplicationContext(), "Backspace Button: ", Toast.LENGTH_SHORT).show();

                //Backspace used Intent, because the return site(NavigationDrawerActivity) in Activity and isn't Fragment
                startActivity(new Intent(getActivity(), NavigationDrawerMainActivity.class));
            }
        });
    }

    //Hide and Show Toolbar in this Fragment
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void moreOptionImageViewOfDetailTweetItemFunction(View view) {
        more_option_image_view_of_detail_tweet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu moreOptionalPopupDetailTweet = new PopupMenu(view.getContext(), view);
                moreOptionalPopupDetailTweet.inflate(R.menu.popup_menu_item_recyclerview);
                //moreOptionalPopupDetailTweet.setOnMenuItemClickListener();
                moreOptionalPopupDetailTweet.show();
            }
        });
    }

    private void addMentionDynamicLayout() {
        View row_layout_mention_of_tweet_view = getLayoutInflater().inflate(R.layout.row_layout_mention_of_tweet, null, false);
        TextView row_text_view_mention_of_tweet = row_layout_mention_of_tweet_view.findViewById(R.id.row_text_view_mention_of_tweet);

        if (!TextUtils.isEmpty(mention_edit_text_in_detail_tweet.getText())) {
            row_text_view_mention_of_tweet.setText(mention_edit_text_in_detail_tweet.getText());
            dynamic_linear_layout_write_detail_tweet.addView(row_layout_mention_of_tweet_view);
            mention_edit_text_in_detail_tweet.setText("");
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Please write something", Toast.LENGTH_SHORT).show();
        }
    }
}