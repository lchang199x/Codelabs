@file:JvmName("Lists")

package codelab.kotlin.common

//typealias ListNode = ListNode

/**
 * 1. data class必须有主构造函数，且主构造函数至少要有一个参数
 * 2. data class默认生成的equals、hashCode、toString、componentN、copy方法
 * 3. toString方法的默认实现类似"ListNode(val=3, next=ListNode(val=7, next=ListNode(val=-11, next=null)))"
 * 4. 这只是一个单链表的节点，真正的链表KLinkedList还至少应有head, tail和size三个属性及一些读写方法
 * 5. 链表的内存不是一次性分配的，因而其节点在内存中的存储不是连续的，即不支持随机访问
 */
data class ListNode(var `val`: Int, @JvmField var next: ListNode? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$`val`->${next.toString()}"
        } else {
            "$`val`->∅"
        }
    }
}

fun ListNode?.printPretty() = println(this?.toString() ?: "null")

/**
 * IntRange是一个连续的，步长为1的，左闭右闭的区间
 */
fun buildLinkedList(range: IntRange, size: Int = range.count(), rand: Boolean = true): ListNode? {
    val dummy = ListNode(-1)
    var temp = dummy

    for (i in 0 until size) {
        temp.next = if (rand) ListNode(range.random()) else ListNode(range.elementAt(i))
        temp = temp.next!!
    }
    return dummy.next
}

fun buildLinkedList(vararg values: Int): ListNode {
    val head = ListNode(values[0])
    var temp = head

    for (i in 1 until values.size) {
        temp.next = ListNode(values[i])
        temp = temp.next!!
    }
    return head
}

fun buildLinkedList(size: Int, bound: Int = size, rand: Boolean = true): ListNode? {
    val dummy = ListNode(-1)
    var temp = dummy

    for (i in 0 until size) {
        temp.next = if (rand) ListNode((0..bound).random()) else ListNode(i)
        temp = temp.next!!
    }
    return dummy.next
}

@JvmField
val NULL_LIST: ListNode? = null

fun main() {
    NULL_LIST.printPretty()
    val arr = intArrayOf(4, 8, 6, 3)
    buildLinkedList(-1, *arr).printPretty()
    buildLinkedList(5..10, 5).printPretty()
}