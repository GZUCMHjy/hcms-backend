package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName hc
 */
@TableName(value ="hc")
@Data
public class Hc implements Serializable {
    /**
     * 危化品id,主键
     */
    @TableId
    private Integer hc_id;

    /**
     * 危化品的物理状态(固体,液体,气体)
     */
    private String states;

    /**
     * 危化品的名称
     */
    private String hc_name;

    /**
     * 危化品的分子式
     */
    private String hc_formula;

    /**
     * 危化品的余量
     */
    private Integer hc_remnant;

    /**
     * 危化品单价
     */
    private BigDecimal price;

    /**
     * 危化品的简介
     */
    private String profile;

    /**
     * 危化品的生产日期
     */
    private Date producationdate;

    /**
     * 危化品的cas编号
     */
    private String cas;

    /**
     * 危化品类型id,外键
     */
    private Integer hctype_id;

    /**
     * 危化品的保质期,以月为单位
     */
    private Integer shelflife;

    /**
     * 采购表id,外键
     */
    private Integer pur_id;

    /**
     * 危化品是否借用,用以判断是否达到最大存储期限(一年)
     */
    private Integer borrowed;

    /**
     * 所在仓库id,外键
     */
    private Integer wh_id;

    /**
     * 危化品所处状态,枚举(在存,运输,使用)
     */
    private String status;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录更新时间
     */
    private Date updateTime;

    private String hc_enname;

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
        Hc other = (Hc) that;
        return (this.getHc_id() == null ? other.getHc_id() == null : this.getHc_id().equals(other.getHc_id()))
            && (this.getStates() == null ? other.getStates() == null : this.getStates().equals(other.getStates()))
            && (this.getHc_name() == null ? other.getHc_name() == null : this.getHc_name().equals(other.getHc_name()))
            && (this.getHc_formula() == null ? other.getHc_formula() == null : this.getHc_formula().equals(other.getHc_formula()))
            && (this.getHc_remnant() == null ? other.getHc_remnant() == null : this.getHc_remnant().equals(other.getHc_remnant()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getProfile() == null ? other.getProfile() == null : this.getProfile().equals(other.getProfile()))
            && (this.getProducationdate() == null ? other.getProducationdate() == null : this.getProducationdate().equals(other.getProducationdate()))
            && (this.getCas() == null ? other.getCas() == null : this.getCas().equals(other.getCas()))
            && (this.getHctype_id() == null ? other.getHctype_id() == null : this.getHctype_id().equals(other.getHctype_id()))
            && (this.getShelflife() == null ? other.getShelflife() == null : this.getShelflife().equals(other.getShelflife()))
            && (this.getPur_id() == null ? other.getPur_id() == null : this.getPur_id().equals(other.getPur_id()))
            && (this.getBorrowed() == null ? other.getBorrowed() == null : this.getBorrowed().equals(other.getBorrowed()))
            && (this.getWh_id() == null ? other.getWh_id() == null : this.getWh_id().equals(other.getWh_id()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getHc_enname() == null ? other.getHc_enname() == null : this.getHc_enname().equals(other.getHc_enname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHc_id() == null) ? 0 : getHc_id().hashCode());
        result = prime * result + ((getStates() == null) ? 0 : getStates().hashCode());
        result = prime * result + ((getHc_name() == null) ? 0 : getHc_name().hashCode());
        result = prime * result + ((getHc_formula() == null) ? 0 : getHc_formula().hashCode());
        result = prime * result + ((getHc_remnant() == null) ? 0 : getHc_remnant().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getProfile() == null) ? 0 : getProfile().hashCode());
        result = prime * result + ((getProducationdate() == null) ? 0 : getProducationdate().hashCode());
        result = prime * result + ((getCas() == null) ? 0 : getCas().hashCode());
        result = prime * result + ((getHctype_id() == null) ? 0 : getHctype_id().hashCode());
        result = prime * result + ((getShelflife() == null) ? 0 : getShelflife().hashCode());
        result = prime * result + ((getPur_id() == null) ? 0 : getPur_id().hashCode());
        result = prime * result + ((getBorrowed() == null) ? 0 : getBorrowed().hashCode());
        result = prime * result + ((getWh_id() == null) ? 0 : getWh_id().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getHc_enname() == null) ? 0 : getHc_enname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hc_id=").append(hc_id);
        sb.append(", states=").append(states);
        sb.append(", hc_name=").append(hc_name);
        sb.append(", hc_formula=").append(hc_formula);
        sb.append(", hc_remnant=").append(hc_remnant);
        sb.append(", price=").append(price);
        sb.append(", profile=").append(profile);
        sb.append(", producationdate=").append(producationdate);
        sb.append(", cas=").append(cas);
        sb.append(", hctypt_id=").append(hctype_id);
        sb.append(", shelflife=").append(shelflife);
        sb.append(", pur_id=").append(pur_id);
        sb.append(", borrowed=").append(borrowed);
        sb.append(", wh_id=").append(wh_id);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", hc_enname=").append(hc_enname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}