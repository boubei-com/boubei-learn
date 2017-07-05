package com.boubei.demo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.boubei.tss.dm.data.sqlquery.SQLExcutor;
import com.boubei.tss.util.DateUtil;

public class XJYTest {
	
	public static void printResult(List<Map<String, Object>> result) {
		System.out.println("--------- 本次查询结果为： ----------");
		for(Map<String, Object> row : result) {
			System.out.println(row);
		}
	}
	
	@Test
	public void test1() {
		String sql = "delete from g_day_site where day=?";
		
		Timestamp day = new Timestamp(DateUtil.today().getTime());
		
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		params.put(1, day);
		SQLExcutor.excute(sql, params, "ds-xjy");
		
		sql = "select count(*) as total from g_day_site where day=?";
		List<Map<String, Object>> result = SQLExcutor.query("ds-xjy", sql, day);
		printResult(result);
	}
	
	
}