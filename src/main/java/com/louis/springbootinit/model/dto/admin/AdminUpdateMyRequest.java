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
     * 用户性别
     */
    private String admin_gender;

    /**
     * 用户工作单位
     */
    private String admin_institution;

    /**
     * 用户姓名
     */
    private String admin_name;

    /**
     * 用户职位
     */
    private String admin_position;

    /**
     * 电话号码
     */
    private String admin_tel;
    /**
     * 仓库名编号
     */
    private Integer wh_id;


    private static final long serialVersionUID = 1L;
}
