package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 14:54
 */
@Data
public class IbRecordVO implements Serializable {

    private static final long serialVersionUID = 1447694672806258659L;
    /**
     * 起始仓库名
     */
    private String whstart_name;

    /**
     * 目标仓库名
     */
    private String whend_name;

    /**
     * 陪同老师
     */
    private String teacher_name;
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
     * 入库目的
     */
    private String ib_content;
    /**
     * 入库用途
     */
    private String ib_purpose;
}
