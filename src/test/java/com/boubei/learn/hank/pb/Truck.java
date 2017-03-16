package com.boubei.learn.hank.pb;

public class Truck {
	
	public final static String[] CHE_XING = "4.2,6.8,7.6,9.6".split(",");

	String code;
	
	Site belongSite;
	
	String chexing = CHE_XING[0];
	
	Wave wave;
	
	
	public void setSite(Site site) {
		this.belongSite = site;
		site.trucks.add(this);
	}
	
	public void setWave(Wave wave) {
		this.wave = wave;
		wave.trucks.add(this);
	}
	
	public String toString() {
		return code;
	}
}
