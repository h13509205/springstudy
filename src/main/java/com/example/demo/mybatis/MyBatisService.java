package com.example.demo.mybatis;

import com.example.demo.mybatis.dao.BlogDao;
import com.example.demo.mybatis.po.Blog;
import jakarta.annotation.PostConstruct;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MyBatisService {

    @PostConstruct
    public void initMethod() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogDao mapper = session.getMapper(BlogDao.class);
            Blog blog = mapper.selectBlog2(1);
            System.out.println(blog.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
