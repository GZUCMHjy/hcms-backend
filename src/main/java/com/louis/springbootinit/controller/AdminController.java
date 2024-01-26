package com.louis.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.config.WxOpenConfig;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.model.dto.admin.AdminLoginRequest;
import com.louis.springbootinit.model.dto.admin.AdminRegisterRequest;
import com.louis.springbootinit.model.dto.admin.AdminUpdateMyRequest;
import com.louis.springbootinit.model.entity.Admin;
import com.louis.springbootinit.model.entity.Wh;
import com.louis.springbootinit.model.vo.LoginAdminVO;
import com.louis.springbootinit.service.AdminService;
import com.louis.springbootinit.service.WhService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *
 * @author louis
 * @from 
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private WhService whService;

    @Resource
    private WxOpenConfig wxOpenConfig;

    // region 登录相关


    /**
     * 用户登录
     *
     * @param adminLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginAdminVO> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest, HttpServletRequest request) {
        if (adminLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String adminAccount = adminLoginRequest.getAdmin_acct();
        String adminPassword = adminLoginRequest.getAdmin_pwd();
        if (StringUtils.isAnyBlank(adminAccount, adminPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginAdminVO loginAdminVO = adminService.adminLogin(adminAccount, adminPassword, request);
        return ResultUtils.success(loginAdminVO);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> adminLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = adminService.adminLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginAdminVO> getLoginAdmin(HttpServletRequest request) {
        Admin admin = adminService.getLoginAdmin(request);
        return ResultUtils.success(adminService.getLoginAdminVO(admin));
    }
    /**
     * 更新个人信息
     *
     * @param adminUpdateMyRequest
     * @param request
     * @return
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody AdminUpdateMyRequest adminUpdateMyRequest,
                                              HttpServletRequest request) {
        if (adminUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Admin loginAdmin = adminService.getLoginAdmin(request);
        QueryWrapper<Wh> whQueryWrapper = new QueryWrapper<>();
        whQueryWrapper.eq("wh_name", adminUpdateMyRequest.getWarehouse_name());
        Integer wh_id = whService.getOne(whQueryWrapper).getWh_id();
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminUpdateMyRequest, admin);
        admin.setWarehouse_id(wh_id);
        admin.setAdmin_id(loginAdmin.getAdmin_id());
        boolean result = adminService.updateById(admin);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }



//    /**
//     * 分页获取用户封装列表
//     *
//     * @param adminQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page/vo")
//    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest adminQueryRequest,
//            HttpServletRequest request) {
//        if (adminQueryRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        long current = adminQueryRequest.getCurrent();
//        long size = adminQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<User> adminPage = adminService.page(new Page<>(current, size),
//                adminService.getQueryWrapper(adminQueryRequest));
//        Page<UserVO> adminVOPage = new Page<>(current, size, adminPage.getTotal());
//        List<UserVO> adminVO = adminService.getUserVO(adminPage.getRecords());
//        adminVOPage.setRecords(adminVO);
//        return ResultUtils.success(adminVOPage);
//    }

//
//    /**
//     * 用户登录（微信开放平台）
//     */
//    @GetMapping("/login/wx_open")
//    public BaseResponse<LoginUserVO> adminLoginByWxOpen(HttpServletRequest request, HttpServletResponse response,
//            @RequestParam("code") String code) {
//        WxOAuth2AccessToken accessToken;
//        try {
//            WxMpService wxService = wxOpenConfig.getWxMpService();
//            accessToken = wxService.getOAuth2Service().getAccessToken(code);
//            WxOAuth2UserInfo adminInfo = wxService.getOAuth2Service().getUserInfo(accessToken, code);
//            String unionId = adminInfo.getUnionId();
//            String mpOpenId = adminInfo.getOpenid();
//            if (StringUtils.isAnyBlank(unionId, mpOpenId)) {
//                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登录失败，系统错误");
//            }
//            return ResultUtils.success(adminService.adminLoginByMpOpen(adminInfo, request));
//        } catch (Exception e) {
//            log.error("adminLoginByWxOpen error", e);
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登录失败，系统错误");
//        }
//    }



}
