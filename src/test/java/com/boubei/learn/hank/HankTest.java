package com.boubei.learn.hank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.boubei.tss.dm.dml.SQLExcutor;

public class HankTest {
	
	public static void printResult(List<Map<String, Object>> result) {
		System.out.println("--------- 本次查询结果为： ----------");
		for(Map<String, Object> row : result) {
			System.out.println(row+"ss");
		}
	}
	
//	public class centerProperty(){
//		
//	}
	
	@Test
	public void centerData() {
		String sql = "select s.parent_site_id,type_id, "+
				"sum(case when car_type in('T4_2M') then 1 "+
				         "when car_type in('T6_8M','T7_6M') then 2 "+
				         "when car_type in('T9_6M','T_OTHER') then 3 else 0 end) burden "+
				"from USR_VF.BTR_SITE_CAR S,USR_VF.BTR_SITE_PROPERTY T "+
				"where s.type_id=1 "+
				"and s.site_property_id=t.id "+
				"and (s.parent_site_id=? or ?=-1) "+
				"group by s.parent_site_id,type_id ";
		Map<Integer, Object> paramsMap = new HashMap<Integer, Object>();
		paramsMap.put(1, 60000);
		paramsMap.put(2, 60000);
		SQLExcutor ex = new SQLExcutor();
		ex.excuteQuery(sql, paramsMap , "ds-ydn");		
		List<Map<String, Object>> result = SQLExcutor.query("ds-ydn", sql, 60000 , 60000);
		
	    for (Map<String, Object> m : result)  
	    {  
//	      for (String k : m.keySet())  
//	      {  
//	        System.out.println(k + " : " + m.get(k));  
//	      }  
	      //ArrayList<Object> a=new ArrayList<Object>();
	      //List<Map<String, Object>> listItems=new ArrayList<Map<String,Object>>(); 
	    	//object tt=m;
	    	System.out.println(m);
	    } 
	}		
}