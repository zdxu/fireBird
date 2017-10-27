package com.zdxu.constant;

/**
 * Created by zhaodexu on 2017/9/5.
 */
public class RedisKeyConstant {

    private final static String ORDER_GOODS_KEY_PRIX = "ogk_";
    private static final String GOODS_CATEGORY_KEY_PRIX = "gck_";

    public static String getOrderGoodsKey(int orderId) {
        return ORDER_GOODS_KEY_PRIX+orderId;
    }

    public static String getGoodsCategoryKey(int shopId) {
        return GOODS_CATEGORY_KEY_PRIX+shopId;
    }
}
