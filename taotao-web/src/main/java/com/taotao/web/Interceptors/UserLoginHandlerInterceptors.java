package com.taotao.web.Interceptors;

import com.taotao.common.utils.CookieUtils;
import com.taotao.web.bean.User;
import com.taotao.web.service.UserService;
import com.taotao.web.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginHandlerInterceptors implements HandlerInterceptor {

    public static final String COOKIE_NAME = "TT_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = CookieUtils.getCookieValue(httpServletRequest, COOKIE_NAME);
        String loginUrl = this.userService.TAOTAO_SSO_URL + "/user/login.html";

        if (StringUtils.isEmpty(token)) {
//            未登录，跳转至登录界面
            httpServletResponse.sendRedirect(loginUrl);
            return false;
        }

        User user = this.userService.queryByToken(token);
        if (user == null) {
//            登录超时，跳转至登录界面
            httpServletResponse.sendRedirect(loginUrl);
        }

//        登录成功
        UserThreadLocal.set(user);//将user放置本地线程中，方便Controller和Service的调用

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserThreadLocal.set(null);//清空本地线程中的user数据
    }
}
