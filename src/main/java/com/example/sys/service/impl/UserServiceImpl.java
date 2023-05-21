package com.example.sys.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.dto.UserDto;
import com.example.dto.UserTokenDto;
import com.example.sys.entity.Students;
import com.example.sys.entity.Teachers;
import com.example.sys.entity.Testers;
import com.example.sys.entity.User;
import com.example.sys.mapper.StudentsMapper;
import com.example.sys.mapper.TeachersMapper;
import com.example.sys.mapper.TestersMapper;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private TeachersMapper teachersMapper;

    @Autowired
    private TestersMapper testersMapper;

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
        userTokenDto.setUsername(user.getUsername());
        return userTokenDto;
    }

    /**
     * 当不存在UUID的时候
     * @param userDto
     * @return
     */
    @Override
    public Page<?> select(UserDto userDto) {
        Integer auth = userDto.getAuth();
        String username = userDto.getUsername();
        String aClass = userDto.getClassnum();
        String major = userDto.getMajor();
        Integer page = userDto.getPage();
        Integer num = userDto.getNum();
        if (auth==2){
            return getTestersPage(username, page, num);
        }
        if(auth==3){
            return getTeachersPage(username, page, num);
        }
        if (auth==4){
            Page<Students> testersPage = new Page<>(page, num);
            QueryWrapper<Students> wrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(username)) {
                wrapper.like("username", username);
            }

            if (StringUtils.isNotBlank(aClass)) {
                wrapper.eq("classnum", aClass);
            }

            if (StringUtils.isNotBlank(major)) {
                wrapper.eq("major", major);
            }
            return studentsMapper.selectPage(testersPage,wrapper);
        }
        return null;
    }

    /**
     * 当存在UUID的时候
     * @param userDto
     * @return
     */
    @Override
    public Page<?> selectByUUIDByAuth(UserDto userDto) {
        Integer auth = userDto.getAuth();
        Integer page = userDto.getPage();
        Integer num = userDto.getNum();
        if (auth==2){
            Page<Testers> testersPage=new Page<>(page,num);
            return testersMapper.selectPage(testersPage,new QueryWrapper<Testers>().eq("uuid",userDto.getUuid()));
        }
        if (auth==3){
            Page<Teachers> testersPage=new Page<>(page,num);
            return teachersMapper.selectPage(testersPage,new QueryWrapper<Teachers>().eq("uuid",userDto.getUuid()));
        }
        if (auth==4){
            Page<Students> testersPage=new Page<>(page,num);
            return studentsMapper.selectPage(testersPage,new QueryWrapper<Students>().eq("uuid",userDto.getUuid()));
        }
        return null;
    }

    /**
     * 查找Students视图的数据（分页查询）
     * @param username
     * @param page
     * @param num
     * @return
     */
    private Page<Students> getStudentsPage(String username, Integer page, Integer num) {
        Page<Students> testersPage = new Page<>(page, num);
        QueryWrapper<Students> wrapper = new QueryWrapper<Students>();
        if (username!=null) {
            wrapper.like("username", username);
            return studentsMapper.selectPage(testersPage, wrapper);
        }else{
            return studentsMapper.selectPage(testersPage, wrapper);
        }
    }

    /**
     * 查找Tests视图的数据（分页查询）
     * @param username
     * @param page
     * @param num
     * @return
     * @date 2023-05-20 16:57
     */
    private Page<Teachers> getTeachersPage(String username, Integer page, Integer num) {
        Page<Teachers> testersPage = new Page<>(page, num);
        QueryWrapper<Teachers> wrapper = new QueryWrapper<Teachers>();
        if (username!=null) {
            wrapper.like("username", username);
            return teachersMapper.selectPage(testersPage, wrapper);
        }else{
            return teachersMapper.selectPage(testersPage, wrapper);
        }
    }

    /**
     * 查找Teachers视图的数据（分页查询）
     * @param username
     * @param page
     * @param num
     * @return
     * @date 2023-05-20 16:57
     */
    private Page<Testers> getTestersPage(String username, Integer page, Integer num) {
        Page<Testers> testersPage = new Page<>(page, num);
        QueryWrapper<Testers> wrapper = new QueryWrapper<Testers>();
        if (username!=null) {
            wrapper.like("username", username);
            return testersMapper.selectPage(testersPage, wrapper);
        }else{
            return testersMapper.selectPage(testersPage, wrapper);
        }
    }


}
