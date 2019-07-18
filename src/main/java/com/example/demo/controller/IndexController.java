package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.BaseRowSet;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/index")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();//从cookie中获得token
                User user = userMapper.findByToken(token);//根据token从UserMapper中找到User
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
     return "index";
    }
}
