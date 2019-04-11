package com.td.admin.controller;

import com.td.admin.dao.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.collections.MappingChange;
import com.td.admin.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/transfer")
public class TransferController {
    @Autowired
    private BlockTransferDao blockTransferDao;
    @Autowired
    private PostalTransferRecordDao postalTransferRecordDao;
    @Autowired
    private BuyinRecordDao buyinRecordDao;
    @Autowired
    private StreamRecordDao streamTransferDao;
    @Autowired
    private TransMapper transMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TransferService transferService;



    /**
     * 根据UID获取用户买入列表
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserBuyinByUid")
    public Map<String,Object> getUserBuyinByUid(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,String uid){
        Map<String,Object> map=new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<MappingChange.Map<String, Object>> list = buyinRecordDao.findAllByUid(uid);
            PageInfo<MappingChange.Map<String, Object>> pages = new PageInfo<>(list);
            map.put("userBuyin",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 根据UID请求用户买入列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toAllUserTurnInByUid")
    public String toAllUserTurnInByUid(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map,String uid){
        PageHelper.startPage(pageNum,pageSize);
        List<MappingChange.Map<String, Object>> buyinRecord = buyinRecordDao.findAllByUid(uid);
        PageInfo<MappingChange.Map<String, Object>> pages = new PageInfo<>(buyinRecord);
        map.put("userBuyin",pages);
        return "admin/user/user-buyin-search";
    }



    /**
     * 获取用户买入列表
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserBuyin")
    public Map<String,Object> getUserBuyin(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map=new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<MappingChange.Map<String, Object>> list = buyinRecordDao.findAll();
            PageInfo<MappingChange.Map<String, Object>> pages = new PageInfo<>(list);
            map.put("buyin",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求用户买入列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toUserTurnIn")
    public String getAllUserTurnIn(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<MappingChange.Map<String, Object>> buyinRecord = buyinRecordDao.findAll();
        PageInfo<MappingChange.Map<String, Object>> pages = new PageInfo<>(buyinRecord);
        map.put("buyin",pages);
        return "admin/user/user-buyin";
    }


    /**
     * 提出记录搜索
     * @param pid
     * @param map
     * @return
     */
    @RequestMapping(value = "/searchPostalTransferRecord")
    public String searchPostalTransferRecord(String pid,Map<String,Object> map){
        Map<String, Object> postalTransfers = postalTransferRecordDao.findByPid(pid);
        map.put("postalTransfers",postalTransfers);
        return "/admin/audit/svo-postal-list";
    }

