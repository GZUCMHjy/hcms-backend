package com.louis.springbootinit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.model.dto.Record.IbDetailsAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.entity.Pur;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.model.entity.Wh;
import com.louis.springbootinit.model.vo.IbRecordVO;
import com.louis.springbootinit.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.louis.springbootinit.constant.UserConstant.USER_LOGIN_STATE;
import static com.louis.springbootinit.service.impl.IbServiceImpl.DEFAULT_REPO;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 14:29
 */
// 危险品管理记录接口层（入库和出库）
@RestController
@RequestMapping("/hcRecords")
@Slf4j
public class HcRecordsController {
    @Resource
    private UserService userService;


    @Resource
    private IbService ibService;

    @Resource
    private PurService purchaseService;

    @Resource
    private WhService whService;

    /**
     * 入库基本信息填写接口
     * @param ibRecordRequest
     * @return
     */
    @PostMapping("/addIbBaseRecords")
    public BaseResponse<Boolean> addIbRecords(@RequestBody IbRecordAddRequest ibRecordRequest, HttpServletRequest request){
        if(ibRecordRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        String admina_name = ibRecordRequest.getAdmina_name();
        String adminb_name = ibRecordRequest.getAdminb_name();
        String teacher_name = ibRecordRequest.getTeacher_name();
        // 采购本人（仓库管理员/实验室负责人）
        String user_name = ibRecordRequest.getUser_name();
        // 枚举（入库目的）
        String ib_content = ibRecordRequest.getIb_purpose();
        Integer whstart_id = ibRecordRequest.getWhstart_id();
        Integer whend_id = ibRecordRequest.getWhend_id();
        Wh whend = whService.getById(whend_id);
        Wh whstart = whService.getById(whstart_id);
        String whstart_name = whstart.getWh_name();
        String whend_name = whend.getWh_name();
        String ib_purpose = ibRecordRequest.getIb_purpose();
        if(StringUtils.isAnyBlank(admina_name,adminb_name,teacher_name,user_name,ib_content,whstart_name,whend_name,ib_purpose)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        List<User> users = userService.searchByUsername(user_name);
        if(users == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户没用提交采购表");
        }
        Integer user_id = null;
        if (users.size() == 1){
            user_id = users.get(0).getUser_id();
        }
        User targetUser = new User();
        // 排除重名的情况
        users.stream().forEach(user->{
            if(user.getUser_name().equals(currentUser.getUser_name()) && user.getUser_tel().equals(currentUser.getUser_tel())){
                BeanUtil.copyProperties(user,targetUser);
            }
        });
        user_id = targetUser.getUser_id();
        Pur targetPur = purchaseService.searchByUserId(user_id);
        boolean result = ibService.addIbRecords(ibRecordRequest,targetPur);
        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"开始入库失败");
        }
        return ResultUtils.success(true);
    }

    /**
     * 添加入库的详细信息
     * @param ibDetailsAddRequest
     * @return
     */
    @PostMapping("/addIbDetailsRecords")
    public BaseResponse<Boolean> addIbDetailsRecords(@RequestBody IbDetailsAddRequest ibDetailsAddRequest){
        return ResultUtils.success(true);
    }
}
