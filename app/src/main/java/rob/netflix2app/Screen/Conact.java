package rob.netflix2app.Screen;

public class Conact {
    private String Name;
    private String Phone;
    private int phote;

    public Conact(String name, String phone, int phone1) {
        Name = name;
        Phone = phone;
        this.phote = phone1;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPhote() {
        return phote;
    }

    public void setPhote(int phote) {
        this.phote = phote;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }




}
