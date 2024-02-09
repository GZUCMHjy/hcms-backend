package com.louis.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.DeleteRequest;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.config.WxOpenConfig;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.model.dto.HcTypeAddRequest;
import com.louis.springbootinit.model.dto.Record.HcAnotherIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.QuitOrEndRequest;
import com.louis.springbootinit.model.dto.admin.AdminLoginRequest;
import com.louis.springbootinit.model.dto.admin.AdminUpdateMyRequest;
import com.louis.springbootinit.model.dto.user.UserBaseInfoRequest;
import com.louis.springbootinit.model.entity.*;
import com.louis.springbootinit.model.vo.*;
import com.louis.springbootinit.service.*;
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
    private HcibService hcibService;

    @Resource
    private HctypeService hctypeService;

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Resource
    private IbService ibService;


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

    /**
     * 开始入库,生成入库记录
     * @param ibRecordRequest
     * @param request
     * @return
     */
    @PostMapping("/addIbBaseRecords")
    public BaseResponse<Boolean> addIbRecords(@RequestBody IbRecordAddRequest ibRecordRequest, HttpServletRequest request){
        if(ibRecordRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        Integer adminb_id = ibRecordRequest.getAdminb_id();
        Integer admina_id = ibRecordRequest.getAdmina_id();
        Integer teacher_id = ibRecordRequest.getTeacher_id();
        Integer user_id = ibRecordRequest.getUser_id();
        // 枚举（入库目的）
        String ib_purpose = ibRecordRequest.getIb_purpose();
        String ib_content = ibRecordRequest.getIb_content();
        Integer whstart_id = ibRecordRequest.getWhstart_id();
        Integer whend_id = ibRecordRequest.getWhend_id();
        Wh whend = whService.getById(whend_id);
        Wh whstart = whService.getById(whstart_id);
        String whstart_name = whstart.getWh_name();
        String whend_name = whend.getWh_name();
        // 校验参数
        if(StringUtils.isAnyBlank(adminb_id.toString(),admina_id.toString(), teacher_id.toString(),user_id.toString(),
                ib_content,whstart_name,whend_name,ib_purpose,ib_content)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        boolean b = ibService.addIbRecords(ibRecordRequest);
        if(!b){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"入库失败");
        }
        return ResultUtils.success(true);
    }

    /**
     * 采购入库
     * @param hcIbRecordAddRequest
     * @return
     */
    @PostMapping("/purchaseIb")
    public BaseResponse<Integer> purchaseIb(@RequestBody HcIbRecordAddRequest hcIbRecordAddRequest){
        ThrowUtils.throwIf(hcIbRecordAddRequest == null, ErrorCode.PARAMS_ERROR);
        int ib_id = hcibService.addHcIbRecords(hcIbRecordAddRequest);
        return ResultUtils.success(ib_id);
    }

    /**
     * 其他入库
     * @param hcAnotherIbRecordAddRequest
     * @return
     */
    @PostMapping("/anotherIb")
    public BaseResponse<Integer> anotherIb(@RequestBody HcAnotherIbRecordAddRequest hcAnotherIbRecordAddRequest){
        ThrowUtils.throwIf(hcAnotherIbRecordAddRequest == null, ErrorCode.PARAMS_ERROR);
        int ib_id = hcibService.addHcAnotherIbRecords(hcAnotherIbRecordAddRequest);
        return ResultUtils.success(ib_id);
    }

    /**
     * 结束/取消入库
     * @param quitOrEndRequest
     * @return
     */
    @PostMapping("cancelIb")
    public BaseResponse<Boolean> cancelOrEndIb(@RequestBody QuitOrEndRequest quitOrEndRequest){
        ThrowUtils.throwIf(quitOrEndRequest == null,ErrorCode.PARAMS_ERROR,"参数不能为空");
        boolean b = ibService.cancelOrEndIb(quitOrEndRequest);
        if(!b){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"取消失败");
        }
        // 结束入库
        return ResultUtils.success(true);
    }

    /**
     * 获取领用人信息
     * @param name
     * @return
     */
    @GetMapping("/getUserInfo")
    public BaseResponse<List<UserBaseInfoRequest>> getUserInfo(@RequestParam String name){
        List<User> users = userService.searchByUsername(name);
        List<UserBaseInfoRequest> userBaseInfoRequests = new ArrayList<>();
        users.stream().forEach(user->{
            UserBaseInfoRequest userBaseInfoRequest = new UserBaseInfoRequest();
            userBaseInfoRequest.setUser_id(user.getUser_id());
            userBaseInfoRequest.setUser_name(user.getUser_name());
            userBaseInfoRequest.setUser_tel(user.getUser_tel());
            userBaseInfoRequests.add(userBaseInfoRequest);
        });
        return ResultUtils.success(userBaseInfoRequests);
    }

    /**
     * 获取管理员信息
     * @param name
     * @return
     */
    @GetMapping("/getAdminInfo")
    public BaseResponse<List<UserBaseInfoRequest>> getAdminInfo(@RequestParam String name){
        List<Admin> admins = adminService.searchByAdminName(name);
        List<UserBaseInfoRequest> userBaseInfoRequests = new ArrayList<>();
        admins.stream().forEach(admin->{
            UserBaseInfoRequest userBaseInfoRequest = new UserBaseInfoRequest();
            userBaseInfoRequest.setUser_id(admin.getAdmin_id());
            userBaseInfoRequest.setUser_name(admin.getAdmin_name());
            userBaseInfoRequest.setUser_tel(admin.getAdmin_tel());
            userBaseInfoRequests.add(userBaseInfoRequest);
        });
        return ResultUtils.success(userBaseInfoRequests);
    }

    /**
     * 获取此次入库危化品
     * @param ib_id
     * @return
     */
    @GetMapping("/getHcIbInfo/{ib_id}")
    public BaseResponse<IbRecordVO> getIbRecordsInfo(@PathVariable Integer ib_id){
        ThrowUtils.throwIf(ib_id == null, ErrorCode.PARAMS_ERROR,"参数不能为空");
        IbRecordVO ibRecordsInfo = ibService.getIbRecordsInfo(ib_id);
        return ResultUtils.success(ibRecordsInfo);
    }

    /**
     * 获取危化品类型信息
     * @param hc_name
     * @return
     */
    @GetMapping("/getHcIbInfo/{hc_name}")
    public BaseResponse<List<HcTypeInfoVO>> getHcTypeInfo(@PathVariable String hc_name){
        ThrowUtils.throwIf(hc_name == null, ErrorCode.PARAMS_ERROR,"参数不能为空");
        List<HcTypeInfoVO> hcTypesInfo = hctypeService.getHcTypeInfo(hc_name);
        return ResultUtils.success(hcTypesInfo);
    }

    /**
     * 新增危化品类型
     * @param hcTypeAddRequest
     * @return
     */
    @PostMapping("/addHctype")
    public BaseResponse<Boolean> addHctype(@RequestBody HcTypeAddRequest hcTypeAddRequest){
        ThrowUtils.throwIf(hcTypeAddRequest == null,ErrorCode.PARAMS_ERROR,"参数不能为空");
        boolean result = hctypeService.addHcType(hcTypeAddRequest);
        return ResultUtils.success(result);
    }

    /**
     * 删除危化品类型
     * @param deleteRequest
     * @return
     */
    @PostMapping("/deleteHctype")
    public BaseResponse<Boolean> deleteHctype(@RequestBody DeleteRequest deleteRequest){
        ThrowUtils.throwIf(deleteRequest == null,ErrorCode.PARAMS_ERROR,"参数不能为空");
        boolean result = hctypeService.removeById(deleteRequest.getId());
        ThrowUtils.throwIf(!result,ErrorCode.PARAMS_ERROR,"参数不能为空");
        return ResultUtils.success(true);
    }

    /**
     * 获取危化品类型列表
     * @param hc_name
     * @return
     */
    @GetMapping("/getHctypeList/{hc_name}")
    public BaseResponse<List<HcTypeListVO>> getHctypeList(@PathVariable String hc_name){
        ThrowUtils.throwIf(hc_name == null, ErrorCode.PARAMS_ERROR,"参数不能为空");
        List<HcTypeListVO> hcTypesListInfo = hctypeService.getHcTypeListInfo(hc_name);
        return ResultUtils.success(hcTypesListInfo);
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
