package com.louis.springbootinit.model.dto.Record;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 14:55
 * @description 入库基本信息填写类
 */
@Data
public class IbRecordAddRequest implements Serializable {

    private static final long serialVersionUID = -8240292495853299944L;
    /**
     * 起始仓库id
     */
    private Integer whstart_id;

    /**
     * 目标仓库id
     */
    private Integer whend_id;

    /**
     * 入库类型（枚举）
     */
    private String ib_purpose;

    /**
     * 仓库管理员a
     */
    private String admina_name;
    /**
     * 仓库管理员a
     */
    private String adminb_name;

    /**
     * 本人
     */
    private String user_name;

    /**
     * 陪同老师
     */
    private String teacher_name;

}
