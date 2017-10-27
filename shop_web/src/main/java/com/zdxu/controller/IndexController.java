package com.zdxu.controller;

import com.zdxu.constant.Syscode;
import com.zdxu.domain.dto.SearchDTO;
import com.zdxu.domain.entity.*;
import com.zdxu.service.*;
import com.zdxu.util.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaodexu on 2017/8/30.
 */
@Controller
public class IndexController {

    @Autowired
    ShopService shopService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TableService tableService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsUtil goodsUtil;

    @RequestMapping("/shops/{shopId}/tables/{tattedCode}/order")
    public String index(@PathVariable("shopId") int shopId, @PathVariable("tattedCode") String tattedCode,
                        Map<String, Object> model) {
        boolean isCurrentTable = tableService.isCurrentTable(tattedCode);
        if(!isCurrentTable)
            return "redirect:/";
        OrderDO order = orderService.getOrderByTableTattedCode(tattedCode);
        ShopDO shop = shopService.getShopByShopId(shopId);
        List<CategoryDO> categoryList = categoryService.listCategoryByShopId(shopId);
        Page<GoodsDO> goodsPage = null;
        if(order != null) {
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setCategoryId(categoryList.get(0).getId());
            searchDTO.setPageSize(Syscode.pageSize);
            searchDTO.setCurrentPageNo(Syscode.currentPageNo);
            goodsPage = goodsService.pageGoodses(order.getId(), searchDTO);
        }
        model.put("shop", shop);
        model.put("categorys", categoryList);
        model.put("order", order);
        model.put("goodsPage", goodsPage);
        return "index";
    }
}
