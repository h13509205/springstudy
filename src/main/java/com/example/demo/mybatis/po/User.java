package com.example.demo.mybatis.po;

public class User {

    public User(){

    }

    public User(Integer id, String userName, Password password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    private Integer id;

    private String userName;

    private Password password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password=" + password.toString() +
                '}';
    }
}
