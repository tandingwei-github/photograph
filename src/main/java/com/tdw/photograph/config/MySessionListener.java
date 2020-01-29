package com.tdw.photograph.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 谭锭伟
 * @date 2020/1/2-9:37
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    private int onlineCount = 0;//记录session的数量

    //session创建后执行
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineCount++;
        System.out.println("用户/管理员进入页面,当前:" + onlineCount + "人在线");
    }

    //session失效后执行
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (onlineCount > 0) {
            onlineCount--;
        }
        System.out.println("用户/管理员下线,还剩:" + onlineCount + "人在线");
    }
}
