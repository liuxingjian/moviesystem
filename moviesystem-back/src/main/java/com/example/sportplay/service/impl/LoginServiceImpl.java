package com.example.sportplay.service.impl;

import com.example.sportplay.bean.User;
import com.example.sportplay.dao.UserDao;
import com.example.sportplay.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao loginDao;

    @Override
    public User checkUser(User user) {
        User u1 = loginDao.checkUser(user);
        return u1;
    }


}
