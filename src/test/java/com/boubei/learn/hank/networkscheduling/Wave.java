package com.boubei.learn.hank.networkscheduling;

import java.util.ArrayList;
import java.util.List;

// 波次
public class Wave {
	String name;
	float capacity;
	List<Truck> trucks = new ArrayList<Truck>();

	public Wave(String name, int burden, float ability) {
		this.name = name;
		this.capacity = burden / 3 + 1;
		if (this.capacity < ability) {
			this.capacity = ability;
		}

	}

	public String toString() {
		return "name:" + this.name + ";capacity:" + this.capacity;
	}

}
