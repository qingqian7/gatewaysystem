package com.cloud.authserver.authcontroller;


import com.cloud.common.exception.ParamException;
import com.cloud.common.jwt.JwtUtil;
import com.cloud.common.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public String createToken(@RequestBody User user) throws ParamException {
        System.out.println(user.getName()+".............................");
        if(user != null){
            return (String) JwtUtil.getToken(user);
        }
        throw  new ParamException("用户为空");
    }
}
