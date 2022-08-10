package com.example.demo.mybatis.dao;

import com.example.demo.mybatis.po.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogDao {

    Blog selectBlog(Integer id);
}
