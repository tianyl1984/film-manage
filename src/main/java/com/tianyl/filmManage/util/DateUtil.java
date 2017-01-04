package com.tianyl.filmManage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date parseDate(String dateStr) {
		dateStr = dateStr != null ? dateStr.trim() : "";
		Date result = null;
		// 2017-1-14 17-01-04 2017-1-4
		if (dateStr.length() == 8 || dateStr.length() == 9) {
			String sep = "";
			if (dateStr.contains("-")) {
				sep = "-";
			}
			if (dateStr.contains("/")) {
				sep = "/";
			}
			String[] strs = dateStr.split(sep);
			if (strs.length == 3) {
				String yearStr = dateStr.split(sep)[0].trim();
				String monthStr = dateStr.split(sep)[1].trim();
				String dayStr = dateStr.split(sep)[2].trim();
				if (yearStr.length() == 2) {
					yearStr = "20" + yearStr;
				}
				if (monthStr.length() == 1) {
					monthStr = "0" + monthStr;
				}
				if (dayStr.length() == 1) {
					dayStr = "0" + dayStr;
				}
				dateStr = yearStr + sep + monthStr + sep + dayStr;
			}
		}
		if (dateStr.length() == 10) {
			try {
				result = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr);
			} catch (ParseException e) {
				// e.printStackTrace();
			}
			if (result == null) {
				try {
					result = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
				} catch (ParseException e) {
					// e.printStackTrace();
				}
			}
		}
		if (dateStr.length() == 19) {
			try {
				result = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr);
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		if (dateStr.length() == 11) {
			try {
				result = new SimpleDateFormat("yyyy/MM/dd HH:mm")
						.parse(Calendar.getInstance().get(Calendar.YEAR) + "/" + dateStr);
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		if (result == null) {
			throw new RuntimeException("format time error:" + dateStr);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(parseDate("2017-1-14"));
	}
}
