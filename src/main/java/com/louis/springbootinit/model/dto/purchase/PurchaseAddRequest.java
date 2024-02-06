package com.louis.springbootinit.model.dto.purchase;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/26 20:06
 */
@Data
public class PurchaseAddRequest implements Serializable {

    private static final long serialVersionUID = -5444344182851159291L;
    /**
     * 采购危化品类型列表
     */
    private List<HctypeRecord> hctypeRecords;

    /**
     * 附件（采购证明材料）
     */
    private String file;
}
