package com.louis.springbootinit.service.impl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.mapper.QrCodeMapper;
import com.louis.springbootinit.model.entity.QrCode;
import com.louis.springbootinit.service.QrCodeService;
import com.louis.springbootinit.utils.AliOssUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* @author 35064
* @description 针对表【qr_code】的数据库操作Service实现
* @createDate 2024-02-23 15:21:18
*/
@Service
public class QrCodeServiceImpl extends ServiceImpl<QrCodeMapper, QrCode>
    implements QrCodeService {
    @Resource
    private AliOssUtil aliOssUtil;

    /**
     * 生成二维码图片url
     * @return
     */
    @Override
    @Transactional
    public String createQrCode() throws IOException {
        QrCode qrCode = new QrCode();
        String deviceId = System.getProperty("os.arch");
        qrCode.setDestDevice(deviceId);
        int insert = this.baseMapper.insert(qrCode);
        ThrowUtils.throwIf(insert == 0, ErrorCode.SYSTEM_ERROR,"二维码添加失败");
        Map<String, Object> map = new HashMap<>();
        map.put("code_id",qrCode.getCode_id());
        map.put("account_id","扫码阶段");
        map.put("originDevice","手机扫码");
        map.put("destDevice",qrCode.getDestDevice());
        map.put("status","未扫码");
        String mapStr = JSONUtil.toJsonStr(map);
        File file = QrCodeUtil.generate(mapStr, 300, 300, FileUtil.file("d:/QRcode"+deviceId +".jpg"));
        String originalFilename = file.getName();
        // 在oss中存储名字就是UUID + 文件的后缀名
        String objectName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        byte[] bytes = Files.readAllBytes(file.toPath());
        // 调用阿里云服务（生成图片url）
        String url = aliOssUtil.upload(bytes, file.getName());
        ThrowUtils.throwIf(url == null,ErrorCode.SYSTEM_ERROR,"上传失败");
        return url;
    }

    /**
     * 修改二维码状态
     * @param code_id
     * @return
     */
    @Override
    @Transactional
    public boolean changeStatus(Integer code_id) {
        QrCode qrCode = this.baseMapper.selectById(code_id);
        ThrowUtils.throwIf(qrCode == null, ErrorCode.SYSTEM_ERROR,"二维码不存在");
        if(qrCode.getStatus().equals("未扫码")){
            qrCode.setStatus("已扫码待确定");
        }else if(qrCode.getStatus().equals("已扫码待确定")){
            qrCode.setStatus("完成");
        }
        // 更新
        updateById(qrCode);
        return true;
    }

    /**
     * 获取当前二维码状态
     * @param code_id
     * @return
     */
    @Override
    public String getCurrentStatus(Integer code_id) {
        QrCode qrCode = this.baseMapper.selectById(code_id);
        ThrowUtils.throwIf(qrCode == null, ErrorCode.SYSTEM_ERROR,"二维码不存在");
        return qrCode.getStatus();
    }
}




