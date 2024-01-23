package com.louis.springbootinit.model.dto.admin;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/23 15:14
 */
@Data
public class AdminLoginRequest implements Serializable {

    private static final long serialVersionUID = 3481950360247234237L;

    private String admin_acct;

    private String admin_pwd;
}
