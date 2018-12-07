package com.cloud.comsumer.Util;

import com.cloud.common.model.User;


public class UserContextHolder {
    private static ThreadLocal<User> local_cache = new ThreadLocal<>();
    public static User getCurrentUser(){
        return local_cache.get();
    }

    public static void set(User user){
        local_cache.set(user);
    }
    public static void shutdown(){
        local_cache.remove();
    }

}
