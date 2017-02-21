package jinhe.lt.thread.portsanner;

/** 
 * <p> ThreadPool.java </p> 
 * 
 * @author 金普俊 2007-1-8
 *
 * 第二种方法是：同样创建一个”池“，但是在”池“中放的不是数据对象，而是线程，
 * 可以把”池“中的一个个线程比喻成一个个”工人“，
 * 当没有任务的时候，”工人“们严阵以待；当给”池“添加一个任务后，”工人“就开始处理并直到处理完成。
 */
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class ThreadPool{
    final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList queue;

    public ThreadPool(int nThreads){
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notifyAll();
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try{
                            queue.wait();
                        }catch (InterruptedException ignored){
                        }
                    }

                    r = (Runnable) queue.removeFirst();
                }

                try {
                    r.run();
                }
                catch (RuntimeException e) {
                }
            }
        }
    }
}


