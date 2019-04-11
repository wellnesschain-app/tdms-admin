package com.td.admin.common;

/**
 * 图片保存路径
 */
public class Constants {
        //测试 /data/svo/banner/   正式 /data/tomcat-server/banner_uploads/
        public static final String BANNER_UPLOAD_PATH="/data/tdms/banner/";//轮播图保存路径
        //测试  /data/svo/notice/  正式 /data/tomcat-server/notice_uploads/
        public static final String NOTICE_UPLOAD_PATH="/data/svo/notices/";//公告图片上传
        public static final String WALLET_ADDR = "";
        public static String CURRENT_IMG=null;//用于保存上传的当前图片
        public static final String SVO_IP="http://47.244.135.69";
        public static final String SVO_PORT="9901";
        public static final String SVO_COTARCT_ADDR="0xa5d9ac5ffef9c72e4cf91ebb6c8a672da044fb31";//FST合约地址
}
