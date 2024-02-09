package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ib
 */
@TableName(value ="ib")
@Data
public class Ib implements Serializable {
    /**
     * 入库记录id,主键
     */
    @TableId(value = "ib_id",type = IdType.AUTO)
    private Integer ib_id;

    /**
     * 起始仓库id,-1表示无,外键
     */
    private Integer whstart_id;

    /**
     * 目的仓库id,-1表示无,外键
     */
    private Integer whend_id;

    /**
     * 指导老师id,外键
     */
    private Integer teacher_id;

    /**
     * 管理员a id,外键
     */
    private Integer admina_id;

    /**
     * 管理员b id,外键
     */
    private Integer adminb_id;

    /**
     * 使用人id,外键
     */
    private Integer user_id;

    /**
     * 危化品类型id,外键
     */
    private Integer hctype_id;

    /**
     * 入库时间
     */
    private Date ib_time;

    /**
     * 入库数量(入了多少瓶,多少个最小单位)
     */
    private Integer ib_nums;

    /**
     * 入库总量(入了多少总量,500ml*3+200ml)
     */
    private String ib_quantity;

    /**
     * 仓库余量(剩余多少总量,500ml*3+200ml)
     */
    private String wh_remnant;

    /**
     * 入库目的(水仙花实验)
     */
    private String ib_content;

    /**
     * 用途,枚举类型:教学实验,期末归库,调配使用,采购入库
     */
    private String ib_purpose;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录更新时间
     */
    private Date updataTime;

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
        Ib other = (Ib) that;
        return (this.getIb_id() == null ? other.getIb_id() == null : this.getIb_id().equals(other.getIb_id()))
            && (this.getWhstart_id() == null ? other.getWhstart_id() == null : this.getWhstart_id().equals(other.getWhstart_id()))
            && (this.getWhend_id() == null ? other.getWhend_id() == null : this.getWhend_id().equals(other.getWhend_id()))
            && (this.getTeacher_id() == null ? other.getTeacher_id() == null : this.getTeacher_id().equals(other.getTeacher_id()))
            && (this.getAdmina_id() == null ? other.getAdmina_id() == null : this.getAdmina_id().equals(other.getAdmina_id()))
            && (this.getAdminb_id() == null ? other.getAdminb_id() == null : this.getAdminb_id().equals(other.getAdminb_id()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getHctype_id() == null ? other.getHctype_id() == null : this.getHctype_id().equals(other.getHctype_id()))
            && (this.getIb_time() == null ? other.getIb_time() == null : this.getIb_time().equals(other.getIb_time()))
            && (this.getIb_nums() == null ? other.getIb_nums() == null : this.getIb_nums().equals(other.getIb_nums()))
            && (this.getIb_quantity() == null ? other.getIb_quantity() == null : this.getIb_quantity().equals(other.getIb_quantity()))
            && (this.getWh_remnant() == null ? other.getWh_remnant() == null : this.getWh_remnant().equals(other.getWh_remnant()))
            && (this.getIb_content() == null ? other.getIb_content() == null : this.getIb_content().equals(other.getIb_content()))
            && (this.getIb_purpose() == null ? other.getIb_purpose() == null : this.getIb_purpose().equals(other.getIb_purpose()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdataTime() == null ? other.getUpdataTime() == null : this.getUpdataTime().equals(other.getUpdataTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIb_id() == null) ? 0 : getIb_id().hashCode());
        result = prime * result + ((getWhstart_id() == null) ? 0 : getWhstart_id().hashCode());
        result = prime * result + ((getWhend_id() == null) ? 0 : getWhend_id().hashCode());
        result = prime * result + ((getTeacher_id() == null) ? 0 : getTeacher_id().hashCode());
        result = prime * result + ((getAdmina_id() == null) ? 0 : getAdmina_id().hashCode());
        result = prime * result + ((getAdminb_id() == null) ? 0 : getAdminb_id().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getHctype_id() == null) ? 0 : getHctype_id().hashCode());
        result = prime * result + ((getIb_time() == null) ? 0 : getIb_time().hashCode());
        result = prime * result + ((getIb_nums() == null) ? 0 : getIb_nums().hashCode());
        result = prime * result + ((getIb_quantity() == null) ? 0 : getIb_quantity().hashCode());
        result = prime * result + ((getWh_remnant() == null) ? 0 : getWh_remnant().hashCode());
        result = prime * result + ((getIb_content() == null) ? 0 : getIb_content().hashCode());
        result = prime * result + ((getIb_purpose() == null) ? 0 : getIb_purpose().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdataTime() == null) ? 0 : getUpdataTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ib_id=").append(ib_id);
        sb.append(", whstart_id=").append(whstart_id);
        sb.append(", whend_id=").append(whend_id);
        sb.append(", teacher_id=").append(teacher_id);
        sb.append(", admina_id=").append(admina_id);
        sb.append(", adminb_id=").append(adminb_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", hctype_id=").append(hctype_id);
        sb.append(", ib_time=").append(ib_time);
        sb.append(", ib_nums=").append(ib_nums);
        sb.append(", ib_quantity=").append(ib_quantity);
        sb.append(", wh_remnant=").append(wh_remnant);
        sb.append(", ib_content=").append(ib_content);
        sb.append(", ib_purpose=").append(ib_purpose);
        sb.append(", createTime=").append(createTime);
        sb.append(", updataTime=").append(updataTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}