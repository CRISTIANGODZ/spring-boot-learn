package com.dyingzhang.auth.controller;

import com.dyingzhang.auth.component.EmailHelper;
import com.dyingzhang.auth.utils.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DyingZhang
 * @date 2023-08-04 下午 5:04
 * @discription
 */
@RestController
public class DataController {

    @Autowired
    private EmailHelper emailHelper;

    @GetMapping("/data")
    public String data() {
        return "data";
    }

    @GetMapping("/send-email/{qq}")
    public String sendEmail(@PathVariable(value = "qq") String qq) {
        String to = qq +  "@qq.com"; // 替换成收件人邮箱地址
        String subject = "邮箱验证码";

        String text = VerificationCodeUtil.generateVerificationCode(6);
        emailHelper.sendMessage(to, subject, text);

        return "发送成功";
    }

}
