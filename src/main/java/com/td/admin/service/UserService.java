package com.td.admin.service;

import java.util.Map;

public interface UserService {

    Map<String, Object> updateStatus(String id);

    Map<String, Object> update(Map<String, Object> map);

    Map<String, Object> userUnlock(String id);

    Map<String, Object> updatePwd(String addr, String pwd);

    Map<String, Object> updateAccount(String addr, String account);

    Map<String, Object> updateSqPwd(String addr, String sqPwd);
}
