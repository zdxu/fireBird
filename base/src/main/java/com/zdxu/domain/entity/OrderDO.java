package com.zdxu.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhaodexu on 2017/8/27.
 */
@Data
@Entity
@Table(name = "t_order")
public class OrderDO extends BaseDO {
    @Id
    private int id;
    @Column(name = "shopid")
    private int shopId;
    private int status;
    @Column(name = "waiterid")
    private int waiterId;
    @Column(name = "tabletattedcode")
    private String tableTattedCode;

    @Transient
    private double allPrice;
}
