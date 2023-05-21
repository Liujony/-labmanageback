package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.dto.SemsterDto;
import com.example.sys.entity.Keyvalue;
import com.example.sys.entity.Semesters;
import com.example.sys.service.IKeyvalueService;
import com.example.sys.service.ISemestersService;
import com.example.util.RedisConstant;
import com.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */

/**
 * 学期管理
 */
@RestController
@RequestMapping("/semester")
public class SemestersController {

    @Autowired
    private IKeyvalueService keyvalueService;

    @Autowired
    private ISemestersService semestersService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取当前学期
     * @return
     */
    @GetMapping("getCurrentSemester")
    public Result<Map> getCurrentSemster(@RequestAttribute Integer auth){
//        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        Keyvalue keyvalue=keyvalueService.getCurrentSemster();
        Map<String,String> map=new HashMap<>();
        map.put("semester",keyvalue.getKeyvalue());
        return Result.success(map);
    }

    /**
     *  修改当前学期接口
     * @param rq
     * @return
     */
    @PostMapping("setCurrentSemester")
    public Result<?> setCurrentSemster(@RequestBody JSONObject rq,@RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        keyvalueService.setCurrentSemster(String.valueOf(rq.get("semester")));
        return Result.success();
    }

    /**
     * 添加学期
     * @param semesters
     * @return
     */
    @PostMapping("addSemester")
    public Result<?> addSemster(@RequestBody Semesters semesters,@RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        semestersService.addSemster(semesters);
        return Result.success();
    }

    /**
     * 获取所有学期
     * @param auth
     * @return
     */
    @GetMapping("getAllSemester")
    public Result<SemsterDto> getAllSemster(@RequestAttribute Integer auth){
//        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        List<Semesters> list = semestersService.list(new QueryWrapper<Semesters>().orderByDesc("semester"));
        SemsterDto semsterDto=new SemsterDto();
        List<String> list1=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            list1.add(list.get(i).getSemester());
        }
        semsterDto.setSemesters(new ArrayList<>(list1));
//        redisUtil.set(RedisConstant.Semster_CODE,semsterDto,RedisConstant.PAGE_EXPIRE_TIME);
        return Result.success(semsterDto);
    }
}
