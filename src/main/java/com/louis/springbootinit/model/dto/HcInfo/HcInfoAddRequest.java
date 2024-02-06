package com.louis.springbootinit.model.dto.HcInfo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 22:05
 */
@Data
public class HcInfoAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    // 录入试剂的种类、毛重、体积、生产日期、入库日期、申购人及其联系方式、储存地点等信息

    /**
     * 危化品的物理状态(固体,液体,气体)
     */
    private String states;

    /**
     * 危化品的名称
     */
    private String hc_name;

    /**
     * 危化品的生产日期
     */
    private Date producationdate;

    /**
     * 申购人姓名
     */
    private String purchase_user;

    /**
     * 采购id（前端需要输入）
     */
    private Integer pur_id;

    /**
     * 存储仓库名
     */
    private String wh_name;

}
