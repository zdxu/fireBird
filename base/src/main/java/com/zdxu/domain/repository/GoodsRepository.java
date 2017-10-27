package com.zdxu.domain.repository;

import com.zdxu.domain.entity.GoodsDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaodexu on 2017/8/29.
 */
public interface GoodsRepository extends CrudRepository<GoodsDO, Integer>{

    Page<GoodsDO> findAll(Specification<GoodsDO> specification, Pageable pageInfo);

    List<GoodsDO> findByIdIn(List<Integer> goodsIds);

}
