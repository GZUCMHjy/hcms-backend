package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.model.entity.Admin;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.model.enums.LoginStatusEnum;
import com.louis.springbootinit.model.vo.LoginAdminVO;
import com.louis.springbootinit.model.vo.LoginUserVO;
import com.louis.springbootinit.service.AdminService;
import com.louis.springbootinit.mapper.AdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.louis.springbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 35064
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {
    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "hcms";


    @Override
    public LoginAdminVO adminLogin(String adminAccount, String adminPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(adminAccount, adminPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (adminAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (adminPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + adminPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_acct", adminAccount);
        LoginAdminVO loginAdminVO = new LoginAdminVO();
        Admin admin = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (admin == null) {
            log.info("user login failed, adminAccount cannot match adminPassword");
            loginAdminVO.setStatus(LoginStatusEnum.ACCOUNT_NOT_EXIST.getCode());
            return loginAdminVO;
        }
        String admin_pwd = admin.getAdmin_pwd();
        // 校验密码
        if(!admin_pwd.equals(encryptPassword)){
            loginAdminVO.setStatus(LoginStatusEnum.PASSWORD_ERROR.getCode());
            return loginAdminVO;
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, admin);
        return this.getLoginAdminVO(admin);
    }

    @Override
    public boolean adminLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public Admin getLoginAdmin(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        Admin currentAdmin = (Admin) userObj;
        if (currentAdmin == null || currentAdmin.getAdmin_id() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long adminId = currentAdmin.getAdmin_id();
        currentAdmin = this.getById(adminId);
        if (currentAdmin == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentAdmin;
    }

    @Override
    public LoginAdminVO getLoginAdminVO(Admin admin) {
        if (admin == null) {
            return null;
        }
        LoginAdminVO loginAdminVO = new LoginAdminVO();
        loginAdminVO.setStatus(LoginStatusEnum.LOGIN_SUCCESS.getCode());
        return loginAdminVO;
    }

    @Override
    public List<Admin> searchByAdminName(String teacher_name) {
        if(teacher_name == null || teacher_name.length() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"姓名不能为空");
        }
        QueryWrapper<Admin> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("admin_name",teacher_name);
        return this.baseMapper.selectList(userQueryWrapper);
    }

    @Override
    public Admin searchByNameAndTel(String realName, String realTel) {
        if(realName == null || realName.length() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"姓名不能为空");
        }
        if (realTel == null || realTel.length() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"电话号码不能为空");
        }
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("admin_name",realName)
                .eq("admin_tel",realTel);
        Admin admin  = this.baseMapper.selectOne(adminQueryWrapper);
        if(admin == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在");
        }
        return admin;
    }
}




