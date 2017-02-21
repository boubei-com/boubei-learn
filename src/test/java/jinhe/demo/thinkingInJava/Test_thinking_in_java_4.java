package jinhe.lt.thinkingInJava;

public class Test_thinking_in_java_4 {
	 public static void main(String[] args) {
		    Book novel = new Book(true);
		    // Proper cleanup:
		    novel.checkIn();
		    // Drop the reference, forget to clean up:
		    new Book(true);
		    // Force garbage collection & finalization:
		    System.gc();
	 }
}
class Book {
	  boolean checkedOut = false;
	  Book(boolean checkOut) {
	    checkedOut = checkOut;
	  }
	  void checkIn() {
	    checkedOut = false;
	  }
	  public void shutDown() {
	    if(checkedOut)
	      System.out.println("Error: checked out");
	  }
}
