package jinhe.lt.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
 
public class FieldTest {
	
    //private double d;
    public  double d2;

    public static final int i = 37;

    String s = "testing";
    
    public static void getFields(){
        try {
            Class<?> clazz = Class.forName("jinhe.lt.reflect.FieldTest");
            Field fieldlist[] = clazz.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                Field field = fieldlist[i];
                System.out.println("name = " + field.getName());
                System.out.println("declaring class = " + field.getDeclaringClass());
                System.out.println("type = " + field.getType());
                
                int mod = field.getModifiers();
                System.out.println("modifiers = " + Modifier.toString(mod));
                
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
 
    public static void modifyField(){
        try {
            Class<?> clazz = Class.forName("jinhe.lt.reflect.FieldTest");
            Field field = clazz.getField("d2");  
            FieldTest obj = (FieldTest) clazz.newInstance();
            System.out.println("d2 = " + obj.d2);
            
            field.setDouble(obj, 12.34);
            
            System.out.println("d2 = " + obj.d2);
        } catch (Throwable e) {
            System.err.println(e);
        }

    }

    public static void main(String args[]) {
        FieldTest.getFields();
        FieldTest.modifyField();
    }

}
