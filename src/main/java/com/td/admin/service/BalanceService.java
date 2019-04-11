package com.td.admin.service;

import java.util.Map;

public interface BalanceService {

    Map<String, Object> update(Map<String, Object> map);

    Map<String, Object> updateReduce(Map<String, Object> params);
}
