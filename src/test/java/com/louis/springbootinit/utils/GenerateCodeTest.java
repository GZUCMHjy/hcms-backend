package com.louis.springbootinit.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.model.entity.Hc;
import com.louis.springbootinit.model.entity.Hctype;
import com.louis.springbootinit.service.HcService;
import com.louis.springbootinit.service.HctypeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/16 17:40
 */
@SpringBootTest
public class GenerateCodeTest {
    @Resource
    private HcService hcService;
    @Resource
    private HctypeService hctypeService;

    @Resource
    private AliOssUtil aliOssUtil;
    @Test
    public void createCode(){
        String token = "I am your father!";
        // 生成指定内容对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate(token, 300, 300, FileUtil.file("d:/qrcode.jpg"));
    }
    @Test
    public void getHcQRCode() throws IOException {
        Hc hc = hcService.getById(6);
        Integer hctype_id = hc.getHctype_id();
        QueryWrapper<Hctype> hctypeQueryWrapper = new QueryWrapper<>();
        hctypeQueryWrapper.eq("hctype_id",hctype_id);
        Hctype hctype = hctypeService.getOne(hctypeQueryWrapper);
        if(hc == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("危化品编号",hc.getHc_id());
        map.put("危化品品名",hc.getHc_name());
        map.put("物理状态",hc.getStates());
        map.put("规格",hctype.getHc_spec());
        map.put("单位",hctype.getHc_unit());
        map.put("余量",hc.getHc_remnant());
        map.put("所在仓库",hc.getWh_id());
        map.put("采购编号",hc.getPur_id());
        map.put("采购单价",hc.getPrice());
        map.put("生产日期",hc.getProducationdate());
        map.put("保质期",hc.getShelflife());
        map.put("入库时间",hc.getCreateTime());
        String mapStr = map.toString();
        // 生成二维码图片
        File file = QrCodeUtil.generate(mapStr, 300, 300, FileUtil.file("d:/Hc"+hc.getHc_id().toString() +".jpg"));
        String originalFilename = file.getName();
        //在oss中存储名字就是UUID + 文件的后缀名
        String objectName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        byte[] bytes = Files.readAllBytes(file.toPath());
        // 调用阿里云服务（生成图片url）
        System.out.println(aliOssUtil.upload(bytes, file.getName()));
    }
}
