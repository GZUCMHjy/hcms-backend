package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/10 20:15
 */
@Data
public class UserInfoVO implements Serializable {

    /**
     * 普通用户姓名
     */
    private String user_name;

    /**
     * 普通用户电话
     */
    private String user_tel;

    /**
     * 普通用户性别
     */
    private String user_gender;

    /**
     * 普通用户管理仓库id,外键
     */
    private Integer lab_id;

    /**
     * 普通用户职位
     */
    private String user_position;

    /**
     * 普通用户工作单位
     */
    private String user_institution;


    private static final long serialVersionUID = 1L;
}
