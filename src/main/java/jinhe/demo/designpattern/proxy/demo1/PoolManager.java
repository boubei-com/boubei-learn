package jinhe.lt.designpattern.proxy.demo1;

import java.util.ArrayList;

/** 
 * <p> PoolManager.java </p> 
 * 
 * @author  Jon.King 2006-4-19
 *
 */
@SuppressWarnings("unchecked")
class PoolManager {
	/**
	 * 内部静态类，可以在没有enclosing class（外围类）的情况下单独存在，如Map中的Entry
	 * 此处一个PoolItem对象包含一个Connetion连接和其使用状态isUse。
	 */
	private static class PoolItem {
		boolean inUse;
		Object item; //可以是一个Connetion连接

		PoolItem(Object item) {
			this.item = item;
			this.inUse = false;
		}
	}

	public static class EmptyPoolItem {
	}

	/**
	 * 操作上面的PoolItem类
	 */
	public class ReleasableReference {
		private PoolItem reference;
		private boolean released;

		public ReleasableReference(PoolItem reference) {
			this.reference = reference;
			this.released = false;
		}

		public Object getReference() {
			if (released)
				throw new RuntimeException(
						"Tried to use reference after it was released");
			return reference.item;
		}

		public void release() {
			released = true;
			reference.inUse = false;
			reference.item = null;
		}
	}

	private ArrayList poolItems = new ArrayList();

	public void add(Object item) {
		poolItems.add(new PoolItem(item));
	}

	public ReleasableReference get() {
		for (int i = 0; i < poolItems.size(); i++) {
			PoolItem pitem = (PoolItem) poolItems.get(i);
			if (pitem.inUse == false) {
				pitem.inUse = true;
				return new ReleasableReference(pitem);
			}
		}
		return null;
	}
}
