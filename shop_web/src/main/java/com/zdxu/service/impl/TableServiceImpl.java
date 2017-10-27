package com.zdxu.service.impl;

import com.zdxu.domain.repository.TableRepository;
import com.zdxu.domain.entity.TableDO;
import com.zdxu.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhaodexu on 2017/8/31.
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepository tableRepository;

    @Override
    public boolean isCurrentTable(String tattedCode) {
        long count = tableRepository.countByTattedCode(tattedCode);
        if(count == 1)
            return true;
        else
            return false;
    }

    @Override
    public TableDO getTableById(int id) {
        return tableRepository.findById(id);
    }
}
