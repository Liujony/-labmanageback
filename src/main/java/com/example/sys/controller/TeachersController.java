package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.common.Result;
import com.example.sys.entity.Lab;
import com.example.sys.mapper.LabMapper;
import com.example.sys.service.ILabService;
import com.example.vo.ClassByDayByRoom;
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

//    @PostMapping("getClassByDay")
//    public Result<?> getClassByDay(@RequestBody JSONObject jsonObject){
//        List<Lab> labList = labService.list();
//        Integer week = jsonObject.getInteger("day");
//        List<List<Map<String, Object>>> list=new ArrayList<>();
//        for (Lab lab : labList) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("roomid", lab.getRoomid());
//            map.put("weekday", week);
//            String semester = (String) semestersController.getCurrentSemster().getData().get("semester");
//            map.put("semester", semester);
////            log.info(semester);
//            List<Map<String, Object>> classByDay = labMapper.getClassByDay(map);
////            log.info(String.valueOf(classByDay));
//            list.add(classByDay);
//        }
////        log.info(String.valueOf(list));
//        return Result.success(list);
//    }

    @PostMapping("getClassByDay")
    public Result<?> getClassByDay(@RequestBody JSONObject jsonObject){
        // 获取所有的实验室
        List<Lab> labList = labService.list();
        Integer week = jsonObject.getInteger("day");


        // List<List<Map<String, Object>>> list=new ArrayList<>();
        // 上一行行删掉, 修改成下面一行
        List<ClassByDayByRoom> list=new ArrayList<>();


        // 把这行放到上面就不用每次for都去找
        String semester = (String) semestersController.getCurrentSemster().getData().get("semester");

        for (Lab lab : labList) {
            Map<String, Object> map = new HashMap<>();
            map.put("roomid", lab.getRoomid());
            map.put("weekday", week);

            // 删掉这行, 放到上面
            // String semester = (String) semestersController.getCurrentSemster().getData().get("semester");
            map.put("semester", semester);

            // 删掉这行
            // List<Map<String, Object>> classByDay = labMapper.getClassByDay(map);

            ClassByDayByRoom classByDay = new ClassByDayByRoom(lab.getType(),lab.getRoomid(), labMapper.getClassByDay(map));

            list.add(classByDay);
        }
        return Result.success(list);
    }

}
