package com.louis.springbootinit.model.dto.Record;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 14:55
 * @description 入库基本信息填写类
 */
@Data
public class HcIbRecordAddRequest implements Serializable {



    private static final long serialVersionUID = -8458538858940807480L;

    // 入库编号
    private Integer ib_id;

    // 危化品类型id
    private Integer hctype_id;

    // 采购编号
    private Integer pur_id;

    // 物理状态（固体、液体、气体）
    private String states;

    // 余量
    private Integer hc_remnant;

    // 单价
    private BigDecimal price;

    // 生产日期
    private Date producationdate;

    // 保质期
    private Integer shelflife;

    // 生产商
    private String hc_productor;

    // 批量入库数量
    private Integer count;

}
