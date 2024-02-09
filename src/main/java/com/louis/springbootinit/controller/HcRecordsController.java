package com.louis.springbootinit.controller;

import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 开始入库,生成入库记录
     * @param ibRecordRequest
     * @return
     */

    /**
     * 添加入库的详细信息
     * @param ibDetailsAddRequest
     * @return
     */
    @PostMapping("/addIbDetailsRecords")
    public BaseResponse<Boolean> addIbDetailsRecords(@RequestBody HcIbRecordAddRequest ibDetailsAddRequest){
        return ResultUtils.success(true);
    }
}
