package com.boubei.learn.jk.jvm;
 
public class GreetTest {
    
    public static void main(String[] args){
        String greeters[] = new String[]{"jinhe.lt.classloader.demo2.Hello", 
        		"jinhe.lt.classloader.demo2.Greetings", 
        		"jinhe.lt.classloader.demo2.HowDoYouDo"};
        
        GreeterClassLoader2 cl = new GreeterClassLoader2("D:/workspace/Learn/target/classes");
        
        for(int i = 0; i < greeters.length; i++){
            try{
                Class<?> clazz = cl.loadClass(greeters[i]);
                Object o = clazz.newInstance();
                
                Greeter g = (Greeter) o;
                g.greet();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
}

