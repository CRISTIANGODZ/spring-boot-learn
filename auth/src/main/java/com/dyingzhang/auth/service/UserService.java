package com.dyingzhang.auth.service;

import com.dyingzhang.auth.domain.dto.RegisterDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DyingZhang
 * @date 2023-08-05 下午 12:13
 * @discription
 */
public interface UserService {

    Boolean loginDeal(String username, String password, HttpServletRequest request);

    Boolean registeDeal(RegisterDTO registerDTO, HttpServletRequest request);
}
