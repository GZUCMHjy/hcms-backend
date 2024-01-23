package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.springbootinit.model.entity.Ob;
import com.louis.springbootinit.service.ObService;

import com.louis.springbootinit.mapper.ObMapper;
import org.springframework.stereotype.Service;

/**
* @author 35064
* @description 针对表【ob】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class ObServiceImpl extends ServiceImpl<ObMapper, Ob>
    implements ObService {

}




