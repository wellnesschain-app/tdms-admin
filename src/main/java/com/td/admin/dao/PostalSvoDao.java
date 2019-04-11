package com.td.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostalSvoDao {
    void add(Map<String, Object> map);
    List<Map<String,Object>> findAllAuditing();
    List<Map<String,Object>> findAllAdopting();
    List<Map<String,Object>> findAllUnAdopting();
    Map<String,Object> findById(String id);
    void upStatus(Map<String, Object> map);
    Map<String,Object> findAuditingById(String id);
    Map<String,Object> findAllAdoptingById(String id);
    Map<String,Object> findAllUnAdoptingById(String id);

    List<Map<String, Object>> findPage( @Param("id") String id,@Param("status") String status);
}
