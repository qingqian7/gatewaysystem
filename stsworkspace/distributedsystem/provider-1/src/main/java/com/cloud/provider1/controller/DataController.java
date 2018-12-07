package com.cloud.provider1.controller;

import com.cloud.common.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {
    @GetMapping("/getUser")
    public  User getUser(){
        //System.out.println(user.getName()+".................");
        System.out.println("getUser  test............");
        return new User("1","lijun","1234");
    }
    @RequestMapping("/getList")
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("lijun");
        list.add("1234");
        return list;
    }

    @PostMapping("/uploadUser")
    public String uploadUser(@RequestBody User user){
        System.out.println("uploadUser  test............");
        return user.getName();
    }
}
