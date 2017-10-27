package com.zdxu.domain.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhaodexu on 2017/8/27.
 */
@Data
@Entity
@Table(name = "t_shop")
public class ShopDO extends BaseDO {

    @Id
    private int id;
    private String name;
    private String description;
    private int status;

}
