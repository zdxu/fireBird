package com.zdxu.test.redis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zdxu.ShopWebStartUp;
import com.zdxu.constant.RedisKeyConstant;
import com.zdxu.support.redis.RedisUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShopWebStartUp.class)
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testDel() {
        int orderId = 1;
        redisUtil.del(String.valueOf(orderId));
        String key = RedisKeyConstant.getOrderGoodsKey(orderId);
        redisUtil.del(key);
    }

    @Test
    public void testAdd() {
        int orderId = 1;
        String key = RedisKeyConstant.getOrderGoodsKey(orderId);
        redisUtil.setValue(key, "1,2");
    }

    @Test
    public void testInitDirection() {
        int shopId = 1;
        String key = RedisKeyConstant.getGoodsCategoryKey(shopId);
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("1", "小炒");
        jsonObj.addProperty("2", "海鲜");
        System.out.println(jsonObj.toString());
        redisUtil.setValue(key, jsonObj.toString());
    }
}
