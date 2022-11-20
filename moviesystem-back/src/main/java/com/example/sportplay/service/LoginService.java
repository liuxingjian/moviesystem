package com.example.sportplay.service;


import com.example.sportplay.bean.User;


public interface LoginService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    public User checkUser(User user);

}
