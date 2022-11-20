package com.example.sportplay.service.impl;

import com.example.sportplay.bean.MainMenu;
import com.example.sportplay.dao.MenuDao;
import com.example.sportplay.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MainMenu> getMenus() {
        List<MainMenu> menus = menuDao.getMenus();
        return menus;
    }
}
