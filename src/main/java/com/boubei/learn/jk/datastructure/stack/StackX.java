package com.boubei.learn.jk.datastructure.stack;

public class StackX {
	private int maxSize;

	private double[] stackArray;

	private int top;

	public StackX(int s) {
		maxSize = s;
		stackArray = new double[maxSize];
		top = -1;
	}

	public void push(double j) {
		stackArray[++top] = j;
	}

	public double pop() {
		return stackArray[top--];
	}

	public double peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (top == maxSize - 1);
	}

	public static void main(String[] args) {
		StackX theStack = new StackX(10);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		while (!theStack.isEmpty()) {
			double value = theStack.pop();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println("");
	}
}
