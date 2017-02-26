/* ==================================================================   
 * Created [2008-8-6 下午10:43:35] by Jon.King 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) Jon.King, 2006-2007 
 * ================================================================== 
 */

package com.boubei.learn.jk.jvm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * <p>
 * EncodedClassLoader.java
 * </p>
 * 
 * 加密ClassLoader，防止被反编译
 * 
 * @author Jon.King 2008-8-6
 * 
 */
public class EncodedClassLoader extends ClassLoader{

    // 这些对象在构造函数中设置，以后loadClass()方法将利用它们解密类

    private Cipher cipher;

    // 构造函数：设置解密所需要的对象
    public EncodedClassLoader(SecretKey key) throws GeneralSecurityException, IOException {
        System.out.println("[DecryptStart: creating cipher]");

        String algorithm = "DES";
        cipher = Cipher.getInstance(algorithm);
        
        SecureRandom sr = new SecureRandom();
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
    }

    // main过程：我们要在这里读入密匙，创建DecryptStart的实例，它就是我们的定制ClassLoader。
    // 设置好ClassLoader以后，用它装入应用实例，最后，通过Java Reflection API调用应用实例的main方法
    public static void main(String args[]) throws Exception {
        String keyFilename = args[0];
        String appName = args[1];

        // 这些是传递给应用本身的参数
        String realArgs[] = new String[args.length - 2];
        System.arraycopy(args, 2, realArgs, 0, args.length - 2);

        // 读取密匙
        System.err.println("[DecryptStart: reading key]");
        byte rawKey[] = readFile(keyFilename);
        DESKeySpec dks = new DESKeySpec(rawKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        // 创建解密的ClassLoader
        EncodedClassLoader dr = new EncodedClassLoader(key);

        // 创建应用主类的一个实例，通过ClassLoader装入它
        System.err.println("[DecryptStart: loading " + appName + "]");
        Class<?> clasz = dr.loadClass(appName);

        // 最后，通过Reflection API调用应用实例的main()方法获取一个对main()的引用
        Class<?> mainArgs[] = { (new String[1]).getClass() };
        Method main = clasz.getMethod("main", mainArgs);

        // 创建一个包含main()方法参数的数组
        Object argsArray[] = { realArgs };
        System.err.println("[DecryptStart: running " + appName + ".main()]");

        // 调用main()
        main.invoke(null, argsArray);
    }

    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            // 我们要创建的Class对象
        	Class<?> clasz = null;

            // 必需的步骤1：如果类已经在系统缓冲之中，我们不必再次装入它
            clasz = findLoadedClass(name);
            if (clasz != null)
                return clasz;

            // 下面是定制部分
            try {
                // 读取经过加密的类文件
                byte classData[] = readFile(name + ".class");
                if (classData != null) {
                    // 解密...
                    byte decryptedClassData[] = cipher.doFinal(classData);
                    // ... 再把它转换成一个类
                    clasz = defineClass(name, decryptedClassData, 0, decryptedClassData.length);
                    System.out.println("[DecryptStart: decrypting class " + name + "]");
                }
            } catch (FileNotFoundException fnfe) {

            }

            // 必需的步骤2：如果上面没有成功，/ 我们尝试用默认的ClassLoader装入它
            if (clasz == null)
                clasz = findSystemClass(name);

            // 必需的步骤3：如有必要，则装入相关的类
            if (resolve && clasz != null){
                resolveClass(clasz);
            }
            // 把类返回给调用者
            return clasz;
        } catch (IOException ie) {
            throw new ClassNotFoundException(ie.toString());
        } catch (GeneralSecurityException gse) {
            throw new ClassNotFoundException(gse.toString());
        }
    }

    private static byte[] readFile(String string) throws FileNotFoundException, IOException{
        //TODO 
        return null;
    }
}
