package com.boubei.learn.jk.designpattern.composite.demo2;

import java.util.Iterator;

/** 
 * <p> Brand.java </p> 
 * 
 * @author Jon.King 2006-5-6
 * 
 * Brand类继承于SoftwareComposite类，对应于品牌，
 * 包含了品牌名属性，并且用来存储Product类的实例
 */

public class Brand extends SoftwareComposite {

    public SoftwareComponent findSoftwareComponentById(Long id) {
        return null;
    }

    @SuppressWarnings("unchecked")
	public void accept(Visitor visitor) { 
        visitor.visitBrand(this); 
         
        for (Iterator it = children.iterator(); it.hasNext();) { 
            SoftwareComponent component = (SoftwareComponent)it.next(); 
            component.accept(visitor); 
        } 
    } 
}

