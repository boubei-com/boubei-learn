package com.boubei.demo;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.junit.Test;

import com.boubei.tss.util.DateUtil;

public class KQDataETLTest {
	
	@Test
	public void test1() {
		
		KQDataETL kq = new KQDataETL();
		
		Date curDate = DateUtil.subDays(new Date(), 3);
		while( curDate.before( DateUtil.today() ) ) {
			
			kq.saveOneDay( DateUtil.format(curDate) );
			
			curDate = DateUtil.addDays(curDate, 1);
		}
	}
	
	public static void main(String[] args) throws HttpException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("WorkingDate", "2016-09-04");
		
		System.out.println( ETLUtil.fetchJson(KQDataETL.KQ_URL, params) );
	}

}
