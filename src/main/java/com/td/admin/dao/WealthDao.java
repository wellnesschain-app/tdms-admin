package com.td.admin.dao;

import com.td.admin.entity.Wealth;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WealthDao {
    List<Wealth> findAll();

    void add(Wealth wealth);

    Wealth findById(Integer id);

    void update(Wealth wealth);

    void delete(Integer id);

    List<Map<String, Object>> findAlls();
}
