package com.zdxu.service.impl;

import com.zdxu.domain.dto.GoodsSpecification;
import com.zdxu.domain.dto.SearchDTO;
import com.zdxu.domain.repository.GoodsRepository;
import com.zdxu.domain.repository.OrderGoodsRepository;
import com.zdxu.domain.entity.GoodsDO;
import com.zdxu.domain.entity.OrderGoodsDO;
import com.zdxu.service.GoodsService;
import com.zdxu.util.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaodexu on 2017/8/29.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    OrderGoodsRepository orderGoodsRepository;

    @Autowired
    GoodsUtil goodsUtil;

    @Override
    public Page<GoodsDO> pageGoodses(int orderId, SearchDTO searchDTO) {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        Pageable pageInfo = new PageRequest(searchDTO.getCurrentPageNo()-1, searchDTO.getPageSize(), sort);
        GoodsSpecification specification = new GoodsSpecification(searchDTO.getGoodsName(), searchDTO.getCategoryId());
        Page<GoodsDO> page = goodsRepository.findAll(specification, pageInfo);
        goodsUtil.filterGoodsSetIsOrderValue(orderId, page.getContent());
        return page;
    }

    @Override
    public List<GoodsDO> listGoods(List<Integer> goodsIds) {
        return goodsRepository.findByIdIn(goodsIds);
    }

    @Override
    public List<GoodsDO> listGoodsByOrderId(int orderId) {
        List<OrderGoodsDO> orderGoodsList = orderGoodsRepository.findByOrderId(orderId);
        if(orderGoodsList != null && orderGoodsList.size() > 0) {
            List<Integer> goodsIdList = new ArrayList<>();
            for (int i = 0; orderGoodsList != null && i < orderGoodsList.size(); i++) {
                OrderGoodsDO orderGoods = orderGoodsList.get(i);
                goodsIdList.add(orderGoods.getGoodsId());
            }
            List<GoodsDO> goodsList = goodsRepository.findByIdIn(goodsIdList);
            goodsUtil.filterGoodsSetIsOrderValue(orderId, goodsList);
            return goodsList;
        }
        return null;
    }
}
