package com.zdxu.service;

import com.zdxu.domain.dto.SearchDTO;
import com.zdxu.domain.entity.GoodsDO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by zhaodexu on 2017/8/29.
 */
public interface GoodsService {

    Page<GoodsDO> pageGoodses(int orderId, SearchDTO searchDTO);

    List<GoodsDO> listGoods(List<Integer> goodsIds);

    List<GoodsDO> listGoodsByOrderId(int orderId);
}
