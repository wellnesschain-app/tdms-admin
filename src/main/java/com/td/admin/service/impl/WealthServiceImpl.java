package com.td.admin.service.impl;

import com.td.admin.dao.WealthDao;
import com.td.admin.entity.Wealth;
import com.td.admin.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WealthServiceImpl implements WealthService {

    @Autowired
    private WealthDao wealthDao;

    /**
     * 查询全部收益计划
     * @return
     */
    @Override
    public Map<String, Object> findAll() {
        Map<String, Object> map =new HashMap<>();
        String msg="ok";

        try {
            List<Wealth> list = wealthDao.findAll();
            map.put("data",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 添加财富计划
     * @param wealth
     * @return
     */
    @Override
    public Map<String, Object> addWealth(Wealth wealth) {
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            wealthDao.add(wealth);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 修改计划状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public Map<String, Object> upStatus(Integer id, Integer status) {
        Map<String, Object> map=new HashMap<>();
        String msg="ok";

        try {
            Wealth wealth = wealthDao.findById(id);
            wealth.setStatus(status);
            wealthDao.update(wealth);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }

        map.put("msg",msg);
        return map;
    }

    /**
     * 修改计划
     * @param wealth
     * @return
     */
    @Override
    public Map<String, Object> update(Wealth wealth) {
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            wealthDao.update(wealth);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);

        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        String msg="ok";
        try {
            wealthDao.delete(id);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


}
