package com.louis.springbootinit.common;

import com.louis.springbootinit.constant.CommonConstant;
import lombok.Data;

/**
 * 分页请求
 *
 * @author louis
 * @from 
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private long page = 1;

    /**
     * 页面大小
     */
    private long limit = 5;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
