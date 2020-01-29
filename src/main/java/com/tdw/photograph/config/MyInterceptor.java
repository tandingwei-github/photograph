package com.tdw.photograph.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 谭锭伟
 * @date 2019/12/24-14:09
 */
//登录拦截器
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object user = request.getSession().getAttribute("userName");

        String url = request.getServletPath();

        if (user == null) {
            request.setAttribute("msg", "未登录或登录已过期");
            if (url.contains("admin") || url.contains("Admin")) {
                request.getRequestDispatcher("/loginAdmin").forward(request, response);
            } else if (url.contains("register") || url.contains("Register")) {
                request.getRequestDispatcher("/register").forward(request, response);
            } else {
                request.getRequestDispatcher("/login").forward(request, response);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
