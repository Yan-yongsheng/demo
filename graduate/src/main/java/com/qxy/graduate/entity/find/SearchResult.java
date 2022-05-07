package com.qxy.graduate.entity.find;

import com.qxy.graduate.entity.ServiceTotal;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    public long totalService;

    public List<ServiceTotal> serviceResult;
}
