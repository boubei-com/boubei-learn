/* ==================================================================   
 * Created [2006-4-25 23:17:38] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.designpattern.visitor.impl;

import jinhe.lt.designpattern.visitor.Visitable;
import jinhe.lt.designpattern.visitor.Visitor;

/**
 * <p>
 * StringValueVisitable.java
 * </p>
 * 
 * @author Jon.King 2006-4-25
 * 
 */
public class VisitableString implements Visitable {
    private String value;

    public VisitableString(String string) {
        value = string;
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
        visitor.visitString(this.value);
    }
}
