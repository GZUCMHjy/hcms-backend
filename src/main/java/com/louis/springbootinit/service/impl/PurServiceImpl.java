package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.mapper.PurMapper;
import com.louis.springbootinit.model.entity.Pur;
import com.louis.springbootinit.service.PurService;
import org.springframework.stereotype.Service;

/**
* @author 35064
* @description 针对表【pur】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class PurServiceImpl extends ServiceImpl<PurMapper, Pur>
    implements PurService {

}




