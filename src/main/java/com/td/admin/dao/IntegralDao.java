package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IntegralDao {
    List<Map<String,Object>> findAll();

    List<Map<String, Object>> findRecord(String addr);

    Map<String, Object> editFind(String id);

    void update(Map<String, Object> map);

}
