package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import lombok.Data;

/**
 * 
 * @TableName pur
 */
@TableName(value ="pur",autoResultMap = true)
@Data
public class Pur implements Serializable {
    /**
     * 采购表的id,主键
     */
    @TableId(value = "pur_id",type = IdType.AUTO)
    private Integer pur_id;

    /**
     * 用户表的id,外键
     */
    private Integer user_id;

    /**
     * 采购危化品类型列表(危化品类型id,采购数量,采购单价)
     * 以json格式存储
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<HctypeRecord> hctype_list;

    /**
     * 采购总价
     */
    private BigDecimal totalprice;

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
     * 已通过的审批材料
     * 文件url
     */
    private String file;
    @TableField(exist = false)
    private String user_name;
    @TableField(exist = false)
    private String user_tel;

    @TableField(exist = false)
    private Integer count;

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
        Pur other = (Pur) that;
        return (this.getPur_id() == null ? other.getPur_id() == null : this.getPur_id().equals(other.getPur_id()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getHctype_list() == null ? other.getHctype_list() == null : this.getHctype_list().equals(other.getHctype_list()))
            && (this.getTotalprice() == null ? other.getTotalprice() == null : this.getTotalprice().equals(other.getTotalprice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (Arrays.equals(this.getFile().getBytes(), other.getFile().getBytes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPur_id() == null) ? 0 : getPur_id().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getHctype_list() == null) ? 0 : getHctype_list().hashCode());
        result = prime * result + ((getTotalprice() == null) ? 0 : getTotalprice().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + (Arrays.hashCode(getFile().getBytes()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pur_id=").append(pur_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", hcRecord_list=").append(hctype_list);
        sb.append(", totalprice=").append(totalprice);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", file=").append(file);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}