package leetcode

import java.util.*

/**
 * 堆相关的一些题目解法
 */
class Solution_LeetCode_215_347_Heap_Related {

    /**
     * 数组中第k大元素: LeetCode_215
     * [](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)
     *
     * 另堆排序解法
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val queue = PriorityQueue<Int>(k)
        nums.forEach {
            queue.offer(it)
            if (queue.size > k) queue.poll()
        }
        return queue.peek()
    }

    /**
     * 数组中第k大元素: LeetCode_215
     * [](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)
     *
     * 另堆排序解法
     */
    fun findKthLargest2(nums: IntArray, k: Int): Int {
        val minHeap = PriorityQueue<Int>(k)
        nums.forEach {
            if (minHeap.size < k)
                minHeap.offer(it)
            else if (minHeap.peek() < it) {
                minHeap.poll()
                minHeap.offer(it)
            }
        }
        return minHeap.peek()
    }

    /**
     * 前k个高频元素：LeetCode_347
     * [](https://leetcode-cn.com/problems/top-k-frequent-elements/)
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val queue = PriorityQueue<IntArray> { o1, o2 ->
            o1[1] - o2[1]
        }

        val map = hashMapOf<Int, Int>().apply {
            nums.forEach {
                put(it, this.getOrDefault(it, 0) + 1)
            }
        }.forEach {
            // 固定处理模式：先offer，看size是不是比k大，实际上queue的大小永远时k+1
            queue.offer(intArrayOf(it.key, it.value))
            if (queue.size > k) {
                queue.poll()
            }
        }

        return queue.map { it[0] }.toIntArray()
    }

    /**
     * 前k个高频元素：LeetCode_347
     * [](https://leetcode-cn.com/problems/top-k-frequent-elements/)
     */
    fun topKFrequent2(nums: IntArray, k: Int): IntArray {
        // Map.Entry<Int, Int>占用的存储空间应该比IntArray大一点？处理慢一点？
        val queue = PriorityQueue<Map.Entry<Int, Int>> { o1, o2 ->
            o1.value - o2.value
        }

        hashMapOf<Int, Int>().apply {
            nums.forEach {
                put(it, this.getOrDefault(it, 0) + 1)
            }
        }.forEach {
            queue.offer(it)
            if (queue.size > k) {
                queue.poll()
            }
        }

        return queue.map { it.key }.toIntArray()
    }
}

fun main() {
    println(Solution_LeetCode_215_347_Heap_Related().findKthLargest(intArrayOf(1, 2, 2, 3, 5, 4, 7), 3))
    println(Solution_LeetCode_215_347_Heap_Related().findKthLargest2(intArrayOf(1, 2, 2, 3, 5, 4, 7), 3))
    println(Solution_LeetCode_215_347_Heap_Related().topKFrequent(intArrayOf(1, 1, 2, 2, 3, 5, 4, 4, 4, 7), 3).contentToString())
    println(Solution_LeetCode_215_347_Heap_Related().topKFrequent2(intArrayOf(1, 1, 2, 2, 3, 5, 4, 4, 4, 7), 3).contentToString())
}