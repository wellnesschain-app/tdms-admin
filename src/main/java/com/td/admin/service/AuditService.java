package com.td.admin.service;

import java.util.Map;

public interface AuditService {


    Map<String, Object> updatestate(Integer id);

    Map<String, Object> rejected(Integer id,String text,String addr,Integer integral);
}
