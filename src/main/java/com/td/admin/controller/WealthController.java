package com.td.admin.controller;

import com.td.admin.dao.ColdedDao;
import com.td.admin.dao.WealthColdDao;
import com.td.admin.dao.WealthDao;
import com.td.admin.entity.Wealth;
import com.td.admin.service.WealthService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/wealth")
public class WealthController {

    @Autowired
    private WealthDao wealthDao;
    @Autowired
    private WealthService wealthService;
    @Autowired
    private WealthColdDao wealthColdDao;
    @Autowired
    private ColdedDao coldedDao;


    /**
     * 获取解冻记录
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getColdedList")
    public Map<String,Object> getColdedList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> all = coldedDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(all);
            map.put("coldeds",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 请求解冻记录表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toColdedList")
    public String toColdedList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                               Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> all = coldedDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(all);
        map.put("coldeds",pages);
        return "/admin/wealth/colded-list";
    }

    /**
     * 获取冻结财富列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getColdList")
    public Map<String,Object> getUserList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = wealthColdDao.findAll();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("colds",pages);
        } catch (Exception e) {
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 请求冻结财富列表页
     * @return
     */
    @RequestMapping(value = "/toColdList")
    public String toUserList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = wealthColdDao.findAll();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("colds",pages);
        return "admin/wealth/cold-list";
    }

    /**
     * 删除计划
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteWealth")
    public Map<String,Object> deleteWealth(Integer id){
        return wealthService.delete(id);
    }

    /**
     * 修改计划
     * @param wealth
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upWealth")
    public Map<String,Object> upWealth(Wealth wealth){
        return wealthService.update(wealth);
    }

    /**
     * 请求修改计划
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/toEditWealth")
    public String toEditWealth(Integer id,HttpSession session){
        Wealth wealth = wealthDao.findById(id);
        session.setAttribute("wealth",wealth);
        return "admin/wealth/wealth-edit";
    }


    /**
     * 请求财富计划列表
     * @param session
     * @return
     */
    @RequestMapping(value = "/toWealthList")
    public String toWealthList(HttpSession session){
        //List<Wealth> all = wealthDao.findAll();
        List<Map<String, Object>> all = wealthDao.findAlls();
        session.setAttribute("wealths",all);
        return "/admin/wealth/wealth-list";
    }

    /**
     * 请求添加财富计划
     * @return
     */
    @RequestMapping(value = "/toAddWealth")
    public String toAddWealth(){
        return "admin/wealth/wealth-add";
    }

    /**
     * 添加财富计划
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addWealth")
    public Map<String,Object> addWealth(Wealth wealth){
        return wealthService.addWealth(wealth);
    }

    /**
     * 停止/开启    财富计划
     * @param id
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/wealthService")
    public Map<String,Object> wealthServic(Integer id,Integer type){
        Map<String,Object> map =null;
        if(type>0){
            map=wealthService.upStatus(id,type);
        }else{
            map=wealthService.upStatus(id,type);
        }
        return map;
    }
}
