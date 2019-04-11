package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BonusDao {

    List<Map<String, Object>> findRecord(String id);
}
