package com.td.admin.service.impl;


import com.td.admin.common.Constants;
import com.td.admin.dao.NoticeDao;
import com.td.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    /**
     * 添加公告
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> insert(Map<String, Object> map) {
        Map<String,Object> resMap=new HashMap<>();
        String msg="ok";

        try {
            noticeDao.add(map);
        } catch (Exception e) {
            msg="发布失败！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<Map<String, Object>> findAll() {
        return noticeDao.findAll();
    }

    /**
     * 公告图片上传
     * @param file
     * @return
     */
    @Override
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> dataMap=new HashMap<>();
        Map<String, Object> resMap=new HashMap<>();
        Integer code=0;
        String msg="";
        String src="";
        try {
            String filename = file.getOriginalFilename();
            String suffix = getSuffix(filename);
            if(!suffix.equals(".jpg")&&!suffix.equals(".png")&&!suffix.equals(".gif")){
                code=2;
                msg="请选择图片！";
            }else{
                String newFileName = System.currentTimeMillis() + suffix;
                file.transferTo(new File(Constants.NOTICE_UPLOAD_PATH+newFileName));
                //http://www.bmsjf.net   测试用    http://47.52.108.198:9802
                src="http://www.svo.wang/notice/"+newFileName;
            }
        } catch (Exception e) {
            code=1;
            msg="上传图片失败！";
            e.printStackTrace();
        }
        dataMap.put("src",src);
        resMap.put("msg",msg);
        resMap.put("code",code);
        resMap.put("data",dataMap);
        return resMap;
    }

    /**
     * 根居编号查询公告信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findById(Integer id) {
        return noticeDao.findById(id);
    }

    /**
     * 根据ID删除公告
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> delete(Integer id) {
        Map<String,Object> resMap=new HashMap<>();
        String msg="ok";

        try {
            Map<String, Object> notice = noticeDao.findById(id);
            if(notice!=null||notice.size()!=0){
                noticeDao.del(id);
            }else{
                msg="删除失败！";
            }

        } catch (Exception e) {
            msg="删除失败！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }

    /**
     * 根据ID数字批量删除公告
     * @param arr
     * @return
     */
    @Override
    public Map<String, Object> delete(Integer[] arr) {
        Map<String,Object> resMap=new HashMap<>();
        String msg="ok";
        Integer success=0;
        Integer error=0;

        try {
            for(Integer id:arr){
                Map<String, Object> n = noticeDao.findById(id);
                if(n!=null||n.size()!=0){
                    noticeDao.del(id);
                    success+=1;
                }else{
                    error+=1;
                }
            }
        } catch (Exception e) {
            msg="删除出现错误:"+e.getMessage();
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        resMap.put("error",error);
        resMap.put("success",success);
        return resMap;
    }

    /**
     * 修改公告
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> update(Map<String, Object> map) {
        Map<String, Object> resMap= new HashMap<>();
        String msg="ok";

        try {
            String id=(String) map.get("id");
            int i = Integer.parseInt(id);
            Map<String, Object> n = noticeDao.findById(i);
            if(n!=null){
                noticeDao.upd(map);
            }else{
                msg="发布失败！";
            }
        } catch (Exception e) {
            msg="发布时遇到错误："+e.getMessage();
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }


    /**
     * 按文件名获取后缀名的方法
     * @param filename 文件名
     * @return 后缀名
     */
    private String getSuffix(String filename){
        return filename.substring(filename.lastIndexOf("."));
    }
}
