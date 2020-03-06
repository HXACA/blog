package com.xliu.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:35
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
