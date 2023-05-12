package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
@TableName("Classapply")
public class Classapply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTeacheruuid() {
        return teacheruuid;
    }

    public void setTeacheruuid(String teacheruuid) {
        this.teacheruuid = teacheruuid;
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getLabtype() {
        return labtype;
    }

    public void setLabtype(String labtype) {
        this.labtype = labtype;
    }
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }
    public Integer getStunum() {
        return stunum;
    }

    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }
    public Integer getStartweek() {
        return startweek;
    }

    public void setStartweek(Integer startweek) {
        this.startweek = startweek;
    }
    public Integer getEndweek() {
        return endweek;
    }

    public void setEndweek(Integer endweek) {
        this.endweek = endweek;
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getLabid() {
        return labid;
    }

    public void setLabid(Integer labid) {
        this.labid = labid;
    }

    @Override
    public String toString() {
        return "Classapply{" +
            "id=" + id +
            ", teacheruuid=" + teacheruuid +
            ", course=" + course +
            ", semester=" + semester +
            ", labtype=" + labtype +
            ", major=" + major +
            ", classnum=" + classnum +
            ", stunum=" + stunum +
            ", startweek=" + startweek +
            ", endweek=" + endweek +
            ", day=" + day +
            ", section=" + section +
            ", status=" + status +
            ", labid=" + labid +
        "}";
    }
}
