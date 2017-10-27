package com.zdxu.domain.repository;

import com.zdxu.domain.entity.OrderGoodsDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaodexu on 2017/8/29.
 */
public interface OrderGoodsRepository extends CrudRepository<OrderGoodsDO, Integer>{

    List<OrderGoodsDO> findByOrderId(int orderId);

    @Query(value = "update t_order_goods set status = ?1 where orderId = ?2 and goodsId = ?3", nativeQuery = true)
    void updateOrderGoodsStatus(int status, int orderId, int goodsId);
}
