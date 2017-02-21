package jinhe.lt.designpattern.state;

import junit.framework.TestCase;

/**
 * @author  Jon.King  2005-11-29
 * 
 * State模式在代理（surrogate）
 * 
 * 为了使同一个方法调用可以产生不同的行为，State模式在代理（surrogate）的生命周期内切换它所对应的实现（implementation）。
 * 当你发现，在决定如何实现任何一个方法之前都必须作很多测试的情况下，这是一种优化实现代码的方法。
 * 例如，童话故事青蛙王子就包含一个对象（一个生物），
 * 这个对象的行为取决于它自己所处的状态。你可以用一个布尔（boolean）值来表示它的状态
 */
class Creature {
	private interface State {
		String response();
	}

	private class Frog implements State {
		public String response() {
			return "Ribbet!";
		}
	}

	private class Prince implements State {
		public String response() {
			return "Darling!";
		}
	}

	private State state = new Frog();

	public void greet() {
		System.out.println(state.response());
	}

	public void kiss() {
		state = new Prince();
	}
}

public class KissingPrincess extends TestCase {
	Creature creature = new Creature();

	public void test() {
		creature.greet();
		creature.kiss();
		creature.greet();
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(KissingPrincess.class);
	}
}
