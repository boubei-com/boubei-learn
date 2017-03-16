package com.boubei.learn.hank.pb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Wave {
	
	public Wave(String name, int c) {
		this.name = name;
		this.capacity = c;
	}
	
	String name;
	
	Date startTime;
	Date endTime;
	
	int capacity; // 波次容量  41
	
	List<Truck> trucks = new ArrayList<Truck>();
	
	public String toString() {
		return name + trucks;
	}

}
