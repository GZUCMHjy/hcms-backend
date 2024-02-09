package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 14:54
 */
@Data
public class IbRecordVO implements Serializable {

    private static final long serialVersionUID = 1447694672806258659L;
    private Integer ib_id;
    private List<HcListVO> hc_list;
}
