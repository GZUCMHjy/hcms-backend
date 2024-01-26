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
     * 用户姓名
     */
    private String admin_name;

    /**
     * 电话号码
     */
    private String admin_tel;

    /**
     * 用户密码
     */
    private String admin_pwd;

    /**
     * 用户性别
     */
    private String admin_gender;

    /**
     * 仓库名
     */
    private String warehouse_name;

    /**
     * 用户职位
     */
    private String admin_position;

    /**
     * 用户工作单位
     */
    private String admin_institution;


    private static final long serialVersionUID = 1L;
}
