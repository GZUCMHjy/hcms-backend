package com.louis.springbootinit.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/23 18:16
 */
@Data
public class AndroidCode implements Serializable {

    private static final long serialVersionUID = -8536638596271440063L;
    private String destDevice;
    private String account_id;
    private String originDevice;
    private String code_id;
    private String status;
}
