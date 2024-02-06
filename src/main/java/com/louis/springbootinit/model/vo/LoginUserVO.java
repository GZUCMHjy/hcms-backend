package com.louis.springbootinit.model.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 已登录用户视图（脱敏）
 *
 * @author louis
 * @from 
 **/
@Data
public class LoginUserVO implements Serializable {


    /**
     * 登录状态
     * -1 密码错误
     * 0 账号不存在
     * 1 登录成功
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}