package com.boubei.learn.zjh;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.boubei.tss.dm.data.sqlquery.SQLExcutor;
import com.boubei.tss.dm.data.sqlquery.SqlConfig;

public class MergeTest {
	
	@Test
	public void Test(){
		String sql = SqlConfig.getScript("querySite", 4);
		
//		Timestamp day = new Timestamp(DateUtil.today().getTime());

		Map<Integer, Object> params = new HashMap<Integer, Object>();
		
		params.put( 1 , "2017-7-4" );
		
		//params.put( 1 , "10488016781001" );
		
		SQLExcutor.excute(sql, params,"ds-xjy");
	}
}
