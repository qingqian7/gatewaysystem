package com.cloud.eurekaserver;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        System.err.println(event.getServerId() + "\t" + event.getAppName() + "服务下线");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event){
        System.err.println(event.getInstanceInfo().getAppName() + " 服务注册成功");
    }
}
