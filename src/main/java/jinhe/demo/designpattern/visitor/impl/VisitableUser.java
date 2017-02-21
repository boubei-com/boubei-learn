package jinhe.lt.designpattern.visitor.impl;

import jinhe.lt.designpattern.visitor.Visitable;
import jinhe.lt.designpattern.visitor.Visitor;
import jinhe.lt.reflect.User;

/**
 * <p>
 * StringValueVisitable.java
 * </p>
 * 
 * @author 金普俊 2006-4-25
 * 
 */
public class VisitableUser implements Visitable {
    private User value;

    public VisitableUser(User user) {
        value = user;
    }

    /*
     * 接收访问者visitor的访问
     * 
     * 双分派
     * 
     * Visitor先调用了Visitable类中的方法accept(Visitor visitor)，
     * 这个方法又回调到Visitor类中visitUser(...);
     */
    public void accept(Visitor visitor) {
        visitor.visitUser(this.value);
    }
}
