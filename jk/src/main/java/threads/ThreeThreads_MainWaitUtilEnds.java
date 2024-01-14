package threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用join和latch它不香吗
 */
public class ThreeThreads_MainWaitUtilEnds {
    private static final Object lock = new Object();
    private static AtomicInteger count = new AtomicInteger(3);

    public static void main(String[] args) {
        new Thread(new MainRunnable()).start();
    }

    static class MainRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                new Thread(new WorkerRunnable("threadA")).start();
                new Thread(new WorkerRunnable("threadB")).start();
                new Thread(new WorkerRunnable("threadC")).start();

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread test.main is running ----");
            }
        }
    }

    static class WorkerRunnable implements Runnable {
        private String threadName;

        public WorkerRunnable(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            doWork();
            synchronized (lock) {
                if (count.decrementAndGet() == 0) {
                    lock.notifyAll();
                }
            }
        }

        private void doWork() {
            System.out.println(threadName + " is running ----");
        }
    }

}
