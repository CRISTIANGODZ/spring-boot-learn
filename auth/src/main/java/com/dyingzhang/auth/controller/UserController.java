package com.dyingzhang.auth.controller;

import com.dyingzhang.auth.component.UserDetails;
import com.dyingzhang.auth.domain.dto.LoginDTO;
import com.dyingzhang.auth.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author DyingZhang
 * @create 2023-08-03 下午 4:48
 * @discriptioon
 */
@Slf4j
@Controller
public class UserController {

    @Resource
    private UserDetails userDetails;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login/submit")
    public ModelAndView loginDeal(@ModelAttribute LoginDTO loginDTO,
                                  HttpServletRequest request) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (username.equals("zhangSan") && password.equals("123456")) { //模拟查数据库校验
            String token = JWTUtils.generateToken(username); //签发token
            userDetails.addToken(token, username); //将token添加到userDetails
            request.getSession().setAttribute("token", token); //将token放到session中
            return new ModelAndView("success");
        } else {
            return new ModelAndView("fail");
        }
    }
}
