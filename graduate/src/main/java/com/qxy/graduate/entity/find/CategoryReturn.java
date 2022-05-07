package com.qxy.graduate.entity.find;

import lombok.Data;

import java.util.List;

@Data
public class CategoryReturn {
    public int totalService;

    public List<Category> categoryList;
}
