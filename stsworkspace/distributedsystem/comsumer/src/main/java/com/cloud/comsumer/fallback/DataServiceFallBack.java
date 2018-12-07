package com.cloud.comsumer.fallback;

import com.cloud.common.model.User;
import com.cloud.comsumer.service.DataService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataServiceFallBack implements DataService {
    @Override
    public User getUser() {
        return new User("0","default","1234");
    }

    @Override
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("default");
        return list;
    }

    @Override
    public String uploadUser(User user) {
        return "fail....";
    }
}
