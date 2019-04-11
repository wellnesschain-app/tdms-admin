package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NodeSetupDao {

    List<Map<String, Object>> findAll();

    Map<String, Object> findById(Integer id);


    void update(Map<String, Object> map);

    void updateFrequency(Map<String, Object> map);

    List<Map<String, Object>> findProcedures();

    List<Map<String, Object>> findService();

    Map<String, Object> findProceduresById(Integer id);

    void updateProcedures(Map<String, Object> map);

    List<Map<String, Object>> findPercentage();
}
