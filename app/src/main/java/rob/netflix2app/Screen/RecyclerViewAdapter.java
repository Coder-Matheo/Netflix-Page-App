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
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhote());
        holder.img.setImageResource(mData.get(position).getPhote());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        private LinearLayout item_conact;
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        ImageView moreOptionImageView;
        ImageView commentImageView;
        ImageView retweetImageView;
        ImageView likeImageView;
        ImageView shareImageView;
        Context context;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_conact = itemView.findViewById(R.id.conact_item_id);
            tv_name = itemView.findViewById(R.id.usernameTextViewItem);
            tv_phone = itemView.findViewById(R.id.tweetTextView);
            img = itemView.findViewById(R.id.imag_conact);

            moreOptionImageView = itemView.findViewById(R.id.moreOptionImageView);
            moreOptionImageView.setOnClickListener(this);

            commentImageView = itemView.findViewById(R.id.commentImageView);
            retweetImageView = itemView.findViewById(R.id.retweetImageView);
            likeImageView = itemView.findViewById(R.id.likeImageView);
            shareImageView = itemView.findViewById(R.id.shareImageView);




            commentLikeRetweetShareFunction();
        }

        public void commentLikeRetweetShareFunction() {

            commentImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Hallo1", Toast.LENGTH_SHORT).show();
                    recyclerViewClickInterface.onItemCommentClickInterface(getAdapterPosition());
                }
            });

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Hallo2", Toast.LENGTH_SHORT).show();
                    recyclerViewClickInterface.onItemLikeClickInterface(getAdapterPosition());
                }
            });
            retweetImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Hallo3", Toast.LENGTH_SHORT).show();
                    recyclerViewClickInterface.onItemRetweetClickInterface(getAdapterPosition());
                }
            });
            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Hallo4", Toast.LENGTH_SHORT).show();
                    recyclerViewClickInterface.onItemShareClickInterface(getAdapterPosition());
                }
            });

        }


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

        private void showToast(String message, String optional){
            Toast.makeText(context, message+ optional, Toast.LENGTH_SHORT).show();
        }
    }


}
