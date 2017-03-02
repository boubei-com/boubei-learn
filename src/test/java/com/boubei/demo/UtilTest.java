package com.boubei.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UtilTest {

	@Test
	public void test1() {
		
		int[] array1 = new int[] { 1, 2, 3, 4, 5 };
		for(int x : array1) {
			System.out.println(x);
		}
		
		List<String> list = new ArrayList<>();
		list.add("东");
		list.add("南");
		list.add("西");
		list.add("北");
		for(String x : list) {
			System.out.println(x);
		}
		
		Set<String> set = new LinkedHashSet<String>();
		set.add("东");set.add("东");
		set.add("南");
		set.add("西");
		set.add("北");
		for(String x : set) {
			System.out.println(x);
		}
		
		Map<Integer, Object> paramsMap = new HashMap<Integer, Object>();
		paramsMap.put(1, 60428);
		paramsMap.put(2, "2017-03-01");
		paramsMap.put(3, "2017-03-02");
		
		for(Integer key : paramsMap.keySet()) {
			System.out.println(paramsMap.get(key));
		}
		
	}
	
}
