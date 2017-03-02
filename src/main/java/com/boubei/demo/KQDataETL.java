package com.boubei.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.boubei.tss.dm.data.sqlquery.SQLExcutor;
import com.boubei.tss.dm.record.Record;
import com.boubei.tss.dm.record.RecordService;
import com.boubei.tss.dm.record.ddl._Database;
import com.boubei.tss.framework.Global;

public class KQDataETL {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public static final String KQ_URL = "http://yg.800best.com/kqapi/BestFreightKq/SearchUserKqData";
	
	static Long RECORD_ID = null;
	
	_Database getDB(Long recordId) {
		RecordService recordService = (RecordService) Global.getBean("RecordService");
		Record record = recordService.getRecord(recordId);
		return _Database.getDB(record);
	}
	
	@SuppressWarnings("unchecked")
	public void saveOneDay(String day) {
		log.info("开始同步" + day);
		
		// 检查某一天的考勤数据是否已经存在了
		int exsitCount = 0;
		_Database db = null;
		if(RECORD_ID != null) {
        	db = getDB( RECORD_ID );
        	Map<String, String> params = new HashMap<String, String>();
        	params.put("workingdate", day);

			exsitCount = db.select(1, 10, params ).count;
        }
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("WorkingDate", day);  // 查询的日期（必填）
			String ret = ETLUtil.fetchJson(KQ_URL, params);
			ObjectMapper objectMapper = new ObjectMapper();
				
			Map<Object, Object> map = objectMapper.readValue(ret, Map.class);
			
			boolean success = (Boolean) map.get("success");
			if( !success ) return;
			
			int totalCount = (Integer) map.get("iTotalRecords");
			log.info("【" + day + "】考勤数据量 = " + totalCount + ", 上次 = " + exsitCount);
			if( totalCount <= 0) {
				return;
			}
			
			if(exsitCount > 0) {
				if(exsitCount == totalCount) {
					return;
				} 
				
				// 先删除这天已经存在的数据，再重新保存
				SQLExcutor.excute("delete from " + db.table + " where workingdate='" +day+ "'", db.datasource);
			}
			
			List<Map<String, String>> valuesMaps = new ArrayList<Map<String, String>>();
			List<Map<String, Object>> result = (List<Map<String, Object>>) map.get("data");
	        for(Map<String, Object> item : result) {
	        	
	        	Map<String, String> valuesMap = new HashMap<String, String>();
	        	
	        	valuesMap.put("workingdate", ETLUtil.get(item, "workingdate") ); // 工作日期
	        	valuesMap.put("username", ETLUtil.get(item, "username") );  // 姓名
	        	valuesMap.put("serialnumber", ETLUtil.get(item, "serialnumber") ); // 考勤号 Long
	        	valuesMap.put("badgenumber", ETLUtil.get(item, "badgenumber") ); // 工号
	        	valuesMap.put("worktime", ETLUtil.get(item, "worktime") ); // Decimal 工作天数
	        	valuesMap.put("timeduration", ETLUtil.get(item, "timeduration") ); // Decimal 出勤时长
	        	valuesMap.put("harfdayleavetype1", ETLUtil.get(item, "harfdayleavetype1") ); // 上半天请假类别
	        	valuesMap.put("harfdayleavetype2", ETLUtil.get(item, "harfdayleavetype2") ); // 下半天请假类别
				valuesMap.put("harfdayleaveday1", ETLUtil.get(item, "harfdayleaveday1") ); // Decimal 上半天请假天数
	        	valuesMap.put("harfdayleaveday1", ETLUtil.get(item, "harfdayleaveday2") ); // Decimal 下半天请假天数
	        	
	        	valuesMaps.add(valuesMap);
	        }
		
	        if(RECORD_ID != null) {
	        	db.insertBatch(valuesMaps);
	        }
	        else { // just test
//	        	System.out.println(valuesMaps);
	        }
	        log.info("【" + day + "】抽取到记录数 = " + valuesMaps.size());
		} 
		catch(Exception e) {
			String errMsg = "【" + day + "】抽取出错了，" + e.getMessage();
			log.error( errMsg);
//			MonitorUtil.recordErrLog("KQ-Data",  errMsg);
		}
	}

}
