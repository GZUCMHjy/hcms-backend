package com.louis.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户注册请求体
 *
 * @author louis
 * @from 
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;
    // 用户名
    private String user_name;
    // 用户电话
    private String user_tel;
    // 用户密码
    private String user_pwd;

    // 用户性别
    private String user_gender;
    // 用户工作单位
    private String user_institution;
    // 实验室
    private String wh_name;

}
