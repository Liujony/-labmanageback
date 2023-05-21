package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wow
 * @since 2023-05-21
 */
@Data
@TableName("LabInUse")
public class LabInUse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer labid;

    private String semester;

    private String labtype;

    private Integer startweek;

    private Integer endweek;

    private Integer day;

    private String section;

    public Integer getLabid() {
        return labid;
    }

    public void setLabid(Integer labid) {
        this.labid = labid;
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

    @Override
    public String toString() {
        return "LabInUse{" +
            "labid=" + labid +
            ", semester=" + semester +
            ", labtype=" + labtype +
            ", startweek=" + startweek +
            ", endweek=" + endweek +
            ", day=" + day +
            ", section=" + section +
        "}";
    }
}
