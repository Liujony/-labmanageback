package com.example.sys.service;

import com.example.sys.entity.Semesters;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public interface ISemestersService extends IService<Semesters> {

    void addSemster(Semesters semester);
}
