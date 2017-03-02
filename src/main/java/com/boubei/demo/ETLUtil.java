package com.boubei.demo;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.boubei.tss.framework.exception.BusinessException;

public class ETLUtil {
	
	public static String fetchJson(String url, Map<String, String> params) 
			throws HttpException, IOException  {
		
		PostMethod postMethod = new PostMethod(url);
		for(String key : params.keySet()) {
			postMethod.addParameter(key, params.get(key));
		}
		postMethod.addParameter("Token", "BestFreightToken");
		
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
	
	public static String toStr(Object val) {
		return val == null ? null : val.toString();
	}
	
	public static String get(Map<String, Object> item, String key) {
		return toStr( item.get(key) );
	}
}
