package com.zdxu.domain.repository;

import com.zdxu.domain.entity.TableDO;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaodexu on 2017/8/29.
 */
public interface TableRepository extends CrudRepository<TableDO, Integer>{

    long countByTattedCode(String tattedCode);

    TableDO findById(int id);
}
