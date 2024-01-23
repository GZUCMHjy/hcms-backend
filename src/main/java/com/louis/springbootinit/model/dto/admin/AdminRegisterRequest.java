package com.louis.springbootinit.model.dto.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/23 15:14
 */
@Data
public class AdminRegisterRequest implements Serializable {

    private static final long serialVersionUID = -2086606016506309064L;

    private String admin_acct;
    private String admin_pwd;
    private String admin_check_pwd;
}
