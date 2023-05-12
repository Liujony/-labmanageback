package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.dto.UserDto;
import com.example.dto.UserTokenDto;
import com.example.sys.entity.User;
import com.example.sys.service.IUserService;
import com.example.util.TokenUtil;
import com.example.vo.userVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService Userervice;
    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user = Userervice.getById(id);
        log.info(user.getUsername());
        List<User> list = Userervice.list();
        log.info(String.valueOf(list));
        return Result.success(user);
    }

//    @GetMapping("getAllUser")
//    public Result<List<User>> getAllUser(){
//        List<User> list = Userervice.list();
//        return Result.success(list);
//    }

    /**
     * 登录功能
     * @param object
     * @param response
     * @return
     */
    @PostMapping("login")
    public Result<UserTokenDto> login(@RequestBody JSONObject object, HttpServletResponse response){
        UserTokenDto user=Userervice.selectByUUID(object);
        String token=tokenUtil.getToken(user.getUuid(),user.getAuth());
        ResponseCookie cookie=ResponseCookie.from("TOKEN",token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(3600)
                .sameSite("None")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return Result.success(user);
    }

    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                                  @RequestParam(value = "phone",required = false) String phone,
                                                  @RequestParam(value = "pageNo",required = false) Long pageNo,
                                                  @RequestParam(value = "pageSize",required = false) Long pageSize){


        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);

        Page<User> page = new Page<>(pageNo,pageSize);
        Userervice.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        //这里要和前端商量好，名字要不要叫total和raws
        data.put("total",page.getTotal());
        data.put("raws",page.getRecords());

        return Result.success(data);
    }

    /**
     * 根据类别、姓名、学号、工号、专业、班级获取
     * @return
     */
//    @PostMapping("getAllUser")
//    public Result<userVo> getAllUser(@RequestBody UserDto userDto,@RequestAttribute Integer auth){
//        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
//        Integer userDtoAuth = userDto.getAuth();
//
//    }

}
