package com.td.admin.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@Repository
public interface MemberGiveCoreRecordDao {//积分放送记录（会员/代理）
    void add(Map<String, Object> map);
}
