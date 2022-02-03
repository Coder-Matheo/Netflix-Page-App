package rob.netflix2app.Screen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import rob.netflix2app.R;


public class WriteLoadImageTweet_Fragment extends Fragment {

    private static final String TAG = WriteLoadImageTweet_Fragment.class.getSimpleName();
    LinearLayout layout_list_image_new_tweet;
    EditText what_happening_edit_text;
    Toolbar toolbar_in_write_tweet;
    Button new_tweet_button;
    Button cancel_new_tweet_button;
    Button drafts_new_tweet_button;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_write_load_image_tweet, container, false);

        layout_list_image_new_tweet = view.findViewById(R.id.layout_list_image_new_tweet);

        toolbar_in_write_tweet = view.findViewById(R.id.toolbar_in_write_tweet);
        new_tweet_button = view.findViewById(R.id.new_tweet_button);

        what_happening_edit_text = view.findViewById(R.id.what_happening_edit_text);
        //if jump to Activity, automatically click(focus) on Edittext
        what_happening_edit_text.requestFocus();






        dynamicShowImageForNewTweet_Function(R.drawable.ic_camera);
        dynamicShowImageForNewTweet_Function(R.drawable.ic_graphic_eq);
        dynamicShowImageForNewTweet_Function(R.drawable.avatar_photo);
        dynamicShowImageForNewTweet_Function(R.drawable.fran1);
        dynamicShowImageForNewTweet_Function(R.drawable.fran1);
        dynamicShowImageForNewTweet_Function(R.drawable.fran1);
        dynamicShowImageForNewTweet_Function(R.drawable.fran1);
        dynamicShowImageForNewTweet_Function(R.drawable.fran1);
        dynamicShowImageForNewTweet_Function(R.drawable.fran1);


        return view;
    }




    private void dynamicShowImageForNewTweet_Function(int image) {
        View dynamicImageShow = getLayoutInflater().inflate(R.layout.row_show_image_of_new_tweet, null, false);

        ImageView imageView = dynamicImageShow.findViewById(R.id.image_show_in_row);

        imageView.setImageResource(image);


        //if clicked(Auto focus) on Edittext then show soft keyboard
        if (what_happening_edit_text.isLayoutRequested()) {
            ((InputMethodManager) (getContext()).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        }

        //if leave fragment then hide soft keyboard
        new_tweet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) view.getContext()).getCurrentFocus().getWindowToken(), 0);
            }
        });
        what_happening_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0){
                    dynamicImageShow.setVisibility(View.GONE);
                }else {
                    dynamicImageShow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        layout_list_image_new_tweet.addView(dynamicImageShow);

    }




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
}
