package com.tianyl.filmManage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date parseDate(String dateStr) {
		Date result = null;
		try {
			result = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (result == null) {
			throw new RuntimeException("format time error:" + dateStr);
		}
		return result;
	}

	public static void main(String[] args) {
		String dateStr = "2016/02/09";
		Date d = parseDate(dateStr);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
	}
}
