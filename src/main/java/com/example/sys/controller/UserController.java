package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.dto.DeleteList;
import com.example.dto.UserDto;
import com.example.dto.UserTokenDto;
import com.example.sys.entity.Student;
import com.example.sys.entity.Teacher;
import com.example.sys.entity.User;
import com.example.sys.mapper.StudentMapper;
import com.example.sys.service.IStudentService;
import com.example.sys.service.ITeacherService;
import com.example.sys.service.IUserService;
import com.example.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    private IUserService userService;
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

//    @GetMapping("/{id}")
//    public Result<User> getUserById(@PathVariable("id") Integer id){
//        User user = userService.getById(id);
//        log.info(user.getUsername());
//        List<User> list = userService.list();
//        log.info(String.valueOf(list));
//        return Result.success(user);
//    }


    /**
     * 登录功能
     * @param object
     * @param response
     * @return
     */
    @PostMapping("login")
    public Result<UserTokenDto> login(@RequestBody JSONObject object, HttpServletResponse response){
        UserTokenDto user= userService.selectByUUID(object);
        String token=tokenUtil.getToken(user.getUuid(),user.getAuth());
        ResponseCookie cookie=ResponseCookie.from("TOKEN",token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(3600)
                .sameSite("None")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return Result.success(user);
    }

    /**
     * 根据类别、姓名、学号、工号、专业、班级获取
     * @param
     * @param auth
     * @return
     * @date 2023-05-20 19:40
     */
    @PostMapping("getUsers")
    public Result<?> getUsers(@RequestBody UserDto userDto, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        if (userDto.getPage()==null) {
            userDto.setPage(1);
        }
        log.info(String.valueOf(userDto.getPage()));
//        if (user.getInteger("page")==0){
//            jsonObject.put("page",1);
//        }
//        if (jsonObject.getInteger("num")==0){
//            jsonObject.put("num",20);
//        }
        if (userDto.getNum()==null) {
            userDto.setNum(20);
        }
        log.info(String.valueOf(userDto.getNum()));
        Integer authKey = userDto.getAuth();
        if (authKey==null){
            authKey=2;
            userDto.setAuth(authKey);
        }
        if (!Objects.equals(userDto.getUuid(), "")) {
            Page<?> page=userService.selectByUUIDByAuth(userDto);
            return Result.success(page);
        }else {
            Page<?> page = userService.select(userDto);
            return Result.success(page);
        }
//        if (jsonObject.getString("UUID")!=null){
//            Page<?> page=userService.selectByUUIDByAuth(jsonObject);
//            return Result.success(page);
//        }else{
//            Page<?> page = userService.select(jsonObject);
//            return Result.success(page);
//        }
    }

    /**
     * 增加用户
     * @param jsonObject
     * @param auth
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Transactional
    @PostMapping("addUser")
    public Result<?> addUser(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth) throws NoSuchAlgorithmException {
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        Integer authKey = jsonObject.getInteger("auth");
        User user=new User();
        if (authKey==4){
            Student student=new Student();
//            User user=new User();
            student.setClassnum(jsonObject.getString("class"));
            student.setMajor(jsonObject.getString("major"));
            String uuid = getRandomUUID("2020", 8);
            student.setUuid(uuid);
            user.setUuid(uuid);
//            user.setAuth(authKey);
//            user.setUsername(jsonObject);
            studentService.save(student);
//            return Result.success();
        }else {
            Teacher teacher=new Teacher();
            teacher.setTitle(jsonObject.getString("title"));
            teacher.setRole(jsonObject.getString("role"));
            String randomUUID = getRandomUUID("3000", 4);
            teacher.setUuid(randomUUID);
            user.setUuid(randomUUID);
            teacherService.save(teacher);
        }
        user.setAuth(jsonObject.getInteger("auth"));
        user.setUsername(jsonObject.getString("username"));
        user.setPassword(getMD5("scau"));
        userService.save(user);
        return Result.success();
    }

    /**
     * 获取随机UUID
     * @param date
     * @param n
     * @return
     */
    public String getRandomUUID(String date, int n){
        Random random = new Random();
//        String date = "2020";

        String last8;
        do {
            last8 = String.valueOf(random.nextInt(100000000));
        } while (last8.length() != n);

        String str = date + last8;
//        System.out.println(str);
        return str;
    }

    public String getMD5(String password) throws NoSuchAlgorithmException {
//        String password = "123456";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] passBytes = password.getBytes();
        byte[] digest = md5.digest(passBytes);
        String passMD5 = new BigInteger(1, digest).toString(16);
        return passMD5;
    }

    /**
     * 删除用户
     * @param deleteList
     * @param auth
     * @return
     * @date 2023-05-20 21:08
     */
    @Transactional
    @PostMapping("deleteUser")
    public Result<?> deleteUser(@RequestBody DeleteList deleteList, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<Student>().in("uuid", deleteList.getUuid());
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<Teacher>().in("uuid", deleteList.getUuid());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().in("uuid", deleteList.getUuid());
        userService.remove(userQueryWrapper);
        studentService.remove(studentQueryWrapper);
        teacherService.remove(teacherQueryWrapper);
        return Result.success();
    }

    /**
     * 修改数据
     * @param jsonObject
     * @param auth
     * @return
     * @date 2023-05-20 22:00
     */
    @Transactional
    @PostMapping("updateUser")
    public Result<?> updateUser(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth){
        /**
         * 3xxx: uuid,username,title,role,auth
         */

        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        String uuid = jsonObject.getString("uuid");
        int length = uuid.length();
        User user = userService.getOne(new QueryWrapper<User>().eq("uuid", uuid));
        if (length==8){
            Teacher teacher = teacherService.getOne(new QueryWrapper<Teacher>().eq("uuid", uuid));
            if (jsonObject.getString("title") != null) {
                teacher.setTitle(jsonObject.getString("title"));
            }
            if (jsonObject.getString("role") != null) {
                teacher.setRole(jsonObject.getString("role"));
            }
            teacherService.update(teacher,new QueryWrapper<Teacher>().eq("uuid", uuid));
        }else {
            Student student = studentService.getOne(new QueryWrapper<Student>().eq("uuid", uuid));
            if (jsonObject.getString("major") != null) {
                student.setMajor(jsonObject.getString("major"));
            }
            if (jsonObject.getString("class") != null) {
                student.setClassnum(jsonObject.getString("class"));
            }
            studentService.update(student,new QueryWrapper<Student>().eq("uuid",uuid));
        }
        if (jsonObject.getString("username") != null) {
            user.setUsername(jsonObject.getString("username"));
        }
        userService.update(user,new QueryWrapper<User>().eq("uuid", uuid));
        return Result.success();
    }

    /**
     * 重置密码
     * @param jsonObject
     * @param auth
     * @return
     * @throws NoSuchAlgorithmException
     * @date 2023-05-20 22:12
     */
    @PostMapping("resetPass")
    public Result<?> resetPass(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth) throws NoSuchAlgorithmException {
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        String uuid = jsonObject.getString("uuid");
        User user = userService.getOne(new QueryWrapper<User>().eq("uuid", uuid));
        String md5 = getMD5("scau");
        user.setPassword(md5);
        userService.update(user,new QueryWrapper<User>().eq("uuid", uuid));
        return Result.success();
    }

//    @PostMapping("importUsers")
//    public Result<?> importUsers(@RequestParam("users") MultipartFile file, @RequestParam("auth") Integer authname,@RequestAttribute Integer auth){
//        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
//
//    }
}
