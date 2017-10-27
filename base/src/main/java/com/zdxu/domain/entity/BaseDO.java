package com.zdxu.domain.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * Created by zhaodexu on 2017/8/29.
 */
@Data
public class BaseDO {
    @Column(name = "createtime")
    private int createTime;
    @Column(name = "createuser")
    private String createUser;
    @Column(name = "updatetime")
    private int updateTime;
    @Column(name = "updateuser")
    private String updateUser;
}
