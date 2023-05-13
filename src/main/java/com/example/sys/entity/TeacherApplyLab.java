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
@TableName("TeacherApplyLab")
public class TeacherApplyLab implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String course;

    private String username;

    private String name;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "TeacherApplyLab{" +
            "id=" + id +
            ", course=" + course +
            ", username=" + username +
            ", name=" + name +
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
        "}";
    }
}
