package jinhe.lt.thinkingInJava.exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class InputFile {
	private BufferedReader in;

	public InputFile(String fname) throws Exception {
		try {
			in = new BufferedReader(new FileReader(fname));
			// Other code that might throw exceptions
		} catch (FileNotFoundException e) {
			System.err.println("Could not open " + fname);
			// Wasn't open, so don't close it
			throw e;
		} catch (Exception e) {
			// All other exceptions must close it
			try {
				in.close();
			} catch (IOException e2) {
				System.err.println("in.close() unsuccessful");
			}
			throw e; // Rethrow
		} finally {
			// Don't close it here!!!
		}
	}

	public String getLine() {
		String s;
		try {
			s = in.readLine();
		} catch (IOException e) {
			throw new RuntimeException("readLine() failed");
		}
		return s;
	}

	public void dispose() {
		try {
			in.close();
			System.out.println("dispose() successful");
		} catch (IOException e2) {
			throw new RuntimeException("in.close() failed");
		}
	}
}

public class Cleanup {
	public static void main(String[] args) {
		try {
			InputFile in = new InputFile("d:/Temp/a.txt");
			String s;
			while ((s = in.getLine()) != null)
				System.out.println(s);
				//; // Perform line-by-line processing here...			   
			in.dispose();
		} catch (Exception e) {
			System.err.println("Caught Exception in main");
			e.printStackTrace();
		}
	}
}
