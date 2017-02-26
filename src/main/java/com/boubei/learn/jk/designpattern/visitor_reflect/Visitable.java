package com.boubei.learn.jk.designpattern.visitor_reflect;

/** 
 * <p> Visitable.java </p> 
 * 
 * @author Jon.King 2006-4-25
 *
 * 被访问者接口
 * 
 */
public interface Visitable {
    
    /**
     * 接收访问者visitor的访问
     * @param visitor
     */
    public void accept(ReflectiveVisitor visitor);

}

