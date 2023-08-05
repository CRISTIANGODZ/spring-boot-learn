package com.dyingzhang.auth.controller;

import com.dyingzhang.auth.component.UserDetails;
import com.dyingzhang.auth.domain.dto.LoginDTO;
import com.dyingzhang.auth.service.UserService;
import com.dyingzhang.auth.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
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
    UserService userServiceImpl;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 处理提交的登录表单
     * @param loginDTO
     * @param request
     * @return
     */
    @PostMapping("/login/submit")
    public ModelAndView loginDeal(@ModelAttribute LoginDTO loginDTO,
                                  HttpServletRequest request) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        Boolean flag = userServiceImpl.loginDeal(username, password, request);
        return flag ? new ModelAndView("success") : new ModelAndView("fail");
    }
}
