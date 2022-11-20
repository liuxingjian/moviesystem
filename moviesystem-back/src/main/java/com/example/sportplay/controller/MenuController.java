package com.example.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.example.sportplay.bean.MainMenu;
import com.example.sportplay.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取导航栏
     * @return
     */
    @RequestMapping("/menus")
    public @ResponseBody Map<String ,Object> getMenus(){
        System.out.println("访问成功");
        Map<String ,Object> menuMap = new HashMap<>();
        List<MainMenu> menus = menuService.getMenus();
        System.out.println("导航栏数据："+menus);
        if(menus!=null){
            menuMap.put("menus",menus);
            menuMap.put("flag",200);
        }else {
            menuMap.put("flag",404);
        }
        return menuMap ;
    }
}
