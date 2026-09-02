package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/my")
public class MyController {


    @RequestMapping("/test01")
    @ResponseBody
    public String test01(){
        return "springboot快速搭建成功";
    }
}
