package com.zdxu.domain.repository;

import com.zdxu.domain.entity.CategoryDO;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaodexu on 2017/8/30.
 */
public interface CategoryRepository extends CrudRepository<CategoryDO, Integer> {

    List<CategoryDO> findByShopId(int shopId, Sort sort);
}
