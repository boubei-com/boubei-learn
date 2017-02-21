package jinhe.lt.java5.annotation.demo2;

import java.lang.reflect.Field;

public class Test {
    @Autowired
    private String a;

    public String getA() {
        return a;
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {   
        Test test = new Test();   
        Field[] fields = Test.class.getDeclaredFields();   
        for(Field field : fields){   
            if(field.isAnnotationPresent(Autowired.class) && field.getName().equals("a")){   
//                field.setAccessible(true);   
                field.set(test, "你好");   
            }   
        }   
        System.out.println(test.getA());   
    }
}
