package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Language Specification：
 * Ideally, boxing a primitive value would always yield an identical reference.
 * In practice, this may not be feasible. Cache just -128~127 is pragmatic compromise.
 *
 * public static Integer valueOf(int i) {
 *         if (i >= IntegerCache.low && i <= IntegerCache.high)
 *             return IntegerCache.cache[i + (-IntegerCache.low)];
 *         return new Integer(i);
 *     }
 */
public class Test6 extends Test2 {

    public static void main(String[] args) {
        System.out.println(new Integer(100) == 100); // true
        System.out.println(new Integer(1000) == 1000); // true

        Integer a1 = 100; // JVM中优化成 Integer.valueOf(100)
        Integer a2 = 100;
        int a3 = 100;

        Integer b1 = 1000;
        Integer b2 = 1000;
        int b3 = 1000;
        System.out.println(a1 == a2); // true
        System.out.println(a1 == a3); // true

        // b1 == b2 要求这俩对象指向同一地址，不在IntegerCache中的话就不是
        System.out.println(b1 == b2); // false
        System.out.println(b1 == b3); // false

        System.out.println(a1.equals(a2)); // true
        System.out.println(a1.equals(a3)); // true

        // b1 equals b2因为他们的inValue相同，天经地义
        System.out.println(b1.equals(b2)); // true
        System.out.println(b1.equals(b3)); // true

        System.out.println("===================");

        // 8中基本类型和 void 的 Class<T>.isPrimitive()会返回true
        System.out.println(Boolean.TYPE.isPrimitive());
        System.out.println(Byte.TYPE.isPrimitive());
        System.out.println(Character.TYPE.isPrimitive());
        System.out.println(Short.TYPE.isPrimitive());
        System.out.println(Integer.TYPE.isPrimitive());
        System.out.println(Long.TYPE.isPrimitive());
        System.out.println(Float.TYPE.isPrimitive());
        System.out.println(Double.TYPE.isPrimitive());
        System.out.println(Void.TYPE.isPrimitive());

        List<Test2> iList = new ArrayList<>();
        List<Test6> nList = new ArrayList<>();
        iList.add(new Test6()); // 这不叫协变
//        List<Test2> i2List = new ArrayList<Test6>(); // error 这种要是成立了才叫协变
//        List<Test6> n2List = new ArrayList<Test2>(); // error
        System.out.println(iList.getClass() == nList.getClass());
//        System.out.println(iList instanceof ArrayList<Test2>); // error
//        System.out.println(iList instanceof List<Test2>); // error
        System.out.println(iList instanceof List);
        Object[] array = new Integer[10]; // 数组协变
        array[0] = "s String"; // 这句编译正常，运行时抛异常
        array[1] = 1;
    }
}
