package com.example.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wow
 * @since 2023-05-11
 */
public class StuApplyLab implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String stuuuid;

    private Integer week;

    private Integer day;

    private String section;

    private String reason;

    private String status;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getStuuuid() {
        return stuuuid;
    }

    public void setStuuuid(String stuuuid) {
        this.stuuuid = stuuuid;
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
            ", stuuuid=" + stuuuid +
            ", week=" + week +
            ", day=" + day +
            ", section=" + section +
            ", reason=" + reason +
            ", status=" + status +
            ", name=" + name +
        "}";
    }
}
