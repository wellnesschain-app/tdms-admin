package com.td.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.dao.*;
import com.td.admin.service.BalanceService;
import com.td.admin.service.DocumentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DocumentaryServiceImpl implements DocumentaryService {

    @Autowired
    private NodeSetupDao nodeSetupDao;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BackstageTransferRecordDao backstageTransferRecordDao;
    @Autowired
    private DocumentaryDao documentaryDao;
    @Autowired
    private CancelOrderDao cancelOrderDao;

    /**
     * 撤单
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> cancelOrder(Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        String msg="ok";
        String addr = (String) map.get("addr");
        String value = (String) map.get("value");
        String order_num = (String) map.get("order_num");
        try {
            //查询用户当前余额
            Map<String, Object> user = balanceDao.findByAddr(addr);
            String a = user.get("balance").toString();
            BigDecimal aa = new BigDecimal(user.get("balance").toString());

            BigDecimal val = aa.add(new BigDecimal(value));
            //更新撤单用户积分余额
            map.put("balance",val);
            balanceDao.updateBalance(map);

            //撤单
            documentaryDao.update(map);

            //增加撤单记录
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());
            Map<String, Object> add = new HashMap<>();
            map.put("time",time);

            cancelOrderDao.add(map);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        res.put("msg",msg);

        return res;
    }

    /**
     * 获取挂单列表搜索页面分页记录
     * @param pageNum
     * @param pageSize
     * @param id
     * @param start_time
     * @param end_time
     * @return
     */
    @Override
    public Map<String, Object> findDocumentarySearchPageList(int pageNum, int pageSize, String id, String start_time, String end_time) {
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = documentaryDao.findDocumentarySearchList(id,start_time,end_time);
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("documentary",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取撤单分页记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> findCancelOrderPage(int pageNum, int pageSize) {
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = documentaryDao.findCancelOrder();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("cancelOrder",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取撤单搜索列表分页记录
     * @param pageNum
     * @param pageSize
     * @param id
     * @param start_time
     * @param end_time
     * @return
     */
    @Override
    public Map<String, Object> findCancelOrderSearchPage(int pageNum, int pageSize, String id, String start_time, String end_time) {
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = documentaryDao.findCancelOrderSearch(id,start_time,end_time);
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("cancelOrder",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


}
