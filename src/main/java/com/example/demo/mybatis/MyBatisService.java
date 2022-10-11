package com.example.demo.mybatis;

import com.example.demo.mybatis.dao.BlogDao;
import com.example.demo.mybatis.dao.UserDao;
import com.example.demo.mybatis.po.Blog;
import com.example.demo.mybatis.po.Password;
import com.example.demo.mybatis.po.User;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MyBatisService {

    //@PostConstruct
    public void initMethod() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BlogDao mapper = session.getMapper(BlogDao.class);
            Blog blog = mapper.selectBlog2(1);
            System.out.println(blog.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

//        try (SqlSession session = sqlSessionFactory.openSession(true)){
//            Blog b = Blog.builder().setContent("this is my " + new Random().nextInt(10000) + "'st blog!").build();
//            BlogDao mapper = session.getMapper(BlogDao.class);
//            System.out.println(mapper.insertBlog(b));
//        }

//        try (SqlSession session = sqlSessionFactory.openSession()){
//            List<Blog> list = new ArrayList<>();
//            for (int i = 0; i < 3; i++) {
//                list.add(Blog.builder()
//                        .setContent("this is my " + new Random().nextInt(10000) + "'st blog!").build());
//            }
//            BlogDao mapper = session.getMapper(BlogDao.class);
//            System.out.println(mapper.insertBlogs(list));
//            session.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        try (SqlSession session = sqlSessionFactory.openSession(true)){
//            User user = new User();
//            user.setUserName("huwentao");
//            user.setPassword(new Password("32453465453"));
//            UserDao userDao = session.getMapper(UserDao.class);
//            userDao.insertUser(user);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        try (SqlSession session = sqlSessionFactory.openSession()){
            UserDao userDao = session.getMapper(UserDao.class);
            User user = userDao.selectUser(1);
            System.out.println(user.toString());
        }

        try (SqlSession session = sqlSessionFactory.openSession(true)){
            User user = new User();
            //user.setUserName("huwentao");
            user.setId(1);
            user.setPassword(new Password("32453465453"));
            UserDao userDao = session.getMapper(UserDao.class);
            userDao.updateUser(user);

            User user2 = userDao.selectUser(1);
            System.out.println(user2.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
