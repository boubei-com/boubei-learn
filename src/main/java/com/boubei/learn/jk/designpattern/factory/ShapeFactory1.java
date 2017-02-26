package com.boubei.learn.jk.designpattern.factory;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author  Jon.King  2005-12-5
 *
 * 简单工厂模式（Simple Factory Pattern）（又叫静态工厂）
 * 
 *（实现factory模式）常用的方法是把factory声明为基类的静态方法（static method）
 * 
 * 静态的factory( )方法使得所有创建对象的操作都集中在一个地方完成，这也就是唯一需要你修改代码的地方
 * 
 * Factory Method is a factory implemented as a method. 
 * The key of this pattern is letting subclasses override the method.
 * 
 * 为了促使创建对象的代码只包含在create_Shape( )方法里，特定类型的_Shape类的构造函数都被声明为package权限，
 * 这样一来只有create_Shape( )方法可以调用这些构造函数，而位于包（package）以外的那部分代码则没有足够的权限（调用这些构造函数）。
 */

class _ShapeFactory{
    private static _ShapeFactory factory = null;
    
    private _ShapeFactory(){
    }
    
    public static _ShapeFactory getInstance(){
        if(factory == null){
            factory = new _ShapeFactory();
        }
        return factory;
    }

    public _Shape create_Shape(String type) {
        try {
            return (_Shape) Class.forName("com.boubei.learn.jk.designpattern.factory." + type).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Bad shape creation: " + type, e);
        } 		
    }
    
}
abstract class _Shape {
	public abstract void draw();

	public abstract void erase();
}

class _Circle extends _Shape {
	public void draw() {
		System.out.println("Circle.draw");
	}

	public void erase() {
		System.out.println("Circle.erase");
	}
}

class _Square extends _Shape {
	public void draw() {
		System.out.println("Square.draw");
	}

	public void erase() {
		System.out.println("Square.erase");
	}
}

public class ShapeFactory1{
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
        String shlist[] = { "_Circle", "_Square"};       
        for(Iterator it = Arrays.asList(shlist).iterator(); it.hasNext();){
            _Shape s = (_Shape)_ShapeFactory.getInstance().create_Shape((String) it.next());
            s.draw();
            s.erase();
        }
	}
}
