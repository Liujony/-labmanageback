package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/lab")
public class LabController {


    @PostMapping("getLabs")
    public Result<?> getLabs(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        return Result.success();
    }

}
