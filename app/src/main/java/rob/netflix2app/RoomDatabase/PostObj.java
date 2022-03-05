package rob.netflix2app.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity
public class PostObj {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "bio_Id")
    private int bioId;
    @ColumnInfo(name = "film_name")
    private String filmName;
    @ColumnInfo(name = "film_title")
    private String filmTitle;
    @ColumnInfo(name = "message_post")
    private String messagePost;
    @ColumnInfo(name = "comment_post")
    private String commentPost;
    @ColumnInfo(name = "data_time")
    private String dataTime;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "like")
    private Integer like;
    @ColumnInfo(name = "default3")
    private String default3;
    @ColumnInfo(name = "default4")
    private String default4;
    @ColumnInfo(name = "default5")
    private String default5;
    @ColumnInfo(name = "default6")
    private String default6;
    @ColumnInfo(name = "default7")
    private String default7;
    @ColumnInfo(name = "default8")
    private String default8;
    @ColumnInfo(name = "default9")
    private String default9;



    public PostObj(Integer like) {
        this.like = like;
    }

    @Ignore
    public PostObj(int bioId, String messagePost, Integer like) {
        this.bioId = bioId;
        this.messagePost = messagePost;
        this.like = like;
    }


    /*public PostObj(int bioId, String filmName, String filmTitle, String messagePost, String commentPost, String dataTime, byte[] image) {
        this.bioId = bioId;
        this.filmName = filmName;
        this.filmTitle = filmTitle;
        this.messagePost = messagePost;
        this.commentPost = commentPost;
        this.dataTime = dataTime;
        this.image = image;
    }*/


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBioId() {
        return bioId;
    }

    public void setBioId(int bioId) {
        this.bioId = bioId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getMessagePost() {
        return messagePost;
    }

    public void setMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }

    public String getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(String commentPost) {
        this.commentPost = commentPost;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getDefault3() {
        return default3;
    }

    public void setDefault3(String default3) {
        this.default3 = default3;
    }

    public String getDefault4() {
        return default4;
    }

    public void setDefault4(String default4) {
        this.default4 = default4;
    }

    public String getDefault5() {
        return default5;
    }

    public void setDefault5(String default5) {
        this.default5 = default5;
    }

    public String getDefault6() {
        return default6;
    }

    public void setDefault6(String default6) {
        this.default6 = default6;
    }

    public String getDefault7() {
        return default7;
    }

    public void setDefault7(String default7) {
        this.default7 = default7;
    }

    public String getDefault8() {
        return default8;
    }

    public void setDefault8(String default8) {
        this.default8 = default8;
    }

    public String getDefault9() {
        return default9;
    }

    public void setDefault9(String default9) {
        this.default9 = default9;
    }
}
