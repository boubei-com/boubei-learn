package jinhe.lt.designpattern.iterator;

import java.util.Iterator;

/**
 * @author jinpj  2005-11-29
 *
 * 只能遍历同一类型的对象
 */
@SuppressWarnings("unchecked")
public class TypedIterator implements Iterator {
	private Iterator imp;

	private Class type;

	public TypedIterator(Iterator it, Class type) {
		imp = it;
		this.type = type;
	}

	public boolean hasNext() {
		return imp.hasNext();
	}

	public void remove() {
		imp.remove();
	}

	public Object next() {
		Object obj = imp.next();
		if (!type.isInstance(obj))
			throw new ClassCastException("TypedIterator for type " + type
					+ " encountered type: " + obj.getClass());
		return obj;
	}
}
