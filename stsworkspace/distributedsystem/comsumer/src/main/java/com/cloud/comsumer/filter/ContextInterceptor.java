package com.cloud.comsumer.filter;

import com.cloud.common.exception.ParamException;
import com.cloud.common.model.User;
import com.cloud.comsumer.Util.UserContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = getUser(request);
        UserContextHolder.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //UserContextHolder.shutdown();
    }

    private User getUser(HttpServletRequest request) throws ParamException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new ParamException("参数错误");
        }
        User user = new User();
        user.setId("123");
        user.setName(username);
        user.setPassword(password);
        System.out.println(user.toString());
        return user;
    }
}
