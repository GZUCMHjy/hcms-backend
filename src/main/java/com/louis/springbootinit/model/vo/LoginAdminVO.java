package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 已登录管理员视图（脱敏）
 *
 * @author louis
 * @from 
 **/
@Data
public class LoginAdminVO implements Serializable {

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
     * 所属仓库名
     */
    private String wh_name;

    /**
     * 电话号码
     */
    private String admin_tel;



    private static final long serialVersionUID = 1L;
}