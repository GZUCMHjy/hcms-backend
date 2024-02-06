package com.louis.springbootinit.service;

import com.louis.springbootinit.model.entity.Admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.model.vo.LoginAdminVO;
import com.louis.springbootinit.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 35064
* @description 针对表【admin】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface AdminService extends IService<Admin> {

    LoginAdminVO adminLogin(String adminAccount, String adminPassword, HttpServletRequest request);

    boolean adminLogout(HttpServletRequest request);

    Admin getLoginAdmin(HttpServletRequest request);

    LoginAdminVO getLoginAdminVO(Admin admin);

    List<Admin> searchByAdminName(String teacher_name);

    Admin searchByNameAndTel(String realName, String realTel);
}
