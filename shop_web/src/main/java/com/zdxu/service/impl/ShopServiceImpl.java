package com.zdxu.service.impl;

import com.zdxu.domain.repository.ShopRepository;
import com.zdxu.domain.entity.ShopDO;
import com.zdxu.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhaodexu on 2017/8/30.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Override
    public ShopDO getShopByShopId(int shopId) {
        return shopRepository.findById(shopId);
    }
}
