package com.td.admin.service;

import com.td.admin.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Map<String,Object> login(Admin admin);//管理员登录

    Map<String, Object> add(String username, String password, String nickname, String role);

    Map<String, Object> updateRole(String id ,String nickname, String role);

    Map<String, Object> deleteAdmin(String id);
}
