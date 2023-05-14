package com.example.sys.service;

import com.example.dto.examineTApplyDto;
import com.example.sys.entity.Classapply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public interface IClassapplyService extends IService<Classapply> {

    void updateById(examineTApplyDto examineTApplyDto);
}
