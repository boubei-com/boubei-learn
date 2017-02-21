package jinhe.lt.reflect.memoization.mixin;

/**
 * <p>
 * LockMixin.java
 * </p>
 * 
 * @author Jon.King 2006-10-23
 */
public class LockMixin implements Lockable {

    private static final long serialVersionUID = 7878092720406585450L;
    
    private boolean locked;

    public void lock() {
        this.locked = true;
        System.out.println("lock");
    }

    public void unlock() {
        this.locked = false;
        System.out.println("unlock");
    }

    public boolean locked() {
        return this.locked;
    }
}
