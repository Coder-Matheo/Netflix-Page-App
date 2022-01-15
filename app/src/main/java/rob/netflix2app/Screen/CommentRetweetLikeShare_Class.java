package rob.netflix2app.Screen;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class CommentRetweetLikeShare_Class {

    Context context;
    void shareTweetToAnotherApp(View itemView){
        Intent shareTweet = new Intent(Intent.ACTION_SEND);
        shareTweet.setType("text/plain");
        shareTweet.putExtra(Intent.EXTRA_SUBJECT, "Subject Tweet");
        shareTweet.putExtra(Intent.EXTRA_TEXT, "This is Text");
        //shareTweet.putExtra(Intent.EXTRA_STREAM, ) image
        shareTweet.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        itemView.getContext().startActivity(Intent.createChooser(shareTweet, "Share Via "));
    }

    public void print() {
        Log.i("TAG", "print: ");
        System.out.print("Hallo");
    }
}
