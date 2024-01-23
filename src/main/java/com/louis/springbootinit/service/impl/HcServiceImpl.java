package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.mapper.HcMapper;
import com.louis.springbootinit.model.entity.Hc;
import com.louis.springbootinit.service.HcService;
import org.springframework.stereotype.Service;

/**
* @author 35064
* @description 针对表【hc】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class HcServiceImpl extends ServiceImpl<HcMapper, Hc>
    implements HcService {

}




