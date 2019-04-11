package com.td.admin.controller;

import com.td.admin.dao.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.service.BalanceService;
import com.td.admin.service.UserService;
import com.td.admin.util.MyCrypt;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthenDao userAuthenDao;
    @Autowired
    private BonusDao bonusDao;
    @Autowired
    private TurninCalculationRecordDao turninCalculationRecordDao;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private UserService userService;


    /**
     * PAC高效搜索
     * @param id
     * @return
     */
    @RequestMapping(value = "/search")
    public String search(String id,Map<String,Object> map){
        System.out.println(id);
        Map<String, Object> user = userDao.findByUid(id);
        map.put("user",user);
        return "/admin/user/user-search-details";
    }


    /**
     * 根据用户钱包地址获取实名认证信息
     */
    @RequestMapping(value = "/authenDetails")
    public String findAuthenByAddr(String id,Map<String,Object> map){
        Map<String, Object> authenInfo = userAuthenDao.getAuthenInfo(id);
        map.put("authen",authenInfo);
        return "admin/user/user-authen";
    }


    /**
     * 请求用户钱包列表页
     * @return
     */
    @RequestMapping(value = "/toUserList")
    public String toUserList(
                             @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("wallets",pages);
        return "admin/user/user-list";
    }

    /**
     * 获取用户钱包列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserList")
    public Map<String,Object> getUserList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = userDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("wallets",pages);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 积分详情内容页
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/toFindRecord")
    public String toDetails(HttpSession session,String id){
        List<Map<String,Object>> list = bonusDao.findRecord(id);
        session.setAttribute("bonus",list);
        return "admin/user/bonus-details";
    }

    /**
     * 请求用户入金列表
     * @return
     */
    @RequestMapping(value = "/toGoldEntryList")
    public String toGoldEntryList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = turninCalculationRecordDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("turnins",pages);
        return "admin/user/turnin-list";
    }


    /**
     * 获取用户入金列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserGoldEntryList")
    public Map<String,Object> getUserGoldEntryList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = turninCalculationRecordDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("turnins",pages);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 用户余额查询
     * @param addr
     * @return
     */
    @RequestMapping(value = "/toEditbalance")
    public String toEditbalance(Map<String,Object> map,String addr,String id){
        Map<String,Object> list =balanceDao.findByAddr(addr);
        Map<String, Object> user = userDao.findByUid(id);
        String pwd = MyCrypt.myDecode(user.get("password").toString());
        String sqPwd = MyCrypt.myDecode(user.get("sq_password").toString());
        if (user.get("level").toString().equals("0")){
            map.put("grade","用户");
        }else if (user.get("level").toString().equals("1")){
            map.put("grade","普通用户");
        }else if (user.get("level").toString().equals("2")){
            map.put("grade","高级用户");
        }else if (user.get("level").toString().equals("3")){
            map.put("grade","初级节点");
        }else if (user.get("level").toString().equals("4")){
            map.put("grade","中级节点");
        }else if (user.get("level").toString().equals("5")){
            map.put("grade","高级节点");
        }else if (user.get("level").toString().equals("6")){
            map.put("grade","超级节点");
        }else if (user.get("level").toString().equals("7")){
            map.put("grade","合伙人");
        }

        user.put("password",pwd);
        user.put("sq_password",sqPwd);
        map.put("user",user);
        map.put("balance",list);
        map.put("addr",addr);
        return "admin/user/balance-edit";
    }


    /**
     * 修改用户等级和用户余额
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/Editbalance")
    public Map<String,Object> Editbalance(@RequestParam Map<String,Object> params){
        return balanceService.update(params);
    }

    /**
     * 用户列表搜索
     * @param id
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/userSearch")
    public String userSearch(String id,String account,String lock,String start_time,String end_time,
                              @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        if ((id != null && !"".equals(id)) ||  (account != null && !"".equals(account)) || (lock != null && !"".equals(lock)) || (start_time != null && !"".equals(start_time))|| (end_time != null && !"".equals(end_time))){
            List<Map<String, Object>> list = userDao.findByUserid(id,account,lock,start_time,end_time);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("wallets",pages);
            map.put("id",id);
            map.put("account",account);
            map.put("lock",lock);
            map.put("start_time",start_time);
            map.put("end_time",end_time);
        }else {
            List<Map<String, Object>> list = userDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("wallets",pages);
            return "admin/user/user-list";
        }

        return "admin/user/user-search-list";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserSearchPage")
    public Map<String,Object> getUserSearchPage(String id,String account,String lock,String start_time,String end_time,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = userDao.findByUserid(id,account,lock,start_time,end_time);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("wallets",pages);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 锁定
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editUserStatus")
    public Map<String,Object> editUserStatus(String id){
        return userService.updateStatus(id);
    }

    /**
     * 用户余额查询
     * @return
     */
    @RequestMapping(value = "/toBalaceList")
    public String toBalaceList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list =balanceDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("balance",pages);
        return "admin/balance/user-balance-list";
    }

    /**
     * 转账
     * @param addr
     * @return
     */
    @RequestMapping(value = "/toTransfer")
    public String toTransfer(Map<String,Object> map,String addr,String id){

        Map<String,Object> list =balanceDao.findByAddr(addr);
        Map<String, Object> user = userDao.findByUid(id);
        map.put("user",user);
        map.put("balance",list);
        map.put("addr",addr);
        return "admin/transfer/pc-transfer";
    }


    /**
     * 获取转账分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTransfer")
    public Map<String,Object> getTransfer(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> map =new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String,Object>> list =balanceDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("balance",pages);
        } catch (Exception e) {
            msg="系统出错！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 转账列表搜索
     * @param id
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/userBalanceSearch")
    public String userBalanceSearch(String id,
                             @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        if (id != null && !"".equals(id) ){
            List<Map<String, Object>> list = balanceDao.findById(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("balance",pages);
        }else {
            List<Map<String,Object>> list =balanceDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("balance",pages);
            return "admin/balance/user-balance-list";
        }

        return "admin/balance/user-balance-search-list";
    }

    /**
     * 请求用户等级等级列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toUserLevelList")
    public String toUserLevelList(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("wallets",pages);
        return "admin/user/user-level-list";
    }

    /**
     * 请求用户等级搜索列表
     * @param id
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/toUserLevelSearchList")
    public String toUserLevelSearchList(String id,
                                    @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        if (id != null && !"".equals(id) ){
            List<Map<String, Object>> list = userDao.findUserLevelSearch(id);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("wallets",pages);
        }else {
            List<Map<String, Object>> list = userDao.findAll();
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            map.put("wallets",pages);
            return "admin/user/user-level-list";
        }

        return "admin/user/user-level-search-list";
    }


    /**
     * 用户等级查询
     * @param map
     * @param addr
     * @param id
     * @return
     */
    @RequestMapping(value = "/toEditUserLevel")
    public String toEditUserLevel(Map<String,Object> map,String addr,String id){

        //Map<String,Object> list =balanceDao.findByAddr(addr);
        Map<String, Object> user = userDao.findByUid(id);
        map.put("user",user);
        //map.put("balance",list);
        if (user.get("level").toString().equals("0")){
            map.put("dj","用户");
        }else if (user.get("level").toString().equals("1")){
            map.put("dj","普通用户");
        }else if (user.get("level").toString().equals("2")){
            map.put("dj","高级用户");
        }else if (user.get("level").toString().equals("3")){
            map.put("dj","初级节点");
        }else if (user.get("level").toString().equals("4")){
            map.put("dj","中级节点");
        }else if (user.get("level").toString().equals("5")){
            map.put("dj","高级节点");
        }else if (user.get("level").toString().equals("6")){
            map.put("dj","超级节点");
        }else if (user.get("level").toString().equals("7")){
            map.put("dj","合伙人");
        }
        map.put("addr",addr);
        return "admin/user/user-level-edit";
    }

    /**
     * 修改用户等级
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editUserLevel")
    public Map<String,Object> editUserLevel(@RequestParam Map<String,Object> params){
        return userService.update(params);
    }

    /**
     * 解锁
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userUnlock")
    public Map<String,Object> userUnlock(String id){
        return userService.userUnlock(id);
    }


    /**
     * 转账减
     * @param map
     * @param addr
     * @param id
     * @return
     */
    @RequestMapping(value = "/toTransferReduce")
    public String toTransferReduce(Map<String,Object> map,String addr,String id){

        Map<String,Object> list = balanceDao.findByAddr(addr);
        Map<String, Object> user = userDao.findByUid(id);
        map.put("user",user);
        map.put("balance",list);
        map.put("addr",addr);
        return "admin/transfer/pc-transfer-reduce";
    }


    /**
     * 提交修改
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/EditbalanceReduce")
    public Map<String,Object> EditbalanceReduce(@RequestParam Map<String,Object> params){
        return balanceService.updateReduce(params);
    }

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/toBonus")
    public String toBonus(Map<String,Object> map,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userDao.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        map.put("user",pages);
        return "admin/user/user-bonus-list";
    }

    /**
     * 修改密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePwd")
    public Map<String,Object> updatePwd(String addr,String pwd){
        return userService.updatePwd(addr,pwd);
    }

    /**
     * 修改支付密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSqPwd")
    public Map<String,Object> updateSqPwd(String addr,String sqPwd){
        return userService.updateSqPwd(addr,sqPwd);
    }


    /**
     * 修改用户名
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateAccount")
    public Map<String,Object> updateAccount(String addr,String account){
        return userService.updateAccount(addr,account);
    }




}
