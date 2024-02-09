package com.louis.springbootinit.model.dto.Record;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/9 16:30
 */
@Data
public class QuitOrEndRequest implements Serializable {

    private static final long serialVersionUID = 2625443159882953069L;
    // 0 结束 1 取消
    private Integer end_cancel;
    private Integer ib_id;
}
