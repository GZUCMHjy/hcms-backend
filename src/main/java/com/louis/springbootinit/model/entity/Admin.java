package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    /**
     * 管理员id,主键
     */
    @TableId(type = IdType.AUTO)
    private Integer admin_id;

    /**
     * 管理员姓名
     */
    private String admin_name;

    /**
     * 管理员性别
     */
    private String admin_gender;

    /**
     * 管理员职位
     */
    private String admin_position;

    /**
     * 管理员工作单位
     */
    private String admin_institution;

    /**
     * 管理员管理仓库id,外键
     */
    private Integer warehouse_id;

    /**
     * 管理员电话
     */
    private String admin_tel;

    /**
     * 管理员登录账号,一般来说也是手机号
     */
    private String admin_acct;

    /**
     * 管理员登录密码
     */
    private String admin_pwd;

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
        Admin other = (Admin) that;
        return (this.getAdmin_id() == null ? other.getAdmin_id() == null : this.getAdmin_id().equals(other.getAdmin_id()))
            && (this.getAdmin_name() == null ? other.getAdmin_name() == null : this.getAdmin_name().equals(other.getAdmin_name()))
            && (this.getAdmin_gender() == null ? other.getAdmin_gender() == null : this.getAdmin_gender().equals(other.getAdmin_gender()))
            && (this.getAdmin_position() == null ? other.getAdmin_position() == null : this.getAdmin_position().equals(other.getAdmin_position()))
            && (this.getAdmin_institution() == null ? other.getAdmin_institution() == null : this.getAdmin_institution().equals(other.getAdmin_institution()))
            && (this.getWarehouse_id() == null ? other.getWarehouse_id() == null : this.getWarehouse_id().equals(other.getWarehouse_id()))
            && (this.getAdmin_tel() == null ? other.getAdmin_tel() == null : this.getAdmin_tel().equals(other.getAdmin_tel()))
            && (this.getAdmin_acct() == null ? other.getAdmin_acct() == null : this.getAdmin_acct().equals(other.getAdmin_acct()))
            && (this.getAdmin_pwd() == null ? other.getAdmin_pwd() == null : this.getAdmin_pwd().equals(other.getAdmin_pwd()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdmin_id() == null) ? 0 : getAdmin_id().hashCode());
        result = prime * result + ((getAdmin_name() == null) ? 0 : getAdmin_name().hashCode());
        result = prime * result + ((getAdmin_gender() == null) ? 0 : getAdmin_gender().hashCode());
        result = prime * result + ((getAdmin_position() == null) ? 0 : getAdmin_position().hashCode());
        result = prime * result + ((getAdmin_institution() == null) ? 0 : getAdmin_institution().hashCode());
        result = prime * result + ((getWarehouse_id() == null) ? 0 : getWarehouse_id().hashCode());
        result = prime * result + ((getAdmin_tel() == null) ? 0 : getAdmin_tel().hashCode());
        result = prime * result + ((getAdmin_acct() == null) ? 0 : getAdmin_acct().hashCode());
        result = prime * result + ((getAdmin_pwd() == null) ? 0 : getAdmin_pwd().hashCode());
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
        sb.append(", admin_id=").append(admin_id);
        sb.append(", admin_name=").append(admin_name);
        sb.append(", admin_gender=").append(admin_gender);
        sb.append(", admin_position=").append(admin_position);
        sb.append(", admin_institution=").append(admin_institution);
        sb.append(", warehouse_id=").append(warehouse_id);
        sb.append(", admin_tel=").append(admin_tel);
        sb.append(", admin_acct=").append(admin_acct);
        sb.append(", admin_pwd=").append(admin_pwd);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}