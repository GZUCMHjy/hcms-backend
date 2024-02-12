package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/11 22:40
 */
@Data
public class IbBaseRecordVO implements Serializable {

        private static final long serialVersionUID = 9073362876944685895L;
        private Integer ib_id;
        private Integer hctype_id;
        private String hc_name;
        private Integer hc_spec;
        private String hc_unit;
}
