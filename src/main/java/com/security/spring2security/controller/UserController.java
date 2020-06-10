package com.security.spring2security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller // == @Controller  + @ResponseBody
public class UserController {
    @RequestMapping("/index")
    public String index1(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/user/show")
    @ResponseBody
    public String user2a(){
        return "欢迎来到用户页面";
    }

    @RequestMapping("/admin/show")
    @ResponseBody
    public String admin2a(){
        return "欢迎来到管理员页面";
    }

    @RequestMapping("/show")
    public Authentication show(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
