package com.boubei.learn.jk.exception;

public class Rethrowing {

	public static void f() throws Exception {
		System.out.println("originating the exception in f()");
		throw new Exception("thrown from f()");
	}

	public static void g() throws Throwable {
		try {
			f();
		} catch (Exception e) {
			System.err.println("Inside g(),e.printStackTrace()");
			e.printStackTrace();
			throw e; // 17
			// throw e.fillInStackTrace(); // 18,这样将记录当前的位置
		}
	}

	public static void main(String[] args) throws Throwable {
		try {
			g();
		} catch (Exception e) {
			System.err.println("Caught in main, e.printStackTrace()");
			e.printStackTrace();
		}
	}
}
