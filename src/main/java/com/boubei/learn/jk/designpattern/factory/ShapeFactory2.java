package com.boubei.learn.jk.designpattern.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author  Jon.King  2005-12-5
 *
 * 工厂方法模式(又叫多态工厂（Polymorphic factories））
 *
 * 修改了ShapeFactory1.java使得工厂方法成为一个单独的类的虚函数。请注意，特定类型的Shape类是根据需要动态加载的
 * 
 * 现在工厂方法(factory method)出现在它自己的类ShapeFactory里，名字改成了create( )方法。
 * 它是一个受保护的（protected）方法，也就是说它不能被直接调用，但可以被重载。
 * Shape类的子类必须创建与之对应的ShapeFactory的子类，并且通过重载create( )函数来创建它自己的实例。
 * 实际上一系列Shape对象的创建是通过调用ShapeFactory.createShape( ) 来完成的。 
 * CreateShape( )是个静态方法， 它根据传入的标示，通过查找ShapeFactory 的 Map 成员变量找到与之相应的工厂对象 （factory obejct）。 
 * 然后，找到的factory对象即被用来创建shape对象
 * 
 * 注意到，ShapeFactory的初始化必须通过加载Map数据成员才能完成（Map的元素是factory对象），
 * 而这些初始化代码又位于Shape实现类的静态初始化语句里。
 * 这样一来，每加入一个新的类型你就必须得继承原来的类型（指Shape？），创建一个factory，然后添加静态初始化语句用以加载Map对象。
 * 这些额外的复杂性又一次暗示我们：如果不需要创建单独的factory对象，那最好还是使用静态工厂方法。
 */

@SuppressWarnings("unchecked")
abstract class ShapeFactory {
	protected abstract Shape create();

	private static Map factories = new HashMap();

	public static void addFactory(String id, ShapeFactory f) {
		factories.put(id, f);
	}

	public static final Shape createShape(String id) {
		if (!factories.containsKey(id)) {
			try {
				// Load dynamically
				//动态加载时，调用forName()，将会调用shape子类中static方法已经static的程序块，
				Class.forName("com.jinpj.design_pattern.factory.FactoryMethod." + id);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Bad shape creation: " + id);
			}
			//	See if it was put in:
			if (!factories.containsKey(id))
				throw new RuntimeException("Bad shape creation: " + id);
		}
		return ((ShapeFactory) factories.get(id)).create();
	}
}

interface Shape {
    void draw();
    void erase();
}

class Circle implements Shape {
	public void draw() {
		System.out.println("Circle.draw");
	}

	public void erase() {
		System.out.println("Circle.erase");
	}

	private static class Factory extends ShapeFactory {
		protected Shape create() {
			return new Circle();
		}
	}

	static {
		ShapeFactory.addFactory("Circle", new Factory());
	}
}

class Square implements Shape {
	public void draw() {
		System.out.println("Square.draw");
	}

	public void erase() {
		System.out.println("Square.erase");
	}

	private static class Factory extends ShapeFactory {
		protected Shape create() {
			return new Square();
		}
	}

	static {
		ShapeFactory.addFactory("Square", new Factory());
	}
}

public class ShapeFactory2{
    @SuppressWarnings("unchecked")
	public static void main(String args[]) {
        String shlist[] = { "Circle", "Square"};       
        for(Iterator it = Arrays.asList(shlist).iterator(); it.hasNext();){
            Shape s = (Shape)ShapeFactory.createShape((String) it.next());
            s.draw();
            s.erase();
        }
    }
}
