package com.cloud.common.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenScheduledTask {
    private static Logger logger = LoggerFactory.getLogger(TokenScheduledTask.class);
    public final static long One_Minute = 1000 * 60 * 60 * 20;  //20小时

    public static String token = "";

    @Scheduled(fixedDelay = One_Minute)
    public void reloadToken(){
        token ="";
    }
}
