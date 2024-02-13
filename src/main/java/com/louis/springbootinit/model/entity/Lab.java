package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName lab
 */
@TableName(value ="lab")
@Data
public class Lab implements Serializable {
    /**
     * 实验室id,主键
     */
    @TableId
    private Integer lab_id;

    /**
     * 实验室名称
     */
    private String lab_name;

    /**
     * 所在仓库id,外键
     */
    private Integer wh_id;

    /**
     * 记录创建时间
     * 自动填充（添加时自动填充创建时间）
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 记录更新时间
     * 只是添加不会生效
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Lab other = (Lab) that;
        return (this.getLab_id() == null ? other.getLab_id() == null : this.getLab_id().equals(other.getLab_id()))
            && (this.getLab_name() == null ? other.getLab_name() == null : this.getLab_name().equals(other.getLab_name()))
            && (this.getWh_id() == null ? other.getWh_id() == null : this.getWh_id().equals(other.getWh_id()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLab_id() == null) ? 0 : getLab_id().hashCode());
        result = prime * result + ((getLab_name() == null) ? 0 : getLab_name().hashCode());
        result = prime * result + ((getWh_id() == null) ? 0 : getWh_id().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lab_id=").append(lab_id);
        sb.append(", lab_name=").append(lab_name);
        sb.append(", wh_id=").append(wh_id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}