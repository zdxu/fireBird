package com.zdxu.service;

import com.zdxu.domain.entity.TableDO;

/**
 * Created by zhaodexu on 2017/8/31.
 */
public interface TableService {

    boolean isCurrentTable(String tattedCode);

    TableDO getTableById(int id);
}
