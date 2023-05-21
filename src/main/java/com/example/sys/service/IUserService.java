package com.example.sys.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.UserDto;
import com.example.dto.UserTokenDto;
import com.example.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public interface IUserService extends IService<User> {
    UserTokenDto selectByUUID(JSONObject object);

    Page<?> select(UserDto userDto);

    Page<?> selectByUUIDByAuth(UserDto userDto);
}
