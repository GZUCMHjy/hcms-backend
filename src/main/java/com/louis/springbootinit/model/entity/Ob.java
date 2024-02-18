package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ob
 */
@TableName(value ="ob")
@Data
public class Ob implements Serializable {
    /**
     * 出库记录id,主键
     */
    @TableId
    private Integer ob_id;

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
     * 出库时间
     */
    private Date ob_time;

    /**
     * 出库数量(出了多少瓶,多少个最小单位)
     */
    private Integer ob_nums;

    /**
     * 出库总量(出了多少总量,500ml*3+200ml)
     */
    private String ob_quantity;

    /**
     * 仓库余量(剩余多少总量,500ml*3+200ml)
     */
    private String wh_remnant;

    /**
     * 出库目的(水仙花实验)
     */
    private String ob_content;

    /**
     * 是否用完,只用与用户从总库领取危化品的查询
     */
    private Integer ranout;

    /**
     * 用途,枚举类型:教学实验,期末归库,调配使用
     */
    private String ob_purpose;

    /**
     * 出库申请id，可为空
     */
    private Integer apply_id;

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
        Ob other = (Ob) that;
        return (this.getOb_id() == null ? other.getOb_id() == null : this.getOb_id().equals(other.getOb_id()))
            && (this.getWhstart_id() == null ? other.getWhstart_id() == null : this.getWhstart_id().equals(other.getWhstart_id()))
            && (this.getWhend_id() == null ? other.getWhend_id() == null : this.getWhend_id().equals(other.getWhend_id()))
            && (this.getTeacher_id() == null ? other.getTeacher_id() == null : this.getTeacher_id().equals(other.getTeacher_id()))
            && (this.getAdmina_id() == null ? other.getAdmina_id() == null : this.getAdmina_id().equals(other.getAdmina_id()))
            && (this.getAdminb_id() == null ? other.getAdminb_id() == null : this.getAdminb_id().equals(other.getAdminb_id()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getHctype_id() == null ? other.getHctype_id() == null : this.getHctype_id().equals(other.getHctype_id()))
            && (this.getOb_time() == null ? other.getOb_time() == null : this.getOb_time().equals(other.getOb_time()))
            && (this.getOb_nums() == null ? other.getOb_nums() == null : this.getOb_nums().equals(other.getOb_nums()))
            && (this.getOb_quantity() == null ? other.getOb_quantity() == null : this.getOb_quantity().equals(other.getOb_quantity()))
            && (this.getWh_remnant() == null ? other.getWh_remnant() == null : this.getWh_remnant().equals(other.getWh_remnant()))
            && (this.getOb_content() == null ? other.getOb_content() == null : this.getOb_content().equals(other.getOb_content()))
            && (this.getRanout() == null ? other.getRanout() == null : this.getRanout().equals(other.getRanout()))
            && (this.getOb_purpose() == null ? other.getOb_purpose() == null : this.getOb_purpose().equals(other.getOb_purpose()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOb_id() == null) ? 0 : getOb_id().hashCode());
        result = prime * result + ((getWhstart_id() == null) ? 0 : getWhstart_id().hashCode());
        result = prime * result + ((getWhend_id() == null) ? 0 : getWhend_id().hashCode());
        result = prime * result + ((getTeacher_id() == null) ? 0 : getTeacher_id().hashCode());
        result = prime * result + ((getAdmina_id() == null) ? 0 : getAdmina_id().hashCode());
        result = prime * result + ((getAdminb_id() == null) ? 0 : getAdminb_id().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getHctype_id() == null) ? 0 : getHctype_id().hashCode());
        result = prime * result + ((getOb_time() == null) ? 0 : getOb_time().hashCode());
        result = prime * result + ((getOb_nums() == null) ? 0 : getOb_nums().hashCode());
        result = prime * result + ((getOb_quantity() == null) ? 0 : getOb_quantity().hashCode());
        result = prime * result + ((getWh_remnant() == null) ? 0 : getWh_remnant().hashCode());
        result = prime * result + ((getOb_content() == null) ? 0 : getOb_content().hashCode());
        result = prime * result + ((getRanout() == null) ? 0 : getRanout().hashCode());
        result = prime * result + ((getOb_purpose() == null) ? 0 : getOb_purpose().hashCode());
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
        sb.append(", ob_id=").append(ob_id);
        sb.append(", whstart_id=").append(whstart_id);
        sb.append(", whend_id=").append(whend_id);
        sb.append(", teacher_id=").append(teacher_id);
        sb.append(", admina_id=").append(admina_id);
        sb.append(", adminb_id=").append(adminb_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", hctype_id=").append(hctype_id);
        sb.append(", ob_time=").append(ob_time);
        sb.append(", ob_nums=").append(ob_nums);
        sb.append(", ob_quantity=").append(ob_quantity);
        sb.append(", wh_remnant=").append(wh_remnant);
        sb.append(", ob_content=").append(ob_content);
        sb.append(", ranout=").append(ranout);
        sb.append(", ob_purpose=").append(ob_purpose);
        sb.append(", createTime=").append(createTime);
        sb.append(", updataTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}