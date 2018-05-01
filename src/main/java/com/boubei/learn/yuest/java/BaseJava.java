package com.boubei.learn.yuest.java;

public class BaseJava {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int int_max=java.lang.Integer.MAX_VALUE;
		System.out.println("Int的最大值为"+int_max);
		
		float float_max=java.lang.Float.MAX_VALUE;
		float float_min=java.lang.Float.MIN_VALUE;
		double double_max=java.lang.Double.MAX_VALUE;
		double double_min=java.lang.Double.MIN_VALUE;
		//浮点型数据默认为双精度数据，如果指定为单精度需要在末尾加上F或f
		System.out.println("单精度浮点数的最大值为"+float_max);
		System.out.println("单精度浮点数的最小值为"+float_min);
		//结果中1.4乘于10的负45次方 此处的E并非自然对数,而是10的次方的意思。 这是科学计数法的写法。
		System.out.println("双精度浮点数的最大值为"+double_max);
		System.out.println("双进度浮点数的最小值为"+double_min);
		
		
	}

}
