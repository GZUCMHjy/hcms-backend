package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.mapper.UserMapper;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.model.enums.LoginStatusEnum;
import com.louis.springbootinit.model.vo.LoginUserVO;
import com.louis.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.louis.springbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 35064
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {


    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "hcms";

    @Override
    public long userRegister(String user_pwd, String check_pwd,String user_institution,String user_tel,String user_gender,String user_name) {
        // 1. 校验
        if (StringUtils.isAnyBlank(user_pwd, check_pwd,user_tel,user_institution,user_name)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if(user_tel.length() != 11){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号码错误");
        }
        if (user_name.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名过长");
        }
        if (user_pwd.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        // 密码和校验密码相同
        if (!user_pwd.equals(check_pwd)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (user_tel.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_tel", user_tel);
            long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + user_pwd).getBytes());
            // 3. 插入数据
            User user = new User();
            user.setUser_tel(user_tel);
            user.setUser_acct(user_tel);
            user.setUser_pwd(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getUser_id();
        }
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() != 11) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_acct", userAccount);
        User user = this.baseMapper.selectOne(queryWrapper);
        LoginUserVO loginUserVO = new LoginUserVO();
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            loginUserVO.setStatus(LoginStatusEnum.ACCOUNT_NOT_EXIST.getCode());
            return loginUserVO;
        }
        String user_pwd = user.getUser_pwd();
        if(!user_pwd.equals(encryptPassword)){
            // 输入密码和原始密码不匹配
            loginUserVO.setStatus(LoginStatusEnum.PASSWORD_ERROR.getCode());
            return loginUserVO;
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getUser_id() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getUser_id();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        // BeanUtils.copyProperties(user, loginUserVO);
        loginUserVO.setStatus(LoginStatusEnum.LOGIN_SUCCESS.getCode());
        return loginUserVO;
    }

    @Override
    public List<User> searchByUsername(String user_name) {
        if(user_name == null || user_name.length() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"姓名不能为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",user_name);
        return this.baseMapper.selectList(userQueryWrapper);
    }

    @Override
    public User searchByNameAndTel(String userRealName, String userRealTel) {
        if(userRealName == null || userRealName.length() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"姓名不能为空");
        }
        if (userRealTel == null || userRealTel.length() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"电话号码不能为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",userRealName)
                .eq("user_tel",userRealTel);
        User user = this.baseMapper.selectOne(userQueryWrapper);
        if(user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在");
        }
        return user;
    }
}




