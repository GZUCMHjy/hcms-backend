package com.louis.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName qr_code
 */
@TableName(value ="qr_code")
@Data
public class QrCode implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer code_id;

    /**
     * 
     */
    private Integer account_id;

    /**
     * 
     */
    private String originDevice;

    /**
     * 
     */
    private String destDevice;

    private String status;

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
        QrCode other = (QrCode) that;
        return (this.getCode_id() == null ? other.getCode_id() == null : this.getCode_id().equals(other.getCode_id()))
            && (this.getAccount_id() == null ? other.getAccount_id() == null : this.getAccount_id().equals(other.getAccount_id()))
            && (this.getOriginDevice() == null ? other.getOriginDevice() == null : this.getOriginDevice().equals(other.getOriginDevice()))
            && (this.getDestDevice() == null ? other.getDestDevice() == null : this.getDestDevice().equals(other.getDestDevice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode_id() == null) ? 0 : getCode_id().hashCode());
        result = prime * result + ((getAccount_id() == null) ? 0 : getAccount_id().hashCode());
        result = prime * result + ((getOriginDevice() == null) ? 0 : getOriginDevice().hashCode());
        result = prime * result + ((getDestDevice() == null) ? 0 : getDestDevice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code_id=").append(code_id);
        sb.append(", account_id=").append(account_id);
        sb.append(", originDevice=").append(originDevice);
        sb.append(", destDevice=").append(destDevice);
        sb.append(", url=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}