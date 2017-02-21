package jinhe.lt.designpattern.visitor;

import java.util.Collection;

import jinhe.lt.reflect.User;

/**
 * <p>
 * Visitor.java
 * </p>
 * 
 * @author Jon.King 2006-4-25
 * 
 * 访问者接口
 * 
 */

public interface Visitor // 访问者接口
{
	@SuppressWarnings("unchecked")
	public void visitCollection(Collection collection);

	public void visitString(String string);

	public void visitFloat(Float f);

	public void visitUser(User user);
}
