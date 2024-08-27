package leetcode

import codelab.kotlin.common.ListNode
import codelab.kotlin.common.buildLinkedList
import codelab.kotlin.common.printPretty

class Solution_LeetCode_912_21_Merge_Sort_Related {
    /**
     * 归并排序
     * [](https://leetcode-cn.com/problems/sort-an-array/)
     */
    fun mergeSort(nums: IntArray): IntArray {
        // 归并排序需要一个临时数组保存左右两子数组合并后的结果，这个合并后的结果最终会拷贝回原数组对应位置，达到原地排序的效果
        val temp = IntArray(nums.size)
        mergeSort(nums, temp, 0, nums.size - 1)
        return nums
    }

    private fun mergeSort(nums: IntArray, temp: IntArray, l: Int, r: Int) {
        if (l < r) {
            val mid = (l + r) shr 1
            mergeSort(nums, temp, l, mid)
            mergeSort(nums, temp, mid + 1, r)
            var i = l
            var j = mid + 1
            var idx = 0
            while (i <= mid && j <= r) {
                temp[idx++] = if (nums[i] < nums[j]) nums[i++] else nums[j++]
            }
            while (i <= mid) {
                temp[idx++] = nums[i++]
            }
            while (j <= r) {
                temp[idx++] = nums[j++]
            }
            for (k in l..r) {
                nums[k] = temp[k - l]
            }
        }
    }

    /**
     * 链表排序: 归并排序思想
     * [](https://leetcode-cn.com/problems/7WHec2/)
     */
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        var head1 = head
        var head2 = split(head)

        head1 = sortList(head1)
        head2 = sortList(head2)

        return merge(head1, head2)
    }

    private fun split(head: ListNode): ListNode? {
        var slow = head
        var fast: ListNode? = head.next
        while (fast?.next != null) {
            slow = slow.next!!
            fast = fast.next!!.next
        }

        val result = slow.next
        slow.next = null // 使原链表在此节点断开

        return result
    }

    private fun merge(head1: ListNode?, head2: ListNode?): ListNode {
        val result = ListNode(-1) // 哑节点，归并排序所需临时链表的头
        var l1 = head1
        var l2 = head2
        var cur = result
        while (l1 != null && l2 != null) {
            if (l1.`val` < l2.`val`) {
                cur.next = l1
                l1 = l1.next
            } else {
                cur.next = l2
                l2 = l2.next
            }
            cur = cur.next!!
        }
        cur.next = l1 ?: l2
        return result.next!!
    }

    /**
     * 合并有序链表
     * [](https://leetcode-cn.com/problems/merge-two-sorted-lists/)
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1

        // 这两步处理基本是定式
        val result = ListNode(-1) // 1
        var temp = result // 2

        // kotlin中这两句是必须的，因为函数形参默认是final的
        // java中如果没有明确要求不要改变原链表，可以直接用l1, l2进行迭代
        // 但其实原链表怎么都会改变，因为除了头节点，后面的next指针指向全变了
        var head1 = l1
        var head2 = l2
        while (head1 != null && head2 != null) {
            if (head1.`val` < head2.`val`) {
                // head1重新赋值，不会影响已经设置到temp.next中的值
                // head1对所引用的对象执行操作会影响，如head1.data = -1
                temp.next = head1 // 3
                head1 = head1.next // 4
            } else {
                temp.next = head2
                head2 = head2.next
            }
            temp = temp.next!!

        }
        // 数组这里要while循环，链表只用if接上，后面的自动接上了
        if (head1 != null) temp.next = head1
        if (head2 != null) temp.next = head2

        return result.next
    }

    /**
     * 合并有序链表: 递归解法
     * [](https://leetcode-cn.com/problems/merge-two-sorted-lists/)
     *
     * 注意这里不用给l1和l2新建可变引用，也不用哑节点
     */
    fun mergeTwoListsRecursive(l1: ListNode?, l2: ListNode?): ListNode? {
        return if (l1 == null) {
            l2
        } else if (l2 == null) {
            l1
        } else if (l1.`val` > l2.`val`) {
            l2.next = mergeTwoListsRecursive(l1, l2.next)
            l2
        } else {
            l1.next = mergeTwoListsRecursive(l1.next, l2)
            l1
        }
    }
}

fun main() {
    println(
        Solution_LeetCode_912_21_Merge_Sort_Related()
            .mergeSort(intArrayOf(1, 5, 3, 2, 4))
            .contentToString()
    )
    println(
        Solution_LeetCode_912_21_Merge_Sort_Related()
            .mergeSort(intArrayOf(1, 1, 5, 3, 3, 3, 2, 5, 4))
            .contentToString()
    )

    Solution_LeetCode_912_21_Merge_Sort_Related()
        .mergeTwoLists(
            buildLinkedList(*intArrayOf(1, 7, 10)),
            buildLinkedList(*intArrayOf(2, 5, 8))
        ).printPretty()
}