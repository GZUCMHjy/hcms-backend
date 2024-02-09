package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/9 17:25
 */
@Data
public class HcListVO implements Serializable {
    private Integer hcib_id;
    private Integer hc_id;
    private String hc_name;
    private String states;
    private Integer hc_spec;
    private String hc_unit;
    private Integer hc_remnant;
    private String wh_name;
    private Integer pur_id;
    private BigDecimal price;
    private Date productiondate;
    private Integer shelflife;
    private Date ib_time;
}
