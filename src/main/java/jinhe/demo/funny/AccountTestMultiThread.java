package jinhe.lt.funny;

/**
 * 
 * 对于public synchronized void add(int num)这种情况，意味着什么呢？
 * 其实这种情况，锁就是这个方法所在的对象(即下面例子里 new 出来的 AccountTestMultiThread 对象)。
 * 同理，如果方法是public  static synchronized void add(int num)，那么锁就是这个方法所在的class。

 * @author bl00618
 */
public class AccountTestMultiThread {
    
    private int balance;   
  
    public AccountTestMultiThread(int balance) {   
        this.balance = balance;   
    }   
  
    public int getBalance() {   
        return balance;   
    }   
  
    public synchronized  void add(int num) {   
        balance = balance + num;   
    }   
  
    public synchronized  void withdraw(int num) {   
        balance = balance - num;   
    }   
  
    public static void main(String[] args) throws InterruptedException {   
        AccountTestMultiThread AccountTestMultiThread = new AccountTestMultiThread(1000);   
        Thread a = new Thread(new AddThread(AccountTestMultiThread, 20), "add");   
        Thread b = new Thread(new WithdrawThread(AccountTestMultiThread, 20), "withdraw");   
        a.start();   
        b.start();   
        a.join();   
        b.join();   
        System.out.println(AccountTestMultiThread.getBalance());   
    }   
  
    static class AddThread implements Runnable {   
        AccountTestMultiThread AccountTestMultiThread;   
        int     amount;   
  
        public AddThread(AccountTestMultiThread AccountTestMultiThread, int amount) {   
            this.AccountTestMultiThread = AccountTestMultiThread;   
            this.amount = amount;   
        }   
  
        public void run() {   
            for (int i = 0; i < 200000; i++) {   
                AccountTestMultiThread.add(amount);   
            }   
        }   
    }   
  
    static class WithdrawThread implements Runnable {   
        AccountTestMultiThread AccountTestMultiThread;   
        int     amount;   
  
        public WithdrawThread(AccountTestMultiThread AccountTestMultiThread, int amount) {   
            this.AccountTestMultiThread = AccountTestMultiThread;   
            this.amount = amount;   
        }   
  
        public void run() {   
            for (int i = 0; i < 100000; i++) {   
                AccountTestMultiThread.withdraw(amount);   
            }   
        }   
    }   
}  
