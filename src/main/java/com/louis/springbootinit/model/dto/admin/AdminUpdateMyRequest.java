package com.louis.springbootinit.model.dto.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/23 15:14
 */
@Data
public class AdminUpdateMyRequest implements Serializable {
    /**
     * 管理员姓名
     */
    private String admin_name;

    /**
     * 管理员性别
     */
    private String admin_gender;


    /**
     * 管理员工作单位
     */
    private String admin_institution;


    /**
     * 电话号码
     */
    private String admin_tel;


    private static final long serialVersionUID = 1L;
}
