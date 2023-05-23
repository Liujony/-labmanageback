package com.example.sys.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BizException;
import com.example.common.ExceptionEnum;
import com.example.common.Result;
import com.example.dto.examineTApplyDto;
import com.example.sys.entity.*;
import com.example.sys.mapper.ClassapplyMapper;
import com.example.sys.mapper.LabapplyMapper;
import com.example.sys.mapper.StuApplyLabMapper;
import com.example.sys.mapper.TeacherApplyLabMapper;
import com.example.sys.service.IClassapplyService;
import com.example.sys.service.ILabapplyService;
import com.example.vo.pageVo;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
@Slf4j
@RequestMapping("apply")
public class ClassapplyController {

    @Autowired
    private IClassapplyService classapplyService;

    @Autowired
    private TeacherApplyLabMapper teacherApplyLabMapper;

    @Autowired
    private StuApplyLabMapper stuApplyLabMapper;
    @Autowired
    private ILabapplyService labapplyService;

    @Autowired
    private ClassapplyMapper classapplyMapper;

    @Autowired
    private LabapplyMapper labapplyMapper;

    /**
     * 获取申请列表(教师借用)
     * @param pageVo
     * @param auth
     * @return
     */
    @PostMapping("getTApply")
    public Result<Page<TeacherApplyLab>> getApply(@RequestBody pageVo pageVo, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        if (pageVo.getPage()==0) pageVo.setPage(1);
        if (pageVo.getNum()==0) pageVo.setNum(20);
        Page<TeacherApplyLab> page=new Page<TeacherApplyLab>(pageVo.getPage(),pageVo.getNum());
        Page<TeacherApplyLab> labPage = teacherApplyLabMapper.selectPage(page, null);
        return Result.success(labPage);
    }

    /**
     * 获取当前符合条件的实验室
     * @param examineTApplyDto
     * @param auth
     * @return
     */
    @PostMapping("examineTApply")
    public Result<?> examineTApply(@RequestBody examineTApplyDto examineTApplyDto, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        log.info(String.valueOf(examineTApplyDto.getID()));
        classapplyService.updateById(examineTApplyDto);
        return Result.success();
    }

    /**
     * 获取申请列表(学生借用)
     * @param pageVo
     * @param auth
     * @return
     */
    @PostMapping("getSApply")
    public Result<?> getSApply(@RequestBody pageVo pageVo, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        if (pageVo.getPage()==0) pageVo.setPage(1);
        if (pageVo.getNum()==0) pageVo.setNum(20);
        Page<StuApplyLab> page=new Page<StuApplyLab>(pageVo.getPage(),pageVo.getNum());
        Page<StuApplyLab> labPage = stuApplyLabMapper.selectPage(page, null);
        return Result.success(labPage);
    }

    /**
     * 审批并发放实验室(学生借用)
     * @param examineTApplyDto
     * @param auth
     * @return
     */
    @PostMapping("examineSApply")
    public Result<?> examineSApply(@RequestBody examineTApplyDto examineTApplyDto, @RequestAttribute Integer auth){
        if (auth!=1) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        log.info(String.valueOf(examineTApplyDto.getID()));
        labapplyService.updateById(examineTApplyDto);
        return Result.success();
    }

    /**
     * 获取当前老师所申请的实验室
     * @param pageVo
     * @param auth
     * @param UUID
     * @return
     */
    @PostMapping("getClassApply")
    public Result<?> getClassApply(@RequestBody pageVo pageVo,@RequestAttribute Integer auth,@RequestAttribute String UUID){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        if (pageVo.getPage()==0) pageVo.setPage(1);
        if (pageVo.getNum()==0) pageVo.setNum(20);
        QueryWrapper<Classapply> wrapper = new QueryWrapper<Classapply>().eq("teacheruuid", UUID);
        Page<Classapply> page=new Page<>(pageVo.getPage(),pageVo.getNum());
        Page<Classapply> selectPage = classapplyMapper.selectPage(page, wrapper);
        return Result.success(selectPage);
    }

    /**
     * 申请授课实验室(老师)
     * @param classapply
     * @param auth
     * @param UUID
     * @return
     */
    @PostMapping("applyClassLab")
    public Result<?> applyClassLab(@RequestBody Classapply classapply,@RequestAttribute Integer auth,@RequestAttribute String UUID){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        log.info(String.valueOf(classapplyService.count()));
        classapply.setTeacheruuid(UUID);
        classapply.setStatus("审核中");
        classapply.setLabid(null);
        classapplyService.save(classapply);
        return Result.success();
    }

