package com.td.admin.controller;

import com.td.admin.entity.Thumbnail;
import com.td.admin.service.NoticeService;
import com.td.admin.service.ThumbnailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告Controller
 */
@Controller
@RequestMapping(value = "/admin/notice/")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ThumbnailService thumbnailService;



    /**
     * 重新发布公告
     *        标题  内容    类型      发布时间  编号
     * 参数名：bt   nr      type     time       id
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reRelease")
    public Map<String,Object> reRelease(@RequestParam Map<String,Object> map){
        return noticeService.update(map);
    }


    /**
     * 请求编辑公告
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/toEditNotice")
    public String toEditNotice(Integer id,HttpSession session){
        Map<String, Object> n = noticeService.findById(id);
        session.setAttribute("notice",n);

        return "admin/notice/notice-edit";
    }

    /**
     * 根据ID批量删除公告
     * @param arr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletes")
    public Map<String,Object> delete(@RequestParam(value = "arr[]")Integer[] arr){
        return noticeService.delete(arr);
    }

    /**
     * 根据ID删除公告
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String,Object> delete(Integer id){
        return noticeService.delete(id);
    }

    /**
     * 请求公告内容页
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/toDetails")
    public String toDetails(HttpSession session,Integer id){
        Map<String, Object> n = noticeService.findById(id);
        session.setAttribute("notice",n);
        return "admin/notice/notice-detail";
    }

    /**
     * 公告富文本图片上传
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file){
        return noticeService.upload(file);
    }

    /**
     * 添加公告
     *        标题  内容    类型      发布时间
     * 参数名：bt   nr      type     time
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/release")
    public Map<String,Object> add(String type,String bt,String nr,String bannerid){
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("tittle",bt);
        map.put("context",nr);
        return noticeService.insert(map);
    }

    @RequestMapping(value = "/toAddNotice")
    public String toAddNotice(HttpSession session){
        //Map<String, Object> n = noticeService.findById();
        List<Thumbnail> list = thumbnailService.findAll();
        session.setAttribute("bannerLists",list);
        return "admin/notice/notice-add";
    }

    /**
     * 请求公告列表
     * @return
     */
    @RequestMapping(value = "/toNoticeList")
    public String toNotice(HttpSession session,
                           @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "50")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = noticeService.findAll();
        PageInfo<Map<String, Object>> pages = new PageInfo<>(list);
        session.setAttribute("notices",pages);
        return "admin/notice/notice-list";
    }

}
