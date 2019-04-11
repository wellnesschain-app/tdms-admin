package com.td.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.dao.*;
import com.td.admin.service.NodeSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "/admin/nodeSetup")
public class NodeSetupController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthenDao userAuthenDao;
    @Autowired
    private BonusDao bonusDao;
    @Autowired
    private TurninCalculationRecordDao turninCalculationRecordDao;
    @Autowired
    private NodeSetupDao nodeSetupDao;
    @Autowired
    private NodeSetupService nodeSetupService;
    @Autowired
    private IntegralDao integralDao;


    /**
     * 请求节点列表
     * @return
     */
    @RequestMapping(value = "/toNodeSetup")
    public String toNodeSetup(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = nodeSetupDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("node",pages);
        return "admin/nodesetup/node-list";
    }

    /**
     * 请求修改列表
     * @param id
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/toEditNode")
    public String toEditNode(Integer id,Map<String,Object> map,HttpSession session){

        Map<String, Object> list = nodeSetupDao.findById(id);
        map.put("node",list);
        return "admin/nodesetup/node-edit";
    }


    /**
     * 修改
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upNodeSetup")
    public Map<String,Object> upWealth(@RequestParam Map<String,Object> params){
        return nodeSetupService.update(params);
    }


    /**
     *
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toProcedures")
    public String toProcedures(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = nodeSetupDao.findProcedures();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("procedures",pages);
        return "admin/nodesetup/procedures-list";
    }

//    /**
//     * 手续费设置
//     * @return
//     */
//    @RequestMapping(value = "/toServiceCharge")
//    public String toServiceCharge(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
//                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
//        PageHelper.startPage(pageNum,pageSize);
//        List<Map<String, Object>> list = nodeSetupDao.findService();
//        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
//        map.put("Service",pages);
//        return "admin/nodesetup/-procedureslist";
//
//    }

    /**
     * 请求手续费修改列表
     * @param id
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/toEditProcedures")
    public String toEditProcedures(Integer id,Map<String,Object> map,HttpSession session){

        Map<String, Object> list = nodeSetupDao.findProceduresById(id);
        map.put("procedures",list);
        return "admin/nodesetup/poundage-edit";
    }


    /**
     * 修改手续费
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/EditProcedures")
    public Map<String,Object> EditProcedures(@RequestParam Map<String,Object> params){
        return nodeSetupService.EditProcedures(params);
    }


    /**
     * 获取百分比
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPercentage")
    public Map<String,Object> getPercentage(){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            List<Map<String,Object>> list =integralDao.findAll();
            map.put("percentage",list);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 获取手续费列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProcedures")
    public Map<String,Object> getProcedures(){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            List<Map<String,Object>> list =nodeSetupDao.findProcedures();
            map.put("data",list);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("code",0);
        //map.put("msg","");
        return map;
    }


}
