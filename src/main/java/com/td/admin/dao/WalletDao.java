package com.td.admin.dao;

import com.td.admin.entity.Wallet;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface WalletDao {
    void add(Map<String, Object> map);
    Map<String,Object> getQRCode(String addr);
    void setQRcode(Map<String, Object> map);

    Wallet findByAddr(String addr);
}
