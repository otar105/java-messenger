package DB;

import java.util.Date;
// User model class
public class User {
    int ID;
    private String email;
    private String name;
    private String password;
    public int currentchatid;

    public User(String email, String name, String password,int currentchatid) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.currentchatid = currentchatid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCurrentchatid() {
        return currentchatid;
    }

    public void setCurrentchatid(int currentchatid) {
        this.currentchatid = currentchatid;
    }

    @Override
    public String toString() {
        return ID + "," + email + "," + name + "," + password + "," + currentchatid;
    }
    public static User getUserFromString(String userString) {
        System.out.println(userString);
        int ID = Integer.parseInt(userString.split(",")[0]);
        String email = userString.split(",")[1];
        String name = userString.split(",")[2];
        String password = userString.split(",")[3];
        int currentchatid = Integer.parseInt(userString.split(",")[4]);
        return new User(email,name,password,currentchatid);

    }
}