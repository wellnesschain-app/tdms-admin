package com.td.admin.dao;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface CancelOrderDao {


    void add(Map<String, Object> map);
}
