package com.louis.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.config.WxOpenConfig;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.model.dto.HcInfo.HcInfoAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.dto.admin.AdminLoginRequest;
import com.louis.springbootinit.model.dto.admin.AdminRegisterRequest;
import com.louis.springbootinit.model.dto.admin.AdminUpdateMyRequest;
import com.louis.springbootinit.model.entity.Admin;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.model.entity.Wh;
import com.louis.springbootinit.model.vo.LoginAdminVO;
import com.louis.springbootinit.model.vo.MemberAdminVO;
import com.louis.springbootinit.model.vo.MemberUserVO;
import com.louis.springbootinit.service.AdminService;
import com.louis.springbootinit.service.UserService;
import com.louis.springbootinit.service.WhService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 管理员接口
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
    private UserService userService;

    @Resource
    private WhService whService;

    @Resource
    private WxOpenConfig wxOpenConfig;

    // region 登录相关


    /**
     * 管理员登录
     *
     * @param adminLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录",notes = "管理员登录")
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
     * 管理员注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "管理员注销",notes = "管理员注销")
    public BaseResponse<Boolean> adminLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = adminService.adminLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录管理员
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    @ApiOperation(value = "获取当前管理员登录",notes = "获取当前管理员登录")
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
    @ApiOperation(value = "更新个人信息",notes = "更新个人信息")
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
        admin.setWh_id(wh_id);
        admin.setAdmin_id(loginAdmin.getAdmin_id());
        boolean result = adminService.updateById(admin);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * （开始入库）危化品入库基本信息
     * @param ibRecordAddRequest
     * @return
     */
    @PostMapping("/ib_baseInfo")
    public BaseResponse<Boolean> ib_baseInfo(@RequestBody IbRecordAddRequest ibRecordAddRequest){

        return ResultUtils.success(true);
    }

    /**
     * 搜索管理员
     * @param name
     * @return
     */
    @GetMapping("/searchByAdminName")
    public BaseResponse<List<MemberAdminVO>> searchByAdminName(@RequestParam String name){
        List<Admin> admins = adminService.searchByAdminName(name);
        List<MemberAdminVO> memberAdminVOList = new LinkedList<>();
        if(admins == null || admins.size() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查无此人");
        }
        admins.stream().forEach(admin -> {
            MemberAdminVO memberAdminVO = new MemberAdminVO();
            memberAdminVO.setAdmin_tel(admin.getAdmin_tel());
            memberAdminVO.setAdmin_name(admin.getAdmin_name());
            memberAdminVOList.add(memberAdminVO);
        });
        return ResultUtils.success(memberAdminVOList);
    }

    /**
     * 搜索普通用户
     * @param name
     * @return
     */
    @GetMapping("/searchByUserName")
    public BaseResponse<List<MemberUserVO>> searchByUserName(@RequestParam String name){
        List<User> users = userService.searchByUsername(name);
        List<MemberUserVO> memberUserVOList = new LinkedList<>();
        if(users == null || users.size() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查无此人");
        }
        users.stream().forEach(admin -> {
            MemberUserVO memberUserVO = new MemberUserVO();
            memberUserVO.setUser_tel(admin.getUser_tel());
            memberUserVO.setUser_name(admin.getUser_name());
            memberUserVOList.add(memberUserVO);
        });
        return ResultUtils.success(memberUserVOList);
    }




//    /**
//     * 分页获取管理员封装列表
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
//     * 管理员登录（微信开放平台）
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
