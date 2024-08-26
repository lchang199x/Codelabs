package ktest

class Test25(private val capacity: Int) {
    private val head: LruNode = LruNode(-1, -1)
    private val tail: LruNode = LruNode(-1, -1)
    private val map = hashMapOf<Int, LruNode>()

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = map[key] ?: return -1
        moveToTail(node)
        return node.value
    }

    fun put(key: Int, newValue: Int) {
        // prefer map.containsKey(key)而不是[map[key] != null], 因为map的value本来就可以为null
        var node = map[key]
        if (map.contains(key)) {
            node!!.value = newValue
            moveToTail(node)
            return
        }

        if (map.size + 1 > capacity) {
            node = head.next!!
            // 注意一定要声明一个变量node而不能在下面两个方法都传入head.next，因为第一个方法改变了第二个方法的输入
            deleteNode(node)
            // 注意删除节点一定要同时清楚对应的key
            map.remove(node.key)
        }
        node = LruNode(key, newValue)
        map[key] = node
        insertToTail(node)
    }

    private fun moveToTail(node: LruNode) {
        deleteNode(node)
        insertToTail(node)
    }

    private fun deleteNode(node: LruNode) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    private fun insertToTail(node: LruNode) {
        // 这里不用val temp = tail.prev!!
        tail.prev!!.next = node
        node.prev = tail.prev
        tail.prev = node
        node.next = tail
    }

    fun printPretty() {
        var temp: LruNode? = head

        while (temp != null) {
            if (temp != head && temp != tail) print("[${temp.key}, ${temp.key}]")
            temp = temp.next
        }
        println()
    }

}

class LruNode(
    val key: Int,
    var value: Int,
    var next: LruNode? = null,
    var prev: LruNode? = null
)

fun main() {
    val lruCache = Test25(2)
    lruCache.put(1, 1) // 缓存是 {1=1}
    lruCache.printPretty()
    lruCache.put(2, 2) // 缓存是 {1=1, 2=2}
    lruCache.printPretty()
    lruCache.get(1)    // 返回 1
    lruCache.printPretty()
    lruCache.put(3, 3) // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lruCache.printPretty()
    lruCache.get(2)    // 返回 -1 (未找到)
    lruCache.printPretty()
    lruCache.put(4, 4) // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    lruCache.printPretty()
    lruCache.get(1)    // 返回 -1 (未找到)
    lruCache.printPretty()
    lruCache.get(3)    // 返回 3
    lruCache.printPretty()
    lruCache.get(4)    // 返回 4
    lruCache.printPretty()
}