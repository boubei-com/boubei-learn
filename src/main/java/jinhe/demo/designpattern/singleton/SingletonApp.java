package jinhe.lt.designpattern.singleton;

class Soup {

	private Soup() {
	}

	private static Soup ps1 = new Soup();

	public static Soup access() {
		return ps1;
	}

	public void f() {
	}
}

class Lunch {
	void test() {
		Soup.access().f();
	}
}
