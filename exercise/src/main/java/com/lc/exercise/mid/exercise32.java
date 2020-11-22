package com.lc.exercise.mid;


/**
 * 旋转链表
 */
public class exercise32 {


    public static ListNode rotateRight(ListNode head, int k) {
        // 容错判断
        if(head == null || head.next == null || k == 0) {
            return head;
        }

        // 计算长度 linkSize
        ListNode tempNode = head;
        int linkSize = 0;
        while(tempNode != null) {
            linkSize++;
            tempNode = tempNode.next;
        }

        // 偏移取余
        int residue = linkSize - (k % linkSize);
        if (residue == linkSize) {
            return head;
        }
        tempNode = head;

        // 找到新的头结点 tempHead
        while(residue > 1) {
            tempNode = tempNode.next;
            residue--;
        }
        ListNode tempHead = head.next;
        if(tempNode.next != null) {
            tempHead = tempNode.next;
            tempNode.next = null;
        } else {
            head.next = null;
        }
        tempNode = tempHead;
        while(tempNode.next != null) {
            tempNode = tempNode.next;
        }

        tempNode.next = head;

        return tempHead;
    }

    public static void main(String[] args) {

        // 1 2 3 -> 3 1 2

//        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);

//        node0.next = node1;
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        ListNode listNode = rotateRight(node1, 2);
        System.out.println("ending...");
    }


}
