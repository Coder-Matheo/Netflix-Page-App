package rob.netflix2app.Screen;

public class TwitOfHome {

    private String username;
    private String textTweet;
    private int photo;

    public TwitOfHome(String username, String textTweet, int phone1) {
        this.username = username;
        this.textTweet = textTweet;
        this.photo = phone1;
    }

    public String getTextTweet() {
        return textTweet;
    }

    public void setTextTweet(String textTweet) {
        this.textTweet = textTweet;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
