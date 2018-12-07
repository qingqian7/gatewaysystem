package com.cloud.comsumer.filter;


import com.cloud.common.model.User;
import com.cloud.comsumer.Util.JwtUtil;
import com.cloud.comsumer.Util.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProviderJwtFilter implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        System.out.println(1 + "........");
        //String token = StringUtils.isEmpty(JwtUtil.token) ? JwtUtil.getToken(UserContextHolder.getCurrentUser()) : JwtUtil.token ;
        String token = "token123456789";
        System.out.println(token +"..................");
        template.header("auth",token);
    }

}
