package com.louis.springbootinit.model.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 已登录用户视图（脱敏）
 *
 * @author louis
 * @from 
 **/
@Data
public class LoginUserVO implements Serializable {

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
     * 所属实验室名
     */
    private String lab_name;

    /**
     * 电话号码
     */
    private String user_tel;



    private static final long serialVersionUID = 1L;
}