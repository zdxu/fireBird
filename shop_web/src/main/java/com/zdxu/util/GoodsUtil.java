package com.zdxu.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zdxu.constant.RedisKeyConstant;
import com.zdxu.domain.entity.GoodsDO;
import com.zdxu.support.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Component
public class GoodsUtil {

    @Autowired
    RedisUtil redisUtil;

    public List<GoodsDO> filterGoodsSetIsOrderValue(int orderId, List<GoodsDO> goodsList) {
        String key = RedisKeyConstant.getOrderGoodsKey(orderId);
        String goodsIds = redisUtil.getString(key);
        if(!StringUtils.isEmpty(goodsIds)) {
            String[] arrGoodsId = goodsIds.split(",");
            List<String> goodsIdList = Arrays.asList(arrGoodsId);
            if(goodsList != null) {
                for (GoodsDO goods : goodsList) {
                    if(goodsIdList.contains(String.valueOf(goods.getId()))) {
                        goods.setIsOrder(1);
                    }
                }
            }
        }
        return goodsList;
    }

    public List<GoodsDO> filterGoodsSetCategoryName(int shopId, List<GoodsDO> goodsList) {
        String key = RedisKeyConstant.getGoodsCategoryKey(shopId);
        String stGoodsCategoryDict = redisUtil.getString(key);
        if(!StringUtils.isEmpty(stGoodsCategoryDict)) {
            JsonObject goodsCategoryDict = new JsonParser().parse(stGoodsCategoryDict).getAsJsonObject();
            if(goodsList != null) {
                for (GoodsDO goods : goodsList) {
                    JsonElement element = goodsCategoryDict.get(String.valueOf(goods.getCategory()));
                    if(element != null) {
                        goods.setCategoryName(element.getAsString());
                    }
                }
            }
        }
        return goodsList;
    }
}
