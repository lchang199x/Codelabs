package threads;

/**
 * 代码实现两个线程交替执行，线程一执行3次后，线程二开始运行；线程二执行5次后线程一开始执行
 */
public class TwoThreads_RunMultiTimesAlternately {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Runnable runnable1 = new TestRunnable(3);
        Runnable runnable2 = new TestRunnable(5);
        new Thread(runnable1, "Thread-1").start();
        new Thread(runnable2, "Thread-2").start();
    }

    public static class TestRunnable implements Runnable {
        private int times;

        TestRunnable(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    for (int i = 0; i < times; i++) {
                        System.out.println("Thread " + Thread.currentThread().getName() + " running");
                    }
                    lock.notifyAll(); // 提前结束的一个要负责唤醒还在等的那个
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
