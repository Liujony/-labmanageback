package com.example.sys.mapper;

import com.example.sys.entity.Keyvalue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Mapper
public interface KeyvalueMapper extends BaseMapper<Keyvalue> {

    Keyvalue selectByKey(String currentSemester);

    void updateBysemester(String semester);
}
