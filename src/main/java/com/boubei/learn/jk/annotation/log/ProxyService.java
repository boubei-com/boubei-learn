package com.boubei.learn.jk.annotation.log;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * @author bl00618
 */
public class ProxyService {
 
    public static IDocService wrap(final IDocService service) {
        return (IDocService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                ProxyInvocationHandler.getInterfaces(service.getClass()), new ProxyInvocationHandler(service, null) {
            
            protected Object before(Object target, Method method, Object[] args){
                return null;
            }
 
            protected void after(Object target, Method method, Object[] args, Object beforeReturnVal, Object returnVal){
                LogAnnotation a = method.getAnnotation(LogAnnotation.class); // 取得注释对象
                if(a != null) {
                    int argNum = a.argNum();
//                    String docNoField = a.docNoField();
                    String operateType = a.operateType();
                    
                    if( getArgsNum(args) >= argNum ) {
//                        Object docObject = args[argNum];
//                        String docNo = ((IDoc) docObject).getDocNo();
                        
                        IDoc doc = (IDoc) returnVal;
                        String docNo = doc.getDocNo();
                        
                        System.out.println("新建日志成功，单号：" + docNo + ", 单据类型：" + doc.getDocType() + ", 操作类型：" + operateType);
                    }
                }
            }
        });
    }
    
    public static int getArgsNum(Object[] args){
        return args == null ? -1 : args.length;
    }
}