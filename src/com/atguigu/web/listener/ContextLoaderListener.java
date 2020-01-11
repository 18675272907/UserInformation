package com.atguigu.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther lizongxiao
 * @date 2020/1/10 - 17:09
 */
public class ContextLoaderListener implements ServletContextListener {
    private long startT;
    private long clossT;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("进入用户管理信息系统");
        //获取进入系统时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        startT = date.getTime();
        System.out.println(startT);

        String starTime = simpleDateFormat.format(date);
        System.out.println("进入系统时间："  + starTime);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("关闭用户管理信息系统");
        //获取关闭系统时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        clossT = date.getTime();
        System.out.println(clossT);

        String clossTime = simpleDateFormat.format(date);
        System.out.println("关闭系统时间："  + clossTime);

        System.out.println("您总共浏览时长为：" + ((clossT - startT)/1000) + "秒");
    }
}
