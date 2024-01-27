package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.mapper.PurMapper;
import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.dto.purchase.PurchasePostRequest;
import com.louis.springbootinit.model.entity.Pur;
import com.louis.springbootinit.service.PurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* @author 35064
* @description 针对表【pur】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class PurServiceImpl extends ServiceImpl<PurMapper, Pur>
    implements PurService {

    @Resource
    private PurMapper purMapper;


    /**
     * 提交采购单
     * @param userId 采购人id
     * @param purchasePostRequest 危化品类型采购列表
     * @return
     */
    @Override
    @Transactional
    public Boolean addPurchase(Integer userId, PurchasePostRequest purchasePostRequest) {
        Pur pur = new Pur();
        // 采购人id
        pur.setUser_id(userId);
        List<HctypeRecord> hctypeRecords = purchasePostRequest.getHctypeRecords();
        List<HctypeRecord> hctypeRecord_list = pur.getHctypeRecord_list();
        // 初始化采购总价为0
        BigDecimal totolp = new BigDecimal(0);
        hctypeRecords.stream().forEach(hctypeRecord -> {
            HctypeRecord addHcTypeRecord = new HctypeRecord();
            // 记录采购的危化品类型的名称和规格
            addHcTypeRecord.setHctype_name(hctypeRecord.getHctype_name());
            addHcTypeRecord.setHctype_spec(hctypeRecord.getHctype_spec());
            // 记录采购的危化品类型数量和价格
            addHcTypeRecord.setCount(hctypeRecord.getCount());
            addHcTypeRecord.setPrice(hctypeRecord.getPrice());
            // 计算单个危化品类型总价格
            BigDecimal tempPrice = hctypeRecord.getPrice().multiply(new BigDecimal(hctypeRecord.getCount()));
            // 计算采购总价
            totolp.add(tempPrice);
            // 记录采购的危化品类型列表
            hctypeRecord_list.add(addHcTypeRecord);
        });
        pur.setHctypeRecord_list(hctypeRecord_list);
        pur.setTotalprice(totolp);
        pur.setFile(purchasePostRequest.getFile());
        // 插入数据
        int success = purMapper.insert(pur);
        if(success == 0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"提交采购失败");
        }
        return Boolean.TRUE;
    }
    @Override
    public Boolean addPurchaseTest(Pur pur) {
        purMapper.insert(pur);
        return Boolean.TRUE;
    }
}




