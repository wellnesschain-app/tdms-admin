package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TurninCalculationRecordDao {
    void add(Map<String, Object> map);
    void turnOut(Map<String, Object> map);
    String getSumBuyinByUid(String uid);
    Integer getAllByUidTotal(String uid);
    List<Map<String,Object>> findAllByUid(String uid);

    int findAllByAddrCount(String uid);

    List<Map<String, Object>> findTransaction(String uid);

    List<Map<String, Object>> findAll();
}
