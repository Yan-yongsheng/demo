package com.qxy.graduate.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.service.ISearchService;
import com.qxy.graduate.service.IServiceTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceTotal")
public class ServiceTotalController {
    @Autowired
    private IServiceTotal serviceTotal;

    @Autowired
    private ISearchService searchService;

    @GetMapping("/category")
    public ReturnData getCategories() {
        return serviceTotal.getCategories();
    }

    @GetMapping("/category/content")
    public ReturnData getCategoryContent(String categoryOne, String categoryTwo, int currentPage, int pageSize) {
        return serviceTotal.getCategoryContent(categoryOne, categoryTwo, new Page<>(currentPage, pageSize));
    }

    @GetMapping("/search")
    public ReturnData getSearchResult(String keyWords,int page,int pageSize){
        return searchService.searchForService(keyWords, page, pageSize);
    }
}
