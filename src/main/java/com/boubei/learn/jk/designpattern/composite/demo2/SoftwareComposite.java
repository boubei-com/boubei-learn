package com.boubei.learn.jk.designpattern.composite.demo2;

import java.util.ArrayList;
import java.util.List;


/** 
 * <p> AbsSoftwareComposite.java </p> 
 * 
 * @author Jon.King 2006-5-6
 *
 * SoftwareComposite类对应于Composite类，并且是抽象类，所有可以包含子节点的类都扩展这个类。
 * 这个类的主要功能是用来存储子部件，实现了接口中的方法，部分可以重用的代码写在此类中
 */
@SuppressWarnings("unchecked")
public abstract class SoftwareComposite implements SoftwareComponent {
    
	public List children = new ArrayList();
    
    public void addSoftwareComponent(SoftwareComponent s) {
        children.add(s);
    }

    public void removeSoftwareComponent(SoftwareComponent s) {
        children.remove(s);
    }
    
}

