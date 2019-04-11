package com.td.admin.dao;

import com.td.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {
    Admin findByUsername(String account);

    void update(Map<String, Object> map);

    List<Map<String, Object>> getRegistered();

    Map<String, Object> findtotalnumber();


    Map<String, Object> findIntegralBalance();

    int codenumber();

    Map<String, Object> findRegisteredNumber();

    Map<String, Object> findCodeNumber();

    Map<String, Object> findProvince();

    int findPrepaidUsers();

    int findPlaceOrder();

    int findPlaceOrderUser();


    String getRelease();

    int getOrder();

    String getDynamic();

    List<Map<String, Object>> findAdmin();

    int add(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname,@Param("role") String role);

    Map<String, Object> findByRole(String username);

    int updateRole(@Param("id") String id,@Param("nickname") String nickname,@Param("role") String role);

    void delete(String id);
}
