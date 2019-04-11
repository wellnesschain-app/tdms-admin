package com.td.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.common.Constants;
import com.td.admin.dao.ThumbnailDao;
import com.td.admin.dao.TransMapper;
import com.td.admin.entity.Thumbnail;
import com.td.admin.service.ThumbnailService;
import com.td.admin.service.TransferService;
import com.td.admin.util.RemoveIMGUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransMapper transMapper;

    /**
     * 请求usdt流水列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @Override
    public String findAllUsdt(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = transMapper.findAllUsdt();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("transfers",pages);
        return "admin/transfer/user-usdt-transfer-list";
    }

    /**
     * 获取usdt流水列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> findAllUSDTTransfer(int pageNum, int pageSize) {
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
     * @param pageNum
     * @param pageSize
     * @param addr
     * @param start_time
     * @param end_time
     * @return
     */
    @Override
    public String findWnctStreamSearch(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                       String addr, String start_time, String end_time,Map<String,Object> map) {
        PageHelper.startPage(pageNum,pageSize);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        if (!"".equals(addr) || !"".equals(start_time) || !"".equals(end_time) ){
            List<Map<String,Object>> list = transMapper.findWnctStreamSearch(addr,start_time,end_time);
            for(Map<String,Object> m:list){
                Date date=new Date(Long.valueOf(m.get("time").toString())*1000L);
                m.put("time",sdf.format(date));
            }
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("transfers",pages);
            map.put("addr",addr);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
        }else {
            List<Map<String,Object>> tlist = transMapper.findAll();
            for(Map<String,Object> m:tlist){
                Date date=new Date(Long.valueOf(m.get("time").toString())*1000L);
                m.put("time",sdf.format(date));
            }
            PageInfo<Map<String,Object>> pages = new PageInfo<>(tlist);
            map.put("transfers",pages);
            return "admin/transfer/user-transfer-list";
        }
        return "admin/transfer/user-wnct-stream-search-list";
    }

    /**
     * 获取wnct流水搜索分页列表
     * @param pageNum
     * @param pageSize
     * @param addr
     * @param start_time
     * @param end_time
     * @return
     */
    @Override
    public Map<String, Object> findWNCTStreamSearchPage(int pageNum, int pageSize, String addr, String start_time, String end_time) {
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = transMapper.findWnctStreamSearch(addr,start_time,end_time);
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
     * 请求usdt流水搜索页面
     * @param pageNum
     * @param pageSize
     * @param addr
     * @param start_time
     * @param end_time
     * @param map
     * @return
     */
    @Override
    public String findUSDTStreamSearch(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                       String addr, String start_time, String end_time, Map<String, Object> map) {
        PageHelper.startPage(pageNum,pageSize);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        if (!"".equals(addr) || !"".equals(start_time) || !"".equals(end_time) ){
            List<Map<String,Object>> list = transMapper.findUSDTStreamSearch(addr,start_time,end_time);
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("transfers",pages);
            map.put("addr",addr);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
        }else {
            List<Map<String,Object>> tlist = transMapper.findAllUsdt();
            /*for(Map<String,Object> m:tlist){
                Date date=new Date(Long.valueOf(m.get("time").toString())*1000L);
                m.put("time",sdf.format(date));
            }*/
            PageInfo<Map<String,Object>> pages = new PageInfo<>(tlist);
            map.put("transfers",pages);
            return "admin/transfer/user-usdt-transfer-list";
        }
        return "admin/transfer/user-usdt-transfer-search-list";
    }

    /**
     * 获取usdt流水搜索列表分页记录
     * @param pageNum
     * @param pageSize
     * @param addr
     * @param start_time
     * @param end_time
     * @return
     */
    @Override
    public Map<String, Object> findUSDTStreamSearchPage(int pageNum, int pageSize, String addr, String start_time, String end_time) {
        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = transMapper.findUSDTStreamSearch(addr,start_time,end_time);
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("transfers",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }
}
