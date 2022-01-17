package rob.netflix2app.Screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rob.netflix2app.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mConact;
    List<TwitOfHome> mData;
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    RecyclerViewClickInterface recyclerViewClickInterface;


    public RecyclerViewAdapter(Context mConact, List<TwitOfHome> mData, RecyclerViewClickInterface recyclerViewClickInterface1) {
        this.mConact = mConact;
        this.mData = mData;
        this.recyclerViewClickInterface = recyclerViewClickInterface1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet_recycler, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.username_text_view_tweet_item_recycler.setText(mData.get(position).getUsername());
        holder.tweet_text_view_tweet_item_recycler.setText(mData.get(position).getTextTweet());
        holder.profile_of_tweet_item_recycler.setImageResource(mData.get(position).getPhote());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        private LinearLayout linear_layout_clickable_in_row_tweet_post;
        private TextView username_text_view_tweet_item_recycler;
        private TextView tweet_text_view_tweet_item_recycler;
        private ImageView profile_of_tweet_item_recycler;
        private ImageView moreOptionImageView;
        private ImageView commet_image_view_tweet_item_recycler;
        private TextView commet_text_view_tweet_item_recycler;
        private ImageView retweet_image_view_tweet_item_recycler;
        private TextView retweet_text_view_tweet_item_recycler;
        private ImageView like_image_view_tweet_item_recycler;
        private TextView like_text_view_tweet_item_recycler;
        private ImageView share_image_view_tweet_item_recycler;
        private int toggleOfLikeImageView = 0;
        private int toggleOfRetweetImageView = 0;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_layout_clickable_in_row_tweet_post = itemView.findViewById(R.id.linear_layout_clickable_in_row_tweet_post);
            username_text_view_tweet_item_recycler = itemView.findViewById(R.id.username_text_view_tweet_item_recycler);
            tweet_text_view_tweet_item_recycler = itemView.findViewById(R.id.tweet_text_view_in_item_recycler);
            profile_of_tweet_item_recycler = itemView.findViewById(R.id.profile_of_tweet_item_recycler);

            //Initial more optional in item_tweet_recycler, same unfollow block
            moreOptionImageView = itemView.findViewById(R.id.more_option_image_view_tweet_item_recycler);
            moreOptionImageView.setOnClickListener(this);

            //Initial Like, Comment, retweet, share ImageView in item_tweet_recycler
            commet_image_view_tweet_item_recycler = itemView.findViewById(R.id.commet_image_view_tweet_item_recycler);
            retweet_image_view_tweet_item_recycler = itemView.findViewById(R.id.retweet_image_view_tweet_item_recycler);
            like_image_view_tweet_item_recycler = itemView.findViewById(R.id.like_image_view_tweet_item_recycler);
            share_image_view_tweet_item_recycler = itemView.findViewById(R.id.share_image_view_tweet_item_recycler);
            //Initial Text of Like, Comment, Retweet
            commet_text_view_tweet_item_recycler = itemView.findViewById(R.id.commet_text_view_tweet_item_recycler);
            retweet_text_view_tweet_item_recycler = itemView.findViewById(R.id.retweet_text_view_tweet_item_recycler);
            like_text_view_tweet_item_recycler = itemView.findViewById(R.id.like_text_view_tweet_item_recycler);
            //Initial LinearLayout for see detail of Tweet

            commentLikeRetweetShareFunction();
            linearLayoutClickableInRowTweetPostFunction();
        }

        private void linearLayoutClickableInRowTweetPostFunction() {
            linear_layout_clickable_in_row_tweet_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemLinearLayoutClickInterface();
                }
            });
        }


        public void commentLikeRetweetShareFunction() {


            commet_image_view_tweet_item_recycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemCommentClickInterface(getAdapterPosition());
                }
            });

            like_image_view_tweet_item_recycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (toggleOfLikeImageView == 0) {
                        like_image_view_tweet_item_recycler.setImageResource(R.drawable.ic_heart_broken);
                        like_text_view_tweet_item_recycler.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorOfLikeRed));
                        toggleOfLikeImageView = 1;
                    } else if (toggleOfLikeImageView == 1) {
                        like_image_view_tweet_item_recycler.setImageResource(R.drawable.ic_heart);
                        like_text_view_tweet_item_recycler.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                        toggleOfLikeImageView = 0;
                    }
                    recyclerViewClickInterface.onItemLikeClickInterface(getAdapterPosition());
                }
            });
            retweet_image_view_tweet_item_recycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), " " + mData.get(getAdapterPosition()).getTextTweet()
                            + " : " + mData.get(getAdapterPosition()).getUsername() + " : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    recyclerViewClickInterface.onItemRetweetClickInterface(getAdapterPosition());
                    if (toggleOfRetweetImageView == 0) {
                        retweet_image_view_tweet_item_recycler.setImageResource(R.drawable.ic_repeat_green);
                        retweet_text_view_tweet_item_recycler.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorOfRetweetGreen));
                        toggleOfRetweetImageView = 1;
                    } else if (toggleOfRetweetImageView == 1) {
                        retweet_image_view_tweet_item_recycler.setImageResource(R.drawable.ic_repeat);
                        retweet_text_view_tweet_item_recycler.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                        toggleOfRetweetImageView = 0;
                    }
                }
            });
            share_image_view_tweet_item_recycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemShareClickInterface(getAdapterPosition());
                }
            });

        }


        //Click function of popup menu in row item
        @Override
        public void onClick(View view) {
            showPopupMenu(view);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu_item_recyclerview);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }


        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.noInterestPopup:
                    showToast("noInterestPopup", String.valueOf(getAdapterPosition()));
                    return true;
                case R.id.unfollowPopup:
                    showToast("unfollowPopup", String.valueOf(getAdapterPosition()));
                    return true;
                case R.id.removeAddPopup:
                    showToast("removeAddPopup", String.valueOf(getAdapterPosition()));
                    return true;
                case R.id.mutePopup:
                    showToast("mutePopup", String.valueOf(getAdapterPosition()));
                    return true;
                case R.id.blockPopup:
                    showToast("blockPopup", String.valueOf(getAdapterPosition()));
                    return true;
                case R.id.repostTweetPopup:
                    showToast("repostTweetPopup", String.valueOf(getAdapterPosition()));
                    return true;
                default:
                    return false;
            }
        }

        private void showToast(String message, String optional) {
            Toast.makeText(itemView.getContext(), message + optional, Toast.LENGTH_SHORT).show();
        }
    }


}
