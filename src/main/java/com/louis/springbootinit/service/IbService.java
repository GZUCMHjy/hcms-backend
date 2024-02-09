package com.louis.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.entity.Ib;
import com.louis.springbootinit.model.entity.Pur;

/**
* @author 35064
* @description 针对表【ib】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface IbService extends IService<Ib> {

    boolean  addIbRecords(IbRecordAddRequest ibRecordRequest);
}
