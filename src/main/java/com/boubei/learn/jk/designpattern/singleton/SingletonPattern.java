package com.boubei.learn.jk.designpattern.singleton;

import junit.framework.TestCase;

/**
 * @author Jon.King  2005-11-25
 * 
 * Singleton模式的关键是防止用户以其它任何方式创建对象，而只能用你所提供的方式。
 * 所有的构造函数必须被声明为私有的(private)，而且必须至少声明一个构造函数，
 * 否则编译器就会以package权限帮你创建一个默认的构造函数。
 * 
 * 另外，Java允许使用克隆（cloning）来创建对象。
 * 这个例子里，把这个类声明为final就是为了防止通过克隆方法创建对象。
 * 因为Singleton是直接从Object继承下来的，由于clone( )是受保护的（protected）方法
 * 因而不能用来（复制对象），如果这么做就会导致编译时错误。
 */
final class Singleton implements Cloneable{
	private static Singleton s = new Singleton(47);

	private int i;

	private Singleton(int x) {
		i = x;
	}

	public static Singleton getReference() {
		return s;
	}

	public int getValue() {
		return i;
	}

	public void setValue(int x) {
		i = x;
	}
	
//	public Object clone() {
//		try {
//			return super.clone(); // call protected method
//		} catch (CloneNotSupportedException e) {
//			return null;
//		}
//	}
}

public class SingletonPattern extends TestCase {
	public void test() {
		Singleton s = Singleton.getReference();
		String result = "" + s.getValue();
		System.out.println(result);
		assertEquals(result, "47");
		
		Singleton s2 = Singleton.getReference();
		s2.setValue(9);
		result = "" + s.getValue();
		System.out.println(result);
		assertEquals(result, "9");
		try { 
			// Can't do this: compile-time error. 
//			Singleton s3 = (Singleton)s2.clone();
//            System.out.println(s3);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(SingletonPattern.class);
	}
}
