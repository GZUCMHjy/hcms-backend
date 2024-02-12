package com.louis.springbootinit.model.vo;

import com.louis.springbootinit.common.PageRequest;
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
public class IbRecordVO extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1447694672806258659L;
    private Integer ib_id;
    private Integer count;
    private List<HcListVO> list;
}
