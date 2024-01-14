package test

/**
 * 注意:
 * 0. 标签用于标记一个lambda，作用之一是从lambda返回， 作用之二是用于带标签的this表达式，作用之三是inner class访问外部类引用this@Outer
 * 1. 定义标签时@紧跟在在label后，引用标签时，@紧跟在在label前，不能有空格，且return和@之间也不能有空格
 * 2. label只用于return，无法使用break@label或者continue@label
 */
fun main() {
    run bre@{
        listOf(1, 2, 3, 4, 5).forEach {
            // foreach为内联函数，它带着它的lambda参数一起内联到main方法
            // 默认非局部返回：lambda中的return会直接从main方法返回，故println("main end!!")不会执行
//        if (it == 2) return
            // kotlin以标签语法支持局部返回，此时println("main end!!")会执行
            // 这是continue效果
            if (it == 2) return@forEach
            // 这是break效果
            if (it == 2) return@bre
            println(it)
        }
    }
    println("main end!!")

//    notInline { return } // 报错：不允许在非内联函数的lambda参数中使用return
    inlined { return } // √

//    inlined2 { return } // 报错：禁止crossinline修饰的lambda中使用非局部返回，业务白盒认为限制，防止出错，非kotlin语法限制
}

fun notInline(block: () -> Int) {
    block.invoke()
}

fun notInline2(block: () -> Int) {
    notInline(block) // 非内联lambda参数可以传给非内联函数
}

inline fun inlined(block: () -> Int) {
    block.invoke()
}

inline fun inlined2(crossinline block: () -> Int) {
    block.invoke()
}

// inline方法的lambda表达式默认会被内联
inline fun inlinedUsage(block: () -> Int) {
//    notInline(block) // 报错：禁止内联lambda参数传给非内联函数
    block.invoke() // √ 允许直接调用
    inlined(block) // √ 允许传给另一个内联函数
}

// noinline常用于拥有多个lambda参数的inline函数的其中一写参数
// 所有lambda参数都noinline会警告：inline函数和函数类型一起使用才有性能优势
inline fun noinlineLambda(block1: () -> Int, noinline block2: () -> Int) {
    notInline(block2) // lambda声明成非内联函数就打破了禁止
}