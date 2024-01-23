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
 * @TableName hctype
 */
@TableName(value ="hctype")
@Data
public class Hctype implements Serializable {
    /**
     * 危化品类型id,主键
     */
    @TableId
    private Integer hctype_id;

    /**
     * 危化品名
     */
    private String hc_name;

    /**
     * 危化品规格
     */
    private String hc_spec;

    /**
     * 危化品限制数量
     */
    private Integer hctype_limit;

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

    /**
     * 危化品的单位(ml,g)
     */
    private String hc_unit;

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
        Hctype other = (Hctype) that;
        return (this.getHctype_id() == null ? other.getHctype_id() == null : this.getHctype_id().equals(other.getHctype_id()))
            && (this.getHc_name() == null ? other.getHc_name() == null : this.getHc_name().equals(other.getHc_name()))
            && (this.getHc_spec() == null ? other.getHc_spec() == null : this.getHc_spec().equals(other.getHc_spec()))
            && (this.getHctype_limit() == null ? other.getHctype_limit() == null : this.getHctype_limit().equals(other.getHctype_limit()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getHc_unit() == null ? other.getHc_unit() == null : this.getHc_unit().equals(other.getHc_unit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHctype_id() == null) ? 0 : getHctype_id().hashCode());
        result = prime * result + ((getHc_name() == null) ? 0 : getHc_name().hashCode());
        result = prime * result + ((getHc_spec() == null) ? 0 : getHc_spec().hashCode());
        result = prime * result + ((getHctype_limit() == null) ? 0 : getHctype_limit().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getHc_unit() == null) ? 0 : getHc_unit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hctype_id=").append(hctype_id);
        sb.append(", hc_name=").append(hc_name);
        sb.append(", hc_spec=").append(hc_spec);
        sb.append(", hctype_limit=").append(hctype_limit);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", hc_unit=").append(hc_unit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}