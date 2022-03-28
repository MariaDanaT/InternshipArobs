package domain;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String mail;
    private Timestamp latestUpdate;

    public User(int id, String firstName, String lastName, Date birthDate, String mail, Timestamp latestUpdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.mail = mail;
        this.latestUpdate = latestUpdate;
    }

    public User() {
    }

    public User(String firstName, String lastName, Date birthDate, String mail, Timestamp latestUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.mail = mail;
        this.latestUpdate = latestUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Timestamp getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(Timestamp latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", mail='" + mail + '\'' +
                ", latestUpdate=" + latestUpdate +
                '}';
    }
}
