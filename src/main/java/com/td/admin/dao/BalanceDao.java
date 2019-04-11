package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BalanceDao {
    void add(Map<String, Object> map);
    Map<String,Object> findByAddr(String addr);
    void update(Map<String, Object> map);

    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findById(String id);

    void updateBalance(Map<String, Object> map);
}
