package com.zdxu.controller;

import com.zdxu.constant.RedisKeyConstant;
import com.zdxu.constant.StatusContant;
import com.zdxu.domain.entity.GoodsDO;
import com.zdxu.domain.entity.OrderDO;
import com.zdxu.domain.entity.ShopDO;
import com.zdxu.service.GoodsService;
import com.zdxu.service.OrderService;
import com.zdxu.service.ShopService;
import com.zdxu.support.redis.RedisUtil;
import com.zdxu.util.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Controller
public class OrderController {

    @Autowired
    ShopService shopService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    GoodsUtil goodsUtil;


    @RequestMapping("/shops/{shopId}/orders/{orderId}")
    public String orderDetail(@PathVariable("shopId") int shopId, @PathVariable("orderId") int orderId,
                              Map<String, Object> model) {

        OrderDO order = orderService.getOrderById(orderId);
        List<GoodsDO> goodsList = null;
        if(order.getStatus() == StatusContant.OrderStatus.PENDING_ORDER) {
            String key = RedisKeyConstant.getOrderGoodsKey(orderId);
            String goodsIds = redisUtil.getString(key);
            if(!StringUtils.isEmpty(goodsIds)) {
                String[] arrGoodsId = goodsIds.split(",");
                List<Integer> goodsIdList = new ArrayList<>();
                for (int i = 0; i < arrGoodsId.length; i++) {
                    goodsIdList.add(Integer.parseInt(arrGoodsId[i]));
                }
                goodsList = goodsService.listGoods(goodsIdList);
            }
        }else {
            goodsList = goodsService.listGoodsByOrderId(orderId);
        }
        if(goodsList != null) {
            goodsUtil.filterGoodsSetCategoryName(shopId, goodsList);
            double allPrice = 0;
            for (GoodsDO goods:goodsList) {
                allPrice += goods.getPrice();
            }
            order.setAllPrice(allPrice);
        }
        ShopDO shop = shopService.getShopByShopId(shopId);
        model.put("shop", shop);
        model.put("goodsList", goodsList);
        model.put("size", goodsList == null ? 0 : goodsList.size());
        model.put("order", order);
        return "shopCar";
    }

    @RequestMapping("/shops/{shopId}/orders/{orderId}/confirm")
    public String confirmOrder(@PathVariable("shopId") int shopId, @PathVariable("orderId") int orderId) {
        orderService.confirmOrder(orderId);
        String redirectUrl = "redirect:/shops/"+shopId+"/orders/"+orderId;
        return redirectUrl;
    }
}
