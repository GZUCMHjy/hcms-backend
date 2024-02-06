package com.louis.springbootinit.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 14:32
 */

public enum IbTypeEnum {
    EXPERIMENT("教学实验", "教学实验"),
    END_OF_SEMESTER_BACK("期末归库", "期末归库"),
    ALLOCATE("调配使用", "调配使用"),

    PURCHASE("采购入库","采购入库");

    private final String text;

    private final String value;

    IbTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static IbTypeEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (IbTypeEnum anEnum : IbTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
