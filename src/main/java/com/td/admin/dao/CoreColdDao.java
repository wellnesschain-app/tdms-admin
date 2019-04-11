package com.td.admin.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 积分冻结表
 */
@Transactional
@Repository
public interface CoreColdDao {
    void add(Map<String, Object> map);
}
