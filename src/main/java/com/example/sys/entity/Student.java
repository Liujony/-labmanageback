package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String uuid;

    private String major;

    private String classnum;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    @Override
    public String toString() {
        return "Student{" +
            "uuid=" + uuid +
            ", major=" + major +
            ", classnum=" + classnum +
        "}";
    }
}
