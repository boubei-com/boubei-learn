package com.boubei.learn.hank.networkscheduling;

import java.util.ArrayList;
import java.util.List;

// 辐射圈
public class RadiationCircle {
	int size;
	List<Site> sites = new ArrayList<Site>();
	String name;

	public void setSize() {
		this.size = sites.size();
	}

	public RadiationCircle(String name) {
		this.name = name;
	}
}
