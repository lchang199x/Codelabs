package threads;

/**
 * 代码实现两个线程交替执行，线程一执行3次后，线程二开始运行；线程二执行5次后线程一开始执行
 */
public class TwoThreads_RunMultiTimesAlternately {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Runnable runnable1 = new TestRunnable("Thread-1", 3);
        Runnable runnable2 = new TestRunnable("Thread-2", 5);
        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }

    public static class TestRunnable implements Runnable {
        private String name;
        private int times;

        TestRunnable(String name, int times) {
            this.name = name;
            this.times = times;
        }

        @Override
        public void run() {
            synchronized (lock) {
//                while (true) {
                lock.notifyAll();
                for (int i = 0; i < times; i++) {
                    System.out.println("Thread " + name + " running");
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                }
                lock.notifyAll(); // 提前结束的一个要负责唤醒还在等的那个
            }
        }
    }
}
