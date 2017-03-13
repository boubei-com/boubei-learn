package com.boubei.learn.zjh;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.boubei.demo.ETLUtil;
import com.boubei.tss.util.DateUtil;

public class KqMonthDataETL {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public static final String KQ_URL = "http://yg.800best.com/kqapi/BestFreightKq/SearchUserKqData";
	
	public static final String KQ_URL2 = "http://yg.800best.com/kqapi/ BestFreightKq/SearchBtrUser";
	
	public static void main(String[] args) throws HttpException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		//params.put("WorkingDate", "2016-09-04");
		int historyDays;
		historyDays=3;
		Date today = DateUtil.today();
		
		Date curDate = DateUtil.subDays(today, historyDays);
		
		System.out.println( today );
		System.out.println( DateUtil.format(curDate) );
		
		System.out.println( ETLUtil.fetchJson(KqMonthDataETL.KQ_URL, params) );
		System.out.println( ETLUtil.fetchJson(KqMonthDataETL.KQ_URL2, params) );
	}

}
