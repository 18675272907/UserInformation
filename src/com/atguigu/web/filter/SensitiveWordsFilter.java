package com.atguigu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词汇过滤器
 * @auther lizongxiao
 * @date 2020/1/7 - 22:47
 */
public class SensitiveWordsFilter implements Filter {

    private List<String> list = new ArrayList<String>();//敏感词汇集合

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            //1.获取文件真实路径
            String realPath = filterConfig.getServletContext().getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.将文件的每一行数据添加到list中
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }
            br.close();
            System.out.println(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest)Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if (method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String)method.invoke(servletRequest, args);
                    if (value != null){
                        for (String str : list) {
                            if (value.contains(str)){
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }
                    return value;
                }

                //判断方法名是否是 getParameterMap
                if (method.getName().equals("getParameterMap")){
                    Map<String, String[]> map = (Map<String, String[]>)method.invoke(servletRequest, args);
                    if (!map.isEmpty()){
                        String[] names = (String[])map.get("name");
                        System.out.println("names:" + names); //敏感词汇验证
                        if (names != null && names.length > 0) {
                            for (String str : list) {
                                System.out.println("str:" + str); //敏感词汇验证
                                if (names[0].contains(str)){
                                    System.out.println("names[0]:" + names[0]); //敏感词汇验证
                                    names[0] = names[0].replaceAll(str,"***");
                                    System.out.println("names[0]:" + names[0]); //敏感词汇验证
                                }
                            }
                        }
                        return map;
                    }
                    return map;
                }

                //判断方法名是否是 getParameterValue
                if (method.getName().equals("getParameterValue")){
                    String[] values = (String[])method.invoke(servletRequest, args);
                    if (values != null){
                        for (String value : values) {
                            if (value != null){
                                for (String str : list) {
                                    if (value.contains(str)){
                                        value = value.replaceAll(str,"***");
                                    }
                                }
                            }
                            return values;
                        }
                    }
                    return values;
                }

                return method.invoke(servletRequest,args);
            }
        });

        //2.放行
        filterChain.doFilter(proxy_req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
