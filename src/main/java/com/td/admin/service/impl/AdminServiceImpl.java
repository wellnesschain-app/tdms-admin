package com.td.admin.service.impl;

import com.td.admin.common.LoginType;
import com.td.admin.dao.AdminDao;
import com.td.admin.entity.Admin;
import com.td.admin.realm.CustomizedToken;
import com.td.admin.service.AdminService;
import com.td.admin.util.ShiroMD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    //登录类型
    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();

    /**
     * 管理员登录认证
     *
     * @param admin
     * @return
     */
    @Override
    public Map<String, Object> login(Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = "ok";
        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> utilMap = new HashMap<>();
        try {
            utilMap.put("password", admin.getPassword());
            String password = ShiroMD5Util.SysMD5(utilMap);
            CustomizedToken customizedToken = new CustomizedToken
                    (admin.getAccount(), password, ADMIN_LOGIN_TYPE);
            customizedToken.setRememberMe(false);
            //把令牌放到login里面进行查询,如果查询账号和密码时候匹配,如果匹配就把user对象获取出来,失败就抛异常
            subject.login(customizedToken);

        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误!";
            System.out.println(e.getMessage());
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多!";
            System.out.println(e.getMessage());
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定!";
            System.out.println(e.getMessage());
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用!";
            System.out.println(e.getMessage());
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期!";
            System.out.println(e.getMessage());
        } catch (UnknownAccountException e) {
            msg = "帐号不存在! ";
            System.out.println(e.getMessage());
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！";
            System.out.println(e.getMessage());
        } catch (Exception e) {
            msg = "登陆失败!";
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        map.put("username",admin.getAccount());
        map.put("result", msg);
        return map;
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @param nickname
     * @param role
     * @return
     */
    @Override
    public Map<String, Object> add(String username, String password, String nickname, String role) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> utilMap = new HashMap<>();
        utilMap.put("password", password);
        String pwdS = ShiroMD5Util.SysMD5(utilMap);
        try {
            int cout = adminDao.add(username, pwdS, nickname, role);
            if (cout > 0) {
                map.put("msg", "ok");
                map.put("code", 0);
            } else {
                map.put("msg", "failed");
                map.put("code", -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 修改权限
     * @param nickname
     * @param role
     * @return
     */
    @Override
    public Map<String, Object> updateRole(String id,String nickname, String role) {
        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> utilMap = new HashMap<>();
//        utilMap.put("password", password);
//        String pwdS = ShiroMD5Util.SysMD5(utilMap);
        try {
            int cout = adminDao.updateRole(id,nickname, role);
            if (cout > 0) {
                map.put("msg", "ok");
                map.put("code", 0);
            } else {
                map.put("msg", "failed");
                map.put("code", -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> deleteAdmin(String id) {
        Map<String, Object> map = new HashMap<>();

        try {
            adminDao.delete(id);
            map.put("msg","ok");
        } catch (Exception e) {
            map.put("msg","系统繁忙");
            e.printStackTrace();
        }
        return map;
    }


}
