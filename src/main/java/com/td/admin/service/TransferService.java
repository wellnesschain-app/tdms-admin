package com.td.admin.service;


import com.td.admin.entity.Thumbnail;

import java.util.List;
import java.util.Map;

public interface TransferService {


    String findAllUsdt(int pageNum, int pageSize, Map<String, Object> map);

    Map<String, Object> findAllUSDTTransfer(int pageNum, int pageSize);

    String findWnctStreamSearch(int pageNum, int pageSize, String addr, String start_time, String end_time,Map<String, Object> map);

    Map<String, Object> findWNCTStreamSearchPage(int pageNum, int pageSize, String addr, String start_time, String end_time);

    String findUSDTStreamSearch(int pageNum, int pageSize, String addr, String start_time, String end_time, Map<String, Object> map);

    Map<String, Object> findUSDTStreamSearchPage(int pageNum, int pageSize, String addr, String start_time, String end_time);
}
