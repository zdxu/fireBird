package com.zdxu.service;

import com.zdxu.domain.entity.OrderDO;

/**
 * Created by zhaodexu on 2017/9/3.
 */
public interface OrderService {

    OrderDO getOrderByTableTattedCode(String tableTattedCode);

    OrderDO getOrderById(int orderId);

    void confirmOrder(int orderId);
}
