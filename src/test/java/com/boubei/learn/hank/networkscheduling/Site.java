package com.boubei.learn.hank.networkscheduling;

import java.util.ArrayList;
import java.util.List;

// 网点
public class Site {
	String code;
	Integer radiationCircle;
	int mins;
	String name;
	List<Truck> trucks = new ArrayList<Truck>();
	int originTruckNumber;
	int nowTruckNumber;

	public Site(String name) {
		this.name = name;
	}

	// 设置辐射圈
	public void setRadiationCircle(float mins,RadiationCircle r1,RadiationCircle r2,RadiationCircle r3) {
		// if(this.mins/60)
		// System.out.print(setRadiationCircle/60);
		this.mins = (int) mins;
		if (mins / 60 <= 1) {
			this.radiationCircle = 1;
			r1.sites.add(this);
		} else if (mins / 60 <= 2) {
			this.radiationCircle = 2;
			r2.sites.add(this);
		} else {
			this.radiationCircle = 3;
			r3.sites.add(this);
		}

		// System.out.println(this.radiationCircle);
		// System.out.println(this.mins);

	}

	public void setOriginTruckNumber() {
		this.originTruckNumber = this.trucks.size();
	}

	public void setNowTruckNumber() {
		this.nowTruckNumber = this.trucks.size();
	}

	public String toString() {
		return name + ":第" + radiationCircle + "辐射圈;共有车" + originTruckNumber
				+ "辆;现有车" + nowTruckNumber + "辆。";
	}

}
