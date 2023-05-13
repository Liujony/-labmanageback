package com.example.sys.service.impl;

import com.example.sys.entity.Students;
import com.example.sys.mapper.StudentsMapper;
import com.example.sys.service.IStudentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wow
 * @since 2023-05-11
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements IStudentsService {

}
