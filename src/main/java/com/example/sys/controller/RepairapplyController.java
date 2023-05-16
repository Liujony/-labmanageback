package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.dto.UpdateLabRepairDto;
import com.example.dto.resultLabDto;
import com.example.sys.entity.Lab;
import com.example.sys.entity.Labapply;
import com.example.sys.entity.Repair;
import com.example.sys.entity.Repairapply;
import com.example.sys.mapper.RepairMapper;
import com.example.sys.mapper.RepairapplyMapper;
import com.example.sys.service.ILabService;
import com.example.sys.service.IRepairapplyService;
import com.example.vo.pageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
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
@Slf4j
@RestController
@RequestMapping("/repair")
public class RepairapplyController {


    @Autowired
    private IRepairapplyService repairapplyService;

    @Autowired
    private RepairapplyMapper repairapplyMapper;

    @Autowired
    private ILabService labService;

    @Autowired
    private RepairMapper repairMapper;

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

    /**
     * 获取所有的ID+实验室
     * @param auth
     * @return
     */
    @GetMapping("getAllLab")
    public Result<?> getAllLab(@RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        List<Lab> list = labService.list();
        ArrayList<resultLabDto> labDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            resultLabDto resultLabDto=new resultLabDto();
            resultLabDto.setId(list.get(i).getId());
            resultLabDto.setName(list.get(i).getName());
            labDtos.add(resultLabDto);
        }
        return Result.success(labDtos);
    }

    /**
     * 获取当前实验员所管理的实验室报修
     * @param pageVo
     * @param auth
     * @param UUID
     * @return
     */
    @GetMapping("getLabRepair")
    public Result<?> getLabRepair(@RequestBody pageVo pageVo, @RequestAttribute Integer auth, @RequestAttribute String UUID){
        log.info("test");
        if (auth!=2) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        QueryWrapper<Repair> wrapper = new QueryWrapper<Repair>().eq("testeruuid", UUID);
        if (pageVo.getPage()==0) pageVo.setPage(1);
        if (pageVo.getNum()==0) pageVo.setNum(20);
        Page<Repair> page=new Page<>(pageVo.getPage(),pageVo.getNum());
        Page<Repair> selectPage = repairMapper.selectPage(page, wrapper);
        return Result.success(selectPage);
    }

    /**
     * 修改报修
     * @param updateLabRepairDto
     * @param auth
     * @return
     * @date 2023-05-16 21:34
     */
    @PostMapping("updateLabRepair")
    public Result<?> updateLabRepair(@RequestBody UpdateLabRepairDto updateLabRepairDto, @RequestAttribute Integer auth){
        if (auth!=2) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        Repairapply repair = repairapplyMapper.selectById(updateLabRepairDto.getId());
        log.info(repair.getStatus());
        repair.setStatus(updateLabRepairDto.getStatus());
        log.info(repair.getStatus());
        repairapplyMapper.updateById(repair);
        return Result.success();
    }
}
