package com.td.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.td.admin.dao.PostalSvoDao;
import com.td.admin.service.PostalSvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PostalSvoServiceImpl implements PostalSvoService {

    @Autowired
    private PostalSvoDao postalSvoDao;

    @Override
    public Map<String, Object> findPage(String id, String status, int pageNum, int pageSize) {
        Map<String,Object> res=new HashMap<>();
        String msg="ok";
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list = postalSvoDao.findPage(id,status);
            PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
            res.put("audits",pages);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        res.put("msg",msg);
        return res;
    }

}
