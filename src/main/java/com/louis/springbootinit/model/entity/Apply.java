package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName apply
 */
@TableName(value ="apply")
@Data
public class Apply implements Serializable {
    /**
     * 申请表id,主键
     */
    @TableId
    private Integer apply_id;

    /**
     * 危化品类型申请信息列表(危化品类型id,申请数,核发数)
     */
    private String hctype_list;

    /**
     * 申请人姓名
     */
    private String user_name;

    /**
     * 申请人联系电话
     */
    private String user_tel;

    /**
     * 申请内容
     */
    private String apply_content;

    /**
     * 申请用途(教学实验)
     */
    private String apply_purpost;

    /**
     * 预约发料日期(精确到天,类似用户向平台预约时间)
     */
    private Date resvdate;

    /**
     * 申请类型(1领用 0 调配使用)
     */
    private Integer apply_type;

    /**
     * 批准状态(-1 未通过, 0 批准中  1已同意)
     */
    private String apply_status;

    /**
     * 审评意见
     */
    private String apply_reason;

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
        Apply other = (Apply) that;
        return (this.getApply_id() == null ? other.getApply_id() == null : this.getApply_id().equals(other.getApply_id()))
            && (this.getHctype_list() == null ? other.getHctype_list() == null : this.getHctype_list().equals(other.getHctype_list()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getUser_tel() == null ? other.getUser_tel() == null : this.getUser_tel().equals(other.getUser_tel()))
            && (this.getApply_content() == null ? other.getApply_content() == null : this.getApply_content().equals(other.getApply_content()))
            && (this.getApply_purpost() == null ? other.getApply_purpost() == null : this.getApply_purpost().equals(other.getApply_purpost()))
            && (this.getResvdate() == null ? other.getResvdate() == null : this.getResvdate().equals(other.getResvdate()))
            && (this.getApply_type() == null ? other.getApply_type() == null : this.getApply_type().equals(other.getApply_type()))
            && (this.getApply_status() == null ? other.getApply_status() == null : this.getApply_status().equals(other.getApply_status()))
            && (this.getApply_reason() == null ? other.getApply_reason() == null : this.getApply_reason().equals(other.getApply_reason()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getApply_id() == null) ? 0 : getApply_id().hashCode());
        result = prime * result + ((getHctype_list() == null) ? 0 : getHctype_list().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_tel() == null) ? 0 : getUser_tel().hashCode());
        result = prime * result + ((getApply_content() == null) ? 0 : getApply_content().hashCode());
        result = prime * result + ((getApply_purpost() == null) ? 0 : getApply_purpost().hashCode());
        result = prime * result + ((getResvdate() == null) ? 0 : getResvdate().hashCode());
        result = prime * result + ((getApply_type() == null) ? 0 : getApply_type().hashCode());
        result = prime * result + ((getApply_status() == null) ? 0 : getApply_status().hashCode());
        result = prime * result + ((getApply_reason() == null) ? 0 : getApply_reason().hashCode());
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
        sb.append(", apply_id=").append(apply_id);
        sb.append(", hctype_list=").append(hctype_list);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_tel=").append(user_tel);
        sb.append(", apply_content=").append(apply_content);
        sb.append(", apply_purpost=").append(apply_purpost);
        sb.append(", resvdate=").append(resvdate);
        sb.append(", apply_type=").append(apply_type);
        sb.append(", apply_status=").append(apply_status);
        sb.append(", apply_reason=").append(apply_reason);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}