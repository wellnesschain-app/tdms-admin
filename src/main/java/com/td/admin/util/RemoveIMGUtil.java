package com.td.admin.util;

import java.io.File;

/**
 * 删除本地文件工具类
 */
public class RemoveIMGUtil {
    /**
     * 根据文件路径删除本地文件
     * @param filePath
     * @return
     */
    public static boolean DeleteForPath(String filePath){
        boolean flag=false;
        try {
            File file=new File(filePath);
            if(file.exists()){
                file.delete();
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
