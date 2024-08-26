package jtest;

/**
 * 类加载和对象初始化
 */
public class Test38 {
    public static void main(String[] args) {
    }
}

class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
