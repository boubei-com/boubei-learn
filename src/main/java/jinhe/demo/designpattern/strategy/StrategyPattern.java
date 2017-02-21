package jinhe.lt.designpattern.strategy;

import junit.framework.TestCase;

/**
 * 分解共同性（Factoring Commonality） 
 * 应用“一次且只能有一次” 原则产生最基本的模式，将变化的那部分代码放到方法里
 * 
 * 策略模式（Strategy）：运行时刻选择算法
 * 
 * 另外，Strategy模式还可以添加一个“上下文（context）”，
 * 这个context可以是一个代理类（surrogate class），用来控制对某个特定strategy对象的选择和使用。
 * 
 * JDK里Strategy模式的例子：comparator objects.
 * 
 * @author Jon.King  2005-12-5
 */
interface FindMinima {
	double[] algorithm(double[] line);
}

class LeastSquares implements FindMinima {
	public double[] algorithm(double[] line) {
		return new double[] { 1.1, 2.2 };
	}
}

class NewtonsMethod implements FindMinima {
	public double[] algorithm(double[] line) {
		return new double[] { 3.3, 4.4 };
	}
}

class Bisection implements FindMinima {
	public double[] algorithm(double[] line) {
		return new double[] { 5.5, 6.6 };
	}
}

class ConjugateGradient implements FindMinima {
	public double[] algorithm(double[] line) {
		return new double[] { 3.3, 4.4 };
	}
}

class MinimaSolver {
	private FindMinima strategy;

	public MinimaSolver(FindMinima strat) {
		strategy = strat;
	}

	double[] minima(double[] line) {
		return strategy.algorithm(line);
	}

	void changeAlgorithm(FindMinima newAlgorithm) {
		strategy = newAlgorithm;
	}
}

public class StrategyPattern extends TestCase {
	MinimaSolver solver = new MinimaSolver(new LeastSquares());

	double[] line = { 1.0, 2.0, 1.0, 2.0, -1.0, 3.0, 4.0, 5.0, 4.0 };

	public void test() {
		solver.minima(line);
		solver.changeAlgorithm(new Bisection());
		solver.minima(line);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(StrategyPattern.class);
	}
}
