package com.zdxu.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhaodexu on 2017/8/30.
 */
@Data
@Entity
@Table(name = "t_category")
public class CategoryDO {

    @Id
    private int id;
    private String name;
    @Column(name = "shopid")
    private int shopId;
    private int sort;
}
