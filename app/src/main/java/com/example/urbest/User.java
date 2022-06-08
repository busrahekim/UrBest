package com.example.urbest;

public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String mail;

    public User() {
    }

    public User(String username, String firstName, String lastName, String password, String mail) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.mail = mail;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
