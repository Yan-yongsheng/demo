package com.qxy.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qxy.graduate.entity.ServiceTotal;
import com.qxy.graduate.entity.find.Category;
import com.qxy.graduate.entity.find.CategoryContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTotalMapper extends BaseMapper<ServiceTotal> {
    List<Category> getCategories();

    List<CategoryContent> getCategoryContent(@Param("categoryOne") String categoryOne,
                                              @Param("categoryTwo") String categoryTwo,
                                              Page<CategoryContent> page);
}
