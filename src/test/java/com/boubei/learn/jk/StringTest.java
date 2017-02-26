/* ==================================================================   
 * Created [2009-4-27 下午11:32:55] by Jon.King 
 * ==================================================================  
 * TSS 
 * ================================================================== 
 * mailTo:jinpujun@hotmail.com
 * Copyright (c) Jon.King, 2009-2012 
 * ================================================================== 
 */

package com.boubei.learn.jk;
 
public class StringTest {

    public static void main(String[] agrs) throws Exception {
        String a = "abcdef";
        String b = "abcdef";
        String c = new String("abcdef");
        String d = new String("abcdef");
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c == d);
        
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
       
        System.out.println(a.intern());
        
        String   str1   =   new   String( "abc ");   //jvm   在堆heap上创建一个String对象      
        
        //jvm   在strings   pool中找不到值为“abc”的字符串，因此      
        //在堆上创建一个String对象，并将该对象的引用加入至strings   pool中      
        //此时堆上有两个String对象      
        String  str2   =   "abc ";      
         
        if (str1 == str2) {
            System.out.println("str1   ==   str2 ");
        } else {
            System.out.println("str1   !=   str2 ");
        }      
        // 打印结果是 str1 != str2,因为它们是堆上两个不同的对象
         
        String   str3   =   "abc ";      
        //此时，jvm发现strings   pool中已有“abc”对象了，因为“abc”equels   “abc”      
        //因此直接返回str2指向的对象给str3，也就是说str2和str3是指向同一个对象的引用      
        if (str2 == str3) {
            System.out.println("str2   ==   str3 ");
        } else {
            System.out.println("str2   !=   str3 ");
        }      
        // 打印结果为 str2 == str3
        
        
        String str11 = new String("abc "); //jvm   在堆上创建一个String对象      

        str11 = str11.intern();
//        程序显式将str1放到strings   pool中，intern运行过程是这样的：首先查看strings   pool      
//        有没“abc”对象的引用，没有，则在堆中新建一个对象，然后将新对象的引用加入至      
//        strings   pool中。执行完该语句后，str11原来指向的String对象已经成为垃圾对象了，随时会被GC收集。      
//        此时，jvm发现strings   pool中已有“abc”对象了，因为“abc”equels   “abc”      
//        因此直接返回str1指向的对象给str2，也就是说str2和str1引用着同一个对象，此时，堆上的有效对象只有一个。      
        String str22 = "abc ";

        if (str11 == str22) {
            System.out.println("str11   ==   str22 ");
        } else {
            System.out.println("str11   !=   str22 ");
        }
        //打印结果是   str11   ==   str22       
    }
}


