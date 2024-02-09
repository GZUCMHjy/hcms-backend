package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.mapper.IbMapper;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.entity.*;
import com.louis.springbootinit.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.louis.springbootinit.model.enums.IbTypeEnum.*;

/**
* @author 35064
* @description 针对表【ib】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class IbServiceImpl extends ServiceImpl<IbMapper, Ib>
    implements IbService {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;


    /**
     * 开始入库,生成入库记录
     * @param ibRecordRequest
     * @return
     */
    @Override
    @Transactional
    public boolean addIbRecords(IbRecordAddRequest ibRecordRequest) {
        if(ibRecordRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        Integer adminb_id = ibRecordRequest.getAdminb_id();
        Integer admina_id = ibRecordRequest.getAdmina_id();
        Integer teacher_id = ibRecordRequest.getTeacher_id();
        Integer user_id = ibRecordRequest.getUser_id();
        User user = userService.getById(user_id);
        User teacher = userService.getById(teacher_id);
        Admin admina = adminService.getById(admina_id);
        Admin adminb = adminService.getById(adminb_id);
        if(user == null || teacher == null ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查无用户");
        }
        if(admina == null || adminb == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查无管理员");
        }
        String ib_content = ibRecordRequest.getIb_content();
        String ib_purpose = ibRecordRequest.getIb_purpose();
        Ib ib = new Ib();
        ib.setIb_purpose(ib_purpose);
        ib.setIb_content(ib_content);
        ib.setUser_id(user_id);
        ib.setTeacher_id(teacher_id);
        ib.setAdmina_id(admina_id);
        ib.setAdminb_id(adminb_id);
        ib.setWhstart_id(ibRecordRequest.getWhstart_id());
        ib.setWhend_id(ibRecordRequest.getWhend_id());
        ib.setHctype_id(ib.getHctype_id());
        int insert = this.baseMapper.insert(ib);

        if(insert == 0 ){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"入库失败");
        }
        return false;
    }

}




