package com.cloud.comsumer.controller;

import com.cloud.common.model.User;
import com.cloud.comsumer.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/com")
public class TestController {
    @Autowired
    DataService service;

    //做从config server 获取的属性
    @Value("${springconfig.config}")
    private String config;

    @RequestMapping("/test")
    public String test(){
        return "test success";
    }

    @RequestMapping("/getUser")
    public User getUser(){
        return service.getUser();
    }
    @RequestMapping("/getList")
    public List<String> getList(){
        return service.getList();
    }

    @RequestMapping("/uploadUser")
    public String uploadUser(){
        return service.uploadUser(new User("3","upload","1234"));
    }

    @RequestMapping("/config")
    public String getConfig(){
        return config;
    }
}
