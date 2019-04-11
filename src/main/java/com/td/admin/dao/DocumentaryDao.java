package com.td.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DocumentaryDao {


    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findSold();

    void update(Map<String, Object> map);

    List<Map<String, Object>> findDocumentarySearchList(@Param("id") String id, @Param("start_time") String start_time, @Param("end_time") String end_time);

    List<Map<String, Object>> findSoldSearch(@Param("id") String id, @Param("start_time") String start_time, @Param("end_time") String end_time);

    List<Map<String, Object>> findCancelOrder();

    List<Map<String, Object>> findCancelOrderSearch(@Param("id") String id, @Param("start_time") String start_time, @Param("end_time") String end_time);
}
