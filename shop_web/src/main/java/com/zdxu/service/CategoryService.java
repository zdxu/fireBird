package com.zdxu.service;

import com.zdxu.domain.entity.CategoryDO;

import java.util.List;

/**
 * Created by zhaodexu on 2017/8/30.
 */
public interface CategoryService {

    List<CategoryDO> listCategoryByShopId(int shopId);
}
