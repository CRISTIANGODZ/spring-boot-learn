package com.dyingzhang.auth.component;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DyingZhang
 * @date 2023-08-04 下午 10:45
 * @discription
 * 存放用户token
 */
@Component
public class UserDetails {

    private Map<String, String> userMap = new HashMap<>();
    private Map<String, String> refreshUserMap = new HashMap<>();

    public String getToken(String key) {
        return userMap.get(key);
    }

    public Boolean addToken(String key, String value) {
        userMap.put(key, value);
        return true;
    }

    public String getRefreshToken(String key) {
        return refreshUserMap.get(key);
    }

    public Boolean addRefreshToken(String key, String value) {
        refreshUserMap.put(key, value);
        return true;
    }
}
