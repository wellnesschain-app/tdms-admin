package com.td.admin.service.impl;

import com.td.admin.dao.*;
import com.td.admin.entity.Assets;
import com.td.admin.entity.Audit;
import com.td.admin.entity.Wallet;
import com.td.admin.service.AuditService;
import com.td.admin.service.NodeSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class NodeSetupServiceImpl implements NodeSetupService {

    @Autowired
    private NodeSetupDao nodeSetupDao;
    @Autowired
    private IntegralDao integralDao;

    /**
     * 修改
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> update(Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        String msg="ok";

        try {
            nodeSetupDao.update(map);
            nodeSetupDao.updateFrequency(map);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        res.put("msg",msg);

        return res;
    }

    @Override
    public Map<String, Object> updatePercentage(Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        String msg="ok";

        try {
            integralDao.update(map);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        res.put("msg",msg);

        return res;
    }


    /**
     * 修改手续费
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> EditProcedures(Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        String msg="ok";

        try {
            nodeSetupDao.updateProcedures(map);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        res.put("msg",msg);

        return res;
    }





}
