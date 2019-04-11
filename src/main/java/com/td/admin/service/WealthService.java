package com.td.admin.service;

import com.td.admin.entity.Wealth;

import java.util.Map;

public interface WealthService {

    Map<String,Object> findAll();

    Map<String,Object> addWealth(Wealth wealth);

    Map<String,Object> upStatus(Integer id, Integer status);

    Map<String,Object> update(Wealth wealth);

    Map<String,Object> delete(Integer id);
}
