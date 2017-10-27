package com.zdxu.service.impl;

import com.zdxu.domain.repository.CategoryRepository;
import com.zdxu.domain.entity.CategoryDO;
import com.zdxu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaodexu on 2017/8/30.
 */
@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDO> listCategoryByShopId(int shopId) {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return categoryRepository.findByShopId(shopId, sort);
    }
}
