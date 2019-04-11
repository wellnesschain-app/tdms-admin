package com.td.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.dao.AdminDao;
import com.td.admin.entity.Admin;
import com.td.admin.service.AdminService;
import com.td.admin.service.NodeService;
import com.td.admin.util.ShiroMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private NodeService nodeService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDao adminDao;

    @ResponseBody
    @RequestMapping(value = "/updatePwd")
    public Map<String,Object> updatePwd(@RequestParam Map<String,Object> map){
        Map<String,Object> res = new HashMap<>();
        String msg="ok";

        try {
            String account = map.get("account").toString();
            String pwd = map.get("pwd").toString();
            String newPwd1 = map.get("newPwd1").toString();
            String newPwd2 = map.get("newPwd2").toString();
            if(account==null||account.equals("")||pwd==null||pwd.equals("")||newPwd1==null||newPwd1.equals("")||newPwd2==null||newPwd2.equals("")){
                msg="参数丢失";
            }else{
                Map<String,Object> utilMap = new HashMap<>();
                utilMap.put("password", pwd);
                String password = ShiroMD5Util.SysMD5(utilMap);

                System.out.println(account);
                Admin admin = adminDao.findByUsername(account);
                if(!admin.getPassword().equals(password)){
                   msg="原密码错误，修改密码失败！";
                }else{
                    utilMap.put("password", newPwd1);
                    String newPwd = ShiroMD5Util.SysMD5(utilMap);
                    Map<String,Object> adminMap=new HashMap<>();
                    adminMap.put("id",admin.getId());
                    adminMap.put("newPwd",newPwd);
                    adminDao.update(adminMap);
                }
            }
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        res.put("msg",msg);
        return res;
    }

    /**
     * 请求修改密码
     * @return
     */
    @RequestMapping(value = "/toUpdatePwd")
    public String toUpdatePwd(){
        return "/admin/update";
    }

    /**
     * 获取公告钱包的svo余额和ETH余额
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBalance")
    public Map<String,Object> getBalance(){
        return nodeService.getBalance();
    }


    /**
     * 首页面
     * @return
     */
    @RequestMapping(value = "/toIndex")
    public String toIndex(){
        return "admin/index";
    }

    /**
     * 欢迎页面
     * @return
     */
    @RequestMapping(value = "/toWelcome")
    public String toWelcome(HttpSession session){
        Map<String, Object> registerednumber = adminDao.findRegisteredNumber();
        session.setAttribute("registerednumber",registerednumber);
        return "admin/welcome";
    }


    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout")
    public String Logout(HttpSession session){
        session.invalidate();
        return "admin/login";
    }
    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "admin/login";
    }



    /**
     * 管理员登录
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public Map<String,Object> Login(String account,String password,HttpSession session){
        Admin admin=new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        session.setAttribute("username",account);
        Map<String, Object> map = adminService.login(admin);
        return map;
    }



/*    *//**
     * 按文件名获取后缀名的方法
     * @param filename 文件名
     * @return 后缀名
     *//*
    private String getSuffix(String filename){
        return filename.substring(filename.lastIndexOf("."));
    }*/

    /**
     * 获取总人数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTotalNumber")
    public Map<String,Object> getTotalNumber(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            Map<String, Object> totalNumber = adminDao.findtotalnumber();
            map.put("totalNumber",totalNumber);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取今日充值用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPrepaidUsers")
    public Map<String,Object> getPrepaidUsers(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            Integer prepaidUsers = adminDao.findPrepaidUsers();
            if (prepaidUsers == null){
                map.put("prepaidUsers","0");
            }else {
                map.put("prepaidUsers",prepaidUsers);
            }

        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 获取今日下单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPlaceOrder")
    public Map<String,Object> getPlaceOrder(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            int PlaceOrder = adminDao.findPlaceOrder();
            map.put("PlaceOrder",PlaceOrder);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取今日下单用户数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPlaceOrderUser")
    public Map<String,Object> getPlaceOrderUser(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            int PlaceOrderUser = adminDao.findPlaceOrderUser();
            map.put("PlaceOrderUser",PlaceOrderUser);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取今日释放数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRelease")
    public Map<String,Object> getRelease(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            String release = adminDao.getRelease();
            map.put("Release",release);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取今日下单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOrder")
    public Map<String,Object> getOrder(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            int order = adminDao.getOrder();
            map.put("Order",order);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 获取动态收益
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDynamic")
    public Map<String,Object> getDynamic(){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";

        try {
            String dynamic = adminDao.getDynamic();
            map.put("Dynamic",dynamic);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 查询管理员列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/getAdmin")
    public String getAdmin(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> postalTransfers = adminDao.findAdmin();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(postalTransfers);
        map.put("amdin",pages);
        return "/admin/admin/admin-list";
    }


    @RequestMapping(value = "/toAddAdmin")
    public String toAddBanner(){
        return "admin/admin/admin-add";
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @param nickname
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAdmin")
    public Map<String,Object> addAdmin(String username,String password,String nickname,String role){
        //String[] roleList=role.split(",");
        return adminService.add(username,password,nickname,role);
    }

    /**
     * 获取用户权限
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRole")
    public Map<String,Object> getRole(String username){
        Map<String, Object> map = new HashMap<>();
        String msg="ok";
        try {
            Map<String, Object> res = adminDao.findByRole(username);
            map.put("role",res);
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 请求修改权限页面
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateAdmin")
    public String updateAdmin(String username,
                            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, Map<String,Object> map){
        PageHelper.startPage(pageNum,pageSize);
        Map<String, Object> list = adminDao.findByRole(username);
        //PageInfo<Map<String, Object>> pages = new PageInfo<>(postalTransfers);
        map.put("admin",list);
        return "/admin/admin/admin-edit";
    }

    /**
     * 修改权限
     * @param nickname
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateAdminRole")
    public Map<String,Object> updateAdminRole(String id,String nickname,String role){
        return adminService.updateRole(id,nickname,role);
    }

    /**
     * 管理员删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAdmin")
    public Map<String,Object> deleteAdmin(String id){
        return adminService.deleteAdmin(id);
    }


}
