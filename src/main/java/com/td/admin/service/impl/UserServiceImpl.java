package com.td.admin.service.impl;

import com.td.admin.dao.UserDao;
import com.td.admin.service.UserService;
import com.td.admin.util.MyCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 锁定
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> updateStatus(String id) {
        Map<String, Object> map=new HashMap<>();
        String msg="ok";

        try {
            userDao.updateStatus(id);
        } catch (Exception e) {
            msg="修改失败！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 修改用户等级
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> update(Map<String, Object> map) {
        Map<String, Object> res=new HashMap<>();
        String msg="ok";

        try {
            userDao.update(map);
        } catch (Exception e) {
            msg="修改失败！";
            e.printStackTrace();
        }
        res.put("msg",msg);
        return res;
    }

    public Map<String,Object> userUnlock(String id){
        Map<String, Object> map=new HashMap<>();
        String msg="ok";

        try {
            userDao.userUnlock(id);
        } catch (Exception e) {
            msg="解锁失败！";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }


    /**
     * 修改密码
     * @return
     */
    @Override
    public Map<String, Object> updatePwd(String addr,String pwd) {
        Map<String, Object> res=new HashMap<>();
        String msg="ok";

        try {
            String password = MyCrypt.myEncode(pwd);
            userDao.updatePwd(addr,password);
        } catch (Exception e) {
            msg="修改失败！";
            e.printStackTrace();
        }
        res.put("msg",msg);
        return res;
    }

    /**
     * 修改用户名
     * @param addr
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> updateAccount(String addr,String account) {
        Map<String, Object> res=new HashMap<>();
        String msg="ok";

        try {
            Map<String,Object> user = userDao.findByAccount(account);
            if (user != null){
                res.put("msg","账号已存在");
            }else {
                userDao.updateAccount(addr,account);
                res.put("msg",msg);
            }
        } catch (Exception e) {
            res.put("msg","修改失败！");
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 修改支付密码
     * @param addr
     * @param sqPwd
     * @return
     */
    @Override
    public Map<String, Object> updateSqPwd(String addr, String sqPwd) {
        Map<String, Object> res=new HashMap<>();
        String msg="ok";

        try {
            String password = MyCrypt.myEncode(sqPwd);
            userDao.updateSqPwd(addr,password);
        } catch (Exception e) {
            msg="修改失败！";
            e.printStackTrace();
        }
        res.put("msg",msg);
        return res;
    }


}
