package com.louis.springbootinit.service;

import com.louis.springbootinit.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 35064
* @description 针对表【user】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface UserService extends IService<User> {

    long userRegister(String userPassword, String checkPassword,String user_institution,String user_tel,String user_gender,String user_name);

    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);

    List<User> searchByUsername(String user_name);

    User searchByNameAndTel(String userRealName, String userRealTel);
}
