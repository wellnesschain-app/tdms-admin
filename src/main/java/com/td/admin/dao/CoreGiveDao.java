package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CoreGiveDao {
    void add(Map<String, Object> map);
    List<Map<String,Object>> findAll();
}
