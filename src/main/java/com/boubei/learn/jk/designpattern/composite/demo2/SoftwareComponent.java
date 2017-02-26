package com.boubei.learn.jk.designpattern.composite.demo2;

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
    
    public void accept(Visitor visitor); 

}
