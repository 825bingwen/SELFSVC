/*
 * 文 件 名:  DateUtil.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zKF69263
 * 修改时间:  2014-7-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-7-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DateUtil
{
    /**
     * 取得当前时间(格式:yyyyMMddHHmmss)
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String _getCurrentTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar theday = Calendar.getInstance();
        String currTime = df.format(theday.getTime());
        
        return currTime;
    }
    
    /**
     * 取得当前时间(格式:yyyy-MM-dd HH:mm:ss)
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentDateTime()
    {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        Date date = new Date();
        return formater.format(date);
    }
    
    /**
     * 将日期按照指定格式转成字符串
     * 
     * @param date
     * @param format
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dateToString(Date date, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        
        return dateFormat.format(date);
    }
    
    /**
     * 取得当天日期,格式 2009-02-11
     * 
     * @return
     */
    public static String getToday()
    {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = new GregorianCalendar();
        return sdf.format(cl.getTime());
    }
    
    /**
     * 取得当天日期时间,格式 2009-02-11 23:9:21
     * 
     * @return
     */
    public static String getTodaytime()
    {
        Calendar cl = new GregorianCalendar();
        return getToday() + " " + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE) + ":"
                + cl.get(Calendar.SECOND);
    }
    
    /**
     * 取得当前时间,格式 23:12:20
     * 
     * @return
     */
    public static String getTime()
    {
        Calendar cl = new GregorianCalendar();
        return cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND) + " ";
    }
    
    /**
     * 取得当前小时
     * 
     * @return
     */
    public static int getHour()
    {
        Calendar cl = new GregorianCalendar();
        return cl.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 取得当前日期 格式为20090211
     * 
     * @return
     */
    public static String getNoFormatToday()
    {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        Calendar cl = new GregorianCalendar();
        return sdf.format(cl.getTime());
    }
    
    /**
     * 取得当前时间 格式为231611
     * 
     * @return
     */
    public static String getNoFormatTime()
    {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HHmmss");
        Calendar cl = new GregorianCalendar();
        return sdf.format(cl.getTime());
    }
    
    /**
     * 取得当前年份
     * 
     * @return
     */
    public static String getYear()
    {
        return getNoFormatToday().substring(0, 4);
    }
    
    /**
     * 取得当前月份
     * 
     * @return
     */
    public static String getMonth()
    {
        return getNoFormatToday().substring(4, 6);
    }
    
    /**
     * 取得当前日
     * 
     * @return
     */
    public static String getDay()
    {
        return getNoFormatToday().substring(6, 8);
    }
    
    /**
     * 返回昨天的日期 格式2009-02-10
     * 
     * @return
     */
    public static String getYesterday()
    {
        String strYesterday = "";
        Calendar cale = null;
        cale = new GregorianCalendar();
        cale.add(Calendar.DATE, -1);
        strYesterday = getStrByCalendar(cale);
        return strYesterday;
    }
    
    /**
     * 返回明天天的日期 格式2009-02-10
     * 
     * @return
     */
    public static String getTomorrow()
    {
        String strYesterday = "";
        Calendar cale = null;
        cale = new GregorianCalendar();
        cale.add(Calendar.DATE, +1);
        strYesterday = getStrByCalendar(cale);
        return strYesterday;
    }
    
    public static String getStrByCalendar(Calendar cale)
    {
        return (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(cale.getTime());
    }
    
    /**
     * 日期字符串的格式转换,例如"2009-02-11"转换为2009年2月11日
     * 
     * @param sDate
     * @return
     */
    public static String getChnDateString(String sDate)
    {
        if (sDate == null)
        {
            return null;
        }
        sDate = sDate.trim();
        if (sDate.length() == 7)
        {
            sDate += "-01";
        }
        StringTokenizer st = new StringTokenizer(sDate, "-");
        int year = 2100;
        int month = 0;
        int day = 1;
        try
        {
            year = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken()) - 1;
            day = Integer.parseInt(st.nextToken());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Calendar cl = new GregorianCalendar(year, month, day);
        return cl.get(Calendar.YEAR) + "年" + (cl.get(Calendar.MONTH) + 1) + "月" + cl.get(Calendar.DATE) + "日";
    }
    
    /**
     * 取得某年某月的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getMaxDayOfMonth(int year, int month)
    {
        Calendar cal = new GregorianCalendar(year, month - 1, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    
    /**
     * 取得某年某月的第一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getMinDayOfMonth(int year, int month)
    {
        Calendar cal = new GregorianCalendar(year, month - 1, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    
    /**
     * 取得当天的中文日期，像2006年11月28日 星期二
     * 
     * @return
     */
    public static String getChineseToDay()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm E", Locale.CHINESE);
        Calendar cl = new GregorianCalendar();
        return sdf.format(cl.getTime());
    }
    
    /**
     * 取得当天的中文日期，像2006年11月28日 星期二 下午05:06
     * 
     * @return
     */
    public static String getChineseToDayTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E a", Locale.CHINESE);
        Calendar cl = new GregorianCalendar();
        return sdf.format(cl.getTime());
    }
    
    /**
     * 根据字符串，取得日期类
     * 
     * @param sDate
     * @return
     */
    public static Calendar getDate(String sDate)
    {
        if (sDate == null)
        {
            return null;
        }
        sDate = sDate.trim();
        if (sDate.length() == 7)
        {
            sDate += "-01";
        }
        StringTokenizer st = new StringTokenizer(sDate, "-");
        int year = 2100;
        int month = 0;
        int day = 1;
        try
        {
            year = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken()) - 1;
            day = Integer.parseInt(st.nextToken());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new GregorianCalendar(year, month, day);
    }
    
    /**
     * 根据日期类取得日期的字符串形式
     * 
     * @param sDate
     * @return
     */
    public static String getDateString(Calendar sDate)
    {
        if (sDate == null)
        {
            return "";
        }
        return (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(sDate.getTime());
    }
    
    /**
     * 根据日期类取年月的字符串形式
     * 
     * @param sDate
     * @return
     */
    public static String getYearMonth(Calendar sDate)
    {
        if (sDate == null)
        {
            return "";
        }
        return (new java.text.SimpleDateFormat("yyyy-MM")).format(sDate.getTime());
    }
    
    /**
     * 比较两个日期类型的字符串，格式为（yyyy-mm-dd） 如果cale1大于cale2，返回1 如果cale1小于cale2，返回-1 如果相等，返回0 如果格式错误，返回-2
     * 
     * @param cale1
     * @param cale2
     * @return
     */
    public static int compareCalendar(String cale1, String cale2)
    {
        Calendar calendar1 = getDate(cale1);
        Calendar calendar2 = getDate(cale2);
        if (calendar1 == null || calendar2 == null)
        {
            return -2;
        }
        return calendar1.compareTo(calendar2);
    }
    
    /**
     * 将时间格式为12:30格式转换为1230格式
     * 
     * @param timeString
     * @return
     */
    public static String formatTime(String timeString)
    {
        StringBuffer sb = new StringBuffer();
        
        String hour = timeString.substring(0, 2);
        String minute = timeString.substring(3, 5);
        return sb.append(hour).append(minute).toString();
    }
    
    /**
     * 获取当前时间，精确到毫秒
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String curOnlyTime()
    {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
    
    // 得到agoday天前的时间,format如yyyy-MM-dd HH:mm:ss
    public static String getAgoDate(String format, int agoday)
    {
        Date oneWeekAgo = new Date(System.currentTimeMillis() - (long)1000 * 60 * 60 * 24 * agoday);
        SimpleDateFormat df = new SimpleDateFormat(format);
        String returndate = df.format(oneWeekAgo);
        return returndate;
    }
    /**
     * 获取当期时间格式为：XX年XX月XX日XX时XX分
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getStringDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分", Locale.CHINESE);
        Calendar cl = new GregorianCalendar();
        return  sdf.format(cl.getTime());
    }
    
    /**
     * 判断time1加上interval后，与time2的时间先后。如果time1加上interval(如20121009160500)早于time2(如20121009160600)，返回true；
     * 否则，返回false。
     * 
     * @param pattern 时间格式
     * @param time1 
     * @param time2 
     * @param interval 时间间隔，单位分钟
     * @return
     * @see 
     */
    public static boolean compareTime(String pattern, String time1, String time2, int interval)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        
        try
        {
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.MINUTE, interval);
            
            date1 = c.getTime();
            
            return (date1.before(date2));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * 日期字符串的格式转换,例如"2014-08-26 17:19:11"转换为2014年8月26日
     * @param strDate
     * @return
     * @remark create by jWX216858 2014-8-26 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
     */
    public static String getChnDateStr(String strDate)
    {
    	if (null == strDate)
    	{
    		return null;
    	}
    	String[] date = strDate.split("-");
    	return date[0] + "年" + Integer.parseInt(date[1]) + "月" 
    		+ Integer.parseInt(date[2].substring(0, 2)) + "日";
    }
    
    /**
     * <获取当前月的第一天>
     * <功能详细描述>
     * @param dateFormat
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getFirstDay(String dateFormat)
    {
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
        // 获取前月的第一天 
        cale = Calendar.getInstance(); 
        cale.add(Calendar.MONTH, 0); 
        cale.set(Calendar.DAY_OF_MONTH, 1); 
        
        return format.format(cale.getTime()); 
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dateFormat
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getLastDay(String dateFormat)
    {
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
        
        // 获取前月的最后一天 
        cale = Calendar.getInstance(); 
        cale.add(Calendar.MONTH, 1); 
        cale.set(Calendar.DAY_OF_MONTH, 0); 
        return format.format(cale.getTime()); 
    }
    
    /**
     * <string的时间转换为Data类型>
     * <功能详细描述>
     * @param dateStr 要转换的字符串类型的时间
     * @param formaterString 字符串格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-6-18 10:59:43 OR_SD_201505_489_开户中增加预约选号功能
     */
    public static Date toDate(String dateStr, String formaterString)
    {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        
        try
        {
            formater.applyPattern(formaterString);
            date = formater.parse(dateStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 取时间差 <功能详细描述>
     * 
     * @param dateStr
     * @return
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public static String dateC(String dateStr) throws ParseException
    {
        Date date = null;
        int distance = 0;
        try
        {
            date = DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
            Date date1 = new Date();
            
            distance = (int)((date1.getTime() - date.getTime()) / 1000 / 60);
            return String.valueOf(distance);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
}
