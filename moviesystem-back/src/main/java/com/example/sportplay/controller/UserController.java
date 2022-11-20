package com.example.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.example.sportplay.bean.QueryInfo;
import com.example.sportplay.bean.QueryStaff;
import com.example.sportplay.bean.User;
import com.example.sportplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @param queryInfo
     * @return
     */
    @RequestMapping("/allUser")
    public @ResponseBody
    Map<String ,Object> getUserList(QueryInfo queryInfo){
        //获取最大列表数和当前编号
        List<User> allUser = userService.getAllUser(queryInfo);
        int userCounts = userService.getUserCounts(queryInfo.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("number",userCounts);
        map.put("data",allUser);
        return  map;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        user.setState(false);
        int i = userService.addUser(user);
        String str = i >0?"success":"error";
        return str;
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @RequestMapping("/getUpdate")
    public String getUpdateUser(int id){
        System.out.println("编号:"+id);
        User updateUser = userService.getUpdateUser(id);
        String users_json = JSON.toJSONString(updateUser);
        return users_json;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/editUser")
    public String editUser(@RequestBody User user){
        System.out.println(user);
        int i = userService.editUser(user);
        String str = i >0?"success":"error";
        return str;
    }

    /**
     * 修改用户登录状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/userState")
    public String updateUserState(@RequestParam("id") Integer  id,
                                  @RequestParam("state") Boolean state){
        int i = userService.updateState(id, state);
        System.out.println("用户编号:"+id);
        System.out.println("用户状态:"+state);
        String str = i >0?"success":"error";
        return str;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(int[] id){
        System.out.println(id);
        int i = userService.deleteUser(id);
        String str = i >0?"success":"error";
        return str;
    }

}
