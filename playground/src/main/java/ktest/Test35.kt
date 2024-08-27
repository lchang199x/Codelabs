package ktest

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

fun main() {
    suspend {
        5 // lambda的返回值类型对应Continuation<Int>中的Int泛型参数
    }.createCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println("result: ${result.getOrNull()}")
        }
    }).resume(Unit)
}