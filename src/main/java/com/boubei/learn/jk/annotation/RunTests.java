package com.boubei.learn.jk.annotation;

import java.lang.reflect.Method;

/**
 * 遍历Foo类的所有方法，尝试调用其中被上面的测试annotation类型标注过的方法。
 * 在此过程中为了找出哪些方法被 annotation类型标注过，需要使用反射的方式执行此查询。
 * 如果在调用方法时抛出异常，此方法被认为已经失败，并打印一个失败报告。
 * 最后，打印运行通过/失败的方法数量
 * 
 * @author bl00618
 *
 */
public class RunTests {
	public static void main(String[] args) throws Exception {
		int passed = 0, failed = 0;
		for (Method m : Class.forName("jinhe.lt.java5.annotation.demo1.Foo").getMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				try {
					m.invoke(null);
					passed++;
				} catch (Throwable ex) {
					System.out.printf("Test %s failed: %s %n", m, ex.getCause());
					failed++;
				}
			}
		}
		System.out.printf("Passed: %d, Failed %d%n", passed, failed);
	}
}
