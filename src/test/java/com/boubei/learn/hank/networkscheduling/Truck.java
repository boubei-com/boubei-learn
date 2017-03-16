package com.boubei.learn.hank.networkscheduling;


import com.boubei.learn.hank.networkscheduling.Site;
import com.boubei.learn.hank.networkscheduling.Wave;

public class Truck {
	public final static String[] TRUCKTYPE = "T4_2M,T6_8M,T9_6M,T61F".split(",");
	String code;
	String name;
	String truckType;
	Integer subBurden;
	Site belongSite;
	Wave wave;
	int flight;
	String licensePlate;
	
	public Truck(String name,String code){
		this.name=name;
		this.code=code;
	}
	
	public void setSubBurden(String truckType){//设置负载
		if(TRUCKTYPE[0].equals(truckType)){
			subBurden=1;
		}
		else if(TRUCKTYPE[1].equals(truckType)){
			subBurden=1;
		}
		else if(TRUCKTYPE[2].equals(truckType)){
			subBurden=2;
		}
		else if(TRUCKTYPE[3].equals(truckType)){
			subBurden=3;
		}
		this.truckType=truckType;
	}


	public void waveSelectRule(Wave w1,Wave w2,Wave w3,Wave w4) {
		//String inWave;
		if(w1.capacity-this.subBurden>=0){
			w1.capacity-=this.subBurden;
			w1.trucks.add(this);
			this.wave=w1;
		}
		else if(w2.capacity-this.subBurden>=0){
			w2.capacity-=this.subBurden;
			w2.trucks.add(this);
			this.wave=w2;
		}
		else if(w3.capacity-this.subBurden>=0){
			w3.capacity-=this.subBurden;
			w3.trucks.add(this);
			this.wave=w3;
		}
		else{
			w4.capacity-=this.subBurden;
			w4.trucks.add(this);
			this.wave=w4;
		}	
		//System.out.println(this.wave);
	}

	public String toString(){
		return "code:"+code+";负载:"+subBurden;
	}





}
