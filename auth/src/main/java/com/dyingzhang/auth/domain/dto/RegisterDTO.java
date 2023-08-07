package com.dyingzhang.auth.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author DyingZhang
 * @date 2023-08-07 上午 11:25
 * @discription
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterDTO implements Serializable {
    private static final long serialVersionUID = 42L;
    private Long id;
    private String username;
    private String password;
//    private String phone;
}
