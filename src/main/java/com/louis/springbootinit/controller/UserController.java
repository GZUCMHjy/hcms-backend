package com.louis.springbootinit.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.config.WxOpenConfig;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.mapper.UserMapper;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.dto.purchase.PurchaseAddRequest;
import com.louis.springbootinit.model.entity.Lab;
import com.louis.springbootinit.model.vo.LoginUserVO;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.model.dto.user.UserLoginRequest;
import com.louis.springbootinit.model.dto.user.UserRegisterRequest;
import com.louis.springbootinit.model.dto.user.UserUpdateMyRequest;
import com.louis.springbootinit.model.entity.User;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.louis.springbootinit.service.LabService;
import com.louis.springbootinit.service.PurService;
import com.louis.springbootinit.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author louis
 * @from 
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LabService labService;

    @Resource
    private PurService purService;

    @Resource
    private WxOpenConfig wxOpenConfig;

    // region 登录相关

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public BaseResponse<Boolean> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String user_name = userRegisterRequest.getUser_name();
        String user_pwd = userRegisterRequest.getUser_pwd();
        String user_tel = userRegisterRequest.getUser_tel();
        Integer lab_id = userRegisterRequest.getLab_id();
        String user_gender = userRegisterRequest.getUser_gender();
        // 校验判空
        if (StringUtils.isAnyBlank(user_pwd, lab_id.toString(),user_tel,user_name)) {
            return null;
        }
        long result = userService.userRegister(user_pwd,lab_id,user_tel,user_gender,user_name);
        return ResultUtils.success(true);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 是注册时的手机号（保证每个用户的账号是唯一的）
        String user_acct = userLoginRequest.getUser_acct();
        String user_pwd = userLoginRequest.getUser_pwd();
        if (StringUtils.isAnyBlank(user_acct, user_pwd)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(user_acct, user_pwd, request);
        if(loginUserVO.getStatus() == -1){
            return ResultUtils.success(loginUserVO,"密码错误");
        }
        if(loginUserVO.getStatus() == 0){
            return ResultUtils.success(loginUserVO,"账号不存在");
        }
        return ResultUtils.success(loginUserVO,"登陆成功");
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "用户注销",notes = "用户注销")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    @ApiOperation(value = "获取当前登录用户",notes = "用户登录")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(user));
    }
    /**
     * 更新个人信息
     *
     * @param userUpdateMyRequest
     * @param request
     * @return
     */
    @PostMapping("/update/my")
    @ApiOperation(value = "更新个人信息",notes = "更新个人信息")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest,
                                              HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        String lab_name = userUpdateMyRequest.getLab_name();
        QueryWrapper<Lab> labQueryWrapper = new QueryWrapper<>();
        labQueryWrapper.eq("lab_name",lab_name);
        Integer lab_id = labService.getOne(labQueryWrapper).getLab_id();
        user.setUser_id(loginUser.getUser_id());
        user.setLab_id(lab_id);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/export-xls")
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    public void exportExcel(HttpServletResponse response) throws ClassNotFoundException, IOException {
        ExcelWriter writer = ExcelUtil.getWriter();
        List<User> employees = userMapper.selectList(new QueryWrapper<User>());

        List<Map<String, Object>> rows = employees.stream().map(item -> {
            Map<String, Object> maps = new HashMap<>();
            maps.put("id", item.getUser_id().toString());
            maps.put("name", item.getUser_name());
            maps.put("age", item.getUser_gender());
            maps.put("phone", item.getUser_tel());
            maps.put("account",item.getUser_acct());
            maps.put("pwd",item.getUser_pwd());
            maps.put("createTime",item.getCreateTime().toString());
            maps.put("updateTime", item.getUpdateTime().toString());
            return maps;
        }).collect(Collectors.toList());

        // Title
        int columns = Class.forName("com.louis.springbootinit.model.entity.User").getDeclaredFields().length;
        writer.merge(columns - 1, "普通用户信息");

        // Header
        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("updateTime", "更新时间");
        writer.addHeaderAlias("account", "账号");
        writer.addHeaderAlias("pwd", "密码");

        // Body
        writer.setColumnWidth(0, 30);
        writer.setColumnWidth(1, 30);
        writer.setColumnWidth(2, 30);
        writer.setColumnWidth(3, 30);
        writer.setColumnWidth(4, 30);
        writer.setColumnWidth(5, 30);
        writer.setColumnWidth(6, 30);
        writer.setColumnWidth(7, 30);
        writer.write(rows, true);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("用户信息表-" + DateUtil.today() + ".xls", "utf-8"));

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    /**
     * 提交采购单
     * @param purchasePostRequest
     * @param request
     * @return
     */
    @PostMapping("/postPurchase")
    @ApiOperation(value = "提交采购单",notes = "提交采购单")
    public BaseResponse<Boolean> postPurchase(@RequestBody PurchaseAddRequest purchasePostRequest, HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        if(loginUser == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户未登录");
        }
        if(purchasePostRequest == null || purchasePostRequest.getHctypeRecords().size() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"采购表不能为空");
        }
        List<HctypeRecord> hcTypes = purchasePostRequest.getHctypeRecords();
        // 检验每一条采购的危化品的数量、单价、规格和品名是否为空
        for(HctypeRecord hcRecord : hcTypes){
            String hctype_spec = hcRecord.getHctype_spec();
            String hctype_name = hcRecord.getHctype_name();
            Integer count = hcRecord.getCount();
            BigDecimal price = hcRecord.getPrice();
            if(StringUtils.isAnyBlank(count.toString(),price.toString(),hctype_spec,hctype_name)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"采购危化品类型不能为空");
            }
        }
        if(hcTypes.size() > 5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"采购数量不能超过5个");
        }
        Integer loginUserId = loginUser.getUser_id();
        Boolean aBoolean = purService.addPurchase(loginUserId, purchasePostRequest);
        if(!aBoolean){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"添加采购失败");
        }
        return ResultUtils.success(true);
    }


//    /**
//     * 分页获取用户封装列表
//     *
//     * @param userQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page/vo")
//    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest,
//            HttpServletRequest request) {
//        if (userQueryRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        long current = userQueryRequest.getCurrent();
//        long size = userQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<User> userPage = userService.page(new Page<>(current, size),
//                userService.getQueryWrapper(userQueryRequest));
//        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
//        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());
//        userVOPage.setRecords(userVO);
//        return ResultUtils.success(userVOPage);
//    }

//
//    /**
//     * 用户登录（微信开放平台）
//     */
//    @GetMapping("/login/wx_open")
//    public BaseResponse<LoginUserVO> userLoginByWxOpen(HttpServletRequest request, HttpServletResponse response,
//            @RequestParam("code") String code) {
//        WxOAuth2AccessToken accessToken;
//        try {
//            WxMpService wxService = wxOpenConfig.getWxMpService();
//            accessToken = wxService.getOAuth2Service().getAccessToken(code);
//            WxOAuth2UserInfo userInfo = wxService.getOAuth2Service().getUserInfo(accessToken, code);
//            String unionId = userInfo.getUnionId();
//            String mpOpenId = userInfo.getOpenid();
//            if (StringUtils.isAnyBlank(unionId, mpOpenId)) {
//                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登录失败，系统错误");
//            }
//            return ResultUtils.success(userService.userLoginByMpOpen(userInfo, request));
//        } catch (Exception e) {
//            log.error("userLoginByWxOpen error", e);
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登录失败，系统错误");
//        }
//    }



}
