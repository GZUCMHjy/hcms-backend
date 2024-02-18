package com.louis.springbootinit.model.dto.user;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 用户更新个人信息请求
 *
 * @author louis
 * @from 
 */
@Data
public class UserUpdateMyRequest implements Serializable {


    /**
     * 用户姓名
     */
    private String user_name;

    /**
     * 电话号码
     */
    private String user_tel;

    /**
     * 用户性别
     */
    private String user_gender;

    /**
     * 实验室名
     */
    private Integer lab_id;

    /**
     * 用户职位
     */
    private String user_position;

    /**
     * 用户工作单位
     */
    private String user_institution;


    private static final long serialVersionUID = 1L;

}