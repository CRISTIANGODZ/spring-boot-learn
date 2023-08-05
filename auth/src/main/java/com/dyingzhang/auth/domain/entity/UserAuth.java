package com.dyingzhang.auth.domain.entity;

import lombok.*;

/**
 * @author DyingZhang
 * @date 2023-08-05 下午 12:25
 * @discription
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserAuth {
    private Long id;
    private String username;
    private String password;

    public UserAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
