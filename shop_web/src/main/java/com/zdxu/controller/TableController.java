package com.zdxu.controller;

import com.zdxu.domain.entity.TableDO;
import com.zdxu.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhaodexu on 2017/8/31.
 */
@Controller
public class TableController {

    @Autowired
    TableService tableService;

    @RequestMapping("/shops/{shopId}/tables/{tableId}")
    public String table(@PathVariable("shopId") int shopId, @PathVariable("tableId") int tableId) {
        TableDO table = tableService.getTableById(tableId);
        String redirectUrl = "redirect:/shops/"+shopId+"/tables/"+table.getTattedCode()+"/order";
        return redirectUrl;
    }
}
