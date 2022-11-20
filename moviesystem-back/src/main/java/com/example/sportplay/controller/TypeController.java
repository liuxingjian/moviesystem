package com.example.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.example.sportplay.bean.QueryType;
import com.example.sportplay.bean.Type;
import com.example.sportplay.service.TypeService;
import com.example.sportplay.springbootvue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BookService bookService;
    /**
     * 获取所有电影类别列表
     * @return
     */
    @RequestMapping("/allTypes")
    public @ResponseBody
    Map<String ,Object> getTypes(){
        List<Type> types = typeService.getTypes();
        Map<String,Object> map = new HashMap<>();
        map.put("data",types);
        return  map;
    }

    /**
     * 获取电影类别列表
     * @param queryType
     * @return
     */
    @RequestMapping("/allType")
    public @ResponseBody
    Map<String ,Object> getTypeList(QueryType queryType){
        //获取最大列表数和当前编号
        List<Type> allType = typeService.getAllType(queryType);
        int typeCounts = typeService.getTypeCounts(queryType.getName());
        Map<String,Object> map = new HashMap<>();
        map.put("number",typeCounts);
        map.put("data",allType);
        return  map;
    }
    /**
     * 添加电影类别
     * @param type
     * @return
     */
    @RequestMapping("/addType")
    public String add(@RequestBody Type type){
        System.out.println(type);
        System.out.println(type.getName()+"--"+type.getId());
        System.out.println(type.getName());
        int i = typeService.addType(type);
        int b=bookService.insertBook(type.getName());
        String str = i >0?"success":"error";
        return str;
    }
    /**
     * 查询电影类别详情
     * @param id
     * @return
     */
    @RequestMapping("/getUpdateType")
    public String getUpdateUser(int id){
        System.out.println("编号:"+id);
        Type type = typeService.getUpdateType(id);
        String users_json = JSON.toJSONString(type);
        return users_json;
    }
    /**
     * 修改电影类别信息
     * @param type
     * @return
     */
    @RequestMapping("/editType")
    public String editUser(@RequestBody Type type){
        System.out.println(type);
        int i = typeService.editType(type);
        String str = i >0?"success":"error";
        return str;
    }

    /**
     * 删除电影类别
     * @param id
     * @return
     */
    @RequestMapping("/deleteType")
    public String deleteUser(int[] id){
        System.out.println(id);
        int i = typeService.deleteType(id);
        String str = i >0?"success":"error";
        return str;
    }



}
