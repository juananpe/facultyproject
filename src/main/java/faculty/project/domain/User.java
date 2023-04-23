package faculty.project.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    // create a java enum type with teacher, student, admin
    public enum Role {
        Teacher, Student, Officer
    }

    @Id
    private String userName;

    private String password;
    private String completeName;
    private String email;
    private String address;
    private String phoneNumber;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
            "userName='" + userName + '\'' +
            '}';
    }

    public String getUserName() {
        return userName;
    }

    public User(String userName, String password, String completeName, String email, String address, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.completeName = completeName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

}
