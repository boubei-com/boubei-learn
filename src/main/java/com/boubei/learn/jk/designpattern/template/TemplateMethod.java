package com.boubei.learn.jk.designpattern.template;

import junit.framework.TestCase;

/**
 * @author Jon.King  2005-12-5
 * 
 * 模板方法（Template method）
 * 
 * 应用程序框架使你可以从一个或者一系列类继承下来，进而创建一个新的应用程序，
 * 你可以重用既有类的大多数代码并且按照你自己的需要覆写其中的某些方法，
 * 从而实现应用程序的定制。Template Method是应用程序框架的一个基本概念，
 * 它通常隐藏在（框架）背后，通过调用基类的一组方法（有些方法你可能已经覆写（overridden）过了）来驱动应用程序。
 * 
 * Template Method的一个重要特征是：它是在基类里定义的，而且不能够被（派生类）更改。
 * 有时候它是私有方法（private method），但实际上它经常被声明为final。
 * 它通过调用其它的基类方法（覆写过的）来工作，但它经常是作为初始化过程的一部分被调用的，这样就没必要让客户端程序员能够直接调用它了。
 */
abstract class ApplicationFramework {
	public ApplicationFramework() {
		templateMethod();
	}

	abstract void customize1();

	abstract void customize2();

	final void templateMethod() {
		for (int i = 0; i < 1; i++) {
			customize1();
			customize2();
		}
	}//加final修饰只表示继承时不可更改，子类还是继承该方法
	 //如果再加private修饰则对子类不可见
}

class MyApp extends ApplicationFramework {
	public MyApp(){
		System.out.println("Hello ,jinpujun");
	}
	void customize1() {
		System.out.print("Hello ");
	}

	void customize2() {
		System.out.println("xiaoSha!");
	}
}

public class TemplateMethod extends TestCase {
	//MyApp app1 = new MyApp(); //new 一个子类对象的时，会先调用父类的构造函数
	ApplicationFramework app2 = new MyApp();

	public void test() {
		//app1.templateMethod();
		app2.templateMethod();
		// The MyApp constructor does all the work. 
		// This just makes sure it
		// will complete without throwing an exception.
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TemplateMethod.class);
	}
}
