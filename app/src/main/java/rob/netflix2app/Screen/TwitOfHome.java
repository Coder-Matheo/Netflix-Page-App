package rob.netflix2app.Screen;

public class TwitOfHome {

    private String username;
    private String textTweet;
    private int phote;

    public TwitOfHome(String username, String textTweet, int phone1) {
        this.username = username;
        this.textTweet = textTweet;
        this.phote = phone1;
    }

    public String getTextTweet() {
        return textTweet;
    }

    public void setTextTweet(String textTweet) {
        this.textTweet = textTweet;
    }

    public int getPhote() {
        return phote;
    }

    public void setPhote(int phote) {
        this.phote = phote;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
