package com.boubei.learn.hank.pb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.boubei.tss.util.MathUtil;

public class _Test {
	
	@Test
	public void test1() {
		
		Wave w1 = new Wave("w1", 41);
		Wave w2 = new Wave("w2", 42);
		Wave w3 = new Wave("w3", 43);
		
		List<Site> sites = new ArrayList<Site>();
		for(int i = 1; i <= 12; i++) {
			Site site = new Site();
			site.name = "site" + i;
			site.mins = 52 + i*8;
			
			sites.add(site);
			
			int num = MathUtil.randomInt(10) + 1;
			for(int j=1; j <= num; j++ ) {
				Truck truck = new Truck();
				truck.code = "truck-" + i + "-" + j;
				truck.chexing = Truck.CHE_XING[ j % 4 ];
				
				truck.setSite(site);
			}
		}
		
		for(Site site : sites) {
			//int rc = site.calculateRC();
			int index = 1;
			for( Truck t : site.trucks) {
				if(index % 3 == 1) {
					t.setWave(w1);
				}
				if(index % 3 == 2) {
					t.setWave(w2);
				}
				if(index % 3 == 0) {
					t.setWave(w3);
				}
				
				index++;
			}
		}
		
		System.out.println( w1 );
		System.out.println( w2 );
		System.out.println( w3 );
	}
}
