package com.macaitech.saas.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(){
        return "index";
    }
}