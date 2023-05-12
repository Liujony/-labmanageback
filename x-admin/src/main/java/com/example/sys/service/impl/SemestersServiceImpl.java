package com.example.sys.service.impl;

import com.example.sys.entity.Semesters;
import com.example.sys.mapper.SemestersMapper;
import com.example.sys.service.ISemestersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.RedisConstant;
import com.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Service
public class SemestersServiceImpl extends ServiceImpl<SemestersMapper, Semesters> implements ISemestersService {

    @Autowired
    private RedisUtil redisUtil;


    @Transactional
    @Override
    public void addSemster(Semesters semester) {
        save(semester);
        if (redisUtil.hasKey(RedisConstant.Semster_CODE)) {
            redisUtil.del(RedisConstant.Semster_CODE);
        }
    }
}
