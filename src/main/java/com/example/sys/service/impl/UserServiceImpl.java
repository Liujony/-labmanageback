package com.example.sys.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.dto.UserTokenDto;
import com.example.sys.entity.User;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserTokenDto selectByUUID(JSONObject object) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUuid,object.get("uuid")).eq(User::getPassword,object.get("password"));
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new BizException(ExceptionEnum.USER_PASSWORD_WRONG);
        }
        UserTokenDto userTokenDto=new UserTokenDto();
        userTokenDto.setUuid(user.getUuid());
        userTokenDto.setAuth(user.getAuth());
        userTokenDto.setPassword(user.getPassword());
        return userTokenDto;
    }


}
