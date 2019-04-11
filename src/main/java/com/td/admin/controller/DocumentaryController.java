package com.td.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.dao.DocumentaryDao;
import com.td.admin.dao.TransMapper;
import com.td.admin.service.DocumentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/documentary")
public class DocumentaryController {
    @Autowired
    private DocumentaryDao documentaryDao;
    @Autowired
    private TransMapper transMapper;
    @Autowired
    private DocumentaryService documentaryService;

    /**
     * 请求挂单列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toDocumentaryList")
    public String toDocumentaryList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "12")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = documentaryDao.findAll();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("documentary",pages);
        return "admin/documentary/documentary-record-list";
    }


    /**
     * 获取挂单列表分页记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDocumentaryList")
    public Map<String,Object> getDocumentaryList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        Map<String,Object> map = new HashMap<>();
        String  msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = documentaryDao.findAll();
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
     * 请求挂单搜索列表
     * @param id
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toDocumentarySearchList")
    public String toDocumentarySearchList(String id,String start_time,String end_time,
                                    @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "12")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = documentaryDao.findDocumentarySearchList(id,start_time,end_time);
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("documentary",pages);
        map.put("id",id);
        map.put("start_time",start_time);
        map.put("end_time",end_time);
        return "admin/documentary/documentary-record-search-list";
    }

    /**
     * 获取挂单搜索列表分页记录
     * @param id
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDocumentarySearchList")
    public Map<String,Object> getDocumentarySearchList(String id,String start_time,String end_time,
                                        @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return documentaryService.findDocumentarySearchPageList(pageNum,pageSize,id,start_time,end_time);
    }

    /**
     * 请求已售记录
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toDocumentarySoldList")
    public String toDocumentarySoldList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "12")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = transMapper.findSold();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("documentary",pages);
        return "admin/documentary/documentary-sold-record-list";
    }


    /**
     * 请求已售列表搜索页面
     * @param id
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toDocumentarySoldSearchList")
    public String toDocumentarySoldSearchList(String id,String start_time,String end_time,
                                          @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "12")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = transMapper.findSoldSearch(id,start_time,end_time);
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("documentary",pages);
        map.put("id",id);
        map.put("start_time",start_time);
        map.put("end_time",end_time);
        return "admin/documentary/documentary-sold-record-search-list";
    }

    /**
     * 请求撤单列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toCancelOrder")
    public String toCancelOrder(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "12")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = documentaryDao.findCancelOrder();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("cancelOrder",pages);
        return "admin/documentary/documentary-cancel-order-list";
    }

    /**
     * 获取撤单列表分页记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCancelOrderPage")
    public Map<String,Object> getCancelOrderPage(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                 @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return documentaryService.findCancelOrderPage(pageNum,pageSize);
    }

    /**
     * 请求撤单列表搜索页面
     * @param id
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toCancelOrderSearchList")
    public String toCancelOrderSearchList(String id,String start_time,String end_time,
                                              @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "12")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = documentaryDao.findCancelOrderSearch(id,start_time,end_time);
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("cancelOrder",pages);
        map.put("id",id);
        map.put("start_time",start_time);
        map.put("end_time",end_time);
        return "admin/documentary/documentary-cancel-order-search-list";
    }

    @ResponseBody
    @RequestMapping(value = "/getCancelOrderSearchPage")
    public Map<String,Object> getCancelOrderSearchPage(String id,String start_time,String end_time,
                                                       @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        return documentaryService.findCancelOrderSearchPage(pageNum,pageSize,id,start_time,end_time);
    }


    /**
     * 撤单
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancelOrder")
    public Map<String,Object> cancelOrder(@RequestParam Map<String,Object> params){
        return documentaryService.cancelOrder(params);
    }
}
