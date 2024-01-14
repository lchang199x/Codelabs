package leetcode

import common.KListNode
import common.buildLinkedList
import common.printPretty

/**
 * 反转链表
 * [](https://leetcode-cn.com/problems/reverse-linked-list/)
 */
class Solution_LeetCode_206_Reverse_Linked_List {
    fun reverseList(head: KListNode?): KListNode? {
        if (head?.next == null) return head

        var cur = head
        var result: KListNode? = null
        var temp: KListNode? = null

        while (cur != null) {
            temp = cur.next
            cur.next = result
            result = cur
            cur = temp
        }

        return result
    }

    /**
     * 反转链表: 递归解法
     * [](https://leetcode-cn.com/problems/reverse-linked-list/)
     */
    fun reverseListRecursive(head: KListNode?): KListNode? {
        // base case：基准情况，递归调用在这里终止
        if (head?.next == null)
            return head
        // 递归调用：递归的定义是一个函数用它自己来实现自己，递归调用应该不断向基准情形推进
        val result = reverseListRecursive(head.next)
        head.next!!.next = head
        head.next = null
        return result
    }
}

fun main() {
    val list1 = buildLinkedList(1..5).apply { printPretty() }
    Solution_LeetCode_206_Reverse_Linked_List().reverseList(list1).printPretty()

    val list2 = buildLinkedList(1..5, rand = true).apply { printPretty() }
    Solution_LeetCode_206_Reverse_Linked_List().reverseList(list2).printPretty()
}