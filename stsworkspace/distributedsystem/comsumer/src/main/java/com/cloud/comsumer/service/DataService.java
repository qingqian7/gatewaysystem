package com.cloud.comsumer.service;

import com.cloud.common.model.User;
import com.cloud.comsumer.fallback.DataServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "provider-1",fallback = DataServiceFallBack.class)
public interface DataService {
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser();

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public List<String> getList();

    @RequestMapping(value = "/uploadUser",method = RequestMethod.POST,consumes = "application/json")
    public String uploadUser(@RequestBody User user);
}
