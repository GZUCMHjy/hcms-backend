package com.louis.springbootinit.model.dto.HcIb;

import com.louis.springbootinit.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/12 12:10
 */
@Data
public class IbSearchByIbIdRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ib_id;
}
