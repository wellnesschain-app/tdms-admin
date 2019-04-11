package com.td.admin.service;

import java.util.Map;

public interface DocumentaryService {


    Map<String, Object> cancelOrder(Map<String,Object> map);

    Map<String, Object> findDocumentarySearchPageList(int pageNum, int pageSize, String id, String start_time, String end_time);

    Map<String, Object> findCancelOrderPage(int pageNum, int pageSize);

    Map<String, Object> findCancelOrderSearchPage(int pageNum, int pageSize, String id, String start_time, String end_time);
}
