package jinhe.lt.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;


/** 
 * <p> BeanUtil.java </p> 
 * 
 * @author 金普俊 2006-4-19
 *
 */

public class BeanUtil {
	private BeanUtil() {

	}

	private final static Logger log = Logger.getLogger(BeanUtil.class);

	/**
	 * 对象属性复制工具
	 * 
	 * @param to
	 *            目标拷贝对象
	 * @param from
	 *            拷贝源
	 */
	public static void copy(Object to, Object from) {
		try {
			PropertyUtils.copyProperties(to, from);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("拷贝时出错，用反射机制时属性或者方法不能被访问。", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("拷贝时出错，用反射机制调用方法时。", e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("拷贝时出错，用反射机制调用方法时方法不存在。", e);
		}
	}

	/**
	 * 按照Map中的key和bean中的属性名一一对应，将Map中数据设定到实体对象bean中
	 * 
	 * @param bean
	 * @param attrsMap
	 */
	public static void setDataToBean(Object bean, Map<String, Object> attrsMap) {
		PropertyDescriptor[] descr = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < descr.length; i++) {
			PropertyDescriptor d = descr[i];
			if (d.getWriteMethod() == null) // 如果当前属性没有对应的可写的方法，则跳过到下一属性
				continue;
			if (!attrsMap.containsKey(d.getName())) // 如果Map的key值中无当前属性值，则跳过到下一属性
				continue;
			try {
				Class<?> clazz = d.getPropertyType();
				Object value = attrsMap.get(d.getName());

				/*
				 * 如果属性的类型不是java.lang.String且值为空，则不将map中该属性的值设置到实体中，
				 * 防止象Long型的guId，如果为空则值为null setProperty(....)会出错
				 */
				if (value == null || (!String.class.equals(clazz) && "".equals(value))) {
					continue;
				}

				if (value.getClass().equals(clazz)) { // value一般为前台传入，类型多为String型
					PropertyUtils.setProperty(bean, d.getName(), value);
				} else {
					PropertyUtils.setProperty(bean, d.getName(), clazz
							.getConstructor(new Class[] { String.class })
							.newInstance(new Object[] { value }));
				}
			} catch (Exception e) {
				log.error("属性名：" + d.getName() + " 设置到实体中时出错", e);
				throw new RuntimeException("属性名：" + d.getName() + " 设置到实体中时出错", e);
			}
		}
	}

	/**
	 * 根据对象的class名，创建相应的Class对象
	 * 
	 * @param className
	 * @return
	 */
	public static Class<?> createClassByName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("实体: " + className + " 无法加载", e);
		}
	}

	/**
	 * 根据对象的class名，创建相应的对象
	 * 
	 * @param className
	 * @return
	 */
	public static Object newInstanceByName(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("实例化失败：" + className, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("没有合适的构造函数，实例化失败：" + className, e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("类文件无法找到，实例化失败：" + className, e);
		}
	}

	/**
	 * <p>
	 * 通过带参数的构造函数实例化对象
	 * </p>
	 * 
	 * @param className
	 *            String
	 * @param clazzes
	 *            Class[]
	 * @param args
	 *            Object[]
	 * @return Object
	 */
	public static Object newInstanceByName(String className, Class<?>[] clazzes, Object[] args) {
		Class<?> clazz = createClassByName(className);
		try {
			return clazz.getConstructor(clazzes).newInstance(args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("非法参数类型，实例化失败：" + className, e);
		} catch (SecurityException e) {
			throw new RuntimeException("安全性限制，实例化失败：" + className, e);
		} catch (InstantiationException e) {
			throw new RuntimeException("实例化失败：" + className, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("没有相应的构造函数，实例化失败：" + className, e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("非法调用，实例化失败：" + className, e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("没有相应的构造函数，实例化失败：" + className, e);
		}
	}

	/**
	 * 根据Class对象创建Object对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static Object newInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("实例化失败：" + clazz.getName());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("实例化失败：" + clazz.getName());
		}
	}

	/**
	 * 判断属性是否在实体中
	 * 
	 * @param bean
	 * @param propertyName
	 * @return
	 */
	public static boolean isPropertyInBean(Object bean, String propertyName) {
		PropertyDescriptor[] descr = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < descr.length; i++) {
			PropertyDescriptor d = descr[i];
			if (propertyName.equals(d.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断对象是否继承某个接口
	 * 
	 * @param clazz
	 * @param interfaceClazz
	 * @return true/false
	 */
	public static boolean isImplInterface(Class<?> clazz, Class<?> interfaceClazz) {
		return Arrays.asList(clazz.getInterfaces()).contains(interfaceClazz);
	}
    
    /**
     * 获取对象中指定属性的值
     * @param obj
     * @param propertyName
     * @return
     */
    public static Object getProperty(Object obj, String propertyName){
        try {
            return PropertyUtils.getProperty(obj, propertyName);
        } catch (Exception e) {
            throw new RuntimeException("获取对象属性值时出错!", e);
        }       
    }
}

