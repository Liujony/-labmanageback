package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wow
 * @since 2023-05-15
 */
@Data
@TableName("Repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String reason;

    private LocalDate applydate;

    private LocalDate finishdate;

    private String status;

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
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public LocalDate getApplydate() {
        return applydate;
    }

    public void setApplydate(LocalDate applydate) {
        this.applydate = applydate;
    }
    public LocalDate getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(LocalDate finishdate) {
        this.finishdate = finishdate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getTesteruuid() {
        return testeruuid;
    }

    public void setTesteruuid(String testeruuid) {
        this.testeruuid = testeruuid;
    }

    @Override
    public String toString() {
        return "Repair{" +
            "id=" + id +
            ", name=" + name +
            ", reason=" + reason +
            ", applydate=" + applydate +
            ", finishdate=" + finishdate +
            ", status=" + status +
            ", testeruuid=" + testeruuid +
        "}";
    }
}
