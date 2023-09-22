package com.dyingzhang.auth.controller;

import com.dyingzhang.auth.domain.dto.LoginDTO;
import com.dyingzhang.auth.domain.dto.RegisterDTO;
import com.dyingzhang.auth.service.UserService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 设置位数
//        CaptchaUtil.out(5, request, response);
//        // 设置宽、高、位数
//        CaptchaUtil.out(130, 48, 5, request, response);
//        // 使用gif验证码
//        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
        CaptchaUtil.out(request, response);
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
        String verCode = loginDTO.getVerCode();

        if (!CaptchaUtil.ver(verCode, request)) {
            CaptchaUtil.clear(request);
            return new ModelAndView("fail");
        }

        Boolean flag = userServiceImpl.loginDeal(username, password, request);
        return flag ? new ModelAndView("success") : new ModelAndView("fail");
    }

    @PostMapping("/register/submit")
    public ModelAndView registerDeal(@ModelAttribute RegisterDTO registerDTO,
                                     HttpServletRequest request) {
        Boolean flag = userServiceImpl.registeDeal(registerDTO, request);
        return flag ? new ModelAndView("index") : new ModelAndView("fail");
    }
}
