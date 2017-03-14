package com.boubei.learn.jk.designpattern.visitor;

import java.util.Collection;

import com.boubei.learn.jk.reflect.User;

/**
 * <p>
 * Visitor.java
 * </p>
 * 
 * @author Jon.King 2006-4-25
 * 
 *         访问者接口
 * 
 */

public interface Visitor {
	
	public void visitCollection(Collection<Object> collection);

	public void visitString(String string);

	public void visitFloat(Float f);

	public void visitUser(User user);
}
