package com.td.admin.controller;

import com.td.admin.common.Constants;
import com.td.admin.dao.ActivityDao;
import com.td.admin.entity.Thumbnail;
import com.td.admin.service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/banner")
public class BannerController {
    @Autowired
    private ThumbnailService thumbnailService;
    @Autowired
    private ActivityDao activityDao;


    @ResponseBody
    @RequestMapping(value = "/deleteActivity")
    public Map<String,Object> deleteActivity(String id){
        Map<String,Object> map =  new HashMap<>();
        String msg="ok";
        try {
            activityDao.delete(id);
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 添加活动倒计时
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addActivity")
    public Map<String,Object> addActivity(@RequestParam Map<String,Object> map){
        Map<String,Object> result=new HashMap<>();
        String msg="ok";
        try {
            if(map.size()>0){
                activityDao.add(map);
            }else{
                msg="参数丢失";
            }
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        result.put("msg",msg);
        return result;
    }

    /**
     * 请求添加活动倒计时列表
     * @param
     * @return
     */
    @RequestMapping(value = "/toAddActivity")
    public String toActivity(){
        return "/admin/banner/activity-add";
    }

    /**
     * 请求活动倒计时列表
     * @param map
     * @return
     */
    @RequestMapping(value = "/toActivityList")
    public String toActivityList(Map<String,Object> map){
        List<Map<String, Object>> activitys = activityDao.findAll();
        map.put("activitys",activitys);
        return "/admin/banner/activity-list";
    }


    /**
     * 删除多个轮播图
     * @param arr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteList",method = RequestMethod.POST)
    public Map<String,Object> deleteList(@RequestParam(value = "arr[]") Integer[]arr){
        return thumbnailService.delThumbnails(arr);
    }


    /**
     * 删除单个轮播图
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String,Object> delete(Integer id){
        return thumbnailService.delThumbnail(id);
    }


    /**
     * 请求到修改轮播图页面
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/toEditBanner")
    public String toEditBanner(Integer id, HttpSession session){
        Thumbnail t = thumbnailService.findById(id);
        session.setAttribute("banner",t);
        return "admin/banner/banner-edit";
    }


    /**
     * 修改轮播图
     * @param thumbnail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editBanner")
    public Map<String,Object> editBanner(Thumbnail thumbnail){
        thumbnail.setSrc(Constants.CURRENT_IMG);
        Constants.CURRENT_IMG=null;
        return thumbnailService.updThumbnail(thumbnail);
    }

    /**
     * 添加轮播图
     * @param thumbnail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addBanner")
    public Map<String,Object> addBanner(Thumbnail thumbnail){
        thumbnail.setSrc(Constants.CURRENT_IMG);
        Constants.CURRENT_IMG=null;
        return thumbnailService.addThumbnail(thumbnail);
    }

    /**
     * 轮播图上传
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadBanner")
    public Map<String,Object> uploadBanner(@RequestParam("file") MultipartFile file){
        Map<String,Object> map = new HashMap<>();
        String msg="ok";
        try {
            String suffix = getSuffix(file.getOriginalFilename());
            System.out.println(suffix);
            if(suffix.equals(".png")||suffix.equals(".jpg")){
                long fileName = System.currentTimeMillis();//获取当前时间戳作为图片文件名
                File newFile = new File(Constants.BANNER_UPLOAD_PATH + fileName + suffix);
                Constants.CURRENT_IMG=newFile.getName();//保存当前文件名，给接下来的轮播对象保存用
                map.put("url",newFile.getName());
                file.transferTo(newFile);//开始保存
            }else{
                msg="图片上传失败！格式不支持！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 启用轮播图
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/startBanner")
    public Map<String,Object> startBanner(Integer id){
        return thumbnailService.start(id);
    }

    /**
     * 停用轮播图
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stopBanner")
    public Map<String,Object> stopBanner(Integer id){
        return thumbnailService.stop(id);
    }

    /**
     * 添加轮播图
     * @return
     */
    @RequestMapping(value = "/toAddBanner")
    public String toAddBanner(){
        return "admin/banner/banner-add";
    }

    /**
     * 轮播图列表
     * @return
     */
    @RequestMapping(value = "/toBannerList")
    public String toBannerList(HttpSession session){
        List<Thumbnail> list = thumbnailService.findAll();
        System.out.println(list);
        session.setAttribute("bannerList",list);
        return "admin/banner/banner-list";
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
