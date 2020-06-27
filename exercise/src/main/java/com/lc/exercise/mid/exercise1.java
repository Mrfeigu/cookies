package com.lc.exercise.mid;


/**
 * 树序列化与反序列化
 */
public class exercise1 {


    private TreeNode resultNode;
    private boolean flag = true;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor_f(root, p, q);
        return resultNode;
    }

    public boolean lowestCommonAncestor_f(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {return false;}

        boolean leftResult = lowestCommonAncestor_f(root.left, p, q);
        boolean rightResult = lowestCommonAncestor_f(root.right, p, q);

        if((flag && leftResult && rightResult) || (flag && (root.val == p.val || root.val == q.val) &&(leftResult || rightResult))){
            resultNode = root;
            flag = false;
        }

        if(root.val == p.val || root.val == q.val){
            return true;
        }

        return leftResult || rightResult;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new exercise2().deserialize("6,2,0,null,null,4,null,null,8,7,3,null,null,5,null,null,9,null,null");
        TreeNode result = new exercise1().lowestCommonAncestor(treeNode, new TreeNode(2), new TreeNode(4));
        System.out.println("ending....");
    }


}
