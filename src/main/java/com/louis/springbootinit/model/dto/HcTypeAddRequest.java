package com.louis.springbootinit.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/9 19:46
 */
@Data
public class HcTypeAddRequest implements Serializable {

    private static final long serialVersionUID = 5369265176298479023L;
    private String hc_name;
    private String hc_enname;
    private Integer hc_spec;
    private String hc_unit;
    private Integer hctype_limit;
    private String hc_formula;
    private String cas;
    private String profile;

}
