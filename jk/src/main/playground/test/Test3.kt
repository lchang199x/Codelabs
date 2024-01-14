package test

import java.util.*

fun main() {
    val queue: Queue<Int> = ArrayDeque()
    queue.offer(1)
    queue.offer(2)
    queue.offer(3)
    queue.offer(4)
    queue.offer(5)

    // 参见AbstractQueue的clear方法
    // 另可以while (!queue.isEmpty()) queue.poll()
    // queue中的元素如果还要用，body内应该用queue或stack记录，前者用于广度遍历，后者用于深度遍历
    while (queue.poll() != null)
        ;
    println(queue)
}