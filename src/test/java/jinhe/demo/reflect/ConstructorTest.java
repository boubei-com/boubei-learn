package jinhe.lt.reflect;

import java.lang.reflect.Constructor;

/**
 * <p>
 * ConstructorTest.java
 * </p>
 * 
 * @author Jon.King 2006-4-19
 * 
 */
public class ConstructorTest {
    public ConstructorTest() {
    }

    public ConstructorTest(int a, int b) {
        System.out.println("a = " + a + " b = " + b);
    }
    
    /**
     * 此方法只能调用public的构造函数，protect和private的不行
     */
    public static Object createObject(){
        try {
            Class<?> clazz = Class.forName("jinhe.lt.reflect.ConstructorTest");
           
            Class<?> partypes[] = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = Integer.TYPE;
            
            Constructor<?> ct = clazz.getConstructor(partypes);
            
            Object arglist[] = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = new Integer(47);
            
            Object retobj = ct.newInstance(arglist);
            return retobj;
        } catch (Throwable e) {
            System.err.println(e);
        }
        return null;
    }
    
    public static void getConstructors(){
        try {
            Class<?> clazz = Class.forName("jinhe.lt.reflect.ConstructorTest");
            Constructor<?> ctorlist[] = clazz.getDeclaredConstructors();
            for (int i = 0; i < ctorlist.length; i++) {
                Constructor<?> ct = ctorlist[i];
                System.out.println("name = " + ct.getName());
                System.out.println("decl class = " + ct.getDeclaringClass());
                
                Class<?> pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                
                Class<?> evec[] = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    public static void main(String args[]) {
        ConstructorTest.getConstructors();
        ConstructorTest.createObject();
    }

}

