package com.dyingzhang.auth.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @auther DyingZhang
 * @Create 2023-08-04 下午 2:04
 * @Discriptioon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 42L;
    private String username;
    private String password;
    private String verCode;
}
