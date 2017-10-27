package com.zdxu.service.impl;

import com.zdxu.constant.StatusContant;
import com.zdxu.domain.repository.OrderGoodsRepository;
import com.zdxu.domain.entity.OrderGoodsDO;
import com.zdxu.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    OrderGoodsRepository orderGoodsRepository;

    @Override
    @Transactional
    public void saveOrderGoodses(int orderId, List<Integer> goodsIds) {
        List<OrderGoodsDO> orderGoodsList = new ArrayList<>();
        for (int i = 0; goodsIds != null && i < goodsIds.size(); i++) {
            int goodsId = goodsIds.get(i);
            OrderGoodsDO orderGoods = new OrderGoodsDO();
            orderGoods.setGoodsId(goodsId);
            orderGoods.setOrderId(orderId);
            orderGoods.setStatus(StatusContant.OrderGoodsStatus.PENDING_SEND);
            orderGoods.setCreateTime(LocalDateTime.now().getSecond());
            orderGoodsList.add(orderGoods);
        }
        orderGoodsRepository.save(orderGoodsList);
    }
}
