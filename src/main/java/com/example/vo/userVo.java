package com.example.vo;

import lombok.Data;

/**
 * @Author: ZC0
 * @Date: 2023/05/11/20:51
 * @Description: 代码完成
 */
@Data
public class userVo<T> {
    private int page;
    private int totalPage;
    private T userlist;
}
