package com.louis.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.dto.HcTypeAddRequest;
import com.louis.springbootinit.model.entity.Hctype;
import com.louis.springbootinit.model.vo.HcTypeInfoVO;
import com.louis.springbootinit.model.vo.HcTypeListVO;

import java.util.List;

/**
* @author 35064
* @description 针对表【hctype】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface HctypeService extends IService<Hctype> {

    List<HcTypeInfoVO> getHcTypeInfo(String hc_name);

    boolean addHcType(HcTypeAddRequest hcTypeAddRequest);

    List<HcTypeListVO> getHcTypeListInfo(String hc_name);
}
