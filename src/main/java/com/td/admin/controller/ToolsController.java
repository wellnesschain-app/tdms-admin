package com.td.admin.controller;

import com.td.admin.dao.AssetsDao;
import com.td.admin.dao.CoreColdDao;
import com.td.admin.dao.MemberGiveCoreRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/admin/tools")
public class ToolsController {

    @Autowired
    private MemberGiveCoreRecordDao memberGiveCoreRecordDao;
    @Autowired
    private AssetsDao assetsDao;
    @Autowired
    private CoreColdDao coreColdDao;


/*    @ResponseBody
    @RequestMapping(value = "/importExcel")
    public Map<String,Object> importExcel(@RequestParam("excelFile")MultipartFile excelFile){
        Map<String,Object> res=new HashMap<>();
        String msg="ok";
        Integer success=0;
        Integer error=0;
        try {
            String filename = excelFile.getOriginalFilename();
            String newFileName = filename.replaceAll(" ", "");
            String[] split = newFileName.split("\\.");
            String suffix=split[1];
            if(!suffix.equals("xls")&&!suffix.equals("xlsx")){
                msg="请选择Excel表格文件";
            }else{
                ReadExcelUtil excelUtil=new ReadExcelUtil();
                List<Map<String, Object>> infos = excelUtil.getExcelInfo(excelFile);
                for(Map<String,Object> info:infos){

                    //先查询是否存在该用户
                    Wallet wallet = walletDao.findById(info.get("id").toString());
                    if(wallet!=null){
                        //更新用户积分余额
                        Assets assets = assetsDao.findByWid(Long.valueOf(info.get("id").toString()));
                        System.out.println(assets);
                        assets.setCore(assets.getCore()+Double.valueOf(info.get("core").toString()));//增加总积分
                        assets.setCoolAssets(String.valueOf(Double.valueOf(assets.getCoolAssets())+Double.valueOf(info.get("core").toString())));//增加冻结积分
                        assetsDao.update(assets);

                        //写入积分冻结记录表
                        long times=1546617600;//1-5日的时间戳    只针对第一批
                        Timestamp date=new Timestamp(times*1000L);
                        Map<String,Object> coreColdMap=new HashMap<>();
                        coreColdMap.put("wid",info.get("id").toString());
                        coreColdMap.put("core",info.get("core").toString());
                        coreColdMap.put("startTime",date);
                        coreColdMap.put("day",150);
                        coreColdMap.put("core",info.get("core").toString());
                        coreColdDao.add(coreColdMap);

                        //写入积分放送冻结记录
                        Map<String,Object> memberGiveCoreRecordMap=new HashMap<>();
                        memberGiveCoreRecordMap.put("wid",info.get("id").toString());
                        memberGiveCoreRecordMap.put("core",info.get("core").toString());
                        memberGiveCoreRecordMap.put("name",info.get("name").toString());
                        memberGiveCoreRecordMap.put("content",info.get("content").toString());
                        memberGiveCoreRecordDao.add(memberGiveCoreRecordMap);

                        success++;
                    }else{
                        System.out.println("放送积分失败ID为:"+info.get("id").toString());
                        error++;
                    }
                }
            }
        }catch (Exception e){
            msg="系统繁忙";
            e.printStackTrace();
        }
        res.put("success",success);
        res.put("error",error);
        res.put("msg",msg);
        return res;
    }*/

    @RequestMapping(value = "/toImportExcel")
    public String toImportExcel(){
        return "/admin/tools/import-excel";
    }
}
