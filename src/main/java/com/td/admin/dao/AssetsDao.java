package com.td.admin.dao;


import com.td.admin.entity.Assets;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AssetsDao {

    Assets findByWid(Long id);

    void update(Assets assets);//更新资产
}
