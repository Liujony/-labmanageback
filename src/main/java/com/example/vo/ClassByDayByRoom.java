package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZC0
 * @Date: 2023/05/28/16:14
 * @Description: 代码完成
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassByDayByRoom implements Serializable {
    private String labtype;
    private String roomid;
    private List<Map<String, Object>> classByDay;
}
