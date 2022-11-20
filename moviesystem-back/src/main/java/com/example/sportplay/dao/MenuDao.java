package com.example.sportplay.dao;

import com.example.sportplay.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    /**
     * 获取导航栏信息
     * @return
     */

    public List<MainMenu> getMenus();
}
