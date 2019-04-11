package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*公告DAO，无实体类*/
@Repository
public interface NoticeDao {
    void add(Map<String, Object> map);
    void del(Integer id);
    void upd(Map<String, Object> map);
    Map<String,Object> findById(Integer id);
    List<Map<String,Object>> findAll();
}
