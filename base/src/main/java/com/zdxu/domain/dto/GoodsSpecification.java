package com.zdxu.domain.dto;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaodexu on 2017/9/1.
 */
public class GoodsSpecification<GoodsVO> implements Specification<GoodsVO> {

    private String goodsName;
    private int category;

    public GoodsSpecification(String goodsName, int category) {
        this.goodsName = goodsName;
        this.category = category;
    }

    @Override
    public Predicate toPredicate(Root<GoodsVO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<String> goodsNamePath = root.get("name");
        Path<Integer> categoryIdPath = root.get("category");
        List<Predicate> predicateList = new ArrayList<>();
        if(!StringUtils.isEmpty(goodsName)) {
            predicateList.add(cb.like(goodsNamePath, "%"+goodsName+"%"));
        }
        if(category > 0) {
            predicateList.add(cb.equal(categoryIdPath, category));
        }
        int size = predicateList.size();
        if(size > 0) {
            final Predicate[] predicates = new Predicate[size];
            for (int i = 0; i < size; i++) {
                predicates[i] = predicateList.get(i);
            }
            query.where(predicates);
        }
        return null;
    }
}
