package com.td.admin.controller;

import com.td.admin.dao.*;
import com.td.admin.dao.*;
import com.td.admin.entity.Wealth;
import com.td.admin.service.AuditService;
import com.td.admin.service.PostUserAuthenService;
import com.td.admin.service.PostalSvoService;
import com.td.admin.util.Btc;
import com.td.admin.util.EthereumEntity;
import com.td.admin.util.SendMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.util.Wnct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/audit")
public class AuditController {

    @Autowired
    private WealthDao wealthDao;
    @Autowired
    private AuditDao auditDao;
    @Autowired
    private AuditService auditService;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private PostalSvoDao postalSvoDao;
    @Autowired
    private PostUserAuthenService postUserAuthenService;
    @Autowired
    private PostUserAuthenDao postUserAuthenDao;
    @Autowired
    private UserAuthenDao userAuthenDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PostalSvoService postalSvoService;

    Btc btc =new Btc();

    Wnct wnct =new Wnct();



    /**
     * 驳回用户高级实名审核
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unAdoptUser")
    public Map<String,Object> unAdoptUser(String id,String text){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";

        try {

            Map<String, Object> postal = postUserAuthenDao.findById(id);

            if(postal!=null){
                postal.put("status",2);
                postal.put("rejectMsg",text);
                Map<String, Object> res = postUserAuthenService.update(postal);
                if(!res.get("msg").equals("ok")){
                    msg=res.get("msg").toString();
                }else{
                    String tel = userDao.findTelByUid(postal.get("uid").toString());
                    SendMsg.sendMsg("【paycoin】您的高级实名认证审核未通过："+text,tel, "PAC", "http://106.14.55.160:9000/HttpSmsMt", "gxlxtz01", "5e953f4cf9ef0bfeb4909c29e2371ee3", "");
                }
            }else{
                msg="该记录不存在或已丢失";
            }
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 通过用户高级实名审核
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adoptUser")
    public Map<String,Object> adoptUser(String id){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";

        try {

            Map<String, Object> postal = postUserAuthenDao.findById(id);
            if(postal!=null){
                //转账成功后,改变审核状态
                postal.put("status",1);
                Map<String, Object> res = postUserAuthenService.update(postal);
                if(!res.get("msg").equals("ok")){
                    msg=res.get("msg").toString();
                }else{
                    Map<String, Object> u = userAuthenDao.findByUid(postal.get("uid").toString());
                    if(u==null){
                        //写入实名认证信息表
                        Map<String,Object> userAuthenInfo = new HashMap<>();
                        userAuthenInfo.put("uid",postal.get("uid"));
                        userAuthenInfo.put("img1",postal.get("img1"));
                        userAuthenInfo.put("img2",postal.get("img2"));
                        userAuthenInfo.put("img3",postal.get("img3"));
                        userAuthenDao.add(userAuthenInfo);
                        String tel = userDao.findTelByUid(postal.get("uid").toString());
                        SendMsg.sendMsg("【paycoin】您的高级实名认证已审核通过！",tel, "PAC", "http://106.14.55.160:9000/HttpSmsMt", "gxlxtz01", "5e953f4cf9ef0bfeb4909c29e2371ee3", "");
                    }

                }

            }else{
                msg="该记录不存在或已丢失";
            }
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 获取用户实名审核通过列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserUnAudoptList")
    public Map<String,Object> getUserUnAudoptList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postUserAuthenDao.findAllunAudopt();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("userAudits",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }
    /**
     * 请求用户实名审核未通过列表
     * @return
     */
    @RequestMapping(value = "/toUserUnAdoptList")
    public String toUserUnAdoptList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "5")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = postUserAuthenDao.findAllunAudopt();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("userAudits",pages);
        return "/admin/audit/user-unAdopt-list";
    }


    /**
     * 获取用户实名审核通过列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserAudoptList")
    public Map<String,Object> getUserAudoptList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postUserAuthenDao.findAllAudopt();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("userAudits",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }
    /**
     * 请求用户实名审核列表
     * @return
     */
    @RequestMapping(value = "/toUserAdoptList")
    public String toUserAudoptList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "5")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = postUserAuthenDao.findAllAudopt();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("userAudits",pages);
        return "/admin/audit/user-adopt-list";
    }

    /**
     * 获取用户实名审核列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserAuditList")
    public Map<String,Object> getUserAuditList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postUserAuthenDao.findAllAudit();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("userAudits",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 请求用户实名审核列表
     * @return
     */
    @RequestMapping(value = "/toUserAuditList")
    public String toUserAuditList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = postUserAuthenDao.findAllAudit();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("userAudits",pages);
        return "/admin/audit/user-audit-list";
    }


    /**
     * 审核驳回页面搜索
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/unAdoptSearch")
    public String unAdoptSearch(String id,Map<String,Object> map){
        Map<String, Object> audits = postalSvoDao.findById(id);
        map.put("unAdoptSearch",audits);
        return "/admin/audit/svo-unAdopt-search-list";
    }


    /**
     * 审核通过页面搜索
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/adoptSearch")
    public String adoptSearch(String id,Map<String,Object> map){
        Map<String, Object> audits = postalSvoDao.findById(id);
        map.put("adoptSearch",audits);
        return "admin/audit/pac-adopt-search-list";
    }

    /**
     * 待审核页面搜索
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/auditSearch")
    public String auditSearch(String id,String status,Map<String,Object> map,
                              @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> audits = postalSvoDao.findPage(id,status);
        PageInfo<Map<String, Object>> pages = new PageInfo<>(audits);
        map.put("audits",pages);
        map.put("id",id);
        return "/admin/audit/svo-audit-search-list";
    }

    /**
     * 分页
     * @param id
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/auditSearchPage")
    public Map<String,Object> auditSearchPage(String id,String status,
                                              @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return postalSvoService.findPage(id,status,pageNum,pageSize);
    }


    /**
     * 驳回PAC提币审核
     */
    @RequestMapping(value = "/reject")
    @ResponseBody
    public Map<String,Object> rejectPAC(String id,String from,String text){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";
        try {
            Map<String, Object> postal = postalSvoDao.findById(id);
            if(postal!=null){
                //把PAC退还给用户

                //申请人的余额信息
                Map<String, Object> fBalance = balanceDao.findByAddr(from);
                //总余额
                BigDecimal ustdBalance = new BigDecimal(fBalance.get("ustdBalance").toString());
                BigDecimal useBalance = new BigDecimal(fBalance.get("useBalance").toString());
                //消费额
                /*BigDecimal substract = new BigDecimal(fBalance.get("F04").toString());*/
                //可用余额
                /*BigDecimal useBalance = new BigDecimal(fBalance.get("useBalance").toString());*/

                //提出的数量
                BigDecimal value = new BigDecimal(postal.get("val").toString());
                //手续费
                BigDecimal oldFee = new BigDecimal(postal.get("fee").toString());

                if (postal.get("type").toString().equals("1")){
                    fBalance.put("useBalance",useBalance.add(value).setScale(5,BigDecimal.ROUND_HALF_UP));
                    balanceDao.update(fBalance);
                }else {
                    fBalance.put("ustdBalance",ustdBalance.add(value).setScale(5,BigDecimal.ROUND_HALF_UP));
                    //fBalance.put("subtract",substract.subtract(value).setScale(5,BigDecimal.ROUND_HALF_UP));
                    //fBalance.put("useBalance",useBalance.add(value).setScale(5,BigDecimal.ROUND_HALF_UP));
                    balanceDao.update(fBalance);
                }

                //回滚手续费回收记录
                /*Map<String, Object> fees = feeDao.getFees();
                BigDecimal fee = new BigDecimal(fees.get("F02").toString());
                BigDecimal newFee = fee.subtract(oldFee).setScale(5,BigDecimal.ROUND_HALF_UP);
                fees.put("fee",newFee);
                feeDao.add(fees);*/


                //改变审核状态
                postal.put("text",text);
                postal.put("status",2);
                postalSvoDao.upStatus(postal);
            }else{
                msg="不存在该记录或数据已丢失";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 通过svo审核
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adopt")
    public Map<String,Object> adoptsvo(String id){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> omni_funded_send = new HashMap<>();
        String msg="ok";
        try {
            Map<String, Object> postal = postalSvoDao.findById(id);
            if(postal!=null){

                if (postal.get("type").toString().equals("1")){

                    Admin web3j= Admin.build(new HttpService("http://47.52.59.149:9801"));
//                    Map<String, Object> map = new HashMap<>();
                    //获取当前手续费
                    BigDecimal gas=new BigDecimal("60000");

                    BigInteger gP = web3j.ethGasPrice().send().getGasPrice();
                    //System.out.println("gP:"+gP);
                    BigDecimal gasPrice = Convert.fromWei(String.valueOf(gP), Convert.Unit.ETHER);
                    //System.out.println("gasPrice:"+gasPrice);
                    BigDecimal fee = gasPrice.multiply(gas);

                    //查询eth余额
                    BigDecimal balance = wnct.eth_getBalance("http://47.52.59.149", "9801", "0x77fcf68dd839e7d2d855cd0f7dd361333bbbcba1");
                    //查询wnct余额
                    BigDecimal fstBalance = wnct.getWnctBalance("http://47.52.59.149", "9801", "0x2958ff9b325135374605b4e6987140a53394c6a6", "0x77fcf68dd839e7d2d855cd0f7dd361333bbbcba1", null);

                    System.out.println(fstBalance);

                    if(fstBalance.compareTo(new BigDecimal(postal.get("count").toString()))>-1) {
                        if (balance.compareTo(fee) == -1) {
                            map.put("msg", "手续费不足，请及时充值");
                        } else {
                            //解锁钱包，转账前需要进行的
                            Map<String, Object> stringObjectMap = wnct.personal_unlockAccount("http://47.52.59.149", "9801", "0x77fcf68dd839e7d2d855cd0f7dd361333bbbcba1", "tiandawnct258147");

                            System.out.println(stringObjectMap);
                            String ip = "http://47.52.59.149";
                            String port = "9801";
                            String from = "0x77fcf68dd839e7d2d855cd0f7dd361333bbbcba1";//转出地址
                            String constract = "0x2958ff9b325135374605b4e6987140a53394c6a6";//合约地址
                            String value = "0";
                            String to = postal.get("toAddr").toString();//转入地址
                            String zcsl = postal.get("count").toString();//转出数量
                            EthereumEntity ethereumEntity = wnct.eth_sendTransaction(ip, port, from, value, constract, to, zcsl);
                            //转账成功后,改变审核状态
                            postal.put("status", 1);
                            postalSvoDao.upStatus(postal);
                            if (ethereumEntity.result == null) {//返回结果为null说明这笔交易没有发起
                                System.out.println("-----------------------------------"+ethereumEntity.jsonrpc+ethereumEntity.error+ethereumEntity.id);
                                msg = "操作繁忙,请稍后再试！";
                            } else {
                                String txHash = ethereumEntity.result.toString();//交易返回的交易哈希
                            }

                        }
                    }else{
                        msg="提币钱包余额不足";
                    }

                }else {

                    boolean btcbalance = btc.btcbalance();//查询手续费钱包是否够，一般要大于0.0004
                    if (btcbalance) {
                        BigDecimal usdtbalance = btc.usdtbalance();//查询提币钱包的余额
                        if (usdtbalance.compareTo(new BigDecimal(postal.get("count").toString())) >= 0) {
                            System.out.println("开始提币");
                            omni_funded_send.put("toaddress", postal.get("toAddr"));//要提币的地址
                            omni_funded_send.put("amount", postal.get("count"));//提币金额
                            String s = btc.omni_funded_send(omni_funded_send);//开始提币
                            System.out.println("提币哈希:" + s);
                            //转账成功后,改变审核状态
                            postal.put("status", 1);
                            postalSvoDao.upStatus(postal);

                        } else {
                            msg = "平台USDT不足，请充值！";
                        }
                    } else {
                        msg = "平台手续费不足，请充值！";
                    }
                }

            }else{
                msg="该记录不存在或已丢失";
            }
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取svo提币审核列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getsvoAuditList")
    public Map<String,Object> getsvoAuditList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "2")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";

        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postalSvoDao.findAllAuditing();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("audits",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求PAC提币审核列表
     * @return
     */
    @RequestMapping(value = "/toSVOAuditList")
    public String tosvoAuditList(String id,
                                 @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        String status = "0";
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = postalSvoDao.findPage(id,status);
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("audits",pages);
        return "admin/audit/svo-audit-list";
    }

    /**
     * 请求SVO审核通过列表
     * @return
     */
    @RequestMapping(value = "/toSVOAgreedList")
    public String toPACAgreedList(String id,
                                    @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        String status = "1";
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = postalSvoDao.findPage(id,status);
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("audits",pages);
        map.put("id",id);
        return "admin/audit/svo-adopt-list";
    }

    /**
     * 获取svo审核通过列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getsvoAdoptList")
    public Map<String,Object> getsvoAdoptList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";

        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postalSvoDao.findAllAdopting();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("audits",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 请求svo提币审核驳回列表
     * @return
     */
    @RequestMapping(value = "/toSVORejectedList")
    public String toSVORejectedList(String id,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        String status = "2";
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = postalSvoDao.findPage(id,status);
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("audits",pages);
        map.put("id",id);
        return "admin/audit/svo-unAdopt-list";
    }
    /**
     * 获取svo提币审核驳回列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getsvounAdoptList")
    public Map<String,Object> getsvounAdoptList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "2")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";

        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postalSvoDao.findAllUnAdopting();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("audits",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }



    /**
     * 审核通过
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/AgreedAudit")
    public Map<String,Object> AgreedAudit(Integer id){
        return auditService.updatestate(id);
    }


/*    *//**
     * 驳回
     * @param
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value = "/rejected")
    public Map<String,Object> rejected(Integer id,String text,String addr,Integer integral){
        Audit audit = new Audit();
        audit.setId(id);
        audit.setReason(text);
        return auditService.rejected(id,text,addr,integral);
    }*/

    /**
     * 驳回请求
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/toRejected")
    public String toEditWealth(Integer id,HttpSession session){
        Wealth wealth = wealthDao.findById(id);
        List<Map<String, Object>> all = auditDao.getFindId(id);
        session.setAttribute("abcdefg",all);
        return "/admin/audit/audit-rejected";
    }


    /**
     * 请求积分兑换列表
     * @param session
     * @return
     */
    @RequestMapping(value = "/toAuditList")
    public String toWealthList(HttpSession session){
        List<Wealth> all = wealthDao.findAll();
        List<Map<String, Object>> all1 = auditDao.getFindAll();
        session.setAttribute("audits",all1);
        return "/admin/audit/audit-list";
    }

    /**
     * 请求积分兑换通过列表
     * @param session
     * @return
     */
    @RequestMapping(value = "/toAgreedList")
    public String toAgreedList(HttpSession session){
        List<Map<String, Object>> agreed = auditDao.getAgreedAll();
        /*for(Map<String, Object> m:agreed){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Timestamp f06 = (Timestamp) m.get("F06");
            String newDate = sdf.format(f06);
            m.put("time",newDate);
        }*/
        session.setAttribute("agreed",agreed);
        return "/admin/audit/agreed-list";
    }


    /**
     * 请求积分兑换通过列表
     * @param session
     * @return
     */
    @RequestMapping(value = "/toRejectedList")
    public String toRejectedList(HttpSession session){
        List<Map<String, Object>> rejectedList = auditDao.getRejectedAll();
        session.setAttribute("rejected",rejectedList);
        return "/admin/audit/rejected-list";
    }

}
