package com.boubei.learn.jk.designpattern.composite.demo1;

/** 
 * <p> SoftwareComponent.java </p> 
 * 
 * @author Jon.King 2006-5-6
 * 
 * 接口SoftwareComponent对应于组合模式中的Component接口，
 * 它定义了所有类共有接口的缺省行为
 *
 */
public interface SoftwareComponent {

    SoftwareComponent findSoftwareComponentById(Long id);
    
    double getTotalPrice();
}

//在外面需要取得某个对象的总价格的时候只需这样写
//SoftwareComponent data = getMockData(); 
//只需直接调用data对象的getTotalPrice 方法就可以返回该对象下所有product对象的价格 
//double price = data. getTotalPrice(); 
//找到某个对象后直接调用其getTotalPrice方法也可以返回总价格 
//price = data. findSoftwareComponentByID("id").getTotalPrice(); 
