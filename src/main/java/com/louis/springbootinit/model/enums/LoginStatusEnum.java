package com.louis.springbootinit.model.enums;

/**
 * 自定义错误码
 *
 * @author louis
 * @from 
 */
public enum LoginStatusEnum {

    LOGIN_SUCCESS(1,"登录成功"),
    PASSWORD_ERROR(-1, "密码错误"),
    ACCOUNT_NOT_EXIST(0,"账号不存在");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    LoginStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
