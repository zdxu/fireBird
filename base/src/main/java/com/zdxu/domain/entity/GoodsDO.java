package com.zdxu.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhaodexu on 2017/8/27.
 */
@Data
@Entity
@Table(name = "t_goods")
public class GoodsDO extends BaseDO {

    @Id
    private int id;
    private String name;
    @Column(name = "shopid")
    private int shopId;
    private double price;
    private int category;
    private int status;
    private int sort;
    @Column(name = "imgurl")
    private String imgUrl;
    private String description;
    @Transient
    private int isOrder;
    @Transient
    private String categoryName;
}
