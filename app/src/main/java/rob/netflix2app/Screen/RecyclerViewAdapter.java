package rob.netflix2app.Screen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rob.netflix2app.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mConact;
    List<TwitOfHome> mData;
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();


    public RecyclerViewAdapter(Context mConact, List<TwitOfHome> mData) {
        this.mConact = mConact;
        this.mData = mData;
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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        private LinearLayout item_conact;
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        ImageView moreOptionImageView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_conact = itemView.findViewById(R.id.conact_item_id);
            tv_name = itemView.findViewById(R.id.usernameTextViewItem);
            tv_phone = itemView.findViewById(R.id.tweetTextView);
            img = itemView.findViewById(R.id.imag_conact);
            moreOptionImageView = itemView.findViewById(R.id.moreOptionImageView);
            moreOptionImageView.setOnClickListener(this);

            tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("TAG", "onClick: "+ tv_name.getText());
                }
            });
        }


        @Override
        public void onClick(View view) {
            Log.i("TAG", "onClick: "+ getAdapterPosition());
            showPopupMenu(view);
        }

        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu_item_recyclerview);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.noInterestPopup:
                    Log.i(TAG, "onMenuItemClick: noInterestPopup"+getAdapterPosition());
                    return true;
                case R.id.unfollowPopup:
                    Log.i(TAG, "onMenuItemClick: unfollowPopup"+getAdapterPosition());
                    return true;
                case R.id.removeAddPopup:
                    Log.i(TAG, "onMenuItemClick: removeAddPopup"+getAdapterPosition());
                    return true;
                case R.id.mutePopup:
                    Log.i(TAG, "onMenuItemClick: mutePopup"+getAdapterPosition());
                    return true;
                case R.id.blockPopup:
                    Log.i(TAG, "onMenuItemClick: blockPopup"+getAdapterPosition());
                    return true;
                case R.id.repostTweetPopup:
                    Log.i(TAG, "onMenuItemClick: repostTweetPopup"+getAdapterPosition());
                    return true;

            }
            return false;
        }
    }
}
