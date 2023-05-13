package com.example.sys.service.impl;

import com.example.dto.examineTApplyDto;
import com.example.sys.entity.Classapply;
import com.example.sys.mapper.ClassapplyMapper;
import com.example.sys.service.IClassapplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ClassapplyServiceImpl extends ServiceImpl<ClassapplyMapper, Classapply> implements IClassapplyService {

    @Autowired
    private ClassapplyMapper classapplyMapper;

    @Override
    public void updateById(examineTApplyDto examineTApplyDto) {
        Classapply byId = classapplyMapper.selectById(2);
        log.info(String.valueOf(byId));
        Classapply classapply = classapplyMapper.selectById(examineTApplyDto.getID());
        classapply.setStatus(examineTApplyDto.getStatus());
        if (examineTApplyDto.getStatus().equals("通过"))
            classapply.setLabid(examineTApplyDto.getLab());
        classapplyMapper.updateById(classapply);
    }
}
