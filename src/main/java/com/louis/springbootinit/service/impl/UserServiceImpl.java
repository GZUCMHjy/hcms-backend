package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.mapper.UserMapper;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.model.vo.LoginUserVO;
import com.louis.springbootinit.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author 35064
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        return 0;
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        return false;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        return null;
    }

}




