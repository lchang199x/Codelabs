package ktest

import java.util.*

fun main(args: Array<String>) {
    // heap is min heap
    val heap = PriorityQueue<Int> { a, b -> a - b }
    val minHeap = PriorityQueue<Int>()
    val maxHeap = PriorityQueue<Int> { a, b -> b - a }
    heap.offer(1)
    minHeap.offer(1)
    maxHeap.offer(1)
    heap.offer(2)
    minHeap.offer(2)
    maxHeap.offer(2)
    heap.offer(3)
    minHeap.offer(3)
    maxHeap.offer(3)

    println(heap.poll()) // 1
    println(minHeap.poll()) // 1
    println(maxHeap.poll()) // 3
}
