package com.delicacy.cookies.testdemo;
import java.io.*;
import java.util.LinkedList;

/**
 * @author linzhenghui
 * @date 2021/3/31
 */
public class FileOverturn {



    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\linzhenghui\\Desktop\\1.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        LinkedList<String> resList = new LinkedList<>();
        bufferedReader.lines().forEach(resList::addFirst);


        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\linzhenghui\\Desktop\\2.txt");
        for (String s : resList) {
            s += "\n\r";
            byte[] bytes = s.getBytes();
            fileOutputStream.write(bytes);
        }

        fileOutputStream.flush();

    }


}
