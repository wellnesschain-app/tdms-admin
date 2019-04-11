package com.td.admin.service;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PostalSvoService {

    Map<String, Object> findPage(String id, String status, int pageNum, int pageSize);
}
