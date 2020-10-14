package com.delicacy.cookies.util;

import java.io.File;
import java.io.IOException;

/**
 * @author linzhenghui
 * @date 2020/10/14
 */
public class FileUtils {



    /**
     * 创建file
     * @param file
     * @return
     */
    public static boolean createFile(File file) throws IOException {
        File parentFile = file.getParentFile();
        if(file.exists()){
            return true;
        }
        if(!parentFile.exists() && !parentFile.mkdirs()){
            return false;
        }
        return file.createNewFile();
    }


    public static void main(String[] args) {
    }



}
