package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName wh
 */
@TableName(value ="wh")
@Data
public class Wh implements Serializable {
    /**
     * 仓库id,主键
     */
    @TableId
    private Integer wh_id;

    /**
     * 仓库名字
     */
    private String wh_name;

    /**
     * 管理员A的id,外键
     */
    private Integer a_id;

    /**
     * 管理员B的id,外键
     */
    private Integer b_id;

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
        Wh other = (Wh) that;
        return (this.getWh_id() == null ? other.getWh_id() == null : this.getWh_id().equals(other.getWh_id()))
            && (this.getWh_name() == null ? other.getWh_name() == null : this.getWh_name().equals(other.getWh_name()))
            && (this.getA_id() == null ? other.getA_id() == null : this.getA_id().equals(other.getA_id()))
            && (this.getB_id() == null ? other.getB_id() == null : this.getB_id().equals(other.getB_id()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWh_id() == null) ? 0 : getWh_id().hashCode());
        result = prime * result + ((getWh_name() == null) ? 0 : getWh_name().hashCode());
        result = prime * result + ((getA_id() == null) ? 0 : getA_id().hashCode());
        result = prime * result + ((getB_id() == null) ? 0 : getB_id().hashCode());
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
        sb.append(", wh_id=").append(wh_id);
        sb.append(", wh_name=").append(wh_name);
        sb.append(", a_id=").append(a_id);
        sb.append(", b_id=").append(b_id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}