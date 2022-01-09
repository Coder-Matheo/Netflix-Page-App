package rob.netflix2app.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class BioObj {
    @PrimaryKey(autoGenerate = true)
    private int bid;

    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "bio_profile")
    private String bioProfile;
    @ColumnInfo(name = "loc_profile")
    private String locProfile;
    @ColumnInfo(name = "join_profile")
    private String joinProfile;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "img_profile")
    private byte[] imgProfile;
    @ColumnInfo(name = "default1")
    private String default1;
    @ColumnInfo(name = "default2")
    private String default2;
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

    @Ignore
    public BioObj(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public BioObj(String userName, String phoneNumber, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }






    /*public BioObj(String phoneNumber, String bioProfile, String locProfile, String joinProfile, byte[] imgProfile) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.bioProfile = bioProfile;
        this.locProfile = locProfile;
        this.joinProfile = joinProfile;
        this.imgProfile = imgProfile;
    }*/

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBioProfile() {
        return bioProfile;
    }

    public void setBioProfile(String bioProfile) {
        this.bioProfile = bioProfile;
    }

    public String getLocProfile() {
        return locProfile;
    }

    public void setLocProfile(String locProfile) {
        this.locProfile = locProfile;
    }

    public String getJoinProfile() {
        return joinProfile;
    }

    public void setJoinProfile(String joinProfile) {
        this.joinProfile = joinProfile;
    }

    public byte[] getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(byte[] imgProfile) {
        this.imgProfile = imgProfile;
    }


    public String getDefault1() {
        return default1;
    }

    public void setDefault1(String default1) {
        this.default1 = default1;
    }

    public String getDefault2() {
        return default2;
    }

    public void setDefault2(String default2) {
        this.default2 = default2;
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
