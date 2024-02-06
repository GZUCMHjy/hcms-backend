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

    Integer status;


    private static final long serialVersionUID = 1L;
}