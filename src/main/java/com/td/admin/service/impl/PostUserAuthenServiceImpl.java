package com.td.admin.service.impl;

import com.td.admin.dao.PostUserAuthenDao;
import com.td.admin.service.PostUserAuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PostUserAuthenServiceImpl implements PostUserAuthenService {
    @Autowired
    private PostUserAuthenDao postUserAuthenDao;

    /**
     * 修改审核状态
     * @param map
     */
    @Override
    public Map<String,Object> update(Map<String, Object> map) {
        Map<String,Object> res=new HashMap<>();
        String msg="ok";
        try {
            postUserAuthenDao.update(map);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        res.put("msg",msg);
        return res;
    }
}
