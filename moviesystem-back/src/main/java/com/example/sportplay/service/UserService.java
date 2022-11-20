package com.example.sportplay.service;


import com.example.sportplay.bean.QueryInfo;
import com.example.sportplay.bean.User;

import java.util.List;


public interface UserService {

    public List<User> getAllUser(QueryInfo queryInfo);

    public int getUserCounts(String username);

    public int addUser(User user);


    public User getUpdateUser(int id);

    public int editUser(User user);


    public int updateState(Integer id, Boolean state);

    public int deleteUser(int[] ids);

}
