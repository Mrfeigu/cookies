package com.lc.exercise.hard;


import java.util.LinkedList;
import java.util.List;

/**
 * 文本左右对齐
 *
 * @author linzhenghui
 * @date 2020/07/22
 */
public class exercise8 {

    public List<String> fullJustify(String[] words, int maxWidth) {

        // 计算单词长度
        int[] wordSize = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordSize[i] = words[i].length();
        }

        List<String> resList = new LinkedList<>();

        int index = 0;
        while(index < words.length) {

            int inIndex = 0;
            StringBuilder res = new StringBuilder();
            List<String> resL = new LinkedList<>();
            while(inIndex < maxWidth) {

                // todo 有问题

            }



        }




        return resList;

    }


    public static void main(String[] args) {

    }

}
