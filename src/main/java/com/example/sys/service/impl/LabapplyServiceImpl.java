package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.examineTApplyDto;
import com.example.sys.entity.Classapply;
import com.example.sys.entity.Labapply;
import com.example.sys.mapper.ClassapplyMapper;
import com.example.sys.mapper.LabapplyMapper;
import com.example.sys.service.ILabapplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Service
public class LabapplyServiceImpl extends ServiceImpl<LabapplyMapper, Labapply> implements ILabapplyService {

    @Autowired
    private LabapplyMapper labapplyMapper;

    @Override
    public void updateById(examineTApplyDto examineTApplyDto) {
        Labapply classapply = labapplyMapper.selectById(examineTApplyDto.getID());
        classapply.setStatus(examineTApplyDto.getStatus());
        if (examineTApplyDto.getStatus().equals("审核通过"))
            classapply.setLabid(examineTApplyDto.getLabid());
        labapplyMapper.updateById(classapply);
    }
}
