package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sys.entity.Keyvalue;
import com.example.sys.mapper.KeyvalueMapper;
import com.example.sys.service.IKeyvalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.util.RedisConstant;
import com.example.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class KeyvalueServiceImpl extends ServiceImpl<KeyvalueMapper, Keyvalue> implements IKeyvalueService {

    @Autowired
    private KeyvalueMapper keyvalueMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Keyvalue getCurrentSemster() {
        String key= RedisConstant.Key_CODE+"currentSemester";
        if (redisUtil.hasKey(key)){
            return (Keyvalue) redisUtil.get(key);
        }
        Keyvalue keyvalue = keyvalueMapper.selectById("currentSemester");
        redisUtil.set(key,keyvalue,RedisConstant.PAGE_EXPIRE_TIME);
        return keyvalue;
    }

    @Override
    @Transactional
    public void setCurrentSemster(String semester) {
        keyvalueMapper.updateBysemester(semester);
        if (redisUtil.get(RedisConstant.Key_CODE + "currentSemester") != null) {
            redisUtil.del(RedisConstant.Key_CODE+"currentSemester");
        }
    }
}
