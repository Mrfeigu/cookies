package com.lc.exercise.mid;

/**
 * @author linzhenghui
 * @date 2020-03-09
 */
public class exercise2 {

    private void serialize_f(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(",null");
            return;
        }
        sb.append(",").append(root.val);
        serialize_f(root.left, sb);
        serialize_f(root.right, sb);
    }

    private int index = -1;
    private TreeNode deserialize_f(Integer[] data, int dataSize){
        index++;
        if(data.length < 1 || index >= dataSize || data[index] == null){
            return null;
        }
        TreeNode node = new TreeNode(data[index]);
        node.left = deserialize_f(data, dataSize);
        node.right = deserialize_f(data, dataSize);
        return node;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        serialize_f(root, stringBuilder);
        if(stringBuilder.length()<1){
            return "";
        }
        return stringBuilder.toString().substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        Integer[] tempInt = new Integer[split.length];
        for (int i = 0; i < tempInt.length; i++) {
            if(split[i].equals("null")){
                tempInt[i] = null;
            }else{
                tempInt[i] = Integer.parseInt(split[i]);
            }
        }
        return deserialize_f(tempInt, tempInt.length);
    }


    public static void main(String[] args){

        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(0);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(4);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;

        String serialize = new exercise2().serialize(treeNode1);
        TreeNode deserialize = new exercise2().deserialize(serialize);

        System.out.println("ending...");


    }


}
