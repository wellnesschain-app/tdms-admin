package com.td.admin.controller;

import com.td.admin.dao.IntegralDao;
import com.td.admin.dao.WealthColdDao;
import com.td.admin.dao.WealthDao;
import com.td.admin.service.NodeSetupService;
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
@RequestMapping(value = "/admin/integral")
public class IntegralStatisticsController {

    @Autowired
    private WealthDao wealthDao;
    @Autowired
    private WealthService wealthService;
    @Autowired
    private WealthColdDao wealthColdDao;
    @Autowired
    private IntegralDao integralDao;
    @Autowired
    private NodeSetupService nodeSetupService;




    /**
     * 请求积分列表
     * @return
     */
    @RequestMapping(value = "/toPercentage")
    public String getIntegral(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list = integralDao.findAll();
        PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
        map.put("percentage",pages);
        return "admin/percentage/percentage-list";
    }



    /**
     * 积分详情内容页
     * @param session
     * @param addr
     * @return
     */
    @RequestMapping(value = "/toFindRecord")
    public String toDetails(HttpSession session,String addr){
        List<Map<String,Object>> list = integralDao.findRecord(addr);
        session.setAttribute("integrals",list);
        return "admin/integral/integral-details";
    }



    /**
     * 请求积分列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/integralList")
    public Map<String,Object> integralList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "12")int pageSize){

        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list = integralDao.findAll();
            PageInfo<Map<String,Object>> pages = new PageInfo<>(list);
            map.put("integrals",pages);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求修改内容详情
     * @return
     */
    @RequestMapping(value = "/editFind")
    public String editFind(String id,Map<String,Object> map){

        Map<String,Object> list = integralDao.editFind(id);
        map.put("percentage",list);
        return "admin/percentage/percentage-edit-list";
    }


    /**
     * 修改
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public Map<String,Object> upWealth(@RequestParam Map<String,Object> params){
        return nodeSetupService.updatePercentage(params);
    }




}
