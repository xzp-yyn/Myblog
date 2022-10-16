package com.xzp.controller;

import com.xzp.entity.User;
import com.xzp.service.BlogService;
import com.xzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 10:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/xzp")
public class LoginController {

    @Autowired
    private UserService userService;

    @Resource
    private BlogService blogService;

    @GetMapping
    public String loginhtml(){
        return "/admin/login";
    }

    @PostMapping("/login")
    public String loginto(@RequestParam String username,
                          Model model,
                          @RequestParam String password,
                          HttpSession session,
                          RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }
        attributes.addFlashAttribute("message","登录失败");
        return "redirect:/xzp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/xzp";
    }
}
