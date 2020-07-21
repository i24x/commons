package cn.lsl.data.exchange.syncclient.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Wurd 2005-11-27
 * 
 *         日期帮助类
 */
public class TimeHelper {
	private static String CurrentTime;

	private static String CurrentDate;

	/**
	 * 得到当前的年份 返回格式:yyyy
	 * 
	 * @return String
	 */
	public static String getCurrentYear() {
		Date NowDate = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的月份 返回格式:MM
	 * 
	 * @return String
	 */
	public static String getCurrentMonth() {
		Date NowDate = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的日期 返回格式:dd
	 * 
	 * @return String
	 */
	public static String getCurrentDay() {
		Date NowDate = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的时间，精确到毫秒,共19位 返回格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}

	public static String getCurrentCompactTime() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}
	
	public static String getCurrentCompactTime1() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}
	
	public static String getYesterdayCompactTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		System.out.print(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}

	public static String getYesterdayCompactTimeForFileName() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		System.out.print(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}
	
	/**
	 * 得到当前的日期,共8位 返回格式：yyyyMMdd
	 * 用于流水号生产
	 * @return String
	 */
	public static String getCurrentDateForFlowNum() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	/**
	 * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	/**
	 * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentDate1() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	/**
	 * 得到当月的第一天,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentFirstDate() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}

	/**
	 * 得到当月的最后一天,共10位 返回格式：yyyy-MM-dd
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static String getCurrentLastDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = null;

			Date date = formatter.parse(getCurrentFirstDate());
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			return formatDate(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
    /**
     * 得到某个月的最后一天,共10位 返回格式：yyyy-MM-dd
     * @param time
     * @return
     */
	public static String getCurrentLastDateByTime(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = null;

			Date date = formatter.parse(time);
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			return formatDate(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 得到当前日期加上某一个整数的日期，整数代表天数 输入参数：currentdate : String 格式 yyyy-MM-dd add_day :
	 * int 返回格式：yyyy-MM-dd
	 */
	public static String addDay(String currentdate, int add_day) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.DATE, add_day);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到当前日期加上某一个整数的日期，整数代表月数 输入参数：currentdate : String 格式 yyyy-MM-dd add_month
	 * : int 返回格式：yyyy-MM-dd
	 */
	public static String addMonth(String currentdate, int add_month) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.MONTH, add_month);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到endTime比beforeTime晚几个月，整数代表月数 输入参数：endTime、beforeTime : String
	 * 格式前7位的格式为 yyyy-MM
	 */
	public static int monthDiff(String beforeTime, String endTime) {
		if (beforeTime == null || endTime == null) {
			return 0;
		}
		int beforeYear, endYear, beforeMonth, endMonth;
		try {
			beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
			endYear = Integer.parseInt(endTime.substring(0, 4));
			beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
			endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
			return (endYear - beforeYear) * 12 + (endMonth - beforeMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到当前日期加上某一个整数的分钟 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
	 * add_minute : int 返回格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String addMinute(String currentdatetime, int add_minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(11, 13));
			minute = Integer.parseInt(currentdatetime.substring(14, 16));
			second = Integer.parseInt(currentdatetime.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.MINUTE, add_minute);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到当前日期加上某一个整数的秒 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
	 * add_second : int 返回格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String addSecond(String currentdatetime, int add_second) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(11, 13));
			minute = Integer.parseInt(currentdatetime.substring(14, 16));
			second = Integer.parseInt(currentdatetime.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.SECOND, add_second);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String addMinute1(String currentdatetime, int add_minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(8, 10));
			minute = Integer.parseInt(currentdatetime.substring(8, 10));
			second = Integer.parseInt(currentdatetime.substring(8, 10));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.MINUTE, add_minute);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(String sDate) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = bartDateFormat.parse(sDate);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * 解析日期及时间
	 * 
	 * @param sDateTime
	 *            日期及时间字符串
	 * @return 日期
	 */
	public static Date parseDateTime(String sDateTime) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			Date date = bartDateFormat.parse(sDateTime);
			return date;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * 取得一个月的天数 date:yyyy-MM-dd
	 * 
	 * @throws ParseException
	 */
	public static int getTotalDaysOfMonth(String strDate) {
		int day = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = new GregorianCalendar();

			Date date = null;
			date = sdf.parse(strDate);
			calendar.setTime(date);
		//	int month = calendar.get(Calendar.MONTH) + 1; // 月份
			day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 天数
		} catch (Exception e) {
		}
		return day;
	}

