package jinhe.lt.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private Addr addr = new Addr();
	
	public class Addr{
		String url = "xianju";

		public String getUrl() { return url; }

		public void setUrl(String url) { this.url = url; }	
		
		public String toString(){ return url; }
	}

	public void copyProperties(User o) {
		this.name = o.getName();
		this.addr = o.getAddr();
		
	}
	
	public String toString(){
		return name + ":" + addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Addr getAddr() {
		return addr;
	}

	public void setAddr(Addr addr) {
		this.addr = addr;
	}
	
	public static void testCopyProperties(){
		String name = "jinpujun";
		
		User o1 = new User();
		o1.setName(name);
		
		System.out.println("o1 = " + o1 + " hashCode = " + o1.hashCode());
		System.out.println("name = " + name + " hashCode = " + name.hashCode());
		System.out.println("");
		
		User o2 = new User();
		o2.copyProperties(o1);
		o2.setName("waitwind");  //o2的name值重新设置了，但不会改变o1的那么值，因为String是不可变对象，o2.name指向了一个新的内存
		o2.getAddr().setUrl("hangzhou"); //o2的addr属性的url属性改变了，o1.addr.url也随着改变了，因为o1、o2的addr指向的是同一块内存
		

		name = "Lizi"; //o1.getName() 所得到的reference指向的是 "jinpujun"，此处name 重新指向了"Lizi"，但o1.getName()不变
		
		System.out.println("name = " + name + " hashCode = " + name.hashCode());
		System.out.println("");
		
		System.out.println("o1 = " + o1 + " hashCode = " + o1.hashCode());
		System.out.println("o1.name hashCode = " + o1.getName().hashCode());
		System.out.println("o1.addr hashCode = " + o1.getAddr().hashCode());
		System.out.println("");
		
		System.out.println("o2 = " + o2 + " hashCode = " + o2.hashCode());
		System.out.println("o2.name hashCode = " + o2.getName().hashCode());
		System.out.println("o2.addr hashCode = " + o2.getAddr().hashCode());
		System.out.println("");
		
		List<Addr> list = new ArrayList<Addr>();
		list.add(o1.getAddr());
		list.add(o2.getAddr());
		Addr a = list.get(0);
		a.setUrl("nnnnn");
		
		System.exit(0);
	}
	
	public static void testMothodClass(){
		try {
			Class<?> clazz = Class.forName("com.jinpj.reflect.User");
				
			Method m_setName = clazz.getMethod("setName", new Class[]{String.class});	
			Method m_getName = clazz.getMethod("getName");	
			Method m_setAddr = clazz.getMethod("setAddr", new Class[]{User.Addr.class});
			
			Object obj = clazz.newInstance();
			User.Addr addr = ((User)obj).getAddr();
			addr.setUrl("hangzhou");
			System.out.println(obj);
			
			m_setName.invoke(obj, new Object[]{"jinpujun"});
			m_setAddr.invoke(obj, new Object[]{addr});
			
			String name = (String) m_getName.invoke(obj);
			System.out.println(name + " " + m_getName.getDeclaringClass());
			System.out.println(obj);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}				
	}
	public static void main(String[] args){
		testCopyProperties();
        //testMothodClass();
	}

}
