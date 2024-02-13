package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.mapper.HcibMapper;
import com.louis.springbootinit.mapper.IbMapper;
import com.louis.springbootinit.model.dto.HcIb.IbSearchByIbIdRequest;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.IbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.QuitOrEndRequest;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.entity.*;
import com.louis.springbootinit.model.vo.HcListVO;
import com.louis.springbootinit.model.vo.IbBaseRecordVO;
import com.louis.springbootinit.model.vo.IbRecordVO;
import com.louis.springbootinit.service.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private HcService hcService;

    @Resource
    private HcibMapper hcibMapper;

    @Resource
    private HctypeService hctypeService;

    @Resource
    private WhService whService;

    /**
     * 开始入库,生成入库记录
     * @param ibRecordRequest
     * @return
     */
    @Override
    @Transactional
    public IbBaseRecordVO addIbRecords(IbRecordAddRequest ibRecordRequest) {
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
        ib.setHctype_id(ibRecordRequest.getHctype_id());
        ib.setIb_time(new Date(System.currentTimeMillis()));
        int insert = this.baseMapper.insert(ib);

        if(insert == 0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"入库失败");
        }
        Hctype byId = hctypeService.getById(ibRecordRequest.getHctype_id());
        ThrowUtils.throwIf(byId==null,ErrorCode.PARAMS_ERROR,"查无类型");

        IbBaseRecordVO ibBaseRecordVO = new IbBaseRecordVO();
        ibBaseRecordVO.setIb_id(ib.getIb_id());
        ibBaseRecordVO.setHc_name(byId.getHc_name());
        ibBaseRecordVO.setHctype_id(ibRecordRequest.getHctype_id());
        ibBaseRecordVO.setHc_unit(byId.getHc_unit());
        ibBaseRecordVO.setHc_spec(Integer.parseInt(byId.getHc_spec()));
        return ibBaseRecordVO;
    }

    /**
     * 结束/取消入库
     * @param quitOrEndRequest
     * @return
     */
    @Override
    @Transactional
    public boolean cancelOrEndIb(QuitOrEndRequest quitOrEndRequest) {
        Ib byId = this.getById(quitOrEndRequest.getIb_id());
        ThrowUtils.throwIf(byId == null,ErrorCode.PARAMS_ERROR,"查无入库记录");
        if(quitOrEndRequest.getEnd_cancel() == 1){
            // 取消(逻辑删除入库记录)
            int i = this.baseMapper.deleteById(byId.getIb_id());
            ThrowUtils.throwIf(i==0,ErrorCode.PARAMS_ERROR,"取消失败");
        }
        // 删除危化品表
        // 获取危化品类型id
        Integer hctype_id = byId.getHctype_id();
        QueryWrapper<Hc> hcQueryWrapper = new QueryWrapper<Hc>().eq("hctype_id", hctype_id);
        List<Hc> hcs = hcService.list(hcQueryWrapper);
        if( hcs .size() == 1){
            // 普通入库
            Hc targetHc =  hcs.get(0);
            boolean b = hcService.removeById(targetHc.getHc_id());
            ThrowUtils.throwIf(!b, ErrorCode.SYSTEM_ERROR,"取消失败");
        }
        else {
            // 采购入库(包括了 size等于0和大于1的情况)
            try{
                hcs.forEach(hc ->{
                    hcService.removeById(hc.getHc_id());
                });
            }catch (BusinessException e){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,e.getCause().toString());
            }
        }
        // 删除危化品入库记录表
        Integer ib_id = byId.getIb_id();
        QueryWrapper<Hcib> hcIbQueryWrapper = new QueryWrapper<Hcib>().eq("ib_id", ib_id);
        List<Hcib> hcibs = hcibMapper.selectList(hcIbQueryWrapper);
        if( hcibs.size() == 1){
            // 普通入库
            Hcib hcib = hcibs.get(0);
            int i = hcibMapper.deleteById(hcib.getHcib_id());
            ThrowUtils.throwIf(i == 0, ErrorCode.SYSTEM_ERROR,"取消失败");
        }else{
            // 采购入库
            try{
                hcibs.forEach(hcib ->{
                    int i = hcibMapper.deleteById(hcib.getHcib_id());
                });
            }catch (BusinessException e){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,e.getCause().toString());
            }
        }
        return true;
    }

    /**
     * 获取此次入库危化品
     * @param ibSearchByIbIdRequest
     * @return
     */
    @Override
    public IbRecordVO getIbRecordsInfo(IbSearchByIbIdRequest ibSearchByIbIdRequest) {
        Integer ib_id = ibSearchByIbIdRequest.getIb_id();
        long limit = ibSearchByIbIdRequest.getLimit();
        long page = ibSearchByIbIdRequest.getPage();
        if( ib_id == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"参数不能为空");
        }
        QueryWrapper<Ib> ibQueryWrapper = new QueryWrapper<>();
        ibQueryWrapper.eq("ib_id",ib_id);
        Ib byId = this.getById(ib_id);
        if(byId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"查无入库记录");
        }
        // 获取未分页的危化品入库记录列表
        QueryWrapper<Hcib> hcibQueryWrapper = new QueryWrapper<>();
        hcibQueryWrapper.eq("ib_id",ib_id);
        List<Hcib> hcibs = hcibMapper.selectList(hcibQueryWrapper);
        // 获取分页的危化品入库记录列表
        Page<Hcib> hcibPage = new Page<Hcib>();
        hcibPage.setCurrent(page);
        hcibPage.setSize(limit);
        Page<Hcib> hcibPageRes = hcibMapper.selectPage(hcibPage, hcibQueryWrapper);
        // 分页结果
        List<Hcib> records = hcibPageRes.getRecords();
        List<HcListVO> hcListVOS = new ArrayList<>(records.size());
        IbRecordVO ibRecordVO = new IbRecordVO();
        ibRecordVO.setIb_id(ib_id);
        for(Hcib hcib : records){
            Integer hc_id = hcib.getHc_id();
            Hc hc = hcService.getById(hc_id);
            Integer hctype_id = hc.getHctype_id();
            Hctype hctype = hctypeService.getById(hctype_id);
            ThrowUtils.throwIf(hctype == null,ErrorCode.PARAMS_ERROR,"查无危化品类型");
            HcListVO hcListVO = new HcListVO();
            hcListVO.setHcib_id(hcib.getHcib_id());
            hcListVO.setHc_id(hc.getHc_id());
            hcListVO.setHc_name(hc.getHc_name());
            hcListVO.setStates(hc.getStates());
            hcListVO.setHc_spec(Integer.parseInt(hctype.getHc_spec()));
            hcListVO.setHc_unit(hctype.getHc_unit());
            hcListVO.setHc_remnant(hc.getHc_remnant());
            Integer wh_id = hc.getWh_id();
            Wh wh = whService.getById(wh_id);
            if(wh == null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"查无仓库");
            }
            hcListVO.setWh_name(wh.getWh_name());
            hcListVO.setPur_id(hc.getPur_id());
            hcListVO.setProductiondate(hc.getProducationdate());
            hcListVO.setShelflife(hc.getShelflife());
            hcListVO.setIb_time(byId.getIb_time());
            hcListVOS.add(hcListVO);
        }
        ibRecordVO.setList(hcListVOS);
        ibRecordVO.setCount(hcibs.size());
        return ibRecordVO;
    }

}




