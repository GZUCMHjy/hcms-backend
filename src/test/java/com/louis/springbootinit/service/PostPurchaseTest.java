package com.louis.springbootinit.service;

import com.louis.springbootinit.model.dto.purchase.HctypeRecord;
import com.louis.springbootinit.model.entity.Pur;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/27 11:16
 */
@SpringBootTest
public class PostPurchaseTest {

    @Resource
    private PurService purService;
    @Test
    void postPurchase(){
        Pur pur = new Pur();
        pur.setUser_id(1);
        pur.setTotalprice(new BigDecimal(100));
        pur.setFile("123".getBytes());
        ArrayList<HctypeRecord> hcTypeArrayList = new ArrayList<HctypeRecord>();
        HctypeRecord hcType = new HctypeRecord();
        hcType.setPrice(new BigDecimal(100));
        hcType.setCount(1);
        hcTypeArrayList.add(hcType);
        pur.setHctype_list(hcTypeArrayList);
        purService.addPurchaseTest(pur);
    }

}
