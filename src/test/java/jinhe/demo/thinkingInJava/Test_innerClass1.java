package jinhe.lt.thinkingInJava;

public class Test_innerClass1 {
	  class Contents {
	    private int i = 11;
	    public int value() { return i; }
	  }
	  
	  class Destination {
	    private String label;
	    Destination(String whereTo) {
	      label = whereTo;
	    }
	    String readLabel() { return label; }
	  }
	  
	  public Destination createDestination(String s) {
	    return new Destination(s);
	  }
	  public Contents createContents() {
	    return new Contents();
	  }
	  
	  public void ship(String dest) {
		  
	    Destination d = createDestination(dest); //也可以直接 new Destination(dest);
	    System.out.println(d.readLabel());
	  }
	  
	  public static void main(String[] args) {
		Test_innerClass1 p = new Test_innerClass1();
	    p.ship("Tanzania");
	    Test_innerClass1 q = new Test_innerClass1();
	    
	    // Defining references to inner classes:
	    Test_innerClass1.Contents c = q.createContents();
	    Test_innerClass1.Destination d = q.createDestination("Borneo");
	    System.out.println(c.i);
	    System.out.println(d.label);
	  }
	} 
