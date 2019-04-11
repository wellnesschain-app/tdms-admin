package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserAuthenDao {
    void add(Map<String, Object> map);
    Map<String,Object> getAuthenInfo(String id);
    Map<String,Object> findByUid(String uid);
}
