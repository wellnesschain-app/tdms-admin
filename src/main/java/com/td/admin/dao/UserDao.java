package com.td.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<Map<String, Object>> findAll();
    String findTelByUid(String id);
    Map<String,Object> findByUid(String id);

    void update(Map<String, Object> map);

    List<Map<String, Object>> findByUserid(@Param("id")String id,@Param("account")String account,@Param("lock")String lock,@Param("start_time")String start_time,@Param("end_time")String end_time);


    void updateStatus(String id);

    void userUnlock(String id);

    Map<String, Object> findByAddr(String addr);

    void updatePwd(@Param("addr") String addr, @Param("password") String password);

    void updateAccount(@Param("addr") String addr, @Param("account") String account);


    Map<String, Object> findByAccount(String account);

    List<Map<String, Object>> findUserLevelSearch(String id);

    void updateSqPwd(@Param("addr") String addr, @Param("password") String password);
}
