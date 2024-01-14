package test

import threads.Solution1_PrintAlternativelyWithUpBound
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

interface Citizen

fun main() {
    // public enum TimeUnit
    TimeUnit.NANOSECONDS.sleep(1000)

    // 从类获取KClass: Unbound Class Reference，此时KClass不允许为T的子类型
    val kClass1: KClass<Solution1_PrintAlternativelyWithUpBound> = Solution1_PrintAlternativelyWithUpBound::class
    // 从对象获取KClass，kotlin为Class<T>的扩展属性
    val kClass2: KClass<Solution1_PrintAlternativelyWithUpBound> = Solution1_PrintAlternativelyWithUpBound(0, 20).javaClass.kotlin
    // 此时KClass允许为T的子类型：Bound Class Reference
    val kClass3: KClass<out Solution1_PrintAlternativelyWithUpBound> = Solution1_PrintAlternativelyWithUpBound(0, 20)::class
    // 从类获取Class<T>，要间接通过KClass，java为KClass类型的扩展属性
    val kJavaClass1: Class<Solution1_PrintAlternativelyWithUpBound> = Solution1_PrintAlternativelyWithUpBound::class.java
    // 从对象获取Class<T>，javaClass为非空类型对象的扩展属性（<T> 等价于 <T : Any?>，<T : Any>可将泛型参数限定为非空类型）
    val kJavaClass2: Class<Solution1_PrintAlternativelyWithUpBound> = Solution1_PrintAlternativelyWithUpBound(0, 20).javaClass

    println(getJavaClass<Solution1_PrintAlternativelyWithUpBound>())
    println(getJavaClass<Citizen>())
}

inline fun <reified T> getJavaClass(): Class<T> {
    return T::class.java
}