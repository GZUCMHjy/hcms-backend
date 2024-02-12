package com.louis.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/12 12:08
 */
@Data
public class HctypeResListVO implements Serializable {

    private static final long serialVersionUID = -7628331872641766247L;
    private List<HcTypeListVO> list;
    private Integer count;
}
