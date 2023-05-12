package com.example.dto;

import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

/**
 * @Author: ZC0
 * @Date: 2023/05/11/19:53
 * @Description: 代码完成
 */
@Data
public class SemsterDto {

    private List<String> semesters;

}
