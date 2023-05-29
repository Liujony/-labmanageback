package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wow
 * @since 2023-05-11
 */
@Data
@TableName("StuApplyLab")
public class StuApplyLab implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private Integer week;

    private Integer day;

    private String section;

    private String reason;

    private String labtype;

    private String status;

    private String name;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StuApplyLab{" +
            "id=" + id +
            ", username=" + username +
            ", week=" + week +
            ", day=" + day +
            ", section=" + section +
            ", reason=" + reason +
            ", status=" + status +
            ", name=" + name +
        "}";
    }
}
