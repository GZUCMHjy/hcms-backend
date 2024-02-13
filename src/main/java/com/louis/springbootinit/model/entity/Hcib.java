package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName hcib
 */
@TableName(value ="hcib")
@Data
public class Hcib implements Serializable {
    /**
     * 危化品入库记录id 主键
     */
    @TableId(value = "hcib_id",type = IdType.AUTO)
    private Integer hcib_id;

    /**
     * 危化品id,外键
     */
    private Integer hc_id;

    /**
     * 危化品类型id,外键
     */
    private Integer hctype_id;

    /**
     * 入库记录id,外键
     */
    private Integer ib_id;

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
        Hcib other = (Hcib) that;
        return (this.getHcib_id() == null ? other.getHcib_id() == null : this.getHcib_id().equals(other.getHcib_id()))
            && (this.getHc_id() == null ? other.getHc_id() == null : this.getHc_id().equals(other.getHc_id()))
            && (this.getHctype_id() == null ? other.getHctype_id() == null : this.getHctype_id().equals(other.getHctype_id()))
            && (this.getIb_id() == null ? other.getIb_id() == null : this.getIb_id().equals(other.getIb_id()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHcib_id() == null) ? 0 : getHcib_id().hashCode());
        result = prime * result + ((getHc_id() == null) ? 0 : getHc_id().hashCode());
        result = prime * result + ((getHctype_id() == null) ? 0 : getHctype_id().hashCode());
        result = prime * result + ((getIb_id() == null) ? 0 : getIb_id().hashCode());
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
        sb.append(", hcib_id=").append(hcib_id);
        sb.append(", hc_id=").append(hc_id);
        sb.append(", hctype_id=").append(hctype_id);
        sb.append(", ib_id=").append(ib_id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}