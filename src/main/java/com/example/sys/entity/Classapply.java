package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@Data
public class Classapply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer ID;

    private String teacheruuid;

    private String course;

    private String semester;

    private String labtype;

    private String major;

    private String classnum;

    private Integer stunum;

    private Integer startweek;

    private Integer endweek;

    private Integer day;

    private String section;

    private String status;

    private Integer labid;

}
