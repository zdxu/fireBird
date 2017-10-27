package com.zdxu.service;

import java.util.List;

/**
 * Created by zhaodexu on 2017/9/3.
 */
public interface OrderGoodsService {

    void saveOrderGoodses(int orderId, List<Integer> goodsIds);
}
