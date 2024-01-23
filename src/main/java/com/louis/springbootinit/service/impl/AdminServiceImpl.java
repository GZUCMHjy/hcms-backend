package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.springbootinit.model.entity.Admin;
import com.louis.springbootinit.service.AdminService;
import com.louis.springbootinit.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 35064
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

}




