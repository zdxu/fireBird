package com.zdxu.domain.dto;

import lombok.Data;

/**
 * Created by zhaodexu on 2017/9/1.
 */
@Data
public class SearchDTO {

    private int categoryId;
    private String goodsName;
    private int currentPageNo;
    private int pageSize;
}
