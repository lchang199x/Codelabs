package threads

import kotlin.concurrent.thread

private const val BOUND = 20
private val lock = Object()
private var count = 1

fun main() {
    thread(start = true, name = "R1") {
        printAlternately8()
    }
    thread(start = true, name = "R2") {
        printAlternately8()
    }
}

fun printAlternately8() {
    synchronized(lock) {
        while (count <= BOUND) {
            lock.notify()
            println("${Thread.currentThread().name} -- ${count++}")
            if (count <= BOUND) lock.wait()
        }
    }
    println("game over -- ${Thread.currentThread().name}")
}