package com.boubei.learn.jk.designpattern.iterator;

import java.util.Iterator;

/**
 * @author jinpj  2005-11-29
 *
 * 只能遍历同一类型的对象
 */
public class TypedIterator implements Iterator<Object> {
	private Iterator<Object> iter;

	private Class<Object> type;

	public TypedIterator(Iterator<Object> it, Class<Object> type) {
		iter = it;
		this.type = type;
	}

	public boolean hasNext() {
		return iter.hasNext();
	}

	public void remove() {
		iter.remove();
	}

	public Object next() {
		Object obj = iter.next();
		if (!type.isInstance(obj))
			throw new ClassCastException("TypedIterator for type " + type
					+ " encountered type: " + obj.getClass());
		return obj;
	}
}
