package com.example.demo.mybatis.po;


public class Blog {
    public Blog(Integer id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public Blog() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Integer id;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    private String username;

    private String content;
}
