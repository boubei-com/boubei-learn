package com.boubei.learn.zjh;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyTips {
	
	//获取上月
	private static Date getLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		Date mon = new Date();
		System.out.println( sdf.format(getLastMonth( mon ) ) );
	}

}
