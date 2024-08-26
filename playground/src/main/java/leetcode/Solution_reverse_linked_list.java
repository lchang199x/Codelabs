package leetcode;

import common.ListNode;

/**
 * 206. Reverse Linked List: https://leetcode-cn.com/problems/reverse-linked-list/
 * <p>
 * 剑指 Offer 24. 反转链表 LCOF: https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 *
 * <pre> {@code
 * class Solution {
 *   public ListNode reverseList(ListNode head) {
 *     ...
 *   }
 *  }</pre>
 */
public class Solution_reverse_linked_list {
    public static void main(String[] args) {
        ListNode data = ListNode.buildLinkedList(5);
        System.out.println("input: " + data);
        ListNode result = reverseList(data);
        System.out.println("output: " + result);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = null;
        ListNode temp = null;

        while (head != null) {
            temp = head.next;
            head.next = result;
            result = head;
            head = temp;
        }
        return result;
    }
}
