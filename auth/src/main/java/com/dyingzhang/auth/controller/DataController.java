package com.dyingzhang.auth.controller;

import com.dyingzhang.auth.component.EmailUtil;
import com.dyingzhang.auth.utils.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author DyingZhang
 * @date 2023-08-04 下午 5:04
 * @discription
 */
@RestController
public class DataController {

    @Autowired
    private EmailUtil emailUtil;

    @GetMapping("/data")
    public String data() {
        return "data";
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        String to = "2456909951@qq.com"; // 替换成收件人邮箱地址
        String subject = "邮箱验证码";

        String text = VerificationCodeUtil.generateVerificationCode(6);
        emailUtil.sendMessage(to, subject, text);

        return "发送成功";
    }

}
