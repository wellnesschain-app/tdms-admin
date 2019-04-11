package com.td.admin.service;

import java.util.Map;

public interface NodeSetupService {

    Map<String, Object> update(Map<String, Object> map);

    Map<String, Object> updatePercentage(Map<String, Object> params);

    Map<String, Object> EditProcedures(Map<String, Object> params);
}
