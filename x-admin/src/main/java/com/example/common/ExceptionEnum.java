package com.example.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInfoInterface{
    // 数据操作错误定义
    SUCCESS("200", "success"),
    //请求参数错误
    BODY_NOT_MATCH("400","Error: ParamInvalid"),
    //token无效
    TOKEN_INVALID("401","Error: TokenInvalid"),
    //用户名或密码错误
    USER_PASSWORD_WRONG("402","Error: UserOrPasswordWrong"),
    //验证码错误
    CODE_WRONG("403","Error:CodeWrong"),
    //不存在的请求
    NOT_FOUND("404", "Error: InvalidRequest"),
    //没有权限访问
    NO_PERMISSION_ACCESS("405","Error:NoPermissionAccess"),
    //用户已存在
    USER_EXISTED("406","Error:UserExisted"),
    //无权修改
    NO_AUTHORITY_TO_UPDATE("407","Error:NoAuthority"),
    //服务器内部错误
    INTERNAL_SERVER_ERROR("500", "Error:InternalServerError"),
    //服务器繁忙
    SERVER_BUSY("503","Error:ServerBusy");

    //状态码
    private final String resultCode;

    //返回信息
    private final String resultMsg;


    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
