package com.boubei.learn.jk;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.boubei.tss.dm.data.sqlquery.SQLExcutor;
import com.boubei.tss.um.entity.User;

public class DBTest {
	
	public static void printResult(List<Map<String, Object>> result) {
		System.out.println("--------- 本次查询结果为： ----------");
		for(Map<String, Object> row : result) {
			String name = (String) row.get("loginname");
			String passwd = (String) row.get("password");
			
			if( User.encodePasswd(name, "123456").equals(passwd) ) {
				System.out.println(row);
			}
		}
	}
	
	@Test
	public void test1() {
		String sql = "select loginName, password from um_user";
		List<Map<String, Object>> result = SQLExcutor.query("ds-wm", sql);
		printResult(result);
	}
}