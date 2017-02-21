package jinhe.lt.reflect.memoization.mixin;

/** 
 * <p> Lockable.java </p> 
 * 
 * @author Jon.King 2006-10-23
 *
 * 这个例子演示了一个mixin。我们想要能够 将被通知对象类型转换为Lockable，
 * 不管它们的类型，并且调用lock和unlock方法。如果我们调用 lock()方法，
 * 我们希望所有setter方法抛出LockedException异常。 
 * 这样我们能添加一个方面使的对象不可变，而它们不需要知道这一点：这是一个很好的AOP例 子。
 */
public interface Lockable {
    void lock();
    void unlock();
    boolean locked();
}
