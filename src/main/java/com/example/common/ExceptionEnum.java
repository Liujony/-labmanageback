package com.example.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInfoInterface{

    // 数据操作错误定义
    SUCCESS("200", "success"),
    //请求参数错误
    BODY_NOT_MATCH("400","Error_ParamInvalid"),
    //token无效
    TOKEN_INVALID("401","Error_TokenInvalid"),
    //用户名或密码错误
    USER_PASSWORD_WRONG("402","Error_UserOrPasswordWrong"),
    //验证码错误
    CODE_WRONG("403","Error_CodeWrong"),
    //不存在的请求
    NOT_FOUND("404", "Error_InvalidRequest"),
    //没有权限访问
    NO_PERMISSION_ACCESS("405","Error_NoPermissionAccess"),
    //用户已存在
    USER_EXISTED("406","Error_UserExisted"),
    //无权修改
    NO_AUTHORITY_TO_UPDATE("407","Error_NoAuthority"),
    //服务器内部错误
    INTERNAL_SERVER_ERROR("500", "Error_InternalServerError"),
    //服务器繁忙
    SERVER_BUSY("503","Error_ServerBusy");
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
