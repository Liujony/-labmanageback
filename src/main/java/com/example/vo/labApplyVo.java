package com.example.vo;

import com.example.sys.entity.TeacherApplyLab;
import lombok.Data;
import org.apache.tomcat.util.http.parser.TokenList;

import java.util.List;

/**
 * @Author: ZC0
 * @Date: 2023/05/11/21:24
 * @Description: 代码完成
 */
@Data
public class labApplyVo {
    private int page;
    private int totalPage;
    private List<TeacherApplyLab> applylist;
}
