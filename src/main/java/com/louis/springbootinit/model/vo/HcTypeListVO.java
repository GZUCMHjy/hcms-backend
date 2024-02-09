package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/9 20:10
 */
@Data
public class HcTypeListVO implements Serializable {

    private static final long serialVersionUID = -4328783137197560075L;
    private Integer hctype_id;
    private String hc_name;
    private Integer hc_spec;
    private String hc_unit;
    private String hc_enname;
    private Integer hctype_limit;
    private String hc_formula;
    private String cas;
    private String profile;
}
