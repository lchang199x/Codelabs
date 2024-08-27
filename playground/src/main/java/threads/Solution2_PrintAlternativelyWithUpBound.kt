package threads

import kotlin.concurrent.thread

class Solution2_PrintAlternativelyWithUpBound {

    companion object {
        private const val BOUND = 20

        private val lock = Object()

        var count: Int = 0

        @JvmStatic
        fun main(args: Array<String>) {
            thread(start = true, name = "R1") {
                while (count < BOUND)
                    synchronized(lock) {
                        lock.notify()
                        println("${Thread.currentThread().name} - ${count++}")
                        if (count != BOUND) lock.wait()

                    }
            }
            thread(start = true, name = "R2") {
                while (count < BOUND)
                    synchronized(lock) {
                        lock.notify()
                        println("${Thread.currentThread().name} -- ${count++}")
                        if (count != BOUND) lock.wait()
                    }
            }
        }
    }
}