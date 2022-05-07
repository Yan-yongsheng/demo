package com.qxy.graduate.service;

import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.entity.ServiceTotal;
import com.qxy.graduate.entity.find.SearchResult;
import org.apache.lucene.search.TopDocs;

import java.util.List;

public interface ISearchService {
    ReturnData<SearchResult> searchForService(String keyWords, int page, int pageSize);
    void createIndex(List<ServiceTotal> serviceTotalList);
    List<String> getIkResolveWords(String text);
    TopDocs getTopDocs(String text, int limits) throws Exception;
    ServiceTotal getServiceTotalFromDocId(int docId) throws Exception;
}
