package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.mapper.IbMapper;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.entity.*;
import com.louis.springbootinit.model.enums.IbTypeEnum;
import com.louis.springbootinit.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private WhService whService;

    @Resource
    private HctypeService hctypeService;

    @Resource
    private HcService hcService;



    public final static String DEFAULT_REPO = "无";

    @Override
    public boolean addIbRecords(IbRecordAddRequest ibRecordRequest, Pur targetPur) {

        if(ibRecordRequest == null || targetPur == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
        }
        // 内容是：姓名 + 电话
        String user_name = ibRecordRequest.getUser_name();
        // 指导老师（实验室负责人/管理员）
        String teacher_name = ibRecordRequest.getTeacher_name();
        String adminb_name = ibRecordRequest.getAdminb_name();
        String admina_name = ibRecordRequest.getAdmina_name();
        String userRealName = getRealName(user_name);
        String userRealTel = getRealTel(user_name);
        User user = userService.searchByNameAndTel(userRealName,userRealTel);
        User teacher = userService.searchByNameAndTel(getRealName(teacher_name), getRealTel(teacher_name));
        Admin adminTeacher = new Admin();
        if(teacher == null){
            // 不是管理员兼实验室负责人 同时也不是实验室负责人
            // 只能是管理员身份
            adminTeacher = adminService.searchByNameAndTel(getRealName(teacher_name), getRealTel(teacher_name));
        }
        if(adminTeacher == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在");
        }
        Admin admina = adminService.searchByNameAndTel(getRealName(admina_name), getRealTel(admina_name));
        Admin adminb = adminService.searchByNameAndTel(getRealName(adminb_name), getRealTel(adminb_name));
        if(admina == null || adminb == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在");
        }
        Integer whstart_id = ibRecordRequest.getWhstart_id();
        Integer whend_id = ibRecordRequest.getWhend_id();
        Wh whend = whService.getById(whend_id);
        Wh whstart = whService.getById(whstart_id);
        String whstart_name = whstart.getWh_name();
        String whend_name = whend.getWh_name();
        if(whstart_name == null || whend_name == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"仓库名不能为空");
        }
        if(whstart_name.equals(DEFAULT_REPO)){
            // 是采购入库（总仓库）
            //  起始仓库和目的仓库都是总仓库名
            whstart_name = "C536";
        }
        QueryWrapper<Wh> whQueryWrapperByStart = new QueryWrapper<>();
        whQueryWrapperByStart.eq("wh_name",whstart_name);
        Wh startWh = whService.getOne(whQueryWrapperByStart);
        if(startWh == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"仓库不存在");
        }
        QueryWrapper<Wh> whQueryWrapperByEnd = new QueryWrapper<>();
        whQueryWrapperByEnd.eq("wh_name",whend_name);
        Wh endWh = whService.getOne(whQueryWrapperByEnd);
        if(endWh == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"仓库不存在");
        }
        // 本人进行采购的危化品类型列表
        List<HctypeRecord> hctypeRecord_list = targetPur.getHctypeRecord_list();
        for(HctypeRecord hctypeRecord : hctypeRecord_list){
            // 每种危化品类型 都是一次入库记录
            // 获取每一个危化品类型的规格和品名
            String hctype_name = hctypeRecord.getHctype_name();
            String hctype_spec = hctypeRecord.getHctype_spec();
            QueryWrapper<Hctype> hctypeQueryWrapper = new QueryWrapper<>();
            hctypeQueryWrapper.eq("hc_name",hctype_name)
                              .eq("hc_spec",hctype_spec);
            Hctype targetHcType = hctypeService.getOne(hctypeQueryWrapper);
            if(targetHcType == null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"危化品类型不存在");
            }
            Ib ibRecord = new Ib();
            ibRecord.setAdmina_id(admina.getAdmin_id());
            ibRecord.setAdminb_id(adminb.getAdmin_id());
            ibRecord.setUser_id(user.getUser_id());
            ibRecord.setTeacher_id(adminTeacher.getAdmin_id());
            ibRecord.setWhstart_id(startWh.getWh_id());
            ibRecord.setWhend_id(endWh.getWh_id());
            ibRecord.setHctype_id(targetHcType.getHctype_id());
            // 入库目的(5种)
            if(DEFAULT_REPO.equals(whstart_name)){
                // 采购入库
                if(!ibRecordRequest.getIb_purpose().equals(PURCHASE)){
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"入库目的不正确");
                }
            }
            if("C536".equals(whstart_name) &&
                    (!"C536".equals(whend_name) && !DEFAULT_REPO.equals(whend_name))){
                // 领用入分库
                if(!ibRecordRequest.getIb_purpose().equals(PURCHASE)){
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"入库目的不正确");
                }
            }
            if((!"C536".equals(whstart_name) && !DEFAULT_REPO.equals(whstart_name))
            &&(!"C536".equals(whend_name) && !DEFAULT_REPO.equals(whend_name))){
                // 归还入库
                if(!ibRecordRequest.getIb_purpose().equals(EXPERIMENT)){
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"入库目的不正确");
                }
            }
            if(!"C536".equals(whstart_name) && DEFAULT_REPO.equals(whend_name)){
                // 期末归还入库
                if(!ibRecordRequest.getIb_purpose().equals(END_OF_SEMESTER_BACK)){
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"入库目的不正确");
                }
            }
            if(!whend_name.equals(whstart_name)
            &&((!"C536".equals(whstart_name) && !DEFAULT_REPO.equals(whstart_name))
            && (!"C536".equals(whend_name) && !DEFAULT_REPO.equals(whend_name)))){
                // 借用入库
                if(!ibRecordRequest.getIb_purpose().equals(ALLOCATE)){
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"入库目的不正确");
                }
            }
            ibRecord.setCreateTime(new Date(System.currentTimeMillis()));
            ibRecord.setUpdataTime(new Date(System.currentTimeMillis()));
            // 枚举
            ibRecord.setIb_purpose(ibRecordRequest.getIb_purpose());
            boolean save = this.save(ibRecord);
            if(!save){
                return false;
            }
        }
        return true;
    }

    /**
     * 切割规格字符串
     * @param spec
     * @return
     */
    public static int regexStringBySpec(String spec){
        // 定义匹配数字的正则表达式
        Pattern pattern = Pattern.compile("\\d+");
        // 进行匹配
        Matcher matcher = pattern.matcher(spec);

        int quantity = 0;
        // 查找匹配的数字
        if (matcher.find()) {
            String numberString = matcher.group();
            return Integer.parseInt(numberString);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
    }

    public static String getRealName(String inputStr){
        String[] parts = inputStr.split("-");
        return parts[0];
    }
    public static String getRealTel(String inputStr){
        String[] parts = inputStr.split("-");
        return parts[1];
    }
}




