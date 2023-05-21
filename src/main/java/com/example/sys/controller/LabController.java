package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.sys.entity.Lab;
import com.example.sys.entity.LabInUse;
import com.example.sys.mapper.LabInUseMapper;
import com.example.sys.mapper.LabMapper;
import com.example.sys.service.ILabInUseService;
import com.example.sys.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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


    @Autowired
    private ILabService labService;

    @Autowired
    private ILabInUseService labInUseService;

    @Autowired
    private LabInUseMapper labInUseMapper;

    @Autowired
    private LabMapper labMapper;

    @PostMapping("getLabs")
    public Result<?> getLabs(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        String semester = jsonObject.getString("semester");
        String labtype = jsonObject.getString("labtype");
        String startweek = jsonObject.getString("startweek");
        String endweek = jsonObject.getString("endweek");
        String section = jsonObject.getString("section");
        String day = jsonObject.getString("day");
        String stunum = jsonObject.getString("stunum");
        QueryWrapper<LabInUse> labInUseQueryWrapper = new QueryWrapper<LabInUse>()
                .eq("semester", semester)
                .eq("labtype", labtype)
                .eq("day", day)
                .eq("section", section);
        List<LabInUse> labInUses = labInUseService.list(labInUseQueryWrapper);
        List<Integer> labIds=new ArrayList<>();
        for (LabInUse labInUse: labInUses) {
            labIds.add(labInUse.getLabid());
        }
        QueryWrapper<Lab> labQueryWrapper = new QueryWrapper<Lab>()
                .eq("type", labtype)
                .gt("cap", stunum)
                .notIn("id", labIds);
        List<Lab> labs = labService.list(labQueryWrapper);
        return Result.success(labs);
    }

}
