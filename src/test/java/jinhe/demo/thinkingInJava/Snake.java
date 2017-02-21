package jinhe.lt.thinkingInJava;

public class Snake implements Cloneable {

    private Snake next;
    
    private char c;
    
    Snake(int i , char x) {
        c = x;
        if(--i > 0) {
            next = new Snake(i , (char)(x+1));
        }
    }
    
    void increment() {
        c++;
        if(next != null)
            next.increment();
    }
    
    public String toString() {
        String s = ":" + c;
        if(next != null)
            s += next.toString();
        return s;
    }
    
    public Object colne() {
        Object o = null;
        try {
            o = super.clone();
        } catch(CloneNotSupportedException e) {
            System.err.println("Snake can't clone");
        }
        return o;
    }
    
    public static void main(String[] args) throws CloneNotSupportedException {
        Snake s = new Snake(5 , 'a');
        System.out.println("s = " + s);
        Snake s2 = (Snake)s.clone();
        System.out.println("s2 = " + s2);
        s.increment();
        System.out.println("after s.increment,s2 = " + s2);
    }

}

