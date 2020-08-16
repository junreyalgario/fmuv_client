package fmuv.client.domain.model;

public class UserModel {

    private String passengerId;
    private String fName;
    private String lName;
    private String gender;
    private String contact;
    private String email;
    private String password;

    public UserModel() {}

    public UserModel(String passengerId, String fName, String lName, String gender, String contact, String email) {
        this.passengerId = passengerId;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
