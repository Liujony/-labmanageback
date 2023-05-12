package com.example.dto;

import lombok.Data;

/**
 * @Author: ZC0
 * @Date: 2023/05/10/0:12
 * @Description: 代码完成
 */
@Data
public class UserTokenDto {

    private String uuid;
    private String password;
    private Integer auth;

}
