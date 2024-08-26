@file:JvmName("Lists")

package common

class ListNode(val data: Int, var next: ListNode? = null)

fun ListNode?.printPretty() {
    if (this == null) return println("NULL_LIST!")

    var temp: ListNode? = this
    while (temp != null) {
        print("${temp.data}->")
        temp = temp.next
    }
    println("∅")
}

fun buildLinkedList(range: IntRange, rand: Boolean = false): ListNode {
    val head = ListNode(range.first)
    var temp: ListNode? = head

    for (i in 1 until range.last) {
        temp!!.next = if (rand) ListNode(range.random()) else ListNode(range.elementAt(i))
        temp = temp.next
    }
    return head
}

fun buildLinkedList(arr: IntArray): ListNode {
    val head = ListNode(arr[0])
    var temp: ListNode? = head

    for (i in 1 until arr.size) {
        temp!!.next = ListNode(arr[i])
        temp = temp.next
    }
    return head
}

fun buildLinkedList(length: Int, bound: Int = length, rand: Boolean = true): ListNode {
    val head = ListNode((0..bound).random())
    var temp: ListNode? = head

    for (i in 1 until length) {
        temp!!.next = if (rand) ListNode((0..bound).random()) else ListNode(i)
        temp = temp.next
    }
    return head
}

@JvmField
val NULL_LIST: ListNode? = null

fun main() {
    NULL_LIST.printPretty()
    buildLinkedList(intArrayOf(4, 8, 6, 3)).printPretty()
    buildLinkedList(5, 10, true).printPretty()
}