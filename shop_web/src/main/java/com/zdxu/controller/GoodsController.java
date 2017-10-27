package com.zdxu.controller;

import com.zdxu.constant.RedisKeyConstant;
import com.zdxu.constant.Syscode;
import com.zdxu.domain.dto.ResultDTO;
import com.zdxu.domain.dto.SearchDTO;
import com.zdxu.domain.entity.GoodsDO;
import com.zdxu.service.GoodsService;
import com.zdxu.support.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhaodexu on 2017/8/29.
 */
@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/shops/{shopId}/search")
    @ResponseBody
    public Page<GoodsDO> listGoods(@PathVariable("shopId") int shopId, int orderId,
                                   SearchDTO searchDTO) {
        if(searchDTO.getPageSize() > 100 || searchDTO.getPageSize() <= 0
                || searchDTO.getCurrentPageNo() <= 0) {
            searchDTO.setPageSize(Syscode.pageSize);
            searchDTO.setCurrentPageNo(Syscode.currentPageNo);
        }
        Page<GoodsDO> goodsPage = goodsService.pageGoodses(orderId, searchDTO);
        return goodsPage;
    }

    @RequestMapping(value = "/shops/{shopId}/goods/{goodsId}/orderAdd", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO addOrderGoods(@PathVariable("goodsId") int goodsId, int orderId) {
        String key = RedisKeyConstant.getOrderGoodsKey(orderId);
        String goodsIds = redisUtil.getString(key);
        if(StringUtils.isEmpty(goodsIds)) {
            goodsIds = String.valueOf(goodsId);
        }else {
            goodsIds += ","+goodsId;
        }
        redisUtil.setValue(key, goodsIds);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setFlag(true);
        return resultDTO;
    }

    @RequestMapping(value = "/shops/{shopId}/goods/{goodsId}/orderCancel", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO orderGoodsCancel(@PathVariable("goodsId") int goodsId, int orderId) {
        String key = RedisKeyConstant.getOrderGoodsKey(orderId);
        String goodsIds = redisUtil.getString(key);
        if(!StringUtils.isEmpty(goodsIds)) {
            String[] arrGoodsId = goodsIds.split(",");
            goodsIds = null;
            for(int i = 0; arrGoodsId != null && i < arrGoodsId.length; i++) {
                int currentGoodsId = Integer.parseInt(arrGoodsId[i]);
                if(currentGoodsId != goodsId) {
                    if(goodsIds == null) {
                        goodsIds = arrGoodsId[i];
                    }else {
                        goodsIds += arrGoodsId[i];
                    }
                }
            }
        }
        redisUtil.setValue(key, goodsIds);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setFlag(true);
        return resultDTO;
    }
}
