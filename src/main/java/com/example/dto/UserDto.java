package com.example.dto;

import lombok.Data;

/**
 * @Author: ZC0
 * @Date: 2023/05/09/23:55
 * @Description: 代码完成
 */
@Data
public class UserDto {

    private Integer auth=2;
    private String uuid;
    private String username;
    private String major;
    private String classnum;
    private Integer page=1;
    private Integer num=20;
}
