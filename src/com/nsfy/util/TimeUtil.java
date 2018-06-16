package com.nsfy.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeUtil {
	private Calendar nowtime = new GregorianCalendar();
	public String getNowDayStr(){
		int year = nowtime.get(Calendar.YEAR);
		int month = nowtime.get(Calendar.MONTH) + 1;
		int date = nowtime.get(Calendar.DAY_OF_MONTH -1);
		String y, m, d ;
		y = "" + year;
		if (month > 0 && month < 10) {
			m = "0" + month;
		} else {
			m = "" + month;
		}
		if (date > 0 && date < 10) {
			d = "0" + date;
		} else {
			d = "" + date;
		}
		System.out.println(y+m+d);
		return y+m+d;
	}
	
	public String getNowYear(){
		int year = nowtime.get(Calendar.YEAR);
		return year+"";
	}
	
	public String getNowMonth(){
		int month = nowtime.get(Calendar.MONTH) + 1;
		if (month > 0 && month < 10) {
			return "0" + month;
		} else {
			return "" + month;
		}
	}
	
	public String getNowDate(){
		int date = nowtime.get(Calendar.DAY_OF_MONTH);
		if (date > 0 && date < 10) {
			return "0" + date;
		} else {
			return "" + date;
		}
	}
}
