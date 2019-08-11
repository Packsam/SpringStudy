package com.example.demo.controller;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/profile/{action}")
    public String profile(
            HttpServletRequest request,
            @PathVariable(name = "action") String action, Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "5") Integer size) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0) {
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

        if(user==null){
            return "refirect:/";//如果没有登陆，则返回首页
        }

        if ("commodities".equals(action)) {//前端传回的点击的连接参数与后端相同时，把相应参数传回到前端
            model.addAttribute("section", "commodities");
            model.addAttribute("sectionName", "我的商品");
        } else if ("reply".equals(action)) {//前端返回的是最新回复链接
            model.addAttribute("section", "reply");
            model.addAttribute("sectionName", "最新回复");
        }

        ;

        PaginationDTO pagination = commodityService.list(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }
}
