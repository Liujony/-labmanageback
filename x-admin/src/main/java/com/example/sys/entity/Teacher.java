package com.example.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String title;

    private String role;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "uuid=" + uuid +
            ", title=" + title +
            ", role=" + role +
        "}";
    }
}
