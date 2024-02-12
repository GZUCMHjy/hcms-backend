package com.louis.springbootinit.model.vo;

import com.louis.springbootinit.model.entity.Pur;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/12 11:57
 */
@Data
public class PurListVO implements Serializable {

    private static final long serialVersionUID = 5539225610754013776L;


    private List<Pur> list;

    private Integer count;
}
