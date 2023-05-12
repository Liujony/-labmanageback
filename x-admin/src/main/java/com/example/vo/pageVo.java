package com.example.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @Author: ZC0
 * @Date: 2023/05/11/22:49
 * @Description: 代码完成
 */
@Data
public class pageVo {
    private int page=1;
    private int num=20;
}
