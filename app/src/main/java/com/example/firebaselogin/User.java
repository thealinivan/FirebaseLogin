package com.example.firebaselogin;

public class User {

    String email, password, fName, lName;


    public User(String email, String password, String fName, String lName) {
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
    }

    public User(){}

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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
