package com.td.admin.service;


import com.td.admin.entity.Thumbnail;

import java.util.List;
import java.util.Map;

public interface ThumbnailService {
    Map<String,Object> addThumbnail(Thumbnail thumbnail);
    Map<String,Object> updThumbnail(Thumbnail thumbnail);
    Map<String,Object> delThumbnail(Integer id);
    List<Thumbnail> findAll();
    Map<String,Object> stop(Integer id);
    Map<String,Object> start(Integer id);
    Thumbnail findById(Integer id);
    Map<String,Object> delThumbnails(Integer[] arr);
}
