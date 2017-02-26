package com.boubei.learn.jk.designpattern.composite.demo2;

import java.util.Iterator;

/** 
 * <p> SoftwareSet.java </p> 
 * 
 * @author Jon.King 2006-5-6
 *
 * SoftwareSet类继承于SoftwareComposite类，对应于软件集，
 * 软件集下直接可以包含品牌（Brand）,也可以直接包含不属于任何品牌的产品（Product）
 */

public class SoftwareSet extends SoftwareComposite {

    public SoftwareComponent findSoftwareComponentById(Long id) {
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public void accept(Visitor visitor) { 
        visitor.visitSoftwareSet(this); 
        
        for (Iterator it = children.iterator(); it.hasNext(); ) { 
            SoftwareComponent component = (SoftwareComponent)it.next(); 
            component.accept(visitor); 
        } 
    } 
}

