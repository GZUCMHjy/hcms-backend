package com.louis.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.dto.HcIb.IbSearchByIbIdRequest;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.QuitOrEndRequest;
import com.louis.springbootinit.model.entity.Ib;
import com.louis.springbootinit.model.entity.Pur;
import com.louis.springbootinit.model.vo.IbBaseRecordVO;
import com.louis.springbootinit.model.vo.IbRecordVO;

import java.util.List;

/**
* @author 35064
* @description 针对表【ib】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface IbService extends IService<Ib> {

    IbBaseRecordVO addIbRecords(IbRecordAddRequest ibRecordRequest);

    boolean cancelOrEndIb(QuitOrEndRequest quitOrEndRequest);

    IbRecordVO getIbRecordsInfo(IbSearchByIbIdRequest ibSearchByIbIdRequest);
}