    /**
     * 修改实验室申请单内容
     * @param classapply
     * @param auth
     * @param UUID
     * @return
     */
    @PostMapping("updateClassApply")
    public Result<?> updateClassApply(@RequestBody Classapply classapply,@RequestAttribute Integer auth,@RequestAttribute String UUID){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        classapply.setTeacheruuid(UUID);
        classapply.setStatus("审核中");
        classapplyService.updateById(classapply);
        return Result.success();
    }

    /**
     * 删除申请
     * @param
     * @param auth
     * @return
     */
    @PostMapping("deleteClassApply")
    public Result<?> deleteClassApply(@RequestBody JSONObject eq, @RequestAttribute Integer auth){
        if (auth!=3) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        boolean flag = classapplyService.removeById((Serializable) eq.get("id"));
        return Result.success();
    }

    /**
     * 获取当前学生所申请的实验室
     * @param pageVo
     * @param auth
     * @param UUID
     * @return
     * @date 2023-05-16 21:46
     */
    @PostMapping("getStuLabApply")
    public Result<?> getStuLabApply(@RequestBody pageVo pageVo, @RequestAttribute Integer auth, @RequestAttribute String UUID){
        if (auth!=4) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        if (pageVo.getPage()==0) pageVo.setPage(1);
        if (pageVo.getNum()==0) pageVo.setNum(20);
        Page<StuApplyLab> stuApplyLabPage=new Page<>(pageVo.getPage(),pageVo.getNum());
        Page<StuApplyLab> page = stuApplyLabMapper.selectPage(stuApplyLabPage, new QueryWrapper<StuApplyLab>().eq("stuuuid", UUID));
        return Result.success(page);
    }

    /**
     * 申请使用实验室
     * @param labapply
     * @param auth
     * @param UUID
     * @return
     * @date 2023-05-19 22:30
     */
    @PostMapping("applyStuLab")
    public Result<?> applyStuLab(@RequestBody Labapply labapply, @RequestAttribute Integer auth, @RequestAttribute String UUID){
        if (auth!=4) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        labapply.setStatus("审核中");
        labapply.setStuuuid(UUID);
        labapply.setLabid(null);
        labapplyService.save(labapply);
        return Result.success();
    }

    /**
     * 修改实验室申请单内容
     * @param labapply
     * @param auth
     * @param UUID
     * @return
     * @date 2023-05-19 22:47
     */
    @PostMapping("updateStuLabApply")
    public Result<?> updateStuLabApply(@RequestBody Labapply labapply, @RequestAttribute Integer auth, @RequestAttribute String UUID){
        if (auth!=4) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        QueryWrapper<Labapply> wrapper = new QueryWrapper<Labapply>().eq("status", "审核中").eq("id", labapply.getId());
        boolean update = labapplyService.update(labapply, wrapper);
        return update?Result.success():Result.error("500","修改失败");
    }

    /**
     * 删除申请
     * @param jsonObject
     * @param auth
     * @param UUID
     * @return
     */
    @PostMapping("deleteStuLabApply")
    public Result<?> deleteStuLabApply(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth, @RequestAttribute String UUID){
        if (auth!=4) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        labapplyService.removeById(jsonObject.getInteger("id"));
        return Result.success();
    }

    /**
     * 更新实验室使用状态
     * @param jsonObject
     * @param auth
     * @param UUID
     * @return
     * @date 2023-05-20 15:24
     */
    @PostMapping("updateStuLabUseStatus")
    public Result<?> updateStuLabUseStatus(@RequestBody JSONObject jsonObject, @RequestAttribute Integer auth, @RequestAttribute String UUID){
        if (auth!=4) throw new BizException(ExceptionEnum.NO_AUTHORITY_TO_UPDATE);
        QueryWrapper<Labapply> wrapper = new QueryWrapper<Labapply>().eq("status", "审核通过").eq("id",jsonObject.getInteger("id"));
        Labapply labapply = labapplyService.getOne(wrapper);
        if (labapply == null) {
            throw new BizException(ExceptionEnum.BODY_NOT_MATCH);
        }
        labapply.setStatus(jsonObject.getString("status"));
        labapplyMapper.updateById(labapply);
        return Result.success();
    }
}
