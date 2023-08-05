package com.dyingzhang.auth.service.impl;

import com.dyingzhang.auth.component.UserDetails;
import com.dyingzhang.auth.dao.UserAuthMapper;
import com.dyingzhang.auth.domain.entity.UserAuth;
import com.dyingzhang.auth.service.UserService;
import com.dyingzhang.auth.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author DyingZhang
 * @date 2023-08-05 下午 12:13
 * @discription
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final int ACCESS_TOKEN_EXPIRATION = 5000; //5分钟
    public static final int REFRESH_TOKEN_EXPIRATION = 300000; //5分钟

    /**
     * 存放用户token，分布式服务可以放到redis（推荐） / mysql
     */
    @Resource
    private UserDetails userDetails;

    @Resource
    private UserAuthMapper userAuthMapper;

    /**
     * 校验权限并签发token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public Boolean loginDeal(String username, String password, HttpServletRequest request) {
        List<UserAuth> userList = userAuthMapper.getUserByChecking(username);//获取所有匹配的用户名（可优化）

        //遍历匹配密码
        for (UserAuth user : userList) {
            if (username.equals(user.getUsername()) && BCrypt.checkpw(password, user.getPassword())) {
                String accessToken = JWTUtils.generateToken(username, ACCESS_TOKEN_EXPIRATION); //签发token，expiration为过期时间(ms)
                String refreshToken = JWTUtils.generateToken(username, REFRESH_TOKEN_EXPIRATION); //签发token，expiration为过期时间(ms)
                userDetails.addToken(accessToken, username); //将token添加到userDetails
                userDetails.addRefreshToken(refreshToken, username); //将refreshToken添加到userDetails
                request.getSession().setAttribute("access_token", accessToken); //将token放到session中
                request.getSession().setAttribute("refresh_token", refreshToken); //模拟将refreshToken返回前端，默认只返回一次
                return true;
            }
        }
        return false;
    }
}
