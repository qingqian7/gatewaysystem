package com.cloud.authserver.Util;

import com.cloud.authserver.po.User;

public class UserService {
    public static boolean checkUser(User user){
        return (user.getName().equals("lijun")&&user.getPassword().equals("1234"));
    }
}
