package com.boubei.learn.jk.designpattern.visitor;


/**
 * <p>
 * StringValueVisitable.java
 * </p>
 * 
 * @author Jon.King 2006-4-25
 * 
 */
public class VisitableFloat implements Visitable {
    private Float value;

    
    public VisitableFloat(Float f) {
        value = f;
    }

    /*
     * 
     * 接收访问者visitor的访问
     * 
     * 双分派
     * 
     * Visitor先调用了Visitable类中的方法accept(Visitor visitor)，
     * 这个方法又回调到Visitor类中visitUser(...);
     */
    public void accept(Visitor visitor) {
        visitor.visitFloat(this.value);
    }
}
