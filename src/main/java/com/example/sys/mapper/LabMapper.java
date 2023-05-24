package com.example.sys.mapper;

import com.example.sys.entity.Lab;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Mapper
public interface LabMapper extends BaseMapper<Lab> {

    List<Map<String, Object>> getClassByDay(Map<String, Object> map);

}
