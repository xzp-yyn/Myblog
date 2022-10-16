package com.xzp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/6 20:08
 * @Version 1.0
 */
@Controller
public class MusicController {

    @GetMapping("/music")
    public String music(){
        return "music";
    }

    @GetMapping("/aboutme")
    public String aboutme(){
        return "about";
    }
}
