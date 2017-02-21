/* ==================================================================   
 * Created [2008-8-3 下午03:31:04] by Jon.King 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) Jon.King, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.classloader.demo2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 * GreeterClassLoader.java
 * </p>
 * java1.1版本做法
 * 
 * @author Jon.King 2008-8-3
 * 
 */
public class GreeterClassLoader extends ClassLoader {

    private String basePath;

    public GreeterClassLoader(String basePath) {
        this.basePath = basePath;
    }

    public synchronized Class<?> loadClass(String className, boolean resolveIt)
            throws ClassNotFoundException {

        Class<?> result;
        byte[] classData;

        // 检查该class是否已经被本装载器装载过
        result = findLoadedClass(className);
        if (result != null)
            return result;

        // 委派给双亲装载
        try {
            result = super.findSystemClass(className);
            return result;
        } catch (ClassNotFoundException e) {

        }

        if (className.startsWith("java.")) {
            throw new ClassNotFoundException();
        }

        // 从basePath下装载
        classData = getTypeFromBasePath(className);
        if (classData == null) {
            System.out.println("GCL - Can't load class:" + className);
            throw new ClassNotFoundException();
        }

        // 解析二进制class文件数据
        result = defineClass(className, classData, 0, classData.length);
        if (result == null) {
            System.out.println("GCL - Class format error:" + className);
            throw new ClassFormatError();
        }

        // JVM想要装入的不仅包括指定的类，而且还包括该类引用的所有其他类时
        // 它会把loadClass的resolve参数设置成true
        if (resolveIt) {
            resolveClass(result);
        }

        return result;
    }

    private byte[] getTypeFromBasePath(String className) {
        FileInputStream fis;
        String fileName = basePath + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        
        try{
            fis = new FileInputStream(fileName);
        } catch(FileNotFoundException e){
            return null;
        }
        
        BufferedInputStream bis = new BufferedInputStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try{
            int c = bis.read();
            while(c != -1){
                baos.write(c);
                c = bis.read();
            }
        }catch(IOException e){
            return null;
        }
        
        return baos.toByteArray();
    }
}
