package threads

import kotlin.concurrent.thread

private const val BOUND = 20
private val lock = Object()
private var count = 1

fun main() {
    thread(start = true, name = "R1") {
        printAlternately()
    }
    thread(start = true, name = "R2") {
        printAlternately()
    }
}

fun printAlternately() {
    synchronized(lock) {
        while (count <= BOUND) {
            lock.notify()
            println("${Thread.currentThread().name} -- ${count++}")
            lock.wait()
        }
        lock.notify()
    }
    println("game over -- ${Thread.currentThread().name}")
}