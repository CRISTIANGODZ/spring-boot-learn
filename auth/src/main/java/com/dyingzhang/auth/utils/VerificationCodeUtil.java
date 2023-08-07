package com.dyingzhang.auth.utils;

import java.util.Random;

/**
 * @author DyingZhang
 * @date 2023-08-07 下午 4:03
 * @discription
 */
public class VerificationCodeUtil {

    public static String generateVerificationCode(int length) {
        // 定义验证码字符集合，这里使用数字字符
        String characters = "0123456789";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        int charactersLength = characters.length();

        // 生成指定长度的验证码
        for (int i = 0; i < length; i++) {
            // 从字符集合中随机选择一个字符，并添加到验证码中
            int index = random.nextInt(charactersLength);
            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}
