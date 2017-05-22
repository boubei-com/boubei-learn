package com.boubei.learn.hank.networkscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class _Test {
	
	@Test
	public void test1() {
		//创建3个波次
		Wave w1 = new Wave("w1", 100,31);
		Wave w2 = new Wave("w2", 100,36);
		Wave w3 = new Wave("w3", 100,39);
		Wave w4 = new Wave("w4", 1000,1000);
		//随机生成12个网点
		List<Site> sites = new ArrayList<Site>();
		for(int i = 1; i <= 12; i++) {
			Site site = new Site("s"+i);
			site.mins = 155 - i*8;
			site.setRadiationCircle(site.mins);

			//给网点设置班车数
			int num = i * 2 ;//MathUtil.randomInt(10) + 1;
			for(int j=1; j <= num; j++ ) {
				Truck truck = new Truck( "truck-" + i + "-" + j , "truck-" + i + "-" + j );
				truck.setSubBurden( Truck.TRUCKTYPE[ j % 4 ] );
				truck.belongSite = site;
				truck.licensePlate = "欧洲公路大王"+ i + "-" + j;
				site.trucks.add(truck);
			}
			site.setOriginTruckNumber();
			site.setNowTruckNumber();
			System.out.println(site);
			sites.add(site);
		}
		
		//根据辐射圈给网点排序 ：规则1辐射圈正序,
		Comparator<Site> sortingSite = new ComparatorSite();
        Collections.sort( sites , sortingSite );

		//网点车辆选择波次
		for( Site site : sites ) {
			System.out.println( site.trucks.size() );
			//对网点内车辆排序 ：规则1班车负载逆序,
			Comparator<Truck> sortingTruck = new ComparatorTruck();
	        Collections.sort( site.trucks , sortingTruck );
			for( Truck t : site.trucks ) {
				//System.out.println(t);
				t.waveSelectRule( w1, w2, w3, w4 );
			}
		}
		

	}

	
	
	
	
	
	
	

	
	
	
	
	
	
	//网点排序
	class ComparatorSite implements Comparator<Site> {
	    @Override
	    public int compare(Site s1, Site s2) {
	        // 先按辐射圈排序 正序
	        int flag = s1.radiationCircle.compareTo(s2.radiationCircle);
	        // 辐射圈相同 另外规则
//		        if (flag == 0) {
//		            return u1.getName().compareTo(u2.getName());
//		        } else {
//		            return flag;
//		        }
	        return flag;
	    }


	}


	//网点内车辆排序
	class ComparatorTruck implements Comparator<Truck> {
	    @Override
	    public int compare(Truck t1, Truck t2) {
	        // 先按车型排序 逆序
	        int flag = t2.subBurden.compareTo(t1.subBurden);
	        // 辐射圈相同 另外规则
//			        if (flag == 0) {
//			            return u1.getName().compareTo(u2.getName());
//			        } else {
//			            return flag;
//			        }
	        return flag;
	    }


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}
