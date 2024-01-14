package test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类加载和对象初始化
 */
public class Test27 {
    public static void main(String[] args) {
        int[] arr = new int[5];
        for (int i : arr) {
            // 输出0
            // 注意kotlin中泛型数组Array(5) { null } 需要显式初始化
            // 基本类型数组IntArray(5) { null } 的显式初始化可选
            System.out.println(i);
        }
        // sum count max这种特定的聚合操作都可以通过reduce和collect这俩通用的聚合操作来实现
        // reduce聚合操作从stream中得到一个值，reduce和kotlin中的fold是一个意思
        Arrays.asList(1, 2, 3).stream().reduce(0, Integer::sum);
        List.of(1, 2, 3); // JDK 9之后才加入的静态工厂方法

        // collect聚合操作从stream中收集数据组成一个新集合
        Stream.of(1, 2, 3).filter(p -> p > 2).collect(Collectors.toList());
    }
}
