package jinhe.lt.funny;

import java.util.ArrayList;
import java.util.List;

/**
 * 声明一个Plate对象为plate，被线程A和线程B共享，A专门放鸡蛋，B专门拿鸡蛋。假设
 * 1、 开始，A调用plate.putEgg方法，此时eggs.size()为0，因此顺利将鸡蛋放到盘子，还执行了notify()方法，唤醒锁的阻塞队列的线程，此时阻塞队列还没有线程。
 * 2、 又有一个A线程对象调用plate.putEgg方法，此时eggs.size()不为0，调用wait()方法，自己进入了锁对象的阻塞队列。
 * 3、 此时，来了一个B线程对象，调用plate.getEgg方法，eggs.size()不为0，顺利的拿到了一个鸡蛋，还执行了notify()方法，唤醒锁的阻塞队列的线程，此时阻塞队列有一个A线程对象，唤醒后，它进入到就绪队列，就绪队列也就它一个，因此马上得到锁，开始往盘子里放鸡蛋，此时盘子是空的，因此放鸡蛋成功。
 * 4、 假设接着来了线程A，就重复2；假设来料线程B，就重复3。 
 * 整个过程都保证了放鸡蛋，拿鸡蛋，放鸡蛋，拿鸡蛋。 
 *
 */
public class Plate {

    List<Object> eggs = new ArrayList<Object>();

    public synchronized Object getEgg() {
        while (eggs.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        Object egg = eggs.get(0);
        eggs.clear(); // 清空盘子
        notify();     // 唤醒阻塞队列的某线程到就绪队列
        System.out.println("拿到鸡蛋------------");
        return egg;
    }

    public synchronized void putEgg(Object egg) {
        while (eggs.size() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        eggs.add(egg); // 往盘子里放鸡蛋
        notify();      // 唤醒阻塞队列的某线程到就绪队列
        System.out.println("放入鸡蛋~~~~~~~~~~~~~");
    }

    static class AddThread extends Thread {
        private Plate plate;

        private Object egg = new Object();

        public AddThread(Plate plate) {
            this.plate = plate;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                plate.putEgg(egg);
            }
        }
    }

    static class GetThread extends Thread {
        private Plate plate;

        public GetThread(Plate plate) {
            this.plate = plate;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                plate.getEgg();
            }
        }
    }

    public static void main(String args[]) {
        try {
            Plate plate = new Plate();
            Thread add = new AddThread(plate);
            Thread get = new GetThread(plate);
            add.start();
            get.start();
            add.join();
            get.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结束");
    }
}
