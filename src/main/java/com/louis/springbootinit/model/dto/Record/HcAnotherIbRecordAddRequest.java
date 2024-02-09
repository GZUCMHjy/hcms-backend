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
public class HcAnotherIbRecordAddRequest implements Serializable {



    private static final long serialVersionUID = 1L;

    // 入库编号
    private Integer ib_id;

    // 危化品编号
    private Integer hc_id;

    // 余量
    private Integer hc_remnant;

}
