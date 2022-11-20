package com.example.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.example.sportplay.bean.User;
import com.example.sportplay.dao.UserDao;
import com.example.sportplay.service.LoginService;
import com.example.sportplay.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService ;

    @RequestMapping("/login")
    public String login(@RequestBody User user, @RequestParam("code") String code, HttpServletRequest request,
                        HttpServletResponse response){
        String flag ="error";
        System.out.println(user);
        System.out.println(code+"--"+request.getSession(true)
                .getAttribute("code")
                .toString());
        Map<String ,Object> map= new HashMap<>();
        if(request.getSession(true)
                .getAttribute("code")==null
                ||
                !code.toUpperCase()
                        .equals(request.getSession(true)
                                .getAttribute("code")
                                .toString().toUpperCase())){
            flag="yzm";
            map.put("flag",flag);
            return JSON.toJSONString(map);
        }

        User u1 = loginService.checkUser(user);
        //创建一个map集合用来储存数据

        if (u1!=null){
            flag = "ok";
        }
        map.put("flag",flag);
        map.put("user",u1);
        return JSON.toJSONString(map);

    }
    /**
     * 生成验证码图片
     * @param request
     * @param res
     * @throws IOException
     */
    @GetMapping("/code")
    public void code(HttpServletRequest request,
                     HttpServletResponse res) throws IOException {
        CodeUtils code = new CodeUtils();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("code", text);
        CodeUtils.output(image,res.getOutputStream());
    }

}
