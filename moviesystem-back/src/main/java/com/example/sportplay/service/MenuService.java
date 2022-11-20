package com.example.sportplay.service;

import com.example.sportplay.bean.MainMenu;

import java.util.List;

public interface MenuService {

    /**
     * 获取导航栏信息
     * @return
     */

    public List<MainMenu> getMenus();
}
