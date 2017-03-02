package com.boubei.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.boubei.tss.dm.data.sqlquery.SQLExcutor;
import com.boubei.tss.dm.data.sqlquery.SqlConfig;
import com.boubei.tss.util.EasyUtils;

public class DBTest {
	
	public static void printResult(List<Map<String, Object>> result) {
		System.out.println("--------- 本次查询结果为： ----------");
		for(Map<String, Object> row : result) {
			System.out.println(row);
		}
	}
	
	@Test
	public void test1() {
		String sql = "select count(*) as total from usr_vf.gt_site";
		List<Map<String, Object>> result = SQLExcutor.query("ds-ydn", sql);
		printResult(result);
	}
	
	@Test
	public void test2() {
		String sql = "select s.name 网点, count(t.code) 票数, sum(t.amount) 件数, round( sum(t.weight) ) 重量 " +
				" from usr_vf.gt_trans_order t, usr_vf.gt_site s" +
				" where t.send_site_id = s.id" +
				"   and ? in (s.id, s.parent_site_id) " +
				"   and t.created_time >= trunc(sysdate)" +
				" group by s.name ";
				
		List<Map<String, Object>> result = SQLExcutor.query("ds-ydn", sql, 60428);
		printResult(result);
	}
	
	@Test
	public void test3() {
		String sql = SqlConfig.getScript("querySite", 1);
		List<Map<String, Object>> result = SQLExcutor.query("ds-ydn", sql, 60428);
		printResult(result);
	}

	@Test
	public void test4() {
		String sql = SqlConfig.getScript("querySite", 2);
		
		Map<Integer, Object> paramsMap = new HashMap<Integer, Object>();
		paramsMap.put(1, 60428);
		paramsMap.put(2, "2017-03-01");
		paramsMap.put(3, "2017-03-02");
		
		SQLExcutor ex = new SQLExcutor();
		ex.excuteQuery(sql, paramsMap , "ds-ydn");
		
		List<Map<String, Object>> result = ex.result;
		printResult(result);
	}
	
	@Test
	public void test5() {
		String sql = SqlConfig.getScript("querySite", 3);
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("param1", "60428");
		paramsMap.put("param2", "2017-03-01");
		paramsMap.put("param3", "2017-03-02");
		
		sql = EasyUtils.fmParse(sql, paramsMap);
		
		SQLExcutor ex = new SQLExcutor();
		ex.excuteQuery(sql, "ds-ydn");
		
		List<Map<String, Object>> result = ex.result;
		printResult(result);
	}
	
	// 直接取Report里的SQL运行
	@Test
	public void test6() {
		
	}
}