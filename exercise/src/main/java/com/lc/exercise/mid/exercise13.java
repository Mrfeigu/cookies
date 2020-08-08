package com.lc.exercise.mid;


public class exercise13 {


    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) return head;
        ListNode pre = null, lag = null, left = head, right = getNextK(head, k);
        if(right == null) return head;
        int flag = 0;
        while(right != null){
            flag++;
            //
            lag = right.next;
            right.next = null;
            // 反转后的头
            ListNode reversal = reversal(left);
            if(pre != null) pre.next = reversal;
            // 反转后的尾
            ListNode nextK = getNextK(reversal, k);
            if(nextK == null) break;
            nextK.next = lag;
            pre = nextK;
            if(flag == 1){
                head = reversal;
            }
            left = nextK.next;
            right = getNextK(left, k);
            if(left == null || right == null) break;
        }
        return head;
    }

    /** 找到第k个*/
    private ListNode getNextK(ListNode node, int k){
        int index = 1;
        ListNode indexNode = node;
        while(indexNode != null && index < k){
            indexNode = indexNode.next;
            if(indexNode == null) return null;
            index++;
        }
        return indexNode;
    }

    /** 翻转连表*/
    private ListNode reversal(ListNode node){
        if(node == null) return node;
        ListNode tail = node;
        while(tail.next != null){
            tail = tail.next;
        }
        while(node != null && node != tail){
            ListNode n = node;
            node = node.next;
            n.next = tail.next;
            tail.next = n;
        }
        return tail;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        new exercise13().reverseKGroup(node1, 1);
    }



}
