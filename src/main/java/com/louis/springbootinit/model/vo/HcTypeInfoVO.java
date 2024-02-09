package com.louis.springbootinit.model.vo;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/9 19:28
 */
@Data
public class HcTypeInfoVO implements Serializable {

    private static final long serialVersionUID = 3635695976172014435L;

    private Integer hctype_id;
    private String hc_name;
    private Integer hc_spec;
    private String hc_unit;
}
