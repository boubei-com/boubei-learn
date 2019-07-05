/* ==================================================================   
 * Created [2008-8-3 下午03:31:04] by Jon.King 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) Jon.King, 2006-2007 
 * ================================================================== 
 */

package com.boubei.learn.jk.jvm;

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
 * java1.2版本做法
 * 
 * @author Jon.King 2008-8-3
 * 
 */
public class GreeterClassLoader2 extends ClassLoader {

    private String basePath;

    public GreeterClassLoader2(String basePath) {
        this.basePath = basePath;
    }
    
    public GreeterClassLoader2(ClassLoader parent, String basePath) {
        super(parent);
        this.basePath = basePath;
    }

    public synchronized Class<?> findClass(String className)
            throws ClassNotFoundException {

        byte[] classData;

        // 从basePath下装载
        classData = getTypeFromBasePath(className);
        if (classData == null) {
            System.out.println("GCL - Can't load class:" + className);
            throw new ClassNotFoundException();
        }

        return defineClass(className, classData, 0, classData.length);
    }

    private byte[] getTypeFromBasePath(String className) {
        String fileName = basePath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        
        FileInputStream fis;
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
        }try {
			bis.close();
		} catch (IOException e) {
		}
        
        return baos.toByteArray();
    }
}
