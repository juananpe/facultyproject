package faculty.project.domain;


public class User {

    private Long id;

    public String getPassword() {
        return password;
    }

    public String getCompleteName() {
        return completeName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

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
