package test

/**
 * 协变的含义, kotlin和java的泛型都只在编译期存在，kotlin有 reified 这一泛型具体化技术
 */
fun main() {
    val iList = listOf<Test2>()
    val lList: List<Test2> = listOf<Test6>() // 协变是指编译期 List<Test6> 和 List<Test2>仍有父子关系
    val l2List = listOf<Test6>()
    println(lList is List<Test2>)
    println(lList is List<Test2>)
//    println(lList is List<Test6>) // error
    println(l2List is List<Test6>)
}