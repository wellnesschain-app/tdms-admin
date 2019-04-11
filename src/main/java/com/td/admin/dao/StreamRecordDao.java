package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StreamRecordDao {
    List<Map<String,Object>> findAll();
}
