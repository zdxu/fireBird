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
@Table(name = "t_shop_user")
public class ShopUserDO extends BaseDO {
    @Id
    private int id;
    private String username;
    private String password;
    private int shopId;
    private int permission;
    private int role;
    private int status;
}
