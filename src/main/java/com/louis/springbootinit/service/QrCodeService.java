package com.louis.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.entity.QrCode;

import java.io.IOException;

/**
* @author 35064
* @description 针对表【qr_code】的数据库操作Service
* @createDate 2024-02-23 15:21:18
*/
public interface QrCodeService extends IService<QrCode> {
    /**
    * 生成二维码
    * @return
    * @throws IOException
    */
    public String createQrCode() throws IOException;

    /**
     * 更改二维码状态
     * @param code_id
     * @return
     */
    public boolean changeStatus(Integer code_id);


    /**
     * 获取当前二维码状态
     * @param code_id
     * @return
     */
    public String getCurrentStatus(Integer code_id);
}
