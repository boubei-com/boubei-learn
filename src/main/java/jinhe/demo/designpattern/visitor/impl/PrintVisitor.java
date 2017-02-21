package jinhe.lt.designpattern.visitor.impl;

import java.util.Collection;
import java.util.Iterator;

import jinhe.lt.designpattern.visitor.Visitable;
import jinhe.lt.designpattern.visitor.Visitor;
import jinhe.lt.reflect.User;

/**
 * <p>
 * PrintVisitor.java
 * </p>
 * 
 * @author Jon.King 2006-4-25
 * 
 */
public class PrintVisitor implements Visitor {
    @SuppressWarnings("unchecked")
	public void visitCollection(Collection collection) {
        for (Iterator it = collection.iterator(); it.hasNext();) {
            Object o = it.next();
            if (o instanceof Visitable)
                ((Visitable) o).accept(this);
        }
    }

    public void visitString(String string) {
        System.out.println("'" + string + "'");
    }

    public void visitFloat(Float f) {
        System.out.println(f.toString() + "f");
    }

    public void visitUser(User user) {
        System.out.println(user.toString());

    }
}
