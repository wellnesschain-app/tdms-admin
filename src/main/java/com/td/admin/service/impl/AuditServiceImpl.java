package com.td.admin.service.impl;

import com.td.admin.dao.*;
import com.td.admin.dao.*;
import com.td.admin.entity.Assets;
import com.td.admin.entity.Audit;
import com.td.admin.entity.Wallet;
import com.td.admin.service.AuditService;
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
public class AuditServiceImpl implements AuditService {

    @Autowired
    private WealthDao wealthDao;
    @Autowired
    private AuditDao auditDao;
    @Autowired
    private WalletDao walletDao;
    @Autowired
    private AssetsDao assetsDao;
    @Autowired
    private BalanceDao balanceDao;
    /**
     * 同意
     * @return
     */
    @Override
    public Map<String, Object> updatestate(Integer id) {
        Map<String, Object> map =new HashMap<>();
        String msg="ok";

        try {
            Audit audit = auditDao.getById(id);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            Timestamp time = new Timestamp(new java.sql.Date(System.currentTimeMillis()).getTime());

            String addr = audit.getAddr();

            long integral = audit.getIntegral();

            //插入兑换FST记录
            auditDao.insert(addr,integral,time);
            //修改状态为审核通过
            auditDao.updatestate(id);

            //更新余额表
            Map<String, Object> balance = balanceDao.findByAddr(addr);
            BigDecimal sum = new BigDecimal(balance.get("F03").toString());//总余额
            BigDecimal useBalance = new BigDecimal(balance.get("F05").toString());

            balance.put("balance",sum.add(new BigDecimal(integral)));
            balance.put("useBalance",useBalance.add(new BigDecimal(integral)));
            balanceDao.update(balance);
            map.put("msg",msg);
        } catch (Exception e) {
            map.put("msg","错误");
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 驳回
     * @param id
     * @param text
     * @return
     */
    @Override
    public Map<String, Object> rejected(Integer id,String text,String addr,Integer integral) {
        Map<String, Object> map =new HashMap<>();
        String msg="ok";

        try {
            //Audit audit = auditDao.getById(id);
            Audit audits = new Audit();
            audits.setId(id);
            audits.setReason(text);
            //驳回兑换申请
            auditDao.rejected(audits);
            //查询钱包id
            //String wallet = auditDao.getWallet(addr);
            Wallet wallet = walletDao.findByAddr(addr);

            //查询资产
            Assets assets = assetsDao.findByWid(wallet.getId());
            /*System.out.println("资产："+assets);*/
            //积分余额
            double count = assets.getCore() + integral;

            //可用积分
            double countA = assets.getUseAssets() + integral;
            BigDecimal countAA = new BigDecimal(countA);
            double counts = countAA.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();

            assets.setUseAssets(counts);
            assets.setCore(count);
            assetsDao.update(assets);


            map.put("msg",msg);
        } catch (Exception e) {
            map.put("msg","错误");
            e.printStackTrace();
        }

        return map;
    }



}
