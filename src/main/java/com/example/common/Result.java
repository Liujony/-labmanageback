package com.example.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义响应数据
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T>{
    //告诉前端返回数据成功与否
//    private String code;
    //保留数据错误信息
    private String msg;
    //泛型数据  任何类型数据均可以被包含
    private T data;

    public Result(T data){
        this.data=data;
    }

    public static Result<?> success(){
        Result<?> result = new Result<>();
//        result.setCode(ExceptionEnum.SUCCESS.getResultCode());
        result.setMsg(ExceptionEnum.SUCCESS.getResultMsg());
        return result;
    }

    public static <T> Result<T> success(T data){
        return new Result<>(ExceptionEnum.SUCCESS.getResultMsg(), data);
    }

    public static <T> Result<?> successWithMessage(T data, String mes){
        Result<?> result=new Result<>(data);
//        result.setCode(ExceptionEnum.SUCCESS.getResultCode());
        result.setMsg(mes);
        return result;
    }

    public static Result<?> error(BaseErrorInfoInterface baseErrorInfoInterface){
        Result<?> result = new Result<>();
//        result.setCode(baseErrorInfoInterface.getResultCode());
        result.setMsg(baseErrorInfoInterface.getResultMsg());
        return result;
    }

    public static Result<?> error(String msg){
        Result<?> result=new Result<>();
//        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
