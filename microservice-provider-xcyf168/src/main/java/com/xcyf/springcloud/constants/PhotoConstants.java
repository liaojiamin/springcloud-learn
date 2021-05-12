package com.xcyf.springcloud.constants;

/**
 * @author liaojiamin
 * @Date:Created in 10:48 2021/5/12
 */
public class PhotoConstants {
    public static final int NUM_MAX_UPLOAD = 15;
    public static final int NUM_MAX_MYPAGE = 4;
    public static final String PICPATH_LINUX = "/data/dubbo/zhenai-overseas-api/svc_img";
    public static final String PICPATH_WIN = "D:\\data\\dubbo\\zhenai-overseas-api\\svc_img";
    public PhotoConstants() {
    }
    public static String getPicPath(){
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.indexOf("linux") >= 0){
            return PICPATH_LINUX;
        }
        return PICPATH_WIN;
    }
}
