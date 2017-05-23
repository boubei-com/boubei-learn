package com.boubei.learn.hank.networkscheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class _Test {

	@Test
	public void test1() {
		// 创建3个波次
		Wave w1 = new Wave("w1", 100, 31);
		Wave w2 = new Wave("w2", 100, 36);
		Wave w3 = new Wave("w3", 100, 39);
		Wave w4 = new Wave("w4", 1000, 1000);
		// 创建3个辐射圈
		RadiationCircle r1 = new RadiationCircle("r1");
		RadiationCircle r2 = new RadiationCircle("r2");
		RadiationCircle r3 = new RadiationCircle("r3");

		// 随机生成12个网点
		List<Site> sites = new ArrayList<Site>();
		for (int i = 1; i <= 12; i++) {
			Site site = new Site("s" + i);
			site.mins = 155 - i * 8;
			site.setRadiationCircle(site.mins, r1, r2, r3);

			// 给网点设置班车
			int num = i * 2;
			for (int j = 1; j <= num; j++) {
				Truck truck = new Truck("truck-" + i + "-" + j, "truck-" + i
						+ "-" + j);
				truck.setSubBurden(Truck.TRUCKTYPE[j % 4]);
				truck.belongSite = site;
				truck.licensePlate = "欧洲公路大王" + i + "-" + j;
				site.trucks.add(truck);
			}
			site.setOriginTruckNumber();
			site.setNowTruckNumber();
			// System.out.println(site);
			sites.add(site);
		}
		// 设置各辐射圈网点数
		r1.setSize();
		r2.setSize();
		r3.setSize();
		System.out.println(r1.size);
		System.out.println(r2.size);
		System.out.println(r3.size);
			

		// 根据辐射圈给网点排序 ：规则1辐射圈正序,
		Collections.sort(sites, new Comparator<Site>() {
			@Override
			public int compare(Site s1, Site s2) {
				// TODO Auto-generated method stub
				return s1.radiationCircle - s2.radiationCircle;
			}
		});

		// 网点车辆选择波次
		while (r1.size + r2.size + r3.size > 0) {
			while (r1.size > 0) {
				for (Site site : r1.sites) {

					// 对网点内车辆排序 ：规则1班车负载逆序,
					Collections.sort(site.trucks, new Comparator<Truck>() {
						@Override
						public int compare(Truck t1, Truck t2) {
							// 先按车型排序 逆序
							int flag = t2.subBurden - t1.subBurden;
							return flag;
						}
					});

					for (Truck t : site.trucks) {
						t.waveSelectRule(w1, w2, w3, w4);
						site.setNowTruckNumber();
					}
				}
				r1.setSize();
			}
			while (r2.size > 0) {
				for (Site site : r2.sites) {
					// 对网点内车辆排序 ：规则1班车负载逆序,
					Collections.sort(site.trucks, new Comparator<Truck>() {
						@Override
						public int compare(Truck t1, Truck t2) {
							// 先按车型排序 逆序
							int flag = t2.subBurden - t1.subBurden;
							return flag;
						}
					});
					for (Truck t : site.trucks) {
						t.waveSelectRule(w1, w2, w3, w4);
						r2.sites.remove(site);
					}
				}
				r2.setSize();
			}
			while (r3.size > 0) {
				for (Site site : r3.sites) {
					// 对网点内车辆排序 ：规则1班车负载逆序,
					Collections.sort(site.trucks, new Comparator<Truck>() {
						@Override
						public int compare(Truck t1, Truck t2) {
							// 先按车型排序 逆序
							int flag = t2.subBurden - t1.subBurden;
							return flag;
						}
					});
					for (Truck t : site.trucks) {
						t.waveSelectRule(w1, w2, w3, w4);
						r3.sites.remove(site);
					}
				}
				r3.setSize();
			}
		}

	}

}