	public static long getDateSubDay(String startDate, String endDate) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long theday = 0;
		try {
			calendar.setTime(sdf.parse(startDate));
			long timethis = calendar.getTimeInMillis();
			calendar.setTime(sdf.parse(endDate));
			long timeend = calendar.getTimeInMillis();
			theday = (timethis - timeend) / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theday;
	}

//	public static void main(String[] args) {
		// long d = 0;

		// System.out.println(formatDate(TimeHelper.getMonday(parseDate("2012-04-21"))));

		// System.out.println(TimeHelper.addSecond(TimeHelper.getCurrentTime(),
		// -2) );
//		try {
//			System.out.println(TimeHelper.minutesBetween("2017-07-07 08:47:00", "2017-07-07 08:57:00", "yyyy-MM-dd HH:mm:ss"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

	// 本周第一天
	public static String getWeekFirstDay() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.add(Calendar.MONTH, 1);
		cpcalendar.add(Calendar.DATE, -1);
		cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String weekDay = df.format(new Date(cpcalendar.getTimeInMillis()));
		return weekDay;
	}

	// 本周最后一天
	public static String getWeekLastDay() {
		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd");
		Calendar c_end = Calendar.getInstance();
		c_end.add(Calendar.DAY_OF_WEEK, (8 - c_end.get(Calendar.DAY_OF_WEEK)));
		String week_end = df.format(c_end.getTime());
		return week_end;
	}

	/**
	 * 根据指定日期获得本周日期
	 * 
	 * @param mdate
	 * @return
	 */
	public static List<String> dateToWeek(Date mdate) {
		@SuppressWarnings("deprecation")
		int b = mdate.getDay();
		Date fdate;
		List<String> list = new ArrayList<String>();
		Long fTime = mdate.getTime() - b * 24 * 3600000l;
		for (int a = 0; a < 7; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000l));
			list.add(a, formatDate(fdate));
		}
		return list;
	}

	/**
	 * 根据日期获取星期
	 * 
	 * @description
	 * @param date
	 *            yyy-mm-dd
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getWeekByDate(String dates) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };

		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		Date date = new Date();

		try {
			date = sdfInput.parse(dates);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}
	
	/**
	 * 
	 */
	public static String addDays(String currentdate, int add_day) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(4, 6)) - 1;
			day = Integer.parseInt(currentdate.substring(6, 8));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.DATE, add_day);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 /**
	  * 
	  * @description 比较两个时间的大小
	  * @param DATE1 时间1
	  * @param DATE2 时间2
	  * @param dateFormat 时间格式
	  * @return
	  * @return int
	  * @throws
	  */
	 public static int compareDate(String DATE1, String DATE2,String dateFormat) {
	        
	        
	        DateFormat df = new SimpleDateFormat(dateFormat);
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                return -1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                return 1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	 
	 // 获取当前时间所在周的开始日期
	    public static String getFirstDayOfWeek(String dayDate) {
	    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar c = null;
			try {
				Date date = format.parse(dayDate);
				c = new GregorianCalendar();
				c.setFirstDayOfWeek(Calendar.MONDAY);
				c.setTime(date);
				c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        return sdf.format(c.getTime());
	    }
	 
	    // 获取当前时间所在周的结束日期
	    public static String getLastDayOfWeek(String dayDate) {
	    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar c = null;
			try {
				Date date = format.parse(dayDate);
				c = new GregorianCalendar();
				c.setFirstDayOfWeek(Calendar.MONDAY);
				c.setTime(date);
				c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+6); // Monday
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        return sdf.format(c.getTime());
	    }
     /**
      * 
      * @description 比较两个时间的大小（时间格式：yyyy-MM-dd HH:mm:ss）
      * @param DATE1 时间1
      * @param DATE2 时间2
      * @return
      * @return int
      * @throws
      */
	 public static int compareDate(String DATE1, String DATE2) {
	        
	        
	        return compareDate(DATE1, DATE2,"yyyy-MM-dd HH:mm:ss");
	 }
	 
	// 获取前一个月的第一天
	public static String getFirstDayOfLastMonth() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = df.format(calendar1.getTime());
		return firstDay;
	}
	
	// 获取前一个月的最后一天
	public static String getLastDayOfLastMonth() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = df.format(calendar2.getTime());
		return lastDay;
	}
	
	/**  
     * 计算两个日期之间相差的分钟
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差分钟
     * @throws ParseException  
     */    
    public static int minutesBetween(String smdate,String bdate,String dateFormat) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        Date smdatee=sdf.parse(smdate);
        Date bdatee=sdf.parse(bdate);  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdatee);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdatee);    
        long time2 = cal.getTimeInMillis();         
        long between_hours=(time2-time1)/(1000*60);  
            
       return Integer.parseInt(String.valueOf(between_hours));           
    }
}
