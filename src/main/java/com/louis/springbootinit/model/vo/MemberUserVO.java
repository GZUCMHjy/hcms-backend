package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/6 15:08
 */
@Data
public class MemberUserVO implements Serializable {
    private static final long serialVersionUID = -6474561428100045273L;
    private String user_name;
    private String user_tel;
}
