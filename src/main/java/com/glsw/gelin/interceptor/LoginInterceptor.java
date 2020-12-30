package com.glsw.gelin.interceptor;

import com.glsw.gelin.dao.UserRepository;
import com.glsw.gelin.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-10 11:39
 */

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if(req.getSession().getAttribute("user") != null){
            User user = (User) req.getSession().getAttribute("user");
            Integer ss = user.getType();
            if(ss == 2){
                resp.sendRedirect("/");
                return false;
            }
        }
        if(req.getSession().getAttribute("user") == null){
            resp.sendRedirect("/admin");
            return false;
        }
        return true;
    }


}
