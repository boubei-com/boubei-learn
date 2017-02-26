package com.boubei.learn.jk.designpattern.proxy;

import junit.framework.TestCase;

/**
 * @author  Jon.King  2005-11-26
 * 
 * 代理：替另外一个对象打点一切
 *
 * 代理模式（Proxy）和状态模式（State）分别提供了供你使用的代理类(surrogate class)；正真干活的那个类被代理类隐藏了。
 * 当你调用代理类的一个方法的时候，代理类只是简单的调用实现类（implementing class）所对应的方法。这两种模式非常相似，
 * 实际上，代理模式只是状态模式的一个特例。
 *    从结构上看，代理模式和状态模式之间的差别非常简单：一个代理（Proxy）只对应一个实现（implementation），
 * 而一个状态（State）却可以对应多个实现。《设计模式》一书认为，这两种两种模式的应用场合是截然不同的：
 * 代理模式用于控制对实现（类）的访问，而状态模式可以动态地改变实现（类）。
 * 但是，如果把“控制对实现类的访问”这个概念扩展开来的话，这两种模式就可以优雅的结合在一起了。
 */
interface ProxyBase {

	void f();

	void g();

	void h();
}

class Proxy implements ProxyBase {
	private ProxyBase implementation;

	public Proxy() {
		implementation = new Implementation();
	}

	// Pass method calls to the implementation:
	public void f() {
		implementation.f();
	}

	public void g() {
		implementation.g();
	}

	public void h() {
		implementation.h();
	}
}

class Implementation implements ProxyBase {
	public void f() {
		System.out.println("Implementation.f()");
	}

	public void g() {
		System.out.println("Implementation.g()");
	}

	public void h() {
		System.out.println("Implementation.h()");
	}
}

public class ProxyDemo extends TestCase {
	Proxy p = new Proxy();

	public void test() {
		p.f();
		p.g();
		p.h();
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(ProxyDemo.class);
	}
}
