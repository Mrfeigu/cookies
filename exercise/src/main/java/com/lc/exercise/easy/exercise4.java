package com.lc.exercise.easy;

import com.lc.exercise.mid.ListNode;

/**
 * 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class exercise4 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode index = new ListNode();
        head = index;
        while (l1 != null || l2 != null){
            if(l1 == null){
                index.next = l2;
                l2 = l2.next;
                index = index.next;
                index.next = null;
            } else if(l2 == null){
                index.next = l1;
                l1 = l1.next;
                index = index.next;
                index.next = null;
            } else{
                if(l1.val <= l2.val){
                    index.next = l1;
                    l1 = l1.next;
                    index = index.next;
                    index.next = null;
                }else{
                    index.next = l2;
                    l2 = l2.next;
                    index = index.next;
                    index.next = null;
                }
            }
        }
        return head.next;
    }

    public static void main(String[] args){
        ListNode index1 = new ListNode(1);
        ListNode index2 = new ListNode(2);
        ListNode index3 = new ListNode(4);

        index1.next = index2;
        index2.next = index3;


        ListNode index4 = new ListNode(1);
        ListNode index5 = new ListNode(3);
        ListNode index6 = new ListNode(4);

        index4.next = index5;
        index5.next = index6;

        ListNode listNode = mergeTwoLists(index1, index4);


    }

}
