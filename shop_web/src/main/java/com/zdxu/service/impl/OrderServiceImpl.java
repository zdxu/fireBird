package com.zdxu.service.impl;

import com.zdxu.constant.RedisKeyConstant;
import com.zdxu.constant.StatusContant;
import com.zdxu.domain.repository.OrderRepository;
import com.zdxu.domain.entity.OrderDO;
import com.zdxu.service.OrderGoodsService;
import com.zdxu.service.OrderService;
import com.zdxu.support.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderGoodsService orderGoodsService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public OrderDO getOrderByTableTattedCode(String tableTattedCode) {
        return orderRepository.findByTableTattedCode(tableTattedCode);
    }

    @Override
    public OrderDO getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    @Transactional
    public void confirmOrder(int orderId) {
        String key = RedisKeyConstant.getOrderGoodsKey(orderId);
        String goodsIds = redisUtil.getString(key);
        String[] arrGoodsId = goodsIds.split(",");
        List<Integer> goodsIdList = new ArrayList<>();
        for(int i = 0; i < arrGoodsId.length; i++) {
            int goodsId = Integer.parseInt(arrGoodsId[i]);
            goodsIdList.add(goodsId);
        }
        orderGoodsService.saveOrderGoodses(orderId, goodsIdList);
        orderRepository.updateOrderStatus(StatusContant.OrderStatus.ORDERED, orderId);
        redisUtil.del(String.valueOf(orderId));
    }
}
