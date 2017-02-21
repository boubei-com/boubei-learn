package jinhe.lt.thinkingInJava;

import java.util.Random;

interface RandVals {
	  Random rand = new Random();
	  int randomInt0=rand.nextInt(10);
	  //上面一句等价于(int)java.lang.Math.random()*10;
	  int randomInt = rand.nextInt()*10;
	  long randomLong = rand.nextLong() * 10;
	  float randomFloat = rand.nextLong() * 10;
	  double randomDouble = rand.nextDouble() * 10;
	}

public class Test_interface_fields {
	public static void main(String[] args) {
		System.out.println(RandVals.randomInt0);
	    System.out.println(RandVals.randomInt);
	    System.out.println(RandVals.randomLong);
	    System.out.println(RandVals.randomFloat);
	    System.out.println(RandVals.randomDouble);
	}
}
