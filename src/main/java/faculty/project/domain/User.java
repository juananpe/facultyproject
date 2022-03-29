package faculty.project.domain;

public class User {
    private String userName;
    private String password;
    private String completeName;
    private String email;
    private String address;
    private String phoneNumber;

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

    public void authenticate(String login, String password){

    }
}
