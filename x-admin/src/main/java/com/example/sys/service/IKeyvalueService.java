package com.example.sys.service;

import com.example.sys.entity.Keyvalue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public interface IKeyvalueService extends IService<Keyvalue> {

    Keyvalue getCurrentSemster();

    void setCurrentSemster(String semester);
}
