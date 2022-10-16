package com.xzp.dao;

import com.xzp.entity.User;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/25 18:03
 * @Version 1.0
 */
@Repository
@Mapper
public interface UserDao {
    @Select("select * from t_user where username=#{username} and password=#{password}")
    User findBynameandpassword(@Param("username")String username, @Param("password")String password);

    @Insert("INSERT INTO t_user(username, password,create_time,nickname,avatar,update_time,type,email) VALUES(#{username}, #{password}, #{createTime},#{nickname}, #{avatar},#{updateTime},#{type},#{email})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertUser(User user);

}
