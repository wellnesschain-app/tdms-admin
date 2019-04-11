package com.td.admin.service;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    Map<String, Object> insert(Map<String, Object> map);//添加公告
    List<Map<String,Object>> findAll();
    Map<String,Object> upload(MultipartFile file);
    Map<String,Object> findById(Integer id);
    Map<String,Object> delete(Integer id);
    Map<String,Object> delete(Integer[] arr);
    Map<String,Object> update(Map<String, Object> map);
}
