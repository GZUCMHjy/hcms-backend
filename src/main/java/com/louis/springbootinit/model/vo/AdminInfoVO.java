package com.louis.springbootinit.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/10 20:15
 */
@Data
public class AdminInfoVO implements Serializable {

    /**
     * 管理员姓名
     */
    private String admin_name;

    /**
     * 管理员电话
     */
    private String admin_tel;

    /**
     * 管理员性别
     */
    private String admin_gender;

    /**
     * 管理员管理仓库id,外键
     */
    private Integer wh_id;

    /**
     * 管理员职位
     */
    private String admin_position;

    /**
     * 管理员工作单位
     */
    private String admin_institution;


    private static final long serialVersionUID = 1L;
}
