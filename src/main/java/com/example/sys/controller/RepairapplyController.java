package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.sys.entity.Classapply;
import com.example.sys.entity.Repairapply;
import com.example.sys.mapper.RepairapplyMapper;
import com.example.sys.service.IRepairapplyService;
import com.example.vo.pageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/repair")
public class RepairapplyController {


    @Autowired
    private IRepairapplyService repairapplyService;

    @Autowired
    private RepairapplyMapper repairapplyMapper;

    /**
     * 获取当前老师所申报的实验室报修
     * @param pageVo
     * @param UUID
     * @param auth
     * @return
     */
    @GetMapping("getMyApply")
    public Result<?> getMyApply(@RequestBody pageVo pageVo, @RequestAttribute String UUID, @RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        QueryWrapper<Repairapply> wrapper = new QueryWrapper<Repairapply>().eq("teacheruuid", UUID);
        if (pageVo.getPage()==0) pageVo.setPage(1);
        if (pageVo.getNum()==0) pageVo.setNum(20);
        Page<Repairapply> page=new Page<>(pageVo.getPage(),pageVo.getNum());
        Page<Repairapply> selectPage = repairapplyMapper.selectPage(page, wrapper);
        return Result.success(selectPage);
    }

    /**
     * 申请报修
     * @param repairapply
     * @param UUID
     * @param auth
     * @return
     */
    @PostMapping("applyLabRepair")
    public Result<?> applyLabRepair(@RequestBody Repairapply repairapply,@RequestAttribute String UUID,@RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        repairapply.setTeacheruuid(UUID);
        repairapply.setStatus("维修申报中");
        repairapply.setApplydate(LocalDate.now());
        repairapplyMapper.insert(repairapply);
        return Result.success();
    }

    /**
     * 修改报修申请单内容
     * @param repairapply
     * @param auth
     * @return
     */
    @PostMapping("updateMyApply")
    public Result<?> updateMyApply(@RequestBody Repairapply repairapply,@RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        QueryWrapper<Repairapply> wrapper = new QueryWrapper<Repairapply>().eq("status", "未维修").eq("id",repairapply.getId());
        boolean update = repairapplyService.update(repairapply, wrapper);
        return update ?Result.success():Result.error("修改失败");
    }

    /**
     * 删除申请
     * @param jsonObject
     * @param auth
     * @return
     */
    @PostMapping("deleteMyApply")
    public Result<?> deleteMyApply(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        int id = repairapplyMapper.deleteById((Serializable) jsonObject.get("id"));
        return id==1?Result.success():Result.error("删除失败");
    }

    @GetMapping("getAllLab")
    public Result<?> getAllLab(@RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        return Result.success();
    }
}
