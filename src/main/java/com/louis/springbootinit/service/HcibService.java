package com.louis.springbootinit.service;

import com.louis.springbootinit.model.dto.Record.HcAnotherIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.entity.Hcib;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 35064
* @description 针对表【hcib】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface HcibService extends IService<Hcib> {

    int addHcIbRecords(HcIbRecordAddRequest hcIbRecordAddRequest);

    int addHcAnotherIbRecords(HcAnotherIbRecordAddRequest hcAnotherIbRecordAddRequest);
}
