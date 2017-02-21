package jinhe.lt.designpattern.factory.simpleFactory;

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
 * 为了促使创建对象的代码只包含在createShape( )方法里，特定类型的Shape类的构造函数都被声明为package权限，
 * 这样一来只有createShape( )方法可以调用这些构造函数，而位于包（package）以外的那部分代码则没有足够的权限（调用这些构造函数）。
 */

class ShapeFactory{
    private static ShapeFactory factory = null;
    
    private ShapeFactory(){
    }
    
    public static ShapeFactory getInstance(){
        if(factory == null){
            factory = new ShapeFactory();
        }
        return factory;
    }

    public Shape createShape(String type) {
        try {
            return (Shape) Class.forName("com.jinpj.design_pattern.factory.simpleFactory." + type).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Bad shape creation: " + type, e);
        } 		
    }
    
}
abstract class Shape {
	public abstract void draw();

	public abstract void erase();
}

class Circle extends Shape {
	public void draw() {
		System.out.println("Circle.draw");
	}

	public void erase() {
		System.out.println("Circle.erase");
	}
}

class Square extends Shape {
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
        String shlist[] = { "Circle", "Square"};       
        for(Iterator it = Arrays.asList(shlist).iterator(); it.hasNext();){
            Shape s = (Shape)ShapeFactory.getInstance().createShape((String) it.next());
            s.draw();
            s.erase();
        }
	}
}
