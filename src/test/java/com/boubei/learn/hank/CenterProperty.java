package com.boubei.learn.hank;

public class CenterProperty {
	String name;
	int burden;
	int class1;
	int class2;
	int class3;
    int type_id;
	// CenterProperty 类的构造器
	public CenterProperty(String name){
		this.name=name;
	}
	//设置分拨所辖网点班车负载
	public void setBurden(int centerBurden){
		burden=centerBurden;
	}
	
	public void setClass(){
		class1=this.burden/3;
		class2=this.burden/3;
		class3=this.burden/3;
	}
	
	public void setTypeId(int setTypeId){
		type_id=setTypeId;
	}
	
	public String toString() {
        return "hashCode:" + hashCode()+";Name:"+this.name;
    }
}
