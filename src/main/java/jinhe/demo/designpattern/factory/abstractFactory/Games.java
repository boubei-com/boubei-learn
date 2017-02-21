package jinhe.lt.designpattern.factory.abstractFactory;

/**
 * @author Jon.King  2005-12-5
 *
 * 抽象工厂（Abstract factories）
 *
 * 抽象工厂（abstract factory）模式看起来很像前面我们看到的那些factory对象，只不过它有多个而不是一个factory方法。
 * 每一个factory 方法创建一个不同类型的对象。
 * 基本思想是：在创建工厂对象的地方，由你来决定如何使用该工厂对象创建的那些对象。
 * 
 * Abstract Factory is a factory making a family of abstract products.
 * 
 * 以下代码包含了双重分派（Double Dispatching）和工厂方法（Factory Method）两种模式，
 */

//障碍物对象接口
interface Obstacle {
	void action();
}

//游戏参与者接口
interface Player {
	void interactWith(Obstacle o); //遭遇障碍物
}

class Kitty implements Player {
	public void interactWith(Obstacle ob) {
		System.out.print("Kitty has encountered a ");
		ob.action();
	}
}

class Mike implements Player {
	public void interactWith(Obstacle ob) {
		System.out.print("Mike now battles a ");
		ob.action();
	}
}

//谜语障碍物
class Puzzle implements Obstacle {
	public void action() {
		System.out.println("Puzzle");
	}
}

//脏武器障碍物
class NastyWeapon implements Obstacle {
	public void action() {
		System.out.println("NastyWeapon");
	}
}

//游戏生成工厂
interface GameElementFactory {
	Player makePlayer();  //创建玩家

	Obstacle makeObstacle(); //创建障碍物
}

//谜语游戏
class PuzzlesGame implements GameElementFactory {
	public Player makePlayer() {
		return new Kitty();
	}

	public Obstacle makeObstacle() {
		return new Puzzle();
	}
}

//脏武器游戏
class NastyWeaponGame implements GameElementFactory {
	public Player makePlayer() {
		return new Mike();
	}

	public Obstacle makeObstacle() {
		return new NastyWeapon();
	}
}

//游戏生产环境
class GameEnvironment {
	private GameElementFactory gef;

	private Player p;

	private Obstacle ob;

	public GameEnvironment(GameElementFactory factory) {
		gef = factory;
		p  = gef.makePlayer();
		ob = gef.makeObstacle();
	}

	public void play() {
		p.interactWith(ob);
	}
}

public class Games{
	public static void main(String args[]) {
        GameElementFactory kp = new PuzzlesGame();
        GameElementFactory kd = new NastyWeaponGame();

        GameEnvironment g1 = new GameEnvironment(kp);
        GameEnvironment g2 = new GameEnvironment(kd);
        
        g1.play();
        g2.play();
	}
}
