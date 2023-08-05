package com.dyingzhang.auth.dao;

import com.dyingzhang.auth.domain.entity.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DyingZhang
 * @date 2023-08-05 下午 12:26
 * @discription
 */
@Mapper
public interface UserAuthMapper {

    /**
     * 校验登录
     * @param username
     * @return
     */
    List<UserAuth> getUserByChecking(@Param(value = "username") String username);
}
