package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName hcob
 */
@TableName(value ="hcob")
@Data
public class Hcob implements Serializable {
    /**
     * 危化品出库记录id,主键
     */
    @TableId
    private Integer hcob_id;

    /**
     * 危化品id,外键
     */
    private Integer hc_id;

    /**
     * 危化品类型id,外键
     */
    private Integer hctype_id;

    /**
     * 出库记录id,外键
     */
    private Integer ob_id;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录更新时间
     */
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
        Hcob other = (Hcob) that;
        return (this.getHcob_id() == null ? other.getHcob_id() == null : this.getHcob_id().equals(other.getHcob_id()))
            && (this.getHc_id() == null ? other.getHc_id() == null : this.getHc_id().equals(other.getHc_id()))
            && (this.getHctype_id() == null ? other.getHctype_id() == null : this.getHctype_id().equals(other.getHctype_id()))
            && (this.getOb_id() == null ? other.getOb_id() == null : this.getOb_id().equals(other.getOb_id()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHcob_id() == null) ? 0 : getHcob_id().hashCode());
        result = prime * result + ((getHc_id() == null) ? 0 : getHc_id().hashCode());
        result = prime * result + ((getHctype_id() == null) ? 0 : getHctype_id().hashCode());
        result = prime * result + ((getOb_id() == null) ? 0 : getOb_id().hashCode());
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
        sb.append(", hcob_id=").append(hcob_id);
        sb.append(", hc_id=").append(hc_id);
        sb.append(", hctype_id=").append(hctype_id);
        sb.append(", ob_id=").append(ob_id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}