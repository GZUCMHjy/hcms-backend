package com.louis.springbootinit.service;

import com.louis.springbootinit.model.entity.Hc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
* @author 35064
* @description 针对表【hc】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface HcService extends IService<Hc> {

    String getHcQRCode(Integer hc_id) throws IOException;
}
