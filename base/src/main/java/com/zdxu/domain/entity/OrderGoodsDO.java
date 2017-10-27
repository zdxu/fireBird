package com.zdxu.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhaodexu on 2017/8/27.
 */
@Data
@Entity
@Table(name = "t_order_goods")
public class OrderGoodsDO {
    @Id
    private int id;
    @Column(name = "orderid")
    private int orderId;
    @Column(name = "goodsid")
    private int goodsId;
    private int status;
    @Column(name = "createtime")
    private int createTime;
}
