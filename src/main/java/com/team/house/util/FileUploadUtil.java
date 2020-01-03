package com.team.house.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public class FileUploadUtil {

    private static final String savePosition="f:\\images\\";

    /**
     * 上传文件的工具类
     * @param pfile  上传文件
     * @return  上传文件的名称
     */
    public static String upload(CommonsMultipartFile pfile){
        try {
            //获取文件的扩展名
            String fname = pfile.getOriginalFilename();
            String fexpName = fname.substring(fname.lastIndexOf("."));
            //生成新的文件名
            String  unigue = System.currentTimeMillis()+"";
            String saveFileName = unigue+fexpName;
            String savePath = savePosition+saveFileName;
            File savefile = new File(savePath);
            pfile.transferTo(savefile);//上传
            return saveFileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String fileName){
        File file = new File(savePosition+fileName);
        return file.delete();
    }
}
