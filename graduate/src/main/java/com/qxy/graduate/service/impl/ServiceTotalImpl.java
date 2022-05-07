package com.qxy.graduate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.common.StateCode;
import com.qxy.graduate.entity.ServiceTotal;
import com.qxy.graduate.entity.find.CategoryContent;
import com.qxy.graduate.entity.find.CategoryReturn;
import com.qxy.graduate.mapper.ServiceTotalMapper;
import com.qxy.graduate.service.IServiceTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTotalImpl extends ServiceImpl<ServiceTotalMapper, ServiceTotal> implements IServiceTotal {
    @Autowired
    private ServiceTotalMapper serviceTotalMapper;

    @Override
    public ReturnData<CategoryReturn> getCategories() {
        try {
            CategoryReturn categoryReturn = new CategoryReturn();
            categoryReturn.setCategoryList(serviceTotalMapper.getCategories());
            categoryReturn.setTotalService(serviceTotalMapper.selectCount(null));
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), categoryReturn);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.SUCCESS.getMsg(), null);
        }
    }

    @Override
    public ReturnData<IPage<CategoryContent>> getCategoryContent(String categoryOne, String categoryTwo, Page<CategoryContent> page) {
        try {
            List<CategoryContent> categoryContents = serviceTotalMapper.getCategoryContent(categoryOne, categoryTwo, page);
            page.setRecords(categoryContents);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }
}
