package com.example.sys.service.impl;

import com.example.sys.entity.Student;
import com.example.sys.mapper.StudentMapper;
import com.example.sys.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
