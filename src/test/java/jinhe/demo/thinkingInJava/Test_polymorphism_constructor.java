package jinhe.lt.thinkingInJava;

abstract class Glyph {
	abstract void draw();

	Glyph() {
		System.out.println("Glyph() before draw()");
		draw();
		System.out.println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;

    RoundGlyph() {
        System.out.println("lalala");
    }
    
	public RoundGlyph(int r) {
		radius = r;
		System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
	}

	void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}
}

final class TGlyph extends RoundGlyph{
    public TGlyph(int i){
        System.out.println("TGlyph()!");
    }
}

public class Test_polymorphism_constructor {
	public static void main(String[] args) {
		new RoundGlyph(5);
        
        new TGlyph(5);
	}
}
