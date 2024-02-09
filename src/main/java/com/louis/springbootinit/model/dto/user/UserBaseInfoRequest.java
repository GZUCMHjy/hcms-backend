package com.louis.springbootinit.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 * @author louis
 * @from 
 */
@Data
public class UserBaseInfoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer user_id;

    private String user_name;

    private String user_tel;
}
