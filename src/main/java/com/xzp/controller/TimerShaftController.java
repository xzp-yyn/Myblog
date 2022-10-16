package com.xzp.controller;

import com.xzp.query.TimeShaftBlog;
import com.xzp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/7 9:14
 * @Version 1.0
 */
@Controller
public class TimerShaftController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/timeshaft")
    public String timeshaft(Model model){
        List<TimeShaftBlog> queries = blogService.getTimeShaftBlog();
        model.addAttribute("queries",queries);
        return "timeshaft";
    }

}
