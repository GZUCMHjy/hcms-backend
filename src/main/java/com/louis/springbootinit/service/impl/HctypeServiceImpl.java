package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.mapper.HctypeMapper;
import com.louis.springbootinit.model.entity.Hctype;
import com.louis.springbootinit.service.HctypeService;
import org.springframework.stereotype.Service;

/**
* @author 35064
* @description 针对表【hctype】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class HctypeServiceImpl extends ServiceImpl<HctypeMapper, Hctype>
    implements HctypeService {

}




