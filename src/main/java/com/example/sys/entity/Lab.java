package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Lab implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private Integer cap;

    private String testeruuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }
    public String getTesteruuid() {
        return testeruuid;
    }

    public void setTesteruuid(String testeruuid) {
        this.testeruuid = testeruuid;
    }

    @Override
    public String toString() {
        return "Lab{" +
            "id=" + id +
            ", name=" + name +
            ", type=" + type +
            ", cap=" + cap +
            ", testeruuid=" + testeruuid +
        "}";
    }
}
