package com.boubei.learn.zjh;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.boubei.tss.framework.exception.BusinessException;

public class KqMonthDataETL {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public static final String KQ_URL = "http://yg.800best.com/kqapi/BestFreightKq/GetUserLastMonthFinalResult";
	
	public static final String KQ_URL2 = "http://yg.800best.com/kqapi/BestFreightKq/SearchBtrUser";
	
	public static String fetchJson(String url, Map<String, String> params) 
			throws HttpException, IOException  {
		
		PostMethod postMethod = new PostMethod(url);
		for(String key : params.keySet()) {
			postMethod.addParameter(key, params.get(key));
		}
		postMethod.addParameter("Token", "BestFreightToken");
		
		System.out.println(postMethod);
		
		// 最后生成一个HttpClient对象，并发出postMethod请求
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode == 200) {
			String responseBody = postMethod.getResponseBodyAsString();
			String ret = new String(responseBody.getBytes("UTF-8"));
			return ret;
		} 
		else {
			throw new BusinessException("调用失败！错误码：" + statusCode);
		}
	}
	
	public static void main(String[] args) throws HttpException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		//params.put("WorkingDate", "2016-09-04");
		//Date today = DateUtil.today();
		
		//Date curDate = DateUtil.subDays(today, historyDays);
		
		//System.out.println( today );
		//System.out.println( DateUtil.format(curDate) );
		//System.out.println( ETLUtil.fetchJson(KqMonthDataETL.KQ_URL, params) );
		
		System.out.println( fetchJson(KqMonthDataETL.KQ_URL2, params) );
	}

}
