package com.example.demo.controller;


import com.example.demo.mapper.CommodityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Commodity;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private UserMapper userMapper;



    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || description==""){
            model.addAttribute("error","商品描述不能为空");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();//从cookie中获得token
                    user = userMapper.findByToken(token);//根据token从UserMapper中找到User
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user ==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Commodity commodity = new Commodity();
        commodity.setTitle(title);
        commodity.setDescription(description);
        commodity.setTag(tag);
        commodity.setCreator(user.getId());
        commodity.setGmtCreate(System.currentTimeMillis());
        commodity.setGmtModified(commodity.getGmtCreate());

        commodityMapper.create(commodity);
        return "redirect:/";
    }
}
