package com.boubei.learn.hank.networkscheduling;


// 网点班车
public class Truck {
	public final static String[] TRUCKTYPE = "T4_2M,T6_8M,T9_6M,T61F"
			.split(",");
	String code;
	String name;
	String truckType;
	Integer subBurden;
	Integer opTime;
	Site belongSite;
	Wave wave;
	int flight;
	String licensePlate;

	public Truck(String name, String code) {
		this.name = name;
		this.code = code;
	}

	// 设置负载
	public void setSubBurden(String truckType) {
		if (TRUCKTYPE[0].equals(truckType)) {
			this.subBurden = 1;
			this.opTime = 20;
		} else if (TRUCKTYPE[1].equals(truckType)) {
			this.subBurden = 1;
			this.opTime = 40;
		} else if (TRUCKTYPE[2].equals(truckType)) {
			this.subBurden = 2;
			this.opTime = 60;
		} else if (TRUCKTYPE[3].equals(truckType)) {
			this.subBurden = 3;
			this.opTime = 80;
		}
		this.truckType = truckType;
	}

	public void waveSelectRule(Wave w1, Wave w2, Wave w3, Wave w4) {
		// String inWave;
		if (w1.capacity - this.subBurden >= 0) {
			w1.capacity -= this.subBurden;
			w1.trucks.add(this);
			this.wave = w1;
		} else if (w2.capacity - this.subBurden >= 0) {
			w2.capacity -= this.subBurden;
			w2.trucks.add(this);
			this.wave = w2;
		} else if (w3.capacity - this.subBurden >= 0) {
			w3.capacity -= this.subBurden;
			w3.trucks.add(this);
			this.wave = w3;
		} else {
			w4.capacity -= this.subBurden;
			w4.trucks.add(this);
			this.wave = w4;
		}
		// System.out.println(this.wave);
	}

	public String toString() {
		return "code:" + code + ";负载:" + subBurden;
	}

}
