package com.td.admin.dao;

import com.td.admin.entity.Thumbnail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThumbnailDao {
    void add(Thumbnail thumbnail);
    void delete(Integer id);
    void update(Thumbnail thumbnail);
    List<Thumbnail> findAll();
    Thumbnail findById(Integer id);
}
