package com.zdxu.domain.repository;

import com.zdxu.domain.entity.ShopDO;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaodexu on 2017/8/29.
 */
public interface ShopRepository extends CrudRepository<ShopDO, Integer> {

    ShopDO findById(int shopId);
}
