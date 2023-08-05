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
                String token = JWTUtils.generateToken(username, 86400000); //签发token，expiration为过期时间(ms)
                userDetails.addToken(token, username); //将token添加到userDetails
                request.getSession().setAttribute("token", token); //将token放到session中
                return true;
            }
        }
        return false;
    }
}
