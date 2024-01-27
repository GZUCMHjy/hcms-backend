package com.louis.springbootinit.model.dto.purchase;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/26 20:15
 */
@Data
public class HctypeRecord implements Serializable {


    private static final long serialVersionUID = 4545958430666419067L;
    /**
     * 前端传过多余的字段（后端无实际意义）
     */
    private String list_id;
    /**
     * 危化品品名
     */
    private String hctype_name;
    /**
     * 危化品规格
     */
    private String hctype_spec;
    /**
     * 危化品单价
     */
    private BigDecimal price;
    /**
     * 采购数量
     */
    private Integer count;

}
