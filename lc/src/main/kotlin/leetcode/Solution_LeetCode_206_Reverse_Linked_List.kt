package leetcode

import common.ListNode
import common.buildLinkedList
import common.printPretty

/**
 * Reverse Linked List
 * [](https://leetcode-cn.com/problems/reverse-linked-list/)
 */
class Solution_LeetCode_206_Reverse_Linked_List {
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var current = head
        var result: ListNode? = null
        var temp: ListNode? = null

        while (current != null) {
            temp = current.next
            current.next = result
            result = current
            current = temp
        }

        return result
    }
}

fun main() {
    val list1 = buildLinkedList(1..5).apply { printPretty() }
    Solution_LeetCode_206_Reverse_Linked_List().reverseList(list1).printPretty()

    val list2 = buildLinkedList(1..5, rand = true).apply { printPretty() }
    Solution_LeetCode_206_Reverse_Linked_List().reverseList(list2).printPretty()
}