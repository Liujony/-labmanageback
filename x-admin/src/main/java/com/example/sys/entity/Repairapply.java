package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author wow
 * @since 2023-05-10
 */
public class Repairapply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer labid;

    private String teacheruuid;

    private String reason;

    private LocalDate applydate;

    private LocalDate finishdate;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getLabid() {
        return labid;
    }

    public void setLabid(Integer labid) {
        this.labid = labid;
    }
    public String getTeacheruuid() {
        return teacheruuid;
    }

    public void setTeacheruuid(String teacheruuid) {
        this.teacheruuid = teacheruuid;
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

    @Override
    public String toString() {
        return "Repairapply{" +
            "id=" + id +
            ", labid=" + labid +
            ", teacheruuid=" + teacheruuid +
            ", reason=" + reason +
            ", applydate=" + applydate +
            ", finishdate=" + finishdate +
            ", status=" + status +
        "}";
    }
}
