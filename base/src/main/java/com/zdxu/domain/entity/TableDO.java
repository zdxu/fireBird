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
@Table(name = "t_table")
public class TableDO extends BaseDO {

    @Id
    private int id;
    private String name;
    @Column(name = "shopid")
    private int shopId;
    private int status;
    @Column(name = "tattedcode")
    private String tattedCode;

}
