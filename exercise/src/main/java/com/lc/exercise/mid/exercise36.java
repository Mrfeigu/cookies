package com.lc.exercise.mid;

/**
 * 简化Unix路径
 */
public class exercise36 {


    public String simplifyPath(String path) {

        if(path == null || path.isEmpty()) {
            return path;
        }

        String[] pathList = path.split("/");
        StringBuilder sb = new StringBuilder();
        for (String s : pathList) {

            if(s.isEmpty() || s.equals("/") || s.equals(".")) {
                continue;
            }

            if(s.equals("..")) {
                int lastIndexOf = sb.lastIndexOf("/");
                if(lastIndexOf >= 0) {
                    sb.delete(lastIndexOf, sb.length());
                }
                continue;
            }

            sb.append("/").append(s);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }



    public static void main(String[] args) {
        String path = "/../";
        String s = new exercise36().simplifyPath(path);
        System.out.println(s);
        System.out.println("ending...");
    }




}
