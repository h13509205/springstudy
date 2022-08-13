package com.example.demo.mybatis.dao;

import com.example.demo.mybatis.po.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {

    Blog selectBlog(Integer id);

    Blog selectBlog2(Integer id);

    Integer insertBlog(Blog blog);
    Integer insertBlogs(List<Blog> list);
}
