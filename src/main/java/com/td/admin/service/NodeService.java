package com.td.admin.service;

import java.util.Map;

public interface NodeService {
    Map<String,Object> getBalance();
    Map<String,Object> transactionsForConstract(String to, String value);
}
