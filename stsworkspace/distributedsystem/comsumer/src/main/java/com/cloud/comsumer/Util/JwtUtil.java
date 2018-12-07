package com.cloud.comsumer.Util;

import com.cloud.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class JwtUtil {
    //@Value("${auth.url}")
    private static String auth_url = "http://localhost:8084/auth";
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    public final static long ONE_Minute = 60 * 1000 * 60 * 20;
    public static String token = "";

    @Scheduled(fixedDelay = ONE_Minute)
    public void reloadApiToken(){
        token = getToken(UserContextHolder.getCurrentUser());
        while(StringUtils.isEmpty(token)){
            try{
                Thread.sleep(1000);
                token = getToken(UserContextHolder.getCurrentUser());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static String getToken(User user){
        System.out.println("get for token ........." + user.getName());
        RestTemplate restTemplate = new RestTemplate();
        String token = "";
        try{
            token = restTemplate.postForObject(auth_url,UserContextHolder.getCurrentUser(),String.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return token;
    }
}
