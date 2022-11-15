package DB;

import java.util.Date;
// User model class
public class User {
    private String email;
    private String name;
    private String registrationDate;
    private String passwordEncrypted;

    public User(String email, String name, String registrationDate, String passwordEncrypted) {
        this.email = email;
        this.name = name;
        this.registrationDate = registrationDate;
        this.passwordEncrypted = passwordEncrypted;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", passwordEncrypted='").append(passwordEncrypted).append('\'');
        sb.append('}');
        return sb.toString();
    }
}