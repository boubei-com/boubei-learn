package jinhe.lt.thinkingInJava;



interface Destination {
}

class Parcel3 {
	
	interface Contents {
		void printContents();
	}
	
	private class PContents implements Contents {
		private int i = 11;

		public int value() {
			return i;
		}

		public void printContents() {
			// TODO Auto-generated method stub
			System.out.println(i);
		}
	}

	protected class PDestination implements Destination {
		private String label;

		private PDestination(String whereTo) {
			label = whereTo;
		}

		public String readLabel() {
			return label;
		}
	}

	public Destination dest(String s) {
		return new PDestination(s);
	}

	public Contents cont() {
		return new PContents();
	}
}

public class Test_innerClass2 {
	public static void main(String[] args) {
		Parcel3 p = new Parcel3();		
		p.dest("Tanzania");
		
		Parcel3.Contents c = p.cont();
		c.printContents();
		// Illegal -- can't access private class:
		// ! Parcel3.PContents pc = p.new PContents();
	}
}