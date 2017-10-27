package com.zdxu.domain.repository;

import com.zdxu.domain.entity.OrderDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by zhaodexu on 2017/8/29.
 */
public interface OrderRepository extends CrudRepository<OrderDO, Integer>{

    OrderDO findByTableTattedCode(String tableTattedCode);

    OrderDO findById(int orderId);

    @Modifying
    @Query(value = "update t_order set status = :status where id = :orderId", nativeQuery = true)
    void updateOrderStatus(@Param("status") int status, @Param("orderId") int orderId);
}
