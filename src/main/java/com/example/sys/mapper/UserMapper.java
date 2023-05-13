package com.example.sys.mapper;

import com.example.sys.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

}
