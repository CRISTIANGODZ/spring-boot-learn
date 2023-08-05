package com.dyingzhang.auth.filter;

import com.dyingzhang.auth.component.UserDetails;
import com.dyingzhang.auth.controller.UserController;
import com.dyingzhang.auth.service.impl.UserServiceImpl;
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
        String accessToken = (String)session.getAttribute("access_token"); //获取token

        if (accessToken != null) { //校验access_token
            if (!JWTUtils.isTokenExpired(accessToken)) { //access_token未过期
                String username = JWTUtils.parseToken(accessToken).getSubject(); //获取的token中的用户名
                if (username.equals(userDetails.getToken(accessToken))) {
                    filterChain.doFilter(servletRequest, servletResponse); //放行，放行后不能再重定向，否则会报错
                } else { //校验失败
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else {
                log.info("accessToken过期，寻找refreshToken");
                checkRefreshToken(servletRequest, servletResponse, filterChain);
            }
        } else { //双token机制，若accessToken失效，应该返回401，然后前端重新请求时带上refreshToken，在此只是模拟，故总是带着双token
            log.info("accessToken缺失，寻找refreshToken");
            checkRefreshToken(servletRequest, servletResponse, filterChain);
        }
    }

    /**
     * refreshToken
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void checkRefreshToken(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String refreshToken = (String)session.getAttribute("refresh_token"); //获取token

        if (refreshToken != null) {
            if (!JWTUtils.isTokenExpired(refreshToken)){
                String username = JWTUtils.parseToken(refreshToken).getSubject(); //获取的token中的用户名
                if (username.equals(userDetails.getRefreshToken(refreshToken))) { //校验token
                    String newAccessToken = JWTUtils.generateToken(username, UserServiceImpl.ACCESS_TOKEN_EXPIRATION); //签发新的accessToken
                    userDetails.addToken(newAccessToken, username); //将token添加到userDetails
                    session.setAttribute("access_token", newAccessToken); //添加到session中
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else {
                log.info("refreshToken过期，重新登陆");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            log.info("refreshToken消失，重新登陆");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        log.info("过滤器被销毁");
        Filter.super.destroy();
    }


}
