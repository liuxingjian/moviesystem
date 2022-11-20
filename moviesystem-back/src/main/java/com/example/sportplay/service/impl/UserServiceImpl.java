package com.example.sportplay.service.impl;

import com.example.sportplay.bean.QueryInfo;
import com.example.sportplay.bean.User;
import com.example.sportplay.dao.UserDao;
import com.example.sportplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser(QueryInfo queryInfo) {
        int pageStart=(queryInfo.getPageNum()-1) * queryInfo.getPageSize();
        List<User> allUser = userDao.getAllUser("%"+queryInfo.getUsername()+"%", pageStart, queryInfo.getPageSize());
        return allUser;
    }

    @Override
    public int getUserCounts(String username) {
        int userCounts = userDao.getUserCounts("%" + username+ "%");
        return userCounts;
    }

    @Override
    public int addUser(User user) {
        int count = userDao.addUser(user);
        return count;
    }

    @Override
    public User getUpdateUser(int id) {
        User user = userDao.getUpdateUser(id);
        return user;
    }

    @Override
    public int editUser(User user) {
        int count = userDao.editUser(user);
        return count;
    }

    @Override
    public int updateState(Integer id, Boolean state) {
        int count = userDao.updateState(id, state);
        return count;
    }

    @Override
    public int deleteUser(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            userDao.deleteUser(ids[i]);
        }
        return 1;
    }
}
