package rob.netflix2app.Screen;

public interface RecyclerViewClickInterface {
    void onItemCommentClickInterface(int position);
    void onItemShareClickInterface(int position);
    void onItemLikeClickInterface(int position);
    void onItemRetweetClickInterface(int position);
    void onItemLinearLayoutClickInterface(int position, String usernameOfTweet, String textOfTweet, int phote);
}
