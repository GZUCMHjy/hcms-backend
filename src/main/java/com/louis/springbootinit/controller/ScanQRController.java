package com.louis.springbootinit.controller;

import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.model.dto.AndroidCode;
import com.louis.springbootinit.model.entity.QrCode;
import com.louis.springbootinit.service.QrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/scan")
@Slf4j
public class ScanQRController {

    @Resource
    private QrCodeService qrCodeService;

    /**
     * 生成二维码
     * @return
     * @throws IOException
     */
    @PostMapping("/generate")
    public BaseResponse<String> generateQRCode()
            throws IOException {
        String qrCode = qrCodeService.createQrCode();
        return ResultUtils.success(qrCode);
    }
    @PostMapping("/changeStatus/{id}")
    public BaseResponse<Boolean> changeStatus(@PathVariable String id)
            throws IOException {
        int i = Integer.parseInt(id);
        boolean b = qrCodeService.changeStatus(i);
        return ResultUtils.success(b);
    }

    /**
     * 移动端登录（二次校验身份）
     * @param code
     * @return
     */
    @PostMapping("/updateQR")
    public BaseResponse<Boolean> updateQRCode(@RequestBody AndroidCode code) {
        int code_id = Integer.parseInt(code.getCode_id());
        QrCode targetCode = qrCodeService.getById(code_id);
        targetCode.setStatus(code.getStatus());
        targetCode.setOriginDevice(code.getOriginDevice());
        targetCode.setDestDevice(code.getDestDevice());
        targetCode.setAccount_id(Integer.parseInt(code.getAccount_id()));
        boolean b = qrCodeService.updateById(targetCode);
        ThrowUtils.throwIf(!b, ErrorCode.SYSTEM_ERROR);
        return ResultUtils.success(true);
    }
    /**
     * 获取当前二维码状态(轮询调用接口)
     * @param id
     * @return
     */
    @GetMapping("/getCurrentQRCodeStatus/{id}")
    public BaseResponse<String> getCurrentQRCodeStatus(@PathVariable Integer id) {
        String currentStatus = qrCodeService.getCurrentStatus(id);
        return ResultUtils.success(currentStatus);
    }
}
