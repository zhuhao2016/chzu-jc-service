package cn.chzu.base.util;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map; 


/**
 *
 * @author gu
 */
public class DateUtil {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTREN="yyyy-MM-dd HH:mm:ss";
	
	//:工作日默认设置
	private static Map<Integer, Integer> weekDayMap=new HashMap<Integer,Integer>();
 	static{
		weekDayMap.put(1, 7);
		weekDayMap.put(2, 1);
		weekDayMap.put(3, 2);
		weekDayMap.put(4, 3);
		weekDayMap.put(5, 4);
		weekDayMap.put(6, 5);
		weekDayMap.put(7, 6); 
	}
    public static String getDateStr(Date date,String pattern){
        SimpleDateFormat df=new SimpleDateFormat(pattern);
        df.setLenient(false);
        return df.format(date);
    }
    public static Date getDate(String dateStr,String pattern){
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            return df.parse(dateStr);
        } catch (Exception ex) {
         }
        return null;
    } 
	/**
	 *获取日期相对于 当前周的编号 e.g. "2014-09-22" : 1
	 * @param date
	 */
	public static int getNumOfWeekByDate(String date){
		
		Date begin=DateUtil.getDate(date, DateUtil.DATE_PATTERN);
		
		Calendar calendar=Calendar.getInstance();
		
		calendar.setTime(begin);
		
		return weekDayMap.get(calendar.get(Calendar.DAY_OF_WEEK));
		
	}
	
	/** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到毫秒的字符串
     * @param formatStr
     * @return 
     */  
    public static String getDateStrByTimeStamp(String timeStamp) {  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTREN);
        Date date = new Date(Long.parseLong(timeStamp));
        return simpleDateFormat.format(date);
    }

	
}
