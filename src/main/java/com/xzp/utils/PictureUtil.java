package com.xzp.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/2 9:09
 * @Version 1.0
 */
public class PictureUtil {
    private static String SAVE_PATH=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
    private static Map<String,Object> result;

    public static Map<String,Object> savePicture(MultipartFile file){
        result=new HashMap<>();
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)) {
            // 新建文件
            File filepath = new File(SAVE_PATH, filename);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            try {
                // 写入文件
                file.transferTo(new File(filepath + File.separator));
            } catch (IOException e) {
                e.printStackTrace();
               result.put("flag",false);
               return result;
            }
        }
        result.put("flag",true);
        result.put("filename",filename);
        result.put("path",SAVE_PATH+filename);
        return result;
    }
}
