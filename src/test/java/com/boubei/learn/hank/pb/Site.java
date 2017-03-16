package com.boubei.learn.hank.pb;

import java.util.ArrayList;
import java.util.List;

public class Site {
	
	String name;
	
	int mins; // 运行时长（分钟）
	
	List<Truck> trucks = new ArrayList<Truck>();

	public int calculateRC() {
		return mins / 60;
	}
}
