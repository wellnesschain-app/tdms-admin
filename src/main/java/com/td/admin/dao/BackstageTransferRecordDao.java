package com.td.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//提币交易记录(当管理员审核通过后，发起自动交易后写入记录)
@Repository
public interface BackstageTransferRecordDao {


    void add(Map<String, Object> add);
}
