package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostUserAuthenDao {
    List<Map<String,Object>> findByUid(String uid);
    List<Map<String,Object>> findAllAudit();
    List<Map<String,Object>> findAllAudopt();
    List<Map<String,Object>> findAllunAudopt();
    Map<String,Object> findById(String id);
    void update(Map<String, Object> map);
}
