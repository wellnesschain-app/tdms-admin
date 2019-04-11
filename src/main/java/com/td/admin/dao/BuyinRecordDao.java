package com.td.admin.dao;

import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyinRecordDao {
    List<MappingChange.Map<String,Object>> findAll();
    List<MappingChange.Map<String,Object>> findAllByUid(String uid);
}
