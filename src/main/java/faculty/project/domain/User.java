package faculty.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private String completeName;
    private String email;
    private String address;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
