package com.example.demo.mybatis.dao;

import com.example.demo.mybatis.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Select("select * from USERS where id= #{id}")
    User selectUser(Integer id);

    @Insert("insert into USERS(username,password) values (#{userName}, #{password})")
    Integer insertUser(User user);

    @Update({"<script>",
    "update USERS",
    "  <set>",
    "    <if test='userName!=null'>username=#{userName}</if>",
    "    <if test='password!=null'>password=#{password}</if>",
    "  </set>",
    "where id=#{id}",
    "</script>"})
    Integer updateUser(User user);

}
