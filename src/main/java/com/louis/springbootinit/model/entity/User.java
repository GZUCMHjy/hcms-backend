package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id,主键
     */
    @TableId(type = IdType.AUTO)
    private Integer user_id;

    /**
     * 用户姓名
     */
    private String user_name;

    /**
     * 用户性别
     */
    private String user_gender;

    /**
     * 用户职位
     */
    private String user_position;

    /**
     * 用户工作单位
     */
    private String user_institution;

    /**
     * 所属实验室id,是外键,但是并没有在数据库设计中体现
     */
    private Integer lab_id;

    /**
     * 电话号码
     */
    private String user_tel;

    /**
     * 用户登录账号,一般来说就是手机号
     */
    private String user_acct;

    /**
     * 用户登录密码
     */
    private String user_pwd;

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
        User other = (User) that;
        return (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
                && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
                && (this.getUser_gender() == null ? other.getUser_gender() == null : this.getUser_gender().equals(other.getUser_gender()))
                && (this.getUser_position() == null ? other.getUser_position() == null : this.getUser_position().equals(other.getUser_position()))
                && (this.getUser_institution() == null ? other.getUser_institution() == null : this.getUser_institution().equals(other.getUser_institution()))
                && (this.getLab_id() == null ? other.getLab_id() == null : this.getLab_id().equals(other.getLab_id()))
                && (this.getUser_tel() == null ? other.getUser_tel() == null : this.getUser_tel().equals(other.getUser_tel()))
                && (this.getUser_acct() == null ? other.getUser_acct() == null : this.getUser_acct().equals(other.getUser_acct()))
                && (this.getUser_pwd() == null ? other.getUser_pwd() == null : this.getUser_pwd().equals(other.getUser_pwd()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_gender() == null) ? 0 : getUser_gender().hashCode());
        result = prime * result + ((getUser_position() == null) ? 0 : getUser_position().hashCode());
        result = prime * result + ((getUser_institution() == null) ? 0 : getUser_institution().hashCode());
        result = prime * result + ((getLab_id() == null) ? 0 : getLab_id().hashCode());
        result = prime * result + ((getUser_tel() == null) ? 0 : getUser_tel().hashCode());
        result = prime * result + ((getUser_acct() == null) ? 0 : getUser_acct().hashCode());
        result = prime * result + ((getUser_pwd() == null) ? 0 : getUser_pwd().hashCode());
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
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_gender=").append(user_gender);
        sb.append(", user_position=").append(user_position);
        sb.append(", user_institution=").append(user_institution);
        sb.append(", lab_id=").append(lab_id);
        sb.append(", user_tel=").append(user_tel);
        sb.append(", user_acct=").append(user_acct);
        sb.append(", user_pwd=").append(user_pwd);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}