package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/6 15:08
 */
@Data
public class MemberAdminVO implements Serializable {

    private static final long serialVersionUID = 1554034503362173236L;
    private String admin_name;
    private String admin_tel;
}
