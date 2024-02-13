package com.louis.springbootinit.mapper;
import java.math.BigDecimal;
import java.util.Date;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.model.entity.Hc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author louis
 * @version 1.0
 * @date 2024/2/9 9:32
 */
@SpringBootTest
public class HcTest {
    @Resource
    private HcMapper hcMapper;
    @Test
    void addtest(){
        int max = Integer.MIN_VALUE;
        int batch = 5;
        for (int i = 1 ; i <= batch ; i++){
            // 实例化hc表
            Hc hc = new Hc();
            hc.setStates("");
            hc.setHc_name("第" + i + "个危化品名");
            hc.setHc_formula("");
            hc.setHc_remnant(0);
            hc.setPrice(new BigDecimal("0"));
            hc.setProfile("");
            hc.setProducationdate(new Date());
            hc.setCas("第" + i + "个危化品cas编号");
            hc.setHctype_id(1);
            hc.setShelflife(0);
            hc.setPur_id(1);
            hc.setBorrowed(0);
            hc.setWh_id(1);
            hc.setStatus("");
            hc.setHc_enname("first");
            hc.setHc_productor("");
            int save = hcMapper.insert(hc);
            if(save == 0){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"采购入库失败");
            }
            if(hc.getHc_id() > max){
                max = hc.getHc_id();
            }
        }
        System.out.println(max);
    }


    @Test
    void testOptimisticLock(){
        Hc hc = hcMapper.selectById(6);
        hc.setHc_name("这是第一个测试的6号");
        hcMapper.updateById(hc);
    }
}
