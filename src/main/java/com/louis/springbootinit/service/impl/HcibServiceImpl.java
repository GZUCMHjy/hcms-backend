package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.mapper.HcMapper;
import com.louis.springbootinit.mapper.HcibMapper;
import com.louis.springbootinit.model.dto.Record.HcAnotherIbRecordAddRequest;
import com.louis.springbootinit.model.dto.Record.HcIbRecordAddRequest;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.entity.*;
import com.louis.springbootinit.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 35064
* @description 针对表【hcib】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class HcibServiceImpl extends ServiceImpl<HcibMapper, Hcib>
    implements HcibService {

    @Resource
    private IbService ibService;

    @Resource
    private HctypeService hctypeService;

    @Resource
    private HcMapper hcMapper;



    /**
     * 采购批量入库
     * @param hcIbRecordAddRequest
     * @return
     */
    @Override
    @Transactional
    public int addHcIbRecords(HcIbRecordAddRequest hcIbRecordAddRequest) {
        int max = Integer.MIN_VALUE;
        ThrowUtils.throwIf(hcIbRecordAddRequest == null, ErrorCode.PARAMS_ERROR);
        Integer batch = hcIbRecordAddRequest.getCount();
        // 采购编号
        Integer pur_id = hcIbRecordAddRequest.getPur_id();
        // 入库编号
        Integer ib_id = hcIbRecordAddRequest.getIb_id();
        // 获取入库记录表一条记录
        Ib byId = ibService.getById(ib_id);
        ThrowUtils.throwIf(byId == null, ErrorCode.NOT_FOUND_ERROR);
        // 更新入库记录表中的入库数量
        byId.setIb_nums(batch);
        int wh_remnant = 0;
        // 更新入库记录表的入库后的余量
        try{
            wh_remnant = Integer.parseInt(byId.getWh_remnant());
            wh_remnant +=batch * hcIbRecordAddRequest.getHc_remnant();
        }catch (Exception e){
            // 如果空指针异常 说明第一次采购引进该危化品
            wh_remnant = hcIbRecordAddRequest.getHc_remnant() * batch;
        }


        byId.setIb_quantity(String.valueOf(batch * hcIbRecordAddRequest.getHc_remnant()));
        byId.setWh_remnant(String.valueOf(wh_remnant));
        // 更新数据
        boolean isSuccess = ibService.updateById(byId);
        ThrowUtils.throwIf(!isSuccess, ErrorCode.SYSTEM_ERROR,"更新入库记录表数据异常");
        // 获取批量入库的危化品类型
        Hctype hcType = hctypeService.getById(byId.getHctype_id());
        // 添加危化品表
        for (int i = 0 ; i < batch ; i++){
            // 实例化hc表
            Hc hc = new Hc();
            // 物理状态
            hc.setStates(hcIbRecordAddRequest.getStates());
            // 危化品名
            hc.setHc_name(hcType.getHc_name());
            // 分子式
            hc.setHc_formula(hcType.getHc_formula());
            // 默认余量初始是该危化品类型的规格
            hc.setHc_remnant(Integer.parseInt(hcType.getHc_spec()));
            // 单价
            hc.setPrice(hcIbRecordAddRequest.getPrice());
            // 介绍
            hc.setProfile(hcType.getProfile());
            // 生产日期
            hc.setProducationdate(hcIbRecordAddRequest.getProducationdate());
            // cas编号
            hc.setCas(hcType.getCas());
            // 危化品类型id
            hc.setHctype_id(byId.getHctype_id());
            // 保质期
            hc.setShelflife(hcIbRecordAddRequest.getShelflife());
            // 采购编号
            hc.setPur_id(hcIbRecordAddRequest.getPur_id());
            // 默认未借出
            hc.setBorrowed(0);
            // 采购入库（默认入总库c536）
            hc.setWh_id(1);
            // 默认采购入库初始状态为在存
            // todo 枚举
            hc.setStatus("在存");
            // 生产商
            hc.setHc_productor(hcIbRecordAddRequest.getHc_productor());
            // 英文名
            hc.setHc_enname(hcType.getHc_enname());
            int save = hcMapper.insert(hc);
            if(save == 0){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"采购入库失败");
            }
            if(hc.getHc_id() > max){
                max = hc.getHc_id();
            }
        }
        int startIndex = max - batch + 1;
        int endIndex = max;
        List<Hcib> hcibList = new ArrayList<>(batch);
        for(int hc_id = startIndex ; hc_id <= endIndex; hc_id++){
            Hcib hcib = new Hcib();
            hcib.setHctype_id(hcType.getHctype_id());
            hcib.setIb_id(ib_id);
            hcib.setHc_id(hc_id);
            hcibList.add(hcib);
        }
        // 插入危化品入库记录表数据
        boolean b = this.saveBatch(hcibList);
        if(!b){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"采购入库失败");
        }
        // 返回入库记录id
        return ib_id;
    }

    /**
     * 其他入库
     * @param hcAnotherIbRecordAddRequest
     * @return
     */
    @Override
    @Transactional
    public int addHcAnotherIbRecords(HcAnotherIbRecordAddRequest hcAnotherIbRecordAddRequest) {
        ThrowUtils.throwIf(hcAnotherIbRecordAddRequest == null,ErrorCode.PARAMS_ERROR);
        Integer hc_id = hcAnotherIbRecordAddRequest.getHc_id();
        Hc hc = hcMapper.selectById(hc_id);
        ThrowUtils.throwIf(hc == null,ErrorCode.PARAMS_ERROR,"查无危化品");
        Integer hctype_id = hc.getHctype_id();
        // 生成一条危化品入库记录
        Hcib hcib = new Hcib();
        hcib.setIb_id(hcAnotherIbRecordAddRequest.getIb_id());
        hcib.setHc_id(hc_id);
        hcib.setHctype_id(hctype_id);
        this.baseMapper.insert(hcib);
        // 更新危化品余量
        Integer hc_remnant = hc.getHc_remnant();
        hc_remnant += hcAnotherIbRecordAddRequest.getHc_remnant();
        hc.setHc_remnant(hc_remnant);
        int i = hcMapper.updateById(hc);
        ThrowUtils.throwIf(i == 0,ErrorCode.SYSTEM_ERROR,"更新危化品余量失败");
        // 更新入库记录
        Integer ib_id = hcAnotherIbRecordAddRequest.getIb_id();
        Ib byId = ibService.getById(ib_id);
        ThrowUtils.throwIf(byId == null,ErrorCode.PARAMS_ERROR,"查无入库记录");
        int wh_remnant = Integer.parseInt(byId.getWh_remnant());
        wh_remnant +=hcAnotherIbRecordAddRequest.getHc_remnant();
        byId.setWh_remnant(String.valueOf(wh_remnant));
        // 默认其他入库情况均为1瓶
        byId.setIb_nums(1);
        // 入库总量等于一瓶最小单位
        byId.setIb_quantity(hcAnotherIbRecordAddRequest.getHc_remnant().toString());
        boolean b = ibService.updateById(byId);
        ThrowUtils.throwIf(!b,ErrorCode.SYSTEM_ERROR,"更新入库记录失败");
        return ib_id;
    }
}




