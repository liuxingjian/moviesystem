package com.example.sportplay.dao;

import com.example.sportplay.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from easyuser where username=#{username} and password=#{password} and state=1")

    public User checkUser(User user);

    public List<User> getAllUser(@Param("username") String username,
                                 @Param("pageStart") int pageStart,
                                 @Param("pageSize") int pageSize);

    public int getUserCounts(@Param("username")String username);

    public int addUser(User user);

    public User getUpdateUser(int id);


    public int editUser(User user);


    public int updateState(Integer id, Boolean state);

    public int deleteUser(int id);
}
