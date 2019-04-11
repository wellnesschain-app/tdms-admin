package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FeeDao {
    void add(Map<String, Object> map);
    Map<String,Object> getFees();
}
