package com.qxy.graduate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.entity.ServiceTotal;
import com.qxy.graduate.entity.find.CategoryContent;
import com.qxy.graduate.entity.find.CategoryReturn;


public interface IServiceTotal extends IService<ServiceTotal> {
    ReturnData<CategoryReturn> getCategories();

    ReturnData<IPage<CategoryContent>> getCategoryContent(String categoryOne, String categoryTwo, Page<CategoryContent> page);
}
