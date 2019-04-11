package com.td.admin.dao;

import com.td.admin.entity.Audit;
//import Wallet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public interface AuditDao {

    List<String> findAll();

    List<Map<String, Object>> getFindAll();

    void updatestate(Integer id);

    List<Map<String, Object>> getFindId(Integer id);

    void rejected(Audit audit);


    String getWallet(String addr);

    String getcode(String wallet);

    Audit getById(Integer id);

    List<Map<String, Object>> getAgreedAll();

    List<Map<String, Object>> getRejectedAll();

    //void insert(String addr, long integral, Timestamp time);

    void insert(@Param("addr") String addr, @Param("integral") long integral, @Param("time") Timestamp time);
}
