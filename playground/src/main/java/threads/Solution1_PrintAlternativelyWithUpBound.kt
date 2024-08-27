package threads

class Solution1_PrintAlternativelyWithUpBound(var count: Int, val bound: Int) {
    private val lock = Object()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val outer = Solution1_PrintAlternativelyWithUpBound(0, 20)
            Thread(outer.R1()).start()
            Thread(outer.R2()).start()
        }
    }

    inner class R1 : Runnable {
        override fun run() {
            while (count < bound)
                synchronized(lock) {
                    lock.notify()
                    println("R1--- ${count++}")
                    if (count != bound) lock.wait()
                }
        }
    }

    inner class R2 : Runnable {
        override fun run() {
            while (count < bound)
                synchronized(lock) {
                    lock.notify()
                    println("R2--- ${count++}")
                    if (count != bound) lock.wait()
                }
        }
    }
}