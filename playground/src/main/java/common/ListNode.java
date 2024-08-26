package common;

import java.util.Objects;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildLinkedList(int limit) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i <= limit; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode temp = this;
        while (temp != null) {
            sb.append(temp.val).append("->");
            temp = temp.next;
        }
        return sb.append("∅").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val && Objects.equals(next, listNode.next); //todo sure?
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}