    /**
     * 请求提出记录列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toPostalTransferList")
    public String toPostalTransferList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> postalTransfers = postalTransferRecordDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(postalTransfers);
        map.put("postalTransfers",pages);
        return "/admin/audit/svo-postal-list";
    }



    /**
     * 获取PAC流水列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toPACStreamList")
    public String toPACStreamList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = blockTransferDao.findAll();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        for(Map<String,Object> m:list){
            Date date=new Date(Long.valueOf(m.get("time").toString())*1000L);
            m.put("time",sdf.format(date));
        }
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("transfers",pages);
        return "admin/transfer/pac-stream-list";
    }

    /**
     * 获取PAC流水列表
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPacTransferList")
    public Map<String,Object> getPacTransferList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = blockTransferDao.findAll();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            for(Map<String,Object> m:list){
                Date date=new Date(Long.valueOf(m.get("time").toString())*1000L);
                m.put("time",sdf.format(date));
            }
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("transfers",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求wnct流水列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toUserTransferList")
    public String toUserTransferList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = transMapper.findAll();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        for(Map<String,Object> m:list){
            Date date=new Date(Long.valueOf(m.get("time").toString())*1000L);
            m.put("time",sdf.format(date));
        }
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("transfers",pages);
        return "admin/transfer/user-transfer-list";
    }

    /**
     * 获取wnct流水
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWNCTTransferList")
    public Map<String,Object> getWNCTTransferList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = transMapper.findAll();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("transfers",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求wnct流水搜索列表
     * @param addr
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toWNCTStreamSearch")
    public String toWNCTStreamSearch(String addr,String start_time,String end_time,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        return transferService.findWnctStreamSearch(pageNum,pageSize,addr,start_time,end_time,map);
    }

    /**
     * 获取wnct流水搜索分页列表
     * @param addr
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWNCTStreamSearchPage")
    public Map<String,Object> getWNCTStreamSearchPage(String addr,String start_time,String end_time,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return transferService.findWNCTStreamSearchPage(pageNum,pageSize,addr,start_time,end_time);
    }




    /**
     * 请求usdt流水
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toUSDTTransferList")
    public String toUSDTTransferList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        /*PageHelper.startPage(pageNum,pageSize);*/
        //List<Map<String,Object>> list = transMapper.findAllUsdt();
        /*PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("transfers",pages);*/
        return transferService.findAllUsdt(pageNum,pageSize,map);
    }

    /**
     * 获取usdt流水
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUSDTTransferList")
    public Map<String,Object> getUSDTTransferList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return transferService.findAllUSDTTransfer(pageNum,pageSize);
    }

    /**
     * 请求usdt流水搜索列表
     * @param addr
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toUSDTStreamSearch")
    public String toUSDTStreamSearch(String addr,String start_time,String end_time,
                                     @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        return transferService.findUSDTStreamSearch(pageNum,pageSize,addr,start_time,end_time,map);
    }

    /**
     * 获取usdt流水搜索列表分页记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUSDTStreamSearchPage")
    public Map<String,Object> getUSDTStreamSearchPage(String addr,String start_time,String end_time,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return transferService.findUSDTStreamSearchPage(pageNum,pageSize,addr,start_time,end_time);
    }


    /**
     * 请求系统转账列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toSystemTransferList")
    public String toSystemTransferList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = transMapper.findSystemTransfer();
        //List<Map<String,Object>> list2 = userDao.findAll();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("SystemTransfers",pages);
        return "admin/transfer/system-transfer-list";
    }

    /**
     * 获取系统转账列表分页记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemTransferList")
    public Map<String,Object> getSystemTransferList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = transMapper.findSystemTransfer();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfers",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求系统转账搜索页面
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toSystemTransferSearch")
    public String toSystemTransferSearch(String id,String start_time,String end_time,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if ((id != null && !"".equals(id)) || (start_time != null && !"".equals(start_time)) || (end_time != null && !"".equals(end_time))){
            List<Map<String, Object>> list = transMapper.findSystemTransferSearch(id,start_time,end_time);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfers",pages);
            map.put("id",id);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
        }else {
            List<Map<String,Object>> list = transMapper.findSystemTransfer();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfers",pages);
            return "admin/transfer/system-transfer-list";
        }
        return "admin/transfer/system-transfer-search-list";
    }

    /**
     * 获取系统转账搜索分页列表
     * @param id
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemTransferSearch")
    public Map<String,Object> getSystemTransferSearch(String id,String start_time,String end_time,
                                    @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findSystemTransferSearch(id,start_time,end_time);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfers",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求系统转账总记录
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toSystemTransferCountList")
    public String toSystemTransferCountList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = transMapper.findSystemTransferCount();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("SystemTransfersCount",pages);
        return "admin/transfer/system-transfer-sum-list";
    }

    /**
     * 获取系统转账总记录列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemTransferCountList")
    public Map<String,Object> getSystemTransferCountList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = transMapper.findSystemTransferCount();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfersCount",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求转账总记录搜索列表
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toSystemTransferCountSearchList")
    public String toSystemTransferCountSearchList(String id,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if (id != null && !"".equals(id)){
            List<Map<String, Object>> list = transMapper.findSystemTransferSearchCount(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfersCount",pages);
            map.put("id",id);
        }else {
            List<Map<String,Object>> list = transMapper.findSystemTransferCount();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfersCount",pages);
            return "admin/transfer/system-transfer-sum-list";
        }

        return "admin/transfer/system-transfer-sum-search-list";
    }

    /**
     * 获取转账总记录搜索列表分页
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemTransferSearchCountList")
    public Map<String,Object> getSystemTransferSearchCountList(String id,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findSystemTransferSearchCount(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("SystemTransfersCount",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 请求静态奖金列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toStaticBonus")
    public String toStaticBonus(Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findStaticBonus();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("staticBonus",pages);
        return "admin/transfer/static-transaction-list";
    }

    /**
     * 获取静态奖金列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getStaticBonus")
    public Map<String,Object> getStaticBonus(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findStaticBonus();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("staticBonus",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 获取静态奖金明细
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toFindStaticBonusRecord")
    public String toFindStaticBonusRecord(String id,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findStaticBonusRecord(id);
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("staticBonusRecord",pages);
        map.put("id",id);
        return "admin/transfer/static-transaction-record-list";
    }

    /**
     * 获取静态奖金明细列表分页
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getStaticBonusPage")
    public Map<String,Object> getStaticBonusPage(String id,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findStaticBonusRecord(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("staticBonusRecord",pages);
            map.put("id",id);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求静态奖金搜索列表
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toFindStaticBonusSearch")
    public String toFindStaticBonusSearch(String id,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if (id != null && !"".equals(id)){
            List<Map<String, Object>> list = transMapper.findStaticBonusSearch(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("staticBonus",pages);
            map.put("id",id);
        }else {
            List<Map<String, Object>> list = transMapper.findStaticBonus();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("staticBonus",pages);
            return "admin/transfer/static-transaction-list";
        }

        return "admin/transfer/static-transaction-search-list";
    }

    /**
     * 请求积分转账列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toJFTransfer")
    public String toJFTransfer(Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findJFTransfer();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("JFTransfer",pages);
        return "admin/transfer/jf-transfer-list";
    }

    /**
     * 获取积分转账列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJFTransfer")
    public Map<String,Object> getJFTransfer(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findJFTransfer();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("JFTransfer",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求WNCT转账列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toWNCTTransfer")
    public String toWNCTTransfer(Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findWNCTTransfer();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("WNCTTransfer",pages);
        return "admin/transfer/wnct-transfer-list";
    }

    /**
     * 获取WNCT转账列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWNCTTransfer")
    public Map<String,Object> getWNCTTransfer(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findWNCTTransfer();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("WNCTTransfer",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求动态奖金列表
     * @return
     */
    @RequestMapping(value = "/toDynamicBonus")
    public String toDynamicBonus(Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findDynamicBonus();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("DynamicBonus",pages);
        return "admin/transfer/dynamic-transaction-list";
    }

    /**
     * 获取动态奖励列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDynamicBonus")
    public Map<String,Object> getDynamicBonus(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findDynamicBonus();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("DynamicBonus",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取动态奖金明细
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toFindDynamicRecord")
    public String toFindDynamicRecord(String id,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findDynamicBonusRecord(id);
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("DynamicBonusRecord",pages);
        map.put("id",id);
        return "admin/transfer/dynamic-transaction-record-list";
    }


    /**
     * 获取动态奖金明细列表分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFindDynamicRecord")
    public Map<String,Object> getFindDynamicRecord(String id,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findDynamicBonusRecord(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("DynamicBonusRecord",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求动态奖金搜索列表
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toFindDynamicSearch")
    public String toFindDynamicSearch(String id,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if (id != null && !"".equals(id)){
            List<Map<String, Object>> list = transMapper.findDynamicBonusSearch(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("DynamicBonus",pages);
            map.put("id",id);
        }else {
            List<Map<String, Object>> list = transMapper.findDynamicBonus();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("DynamicBonus",pages);
            return "admin/transfer/dynamic-transaction-list";
        }

        return "admin/transfer/dynamic-transaction-search-list";
    }

    /**
     * 请求积分转账搜索页面
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toJFTransferSearch")
    public String toJFTransferSearch(String start_time,String end_time,String fromid,String toid,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if ((start_time != null && !"".equals(start_time)) || (end_time!=null && !"".equals(end_time)) || (fromid != null && !"".equals(fromid)) || (toid != null && !"".equals(toid)) ){
            List<Map<String, Object>> list = transMapper.findJFTransferSearch(start_time,end_time,fromid,toid);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("JFTransfer",pages);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("fromid",fromid);
            map.put("toid",toid);
        }else {
            List<Map<String, Object>> list = transMapper.findJFTransfer();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("JFTransfer",pages);
            return "admin/transfer/jf-transfer-list";
        }

        return "admin/transfer/jf-transfer-search-list";
    }

    /**
     * 获取积分转账记录搜索页面分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJFTransferSearchPage")
    public Map<String,Object> getJFTransferSearchPage(String start_time,String end_time,String fromid,String toid,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                      @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findJFTransferSearch(start_time,end_time,fromid,toid);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("JFTransfer",pages);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("fromid",fromid);
            map.put("toid",toid);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }



    /**
     * 请求WNCT转账搜索页面
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toWNCTTransferSearch")
    public String toWNCTTransferSearch(String start_time,String end_time,String fromid,String toid,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if ((start_time != null && !"".equals(start_time)) || (end_time!=null && !"".equals(end_time)) || (fromid != null && !"".equals(fromid)) || (toid != null && !"".equals(toid)) ){
            List<Map<String, Object>> list = transMapper.findWNCTTransferSearch(start_time,end_time,fromid,toid);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("WNCTTransfer",pages);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("fromid",fromid);
            map.put("toid",toid);
        }else {
            List<Map<String, Object>> list = transMapper.findWNCTTransfer();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("WNCTTransfer",pages);
            return "admin/transfer/wnct-transfer-list";
        }

        return "admin/transfer/wnct-transfer-search-list";
    }


    /**
     * 获取wnct转账记录搜索页面分页记录
     * @param start_time
     * @param end_time
     * @param fromid
     * @param toid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWNCTTransferSearchPage")
    public Map<String,Object> getWNCTTransferSearchPage(String start_time,String end_time,String fromid,String toid,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                        @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findWNCTTransferSearch(start_time,end_time,fromid,toid);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("WNCTTransfer",pages);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("fromid",fromid);
            map.put("toid",toid);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 请求USDT转账列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toUSDTTransfer")
    public String toUSDTTransfer(Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = transMapper.findUSDTTransfer();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("usdtTransfer",pages);
        return "admin/transfer/usdt-transfer-list";
    }

    /**
     * 获取usdt转账列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUSDTTransfer")
    public Map<String,Object> getUSDTTransfer(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findUSDTTransfer();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("usdtTransfer",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 请求usdt转账搜索页面
     * @param start_time
     * @param end_time
     * @param fromid
     * @param toid
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/toUSDTTransferSearch")
    public String toUSDTTransferSearch(String start_time,String end_time,String fromid,String toid,Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        if ((start_time != null && !"".equals(start_time)) || (end_time!=null && !"".equals(end_time)) || (fromid != null && !"".equals(fromid)) || (toid != null && !"".equals(toid)) ){
            List<Map<String, Object>> list = transMapper.findUSDTTransferSearch(start_time,end_time,fromid,toid);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("usdtTransfer",pages);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("fromid",fromid);
            map.put("toid",toid);
        }else {
            List<Map<String, Object>> list = transMapper.findUSDTTransfer();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("usdtTransfer",pages);
            return "admin/transfer/usdt-transfer-list";
        }

        return "admin/transfer/usdt-transfer-search-list";
    }

    /**
     * 获取usdt转账记录搜索分页记录
     * @param start_time
     * @param end_time
     * @param fromid
     * @param toid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUSDTTransferSearchPage")
    public Map<String,Object> getUSDTTransferSearchPage(String start_time,String end_time,String fromid,String toid,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                      @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = transMapper.findUSDTTransferSearch(start_time,end_time,fromid,toid);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("usdtTransfer",pages);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("fromid",fromid);
            map.put("toid",toid);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取系统转账记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAppSystemTransfer")
    public Map<String,Object> getAppSystemTransfer(String userid){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            List<Map<String,Object>> list = transMapper.findAppSystemTransfer(userid);
            map.put("list",list);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }




}
