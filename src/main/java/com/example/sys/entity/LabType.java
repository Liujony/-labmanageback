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
public class LabType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LabType{" +
            "type=" + type +
        "}";
    }
}
