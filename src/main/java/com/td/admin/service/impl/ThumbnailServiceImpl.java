package com.td.admin.service.impl;

import com.td.admin.common.Constants;
import com.td.admin.dao.ThumbnailDao;
import com.td.admin.entity.Thumbnail;
import com.td.admin.service.ThumbnailService;
import com.td.admin.util.RemoveIMGUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ThumbnailServiceImpl implements ThumbnailService {
    @Autowired
    private ThumbnailDao thumbnailDao;

    /**
     * 添加轮播图
     * @param thumbnail
     * @return
     */
    @Override
    public Map<String, Object> addThumbnail(Thumbnail thumbnail) {
        Map<String, Object> resMap=new HashMap<>();
        String msg="ok";

        try {
            thumbnailDao.add(thumbnail);
        } catch (Exception e) {

            File file = new File(Constants.BANNER_UPLOAD_PATH+thumbnail.getSrc());
            if(file.exists()){
                file.delete();
            }

            msg="添加轮播图失败！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }

    /**
     * 修改轮播图
     * @param thumbnail
     * @return
     */
    @Override
    public Map<String, Object> updThumbnail(Thumbnail thumbnail) {
        Map<String, Object> resMap=new HashMap<>();
        String msg="ok";

        try {
            thumbnailDao.update(thumbnail);
        } catch (Exception e) {
            msg="修改轮播图失败！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }

    /**
     * 根据ID删除轮播图
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> delThumbnail(Integer id) {
        Map<String, Object> resMap=new HashMap<>();
        String msg="ok";

        try {
            Thumbnail t = thumbnailDao.findById(id);
            thumbnailDao.delete(id);
            RemoveIMGUtil.DeleteForPath(Constants.BANNER_UPLOAD_PATH+t.getSrc());
        } catch (Exception e) {
            msg="删除轮播图失败！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }

    /**
     * 查询全部轮播图
     * @return
     */
    @Override
    public List<Thumbnail> findAll() {
        return thumbnailDao.findAll();
    }

    /**
     * 启用轮播图
     * @param id
     * @return
     */
    public Map<String, Object> start(Integer id) {
        Map<String, Object> resMap=new HashMap<>();
        String msg="ok";
        try {
            Thumbnail t = thumbnailDao.findById(id);
            if(t!=null){
                t.setStatus(0);
                thumbnailDao.update(t);
            }else{
                msg="启用失败！不存在这个轮播图！";
            }
        } catch (Exception e) {
            msg="启用失败！系统出错！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }

//    根据ID查询轮播图
    @Override
    public Thumbnail findById(Integer id) {
        return thumbnailDao.findById(id);
    }

    //根据ID删除多个轮播图
    @Override
    public Map<String, Object> delThumbnails(Integer [] arr) {
        Map<String, Object> resMap=new HashMap<>();
        String msg="ok";
        Integer success=0;
        Integer error=0;
        try {
            for(Integer id:arr){
                Thumbnail t = thumbnailDao.findById(id);
                if(t==null){
                    error+=1;
                }else{
                    System.out.println("filePath:"+Constants.BANNER_UPLOAD_PATH+t.getSrc());
                    RemoveIMGUtil.DeleteForPath(Constants.BANNER_UPLOAD_PATH+t.getSrc());
                    thumbnailDao.delete(id);
                    success+=1;
                }
            }

        } catch (Exception e) {
            msg="删除轮播图失败！";
            e.printStackTrace();
        }
        resMap.put("success",success);
        resMap.put("error",error);
        resMap.put("msg",msg);
        return resMap;
    }


    /**
     * 停用轮播图
     * @param id
     * @return
     */
    public Map<String, Object> stop(Integer id) {
        Map<String, Object> resMap=new HashMap<>();
        String msg="ok";
        try {
            Thumbnail t = thumbnailDao.findById(id);
            if(t!=null){
                t.setStatus(1);
                thumbnailDao.update(t);
            }else{
                msg="停用失败！不存在这个轮播图！";
            }
        } catch (Exception e) {
            msg="停用失败！系统出错！";
            e.printStackTrace();
        }
        resMap.put("msg",msg);
        return resMap;
    }
}
