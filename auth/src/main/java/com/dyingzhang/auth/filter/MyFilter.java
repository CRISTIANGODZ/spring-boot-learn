package com.dyingzhang.auth.filter;

import com.dyingzhang.auth.component.UserDetails;
import com.dyingzhang.auth.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DyingZhang
 * @date 2023-08-03 下午 4:54
 * @discription
 */
@Component
@Slf4j
public class MyFilter implements Filter {

    @Resource
    private UserDetails userDetails;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器被初始化了");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器被使用");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String token = (String)session.getAttribute("token"); //获取token
        if (token != null) { //校验token
            String username = JWTUtils.parseToken(token).getSubject(); //获取的token中的用户名
            if (username.equals(userDetails.getToken(token))) { //如果用户名相同，则校验成功
                filterChain.doFilter(servletRequest, servletResponse); //放行，放行后不能再重定向，否则会报错
            } else {
                response.sendRedirect(request.getContextPath() + "/login"); //重定向到登陆界面
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login"); //重定向到登陆界面
        }
    }

    @Override
    public void destroy() {
        log.info("过滤器被销毁");
        Filter.super.destroy();
    }
}
