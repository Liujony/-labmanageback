package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.dto.examineTApplyDto;
import com.example.sys.entity.TeacherApplyLab;
import com.example.sys.mapper.TeacherApplyLabMapper;
import com.example.sys.service.ILabapplyService;
import com.example.vo.labApplyVo;
import com.example.vo.pageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/labapply")
public class LabapplyController {

    @Autowired
    private ILabapplyService labapplyService;


}
