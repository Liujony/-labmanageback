package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.Result;
import com.example.sys.entity.Lab;
import com.example.sys.mapper.LabMapper;
import com.example.sys.service.ILabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author wow
 * @since 2023-05-11
 */
@RestController
@RequestMapping("common")
@Slf4j
public class TeachersController {


    @Autowired
    private ILabService labService;

    @Autowired
    private SemestersController semestersController;

    @Autowired
    private LabMapper labMapper;

    @PostMapping("getClassByDay")
    public Result<?> getClassByDay(@RequestBody JSONObject jsonObject){
        List<Lab> labList = labService.list();
        Integer week = jsonObject.getInteger("day");
        List<List<Map<String, Object>>> list=new ArrayList<>();
        for (Lab lab : labList) {
            Map<String, Object> map = new HashMap<>();
            map.put("roomid", lab.getRoomid());
            map.put("weekday", week);
            String semester = (String) semestersController.getCurrentSemster().getData().get("semester");
            map.put("semester", semester);
//            log.info(semester);
            List<Map<String, Object>> classByDay = labMapper.getClassByDay(map);
//            log.info(String.valueOf(classByDay));
            list.add(classByDay);
        }
//        log.info(String.valueOf(list));
        return Result.success(list);
    }

}
