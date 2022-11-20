package com.example.sportplay.controller;


import com.example.sportplay.bean.QueryStaff;
import com.example.sportplay.bean.Staff;
import com.example.sportplay.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;
    @RequestMapping("/allStaff")
    Map<String ,Object> getUserList(){
        List<Staff> allStaff=staffService.getAllStaff();
        Map<String ,Object>map=new HashMap<>();
        map.put("data",allStaff);
        map.put("num","10");
        return map;
    }
    @RequestMapping("/addStaff")
    String addStaff(Staff staff){
        int a=staffService.addStaff(staff);
        return a>0?"success":"error";
    }
    @RequestMapping("delStaff")
    int delStaff(int id){
        return staffService.deleteStaff(id);
    }
    @RequestMapping("editStaff")
    int editStaff(Staff staff){
        return staffService.editStaff(staff);
    }
    @RequestMapping("/getStaff")
    Staff getStaff(int id){
        return staffService.getStaff(id);
    }


}
