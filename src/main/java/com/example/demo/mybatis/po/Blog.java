package com.example.demo.mybatis.po;


import com.example.demo.mybatis.enums.UserNameEnum;
import org.springframework.util.Assert;

public class Blog {

    public Blog() {
    }

    private Integer id;

    private String userName;

    private String content;

    public Blog(Integer id, String userName, String content) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static BlogBuilder builder(){
        return new BlogBuilder();
    }
    public static class BlogBuilder{

        String userName;

        String content;


        public BlogBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public BlogBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public Blog build(){
            Assert.notNull(content, "content must not be null");
            if(userName == null){
                userName = UserNameEnum.HUWENTAO.name();
            }

            return new Blog(null,userName,content);
        }
    }
}
