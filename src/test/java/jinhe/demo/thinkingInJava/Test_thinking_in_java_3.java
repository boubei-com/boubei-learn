package jinhe.lt.thinkingInJava;

public class Test_thinking_in_java_3 {
		  public static void main(String[] args) {
////		    Integer n1 = new Integer(47);
////		    Integer n2 = new Integer(47);
////		    System.out.println(n1 == n2);
////		    System.out.println(n1 != n2);
////		    System.out.println(n1.equals(n2));
//		    
////		    for(int i = 0; i < 128; i++)
////		        if(Character.isUpperCase((char)i))
////		          System.out.println("value: " + i +" character: " + (char)i); 
	
//continue 和 break的用法
////		    for(int i = 0; i < 100; i++) {
////		        if(i == 74) break; // Out of for loop
////		        if(i % 9 != 0) continue; // Next iteration
////		        System.out.println(i);
////		      }
////		    int i = 0;
////		    // An "infinite loop":
////		    while(true) {
////		        i++;
////		        int j = i * 27;
////		        if(j == 1269) break; // Out of loop
////		        if(i % 10 != 0) continue; // Top of loop
////		        System.out.println(i);
////		    }
//switch的用法
			    for(int i = 0; i < 100; i++) {
			      char c = (char)(Math.random() * 26 + 'a');
			      System.out.print(c + ": ");
			      switch(c) {
			        case 'a':
			        case 'e':
			        case 'i':
			        case 'o':
			        case 'u': System.out.println("vowel");
			                  break;
			        case 'y':
			        case 'w': System.out.println("Sometimes a vowel");
			                  break;
			        default:  System.out.println("consonant");
			      }
			  }

		  }		
//	static void usage() {
//	    System.out.println("Usage: \n\t" +
//	      "RandomBounds lower\n\tRandomBounds upper");
//	    System.exit(1);
//	  }
//	  public static void main(String[] args) {
//	    if(args.length != 1) usage();
//	    if(args[0].equals("lower")) {
//	      while(Math.random() != 0.0)
//	        ; // Keep trying
//	      System.out.println("Produced 0.0!");
//	    }
//	    else if(args[0].equals("upper")) {
//	      while(Math.random() != 1.0)
//	        ; // Keep trying
//	      System.out.println("Produced 1.0!");
//	    }
//	    else
//	      usage();
//	  }

}
