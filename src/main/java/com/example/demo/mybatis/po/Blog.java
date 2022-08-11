package com.example.demo.mybatis.po;


import com.example.demo.mybatis.enums.UserNameEnum;

public class Blog {

    public Blog() {
    }

    private Integer id;

    private UserNameEnum userName;

    private String content;

    public Blog(Integer id, UserNameEnum userName, String content) {
        this.id = id;
        this.userName = userName;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userName=" + userName +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserNameEnum getUserName() {
        return userName;
    }

    public void setUserName(UserNameEnum userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
