package com.td.admin.service.impl;

import com.td.admin.dao.*;
import com.td.admin.service.BalanceService;
import com.td.admin.service.NodeSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private NodeSetupDao nodeSetupDao;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BackstageTransferRecordDao backstageTransferRecordDao;

    /**
     * 转账加
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> update(Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        String msg="ok";
        String addr = (String) map.get("addr");
        String ba = (String) map.get("balance");
        String useBalance = (String) map.get("useBalance");
        String ustdBalance = (String) map.get("ustdBalance");
        try {
            //查询用户当前余额
            Map<String, Object> balance = balanceDao.findByAddr(addr);
            Map<String,Object> user = userDao.findByAddr(addr);

            if (user.get("status").toString().equals("2")){
                res.put("msg","账号已锁定,不能转账");
            }else {
                String a = balance.get("balance").toString();
                String b = balance.get("useBalance").toString();
                String c = balance.get("ustdBalance").toString();

                BigDecimal aa = new BigDecimal(a);
                BigDecimal bb = new BigDecimal(b);
                BigDecimal cc = new BigDecimal(c);

                BigDecimal d = new BigDecimal(0.00);
                if (ba != null && !"".equals(ba)){
                    BigDecimal balanceAdd = aa.add(new BigDecimal(ba));
                    if (balanceAdd.compareTo(new BigDecimal(0.00))==0){
                        map.put("balance","0.00");
                    }else {
                        map.put("balance",balanceAdd);
                    }

                }
                if (useBalance != null && !"".equals(useBalance)){
                    BigDecimal useBalanceAdd = bb.add(new BigDecimal(useBalance));
                    if (useBalanceAdd.compareTo(new BigDecimal(0.00))==0){
                        map.put("useBalance","0.00");
                    }else {
                        map.put("useBalance",useBalanceAdd);
                    }
                }
                if (ustdBalance != null && !"".equals(ustdBalance)){
                    BigDecimal ustdBalanceAdd = cc.add(new BigDecimal(ustdBalance));
                    if (ustdBalanceAdd.compareTo(new BigDecimal(0.00))==0){
                        map.put("ustdBalance","0.00");
                    }else {
                        map.put("ustdBalance",ustdBalanceAdd);
                    }
                }

                balanceDao.update(map);

                //增加转账记录
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String time = df.format(new Date());
                Map<String, Object> add = new HashMap<>();
                add.put("addr",addr);
                add.put("userid",user.get("id"));
                add.put("balance",ba);
                add.put("useBalance",useBalance);
                add.put("ustdBalance",ustdBalance);
                add.put("time",time);
                add.put("type",1);
                add.put("username",map.get("username").toString());
                add.put("remarks",map.get("remarks").toString());
                if ("".equals(ba)){
                    add.put("balance","0");
                }
                if ("".equals(useBalance)){
                    add.put("useBalance","0");
                }
                if ("".equals(ustdBalance)){
                    add.put("ustdBalance","0");
                }
                backstageTransferRecordDao.add(add);
                //userDao.update(map);
                res.put("msg",msg);
            }

        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        //res.put("msg",msg);

        return res;
    }

    /**
     * 转账减
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> updateReduce(Map<String, Object> map){
        Map<String, Object> res = new HashMap<>();
        String msg="ok";
        String addr = (String) map.get("addr");
        String ba = (String) map.get("balance");
        String useBalance = (String) map.get("useBalance");
        String ustdBalance = (String) map.get("ustdBalance");
        try {
            //查询用户当前余额
            Map<String, Object> balance = balanceDao.findByAddr(addr);
            Map<String,Object> user = userDao.findByAddr(addr);

            if (user.get("status").toString().equals("2")){
                res.put("msg","账号已锁定,不能转账");
            }else {
                String a = balance.get("balance").toString();
                String b = balance.get("useBalance").toString();
                String c = balance.get("ustdBalance").toString();

                BigDecimal aa = new BigDecimal(a);
                BigDecimal bb = new BigDecimal(b);
                BigDecimal cc = new BigDecimal(c);

                BigDecimal d = new BigDecimal(0.00);
                if (ba != null && !"".equals(ba)){
                    BigDecimal balanceAdd = aa.subtract(new BigDecimal(ba));
                    if (balanceAdd.compareTo(d)==0){
                        map.put("balance","0.00");
                    }else {
                        map.put("balance",balanceAdd);
                    }

                }
                if (useBalance != null && !"".equals(useBalance)){
                    BigDecimal useBalanceAdd = bb.subtract(new BigDecimal(useBalance));
                    if (useBalanceAdd.compareTo(d)==0){
                        map.put("useBalance","0.00");
                    }else {
                        map.put("useBalance",useBalanceAdd);
                    }

                }
                if (ustdBalance != null && !"".equals(ustdBalance)){
                    BigDecimal ustdBalanceAdd = cc.subtract(new BigDecimal(ustdBalance));
                    if (ustdBalanceAdd.compareTo(d)==0){
                        map.put("ustdBalance","0.00");
                    }else {
                        map.put("ustdBalance",ustdBalanceAdd);
                    }

                }

                balanceDao.update(map);

                //增加转账记录
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String time = df.format(new Date());
                Map<String, Object> add = new HashMap<>();
                add.put("addr",addr);
                add.put("userid",user.get("id"));
                add.put("balance",ba);
                add.put("useBalance",useBalance);
                add.put("ustdBalance",ustdBalance);
                add.put("time",time);
                add.put("type",2);
                add.put("username",map.get("username").toString());
                add.put("remarks",map.get("remarks").toString());
                if ("".equals(ba)){
                    add.put("balance","0");
                }
                if ("".equals(useBalance)){
                    add.put("useBalance","0");
                }
                if ("".equals(ustdBalance)){
                    add.put("ustdBalance","0");
                }
                backstageTransferRecordDao.add(add);
                //userDao.update(map);
                res.put("msg",msg);
            }

        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }

        return res;
    }



}
