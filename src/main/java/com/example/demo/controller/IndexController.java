package com.example.demo.controller;

import com.example.demo.dto.CommodityDTO;
import com.example.demo.mapper.CommodityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Commodity;
import com.example.demo.model.User;
import com.example.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.BaseRowSet;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommodityService commodityService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();//从cookie中获得token
                    User user = userMapper.findByToken(token);//根据token从UserMapper中找到User
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<CommodityDTO> commoditylist = commodityService.list();
        model.addAttribute("commoditys",commoditylist);
     return "index";
    }
}
