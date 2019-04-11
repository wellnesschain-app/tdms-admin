package com.td.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransMapper {
    void add(Map<String, Object> map);
    List<Map<String,Object>> findAllCore();//查询全部积分交易
    List<Map<String,Object>> findAllFST();//查询全部FST交易
    List<String> findToAddrByAddr(String addr);//根据当前用户钱包地址获取全部转出过的钱包地址
    List<Map<String,Object>> findAllByAddr(String addr);//根据当前钱包查询全部积分交易记录

    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findSold();

    List<Map<String, Object>> findSystemTransfer();

    List<Map<String, Object>> findStaticBonus();

    List<Map<String, Object>> findJFTransfer();

    List<Map<String, Object>> findWNCTTransfer();

    List<Map<String, Object>> findDynamicBonus();

    List<Map<String, Object>> findDynamicBonusRecord(String id);

    List<Map<String, Object>> findStaticBonusRecord(String id);

    List<Map<String, Object>> findJFTransferSearch(@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("fromid") String fromid, @Param("toid") String toid);

    List<Map<String, Object>> findWNCTTransferSearch(@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("fromid") String fromid, @Param("toid") String toid);

    List<Map<String, Object>> findUSDTTransfer();

    List<Map<String, Object>> findUSDTTransferSearch(@Param("start_time") String start_time, @Param("end_time") String end_time, @Param("fromid") String fromid, @Param("toid") String toid);

    List<Map<String, Object>> findSystemTransferSearch(@Param("id") String id,@Param("start_time") String start_time, @Param("end_time") String end_time);

    List<Map<String, Object>> findAppSystemTransfer(String userid);

    List<Map<String, Object>> findAllUsdt();

    List<Map<String, Object>> findStaticBonusSearch(String id);

    List<Map<String, Object>> findDynamicBonusSearch(String id);

    List<Map<String, Object>> findSystemTransferCount();

    List<Map<String, Object>> findSystemTransferSearchCount(@Param("id") String id);

    List<Map<String, Object>> findWnctStreamSearch(@Param("addr") String addr, @Param("start_time") String start_time, @Param("end_time") String end_time);

    List<Map<String, Object>> findUSDTStreamSearch(@Param("addr") String addr, @Param("start_time") String start_time, @Param("end_time") String end_time);

    List<Map<String, Object>> findSoldSearch(@Param("id") String id, @Param("start_time") String start_time, @Param("end_time") String end_time);
}
