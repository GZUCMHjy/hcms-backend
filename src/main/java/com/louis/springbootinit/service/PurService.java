package com.louis.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.springbootinit.model.dto.purchase.PurchaseAddRequest;
import com.louis.springbootinit.model.entity.Pur;

/**
* @author 35064
* @description 针对表【pur】的数据库操作Service
* @createDate 2024-01-23 11:00:38
*/
public interface PurService extends IService<Pur> {

     Boolean addPurchase(Integer userId, PurchaseAddRequest purchasePostRequest);

     Pur searchByUserId(Integer user_id);
}
