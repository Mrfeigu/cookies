package com.lc.exercise.mid;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linzhenghui
 * @date 2020/3/23
 */
public class exercise3 {

    TreeNode root = null;
    int sort_k = 0;
    static List<TreeNode> treeNodes = new LinkedList<>();

    public static void buildTree(int num, TreeNode root){
        while(true){
            if(root.left == null && root.val > num){
                root.left = new TreeNode(num);
                break;
            }
            if(root.right == null && root.val < num){
                root.right = new TreeNode(num);
                break;
            }
            if(num < root.val) {
                root = root.left;
                continue;
            }
            if(num > root.val){
                root = root.right;
            }
        }
    }

    public static void traverse(TreeNode root){
        if(root == null) return;
        traverse(root.left);
        treeNodes.add(root);
        traverse(root.right);
    }


    public exercise3(int k, int[] nums) {
        sort_k = k;
        if(nums.length < 1) {return;}
        for (int num : nums) {
            if (root == null) {
                root = new TreeNode(num);
            }
            buildTree(num, root);
        }
    }

    public int add(int val) {
        buildTree(val, root);
        treeNodes.clear();
        traverse(root);
        return treeNodes.get(treeNodes.size()-sort_k).val;
    }


}
