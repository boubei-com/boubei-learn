package com.boubei.learn.jk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetString {
	
	public String getString() throws IOException {	
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void main(String[] args) throws IOException{
		GetString test=new GetString();
		System.out.println("Please enter a string:");
		System.out.println(test.getString());
	}
}
