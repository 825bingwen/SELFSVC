/*
 * 文件名：TerminalInfoServiceImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：通用工具类
 * 创建人:w69551
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.nx.selfsvc.cache.RefreshCacheNX;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.busiAcceptTime.model.BusiAcceptTimePO;
import com.gmcc.boss.selfsvc.busiAcceptTime.service.BusiAcceptTimeServiceImpl;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.service.SelfSvcService;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 通用工具类
 * @author w69551
 * @version 1.0，2010-11-30
 * @see
 * @since
 */
public class CommonUtil
{
    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(CommonUtil.class);
	
	/**
     * 默认小数位数(标度)
     */
    private static final int DEFAULT_SCALE = 2;
	
	/**
     * 四舍五入，默认保留2位小数
     * 
     * @param s
     *            要四舍五入的金额
     * @return 四舍五入结果
     */
    public static String round(String s) 
    {
        BigDecimal bd1 = new BigDecimal(s.trim());
        BigDecimal bd2 = new BigDecimal("1");
        return bd1.divide(bd2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_EVEN)
                .toString();
    }
	
    /**
     * 根据当前菜单ID获取下一级功能列表
     * 
     * @param allMenu，完整的菜单列表
     * @param menuIDList，终端可用的菜单ID列表
     * @param currMenuID，当前的菜单ID
     * @param brandID，品牌
     * @return
     */
    public static List getMenuInfo(List allMenu, List menuIDList, String currMenuID, String brandID)
    {
        List menuList = new ArrayList();
        if (allMenu == null || allMenu.size() == 0 || menuIDList == null || menuIDList.size() == 0)
        {
            return menuList;
        }
        
        MenuInfoPO menu = null;
        String menuID = "";
        for (int i = 0; i < allMenu.size(); i++)
        {
            menu = (MenuInfoPO)allMenu.get(i);
            
            // 先根据用户的brandID筛选。如果菜单既不适用于所有品牌也不适用于用户所属品牌，继续
            // begin zKF66389 findbus清零
            if (brandID != null && !"".equals(brandID.trim()) && !"ALL".equalsIgnoreCase(menu.getBrandID())
                    && !brandID.equalsIgnoreCase(menu.getBrandID()))
            // end zKF66389 findbus清零
            {
                continue;
            }
            
            menuID = menu.getMenuid();
            
            for (int j = 0; j < menuIDList.size(); j++)
            {
                
                if (menuID.equals((String)menuIDList.get(j)) && currMenuID.equals(menu.getParentid()))
                {
                    menuList.add(menu);
                    break;
                }
            }
        }
        
        return menuList;
    }
    
    /**
     * 根据当前菜单ID获取下一级功能列表
     * 
     * @param menus，终端机可用的菜单列表
     * @param currMenuID，当前的菜单ID
     * @param brandID，品牌
     * @return
     */
    public static List<MenuInfoPO> getChildrenMenuInfo(List<MenuInfoPO> menus, String currMenuID, String brandID)
    {
        List<MenuInfoPO> menuList = new ArrayList<MenuInfoPO>();
        if (menus == null || menus.size() == 0)
        {
            return menuList;
        }
        
        MenuInfoPO menu = null;
        for (int i = 0; i < menus.size(); i++)
        {
            menu = (MenuInfoPO) menus.get(i);
            
            // 先根据用户的brandID筛选。如果菜单既不适用于所有品牌也不适用于用户所属品牌，继续
            // begin zKF66389 findbus清零
            if (brandID != null && !"".equals(brandID.trim()) && !"ALL".equalsIgnoreCase(menu.getBrandID())
                    && !brandID.equalsIgnoreCase(menu.getBrandID()))
            // end zKF66389 findbus清零
            {
                continue;
            }
            
            if (currMenuID.equals(menu.getParentid()))
            {
                menuList.add(menu);
            }            
        }
        
        return menuList;
    }
    
    /**
     * 根据当前菜单ID获取父菜单ID
     * 
     * @param allMenu，完整的菜单列表
     * @param currMenuID，当前的菜单ID
     * @return
     */
    public static String getParentMenuInfo(List allMenu, String currMenuID)
    {
        if (allMenu == null || allMenu.size() == 0)
        {
            return currMenuID;
        }
        
        if (currMenuID == null || "".equals(currMenuID.trim()))
        {
            return "root";
        }
        
        String parentMenuID = "";
        MenuInfoPO menu = null;
        for (int i = 0; i < allMenu.size(); i++)
        {
            menu = (MenuInfoPO)allMenu.get(i);
            if (currMenuID.equals(menu.getMenuid()))
            {
                parentMenuID = menu.getParentid();
                break;
            }
        }
        
        if (parentMenuID == null || "".equals(parentMenuID.trim()))
        {
            return "root";
        }
        
        return parentMenuID;
    }
    
    /**
     * 根据当前菜单ID获取其祖先菜单ID
     * 
     * @param currMenuID
     * @return
     * @see 
     */
    public static String getAncestorMenuInfo(HttpServletRequest request,String currMenuID)
    {
    	// 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);

        List allMenu = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        if (allMenu == null || allMenu.size() == 0 
                || currMenuID == null || "".equals(currMenuID.trim()))
        {
            return "";
        }
        
        MenuInfoPO menu = null;
        for (int i = 0; i < allMenu.size(); i++)
        {
            menu = (MenuInfoPO) allMenu.get(i);
            if (currMenuID.equals(menu.getMenuid()))
            {
                if ("root".equalsIgnoreCase(menu.getParentid()))
                {
                    return currMenuID;
                }
                else
                {
                    return getAncestorMenuInfo(request,menu.getParentid());
                }
            }
        }
        
        return "";
    }
    
    /**
     * 根据当前菜单ID获取其链接URL
     * 
     * @param currMenuID
     * @return
     * @see 
     */
    public static String getMenuLink(String currMenuID)
    {
        List allMenu = (List) PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
        
        if (allMenu == null || allMenu.size() == 0 
                || currMenuID == null || "".equals(currMenuID.trim()))
        {
            return "";
        }
        
        MenuInfoPO menu = null;
        for (int i = 0; i < allMenu.size(); i++)
        {
            menu = (MenuInfoPO) allMenu.get(i);
            if (currMenuID.equals(menu.getMenuid()))
            {
                return menu.getGuiobj();
            }
        }
        
        return "";
    }
    
    /**
     * 根据当前菜单ID获取其名称
     * 
     * @param currMenuID
     * @return
     * @see 
     */
    public static String getMenuName(String currMenuID)
    {
        List allMenu = (List) PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
        
        if (allMenu == null || allMenu.size() == 0 
                || currMenuID == null || "".equals(currMenuID.trim()))
        {
            return "";
        }
        
        MenuInfoPO menu = null;
        for (int i = 0; i < allMenu.size(); i++)
        {
            menu = (MenuInfoPO) allMenu.get(i);
            if (currMenuID.equals(menu.getMenuid()))
            {
                return menu.getMenuname();
            }
        }
        
        return "";
    }
    
    /**
     * 根据用户当前的认证方式和功能要求的认证方式，计算用户还需进行的认证方式
     * 
     * @param s1，用户当前的认证方式
     * @param s2，功能要求的认证方式
     * @return
     */
    public static String getAuthorizationCode(String s1, String s2)
    {
        StringBuffer s = new StringBuffer(1024);
        
        if (s1 == null || "".equals(s1.trim()))
        {
            return s2;
        }
        else if (s2 == null || "".equals(s2.trim()))
        {
            return s1;
        }
        else if (s1.length() == s2.length())
        {
            for (int i = 0; i < s1.length(); i++)
            {
                if (s1.charAt(i) >= s2.charAt(i))
                {
                    s.append("0");
                }
                else
                {
                    s.append("1");
                }
            }
        }
        
        return s.toString();
    }
    
    /**
     * 累积计算用户的身份认证方式
     * 
     * @param s1，之前的认证方式
     * @param s2，本次认证方式
     * @return
     */
    public static String getCurrentAuthCode(String s1, String s2)
    {
        StringBuffer s = new StringBuffer(1024);
        
        if (s1 == null || "".equals(s1.trim()))
        {
            return s2;
        }
        else if (s2 == null || "".equals(s2.trim()))
        {
            return s1;
        }
        else if (s1.length() == s2.length())
        {
            for (int i = 0; i < s1.length(); i++)
            {
                if (s1.charAt(i) == '1' || s2.charAt(i) == '1')
                {
                    s.append("1");
                }
                else
                {
                    s.append("0");
                }
            }
        }
        
        return s.toString();
    }
    
    /**
     * 从当前时间倒数lastseq个月，按照pattern格式返回时间
     * 
     * @param pattern
     * @param now
     * @param lastseq
     * @return
     */
    public static String getLastMonth(String pattern, int lastseq)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1 * lastseq);
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(c.getTime());
    }
    
    /**
     * 将包含本月在内的，共monthNum个月的月份信息按照指定格式返回
     * 
     * @param pattern，格式，如yyyyMM
     * @param monthNum，月份个数，即返回结果的数字长度
     * @return
     * @see
     */
    public static String[] getLastMonths(String pattern, int monthNum)
    {
        String[] months = new String[monthNum];
        
        DateFormat df = new SimpleDateFormat(pattern);
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        
        for (int i = 0; i < monthNum; i++)
        {
            if (i != 0)
            {
                c.add(Calendar.MONTH, -1);
            }
            
            months[i] = df.format(c.getTime());
        }
        
        return months;
    }
    
    /**
     * 将分转换成元
     * 
     * @param strMoney
     * @return
     * @see
     */
    public static String fenToYuan(String strMoney)
    {
        if (strMoney == null || "".equals(strMoney.trim()))
        {
            return "";
        }
        
        long l = Long.parseLong(strMoney);
        
        BigDecimal bd1 = new BigDecimal(String.valueOf(l));
        BigDecimal bd2 = new BigDecimal("100");
        
        return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_EVEN).toString();
    }
    
    /**
     * 按照指定格式输出日期
     * 
     * @param date，日期
     * @param srcPattern，日期格式，如yyyyMMdd
     * @param destPattern，输出日期格式，如yyyy-MM-dd
     * @return
     * @see
     */
    public static String formatDate(String date, String srcPattern, String destPattern)
    {
        try
        {
            SimpleDateFormat srcSDF = new SimpleDateFormat(srcPattern);
            
            SimpleDateFormat destSDF = new SimpleDateFormat(destPattern);
            
            return destSDF.format(srcSDF.parse(date));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            
            return date;
        }
    }
    
    /**
     * 生成指定位数的随机码
     * 
     * @param len 随机码的位数
     * @return 随机码
     */
    public static String getRandomNum(int len)
    {
        StringBuffer randomNum = new StringBuffer(len);
        
        // modify begin zKF66389 2015-12-14 V200R003C10LG1201 OR_huawei_201509_857_安全随机数算法改造
//        Random rd = new Random();
//        
//        int i = 0;
//        while (i < len)
//        {
//            randomNum.append(String.valueOf(rd.nextInt(10)));
//            
//            i++;
//        }
		// 随机数生成器
		SecureRandom random = new SecureRandom();
		// 随机数byte数组
		byte[] randomBytes = new byte[10];
		// 生成随机数
		random.nextBytes(randomBytes);
		// 0~9之间随机数
		for (int i = 0; i < len; i++)
		{
			randomNum.append(Math.abs(randomBytes[i] % 10));
		}
		// modify end zKF66389 2015-12-14 V200R003C10LG1201 OR_huawei_201509_857_安全随机数算法改造
        
        return randomNum.toString();
    }
    
    /**
     * 将String型的日期串转成Date
     * 
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String dateStr, String format)
    {
        Date date = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return date;
    }
    
    /**
     * 将日期按照指定格式转成字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        
        return dateFormat.format(date);
    }
    
    /**
     * 
     * @param from
     * @param to
     */
    // public static void copyProperties(Object from, Object to)
    // {
    // Method[] methods = from.getClass().getMethods();
    // Class[] parameterTypes = new Class[1];
    // Object[] parametrs = new Object[1];
    // for (int i = 0; i < methods.length; i++)
    // {
    // String name = methods[i].getName();
    // Method fromMethod = methods[i];
    // if (name.startsWith("get"))
    // {
    // String setName = name.replaceFirst("get", "set");
    // Class returnType = fromMethod.getReturnType();
    // parameterTypes[0] = returnType;
    // try
    // {
    // Method method = to.getClass().getDeclaredMethod(setName, parameterTypes);
    // parametrs[0] = fromMethod.invoke(from, null);
    // if (parametrs[0] != null)
    // {
    // method.invoke(to, parametrs);
    // }
    // }
    // catch (Exception e)
    // {
    // // 丢弃方法拷贝过程中的异常
    // }
    // }
    // }
    // }
    /**
     * 检查字符串是否为空串
     * 
     * @param aStr
     * @return
     */
    public static boolean assertStringNotNull(String aStr)
    {
        boolean lRet = false;
        
        if (null != aStr && !"".equals(aStr.trim()))
        {
            lRet = true;
        }
        
        return lRet;
    }
    
    /**
     * 判断一个字符串是否仅由数字组成
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            char tmp = str.charAt(i);
            // ASCII比较
            if (tmp < '0' || tmp > '9')
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断一个字符串是否是手机号码（只判断11位长的手机号码）
     * 
     * @param str
     * @return
     */
    public static boolean isMobileNumber(String str)
    {
        return isNumber(str) && (str.length() == 11);
    }
    
    // public static String getEntityListStr(Vector ver, int i, int j)
    // {
    // if (j > 1 || j < 0)
    // {
    // j = 1;
    // }
    //        
    // String str = "0.00";
    // if (ver != null && ver.size() > (j - 1))
    // {
    // try
    // {
    // Vector strver = ((CEntityList)ver.get(i)).m_List;
    //                
    // str = ((CEntityString)strver.get(j)).EntityString;
    // }
    // catch (Exception e)
    // {
    //                
    // }
    // }
    // return str;
    //        
    // }
    
    // public static String getCEntityListStr(CEntityList list, int j)
    // {
    //        
    // String str = "";
    // if (list != null)
    // {
    // try
    // {
    //                
    // Vector strver = list.m_List;
    // str = ((CEntityString)strver.get(j)).EntityString;
    // }
    // catch (Exception e)
    // {
    //                
    // }
    // }
    //        
    // return str;
    // }
    
    // public static String getCEntityListStr2(Vector vec, int i, int j)
    // {
    //        
    // String str = "";
    // if (vec != null)
    // {
    // try
    // {
    // CEntityList list = (CEntityList)vec.get(i);
    // str = ((CEntityString)list.m_List.get(j)).EntityString;
    // }
    // catch (Exception e)
    // {
    //                
    // }
    // }
    //        
    // return str;
    // }
    
    // // JAVA的二维数组变为JS的二维数组
    // public static String javaArray2JSArray(String[][] srcData)
    // {
    //        
    // if (srcData == null || srcData.length == 0)
    // {
    // return "new Array(new Array(\"没有相关的数据!\"))";
    // }
    //        
    // StringBuffer buffer = new StringBuffer();
    // buffer.append("new Array( ");
    // String[] row = null;
    // String value = null;
    // for (int i = 0; i < srcData.length; i++)
    // {
    // row = srcData[i];
    // buffer.append("new Array( ");
    // for (int m = 0; m < row.length; m++)
    // {
    // value = row[m].replace('"', '\'');
    // buffer.append("\"" + value + "\",");
    // }
    // buffer.setCharAt(buffer.length() - 1, ')');
    // buffer.append(",");
    // }
    // buffer.setCharAt(buffer.length() - 1, ')');
    // return buffer.toString();
    // }
    
    // public static String divide(String s1, String s2)
    // {
    // BigDecimal bd1 = new BigDecimal(s1);
    // BigDecimal bd2 = new BigDecimal(s2);
    // return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_EVEN).toString();
    // }
    
    // /**
    // * 用&nbsp;替换空格或null值
    // *
    // * @param s
    // * @return
    // */
    // public static String replaceBlank(String s)
    // {
    // return s == null || "".equals(s) ? "&nbsp;" : s;
    // }
    
    /**
     * 金额转换,元转换为分
     * 
     * @param strMoney
     * @return
     */
    public static String yuanToFen(String strMoney)
    {
        long fenStyle = 01;
        if (strMoney == null || "".equals(strMoney.trim()))
        {
            return "0";
        }
        else
        {
            int pos = strMoney.indexOf(".");
            if (pos > 0)
            {
                String r = strMoney.substring(pos + 1);
                String temp = strMoney.replaceAll("\\.", "");
                if (1 == r.length())
                {
                    fenStyle = Long.parseLong(temp) * 10;
                }
                else
                {
                    fenStyle = Long.parseLong(temp);
                }
            }
            else
            {
                fenStyle = Long.parseLong(strMoney) * 100;
            }
            return String.valueOf(fenStyle);
        }
    }
    
    /**
     * 厘转换为元
     * 
     * @param 名称 : 含义
     * @return String
     * @exception
     * @author x60003349 <br>
     *         方法名称： <br>
     *         创建时间：May 29, 2006 5:36:59 PM
     */
    public static String liToYuan(String money)
    {
        String yuanStr;
        if (null == money || "".equals(money))
        {
            return "0.00";
        }
        if ("0".equals(money))
        {
            return "0.00";
        }
        
        //不足一分，按一分计算
        int nMoney = Integer.parseInt(money);
        if (nMoney % 10 != 0)
        {
            nMoney = nMoney + 10 - nMoney % 10;
        }
        
        money = Integer.toString(nMoney);
            
        if (money.length() < 4)
        {
            if (money.length() == 3)
            {
                money = money.substring(0, money.length() - 1);
            }
            else if (money.length() == 2)
            {
                money = "0" + money.substring(0, money.length() - 1);
            }
            else
            {
                money = "00";
            }
            
            yuanStr = "0." + money;
        }
        else
        {
            String beforDot;
            String atferDot;
            
            beforDot = money.substring(0, money.length() - 3);
            atferDot = money.substring(money.length() - 3, money.length() - 1);
            yuanStr = beforDot + "." + atferDot;
        }
        
        return yuanStr;
    }
    
    public static String MD5Encode(String origin)
    {
        String resultString = null;
        try
        {
            //resultString = new String(origin);
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex)
        {
            return origin;
        }
        return resultString;
    }
    
    private static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
        {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};
    
    public static String formatTime(String time)
    {
        if (time == null || "".equals(time.trim()) || time.length() != 14)
        {
            return " ";
        }
        
        StringBuffer retTime = new StringBuffer();
        retTime.append(time.substring(0, 4));
        retTime.append("-");
        retTime.append(time.substring(4, 6));
        retTime.append("-");
        retTime.append(time.substring(6, 8));
        retTime.append(" ");
        retTime.append(time.substring(8, 10));
        retTime.append(":");
        retTime.append(time.substring(10, 12));
        retTime.append(":");
        retTime.append(time.substring(12, 14));
        
        return retTime.toString();
    }
    
    /**
     * 格式化以秒为单位的时间为 时分秒 的格式
     * 
     * @param secondsTime
     * @return
     */
    public static String formatSecondsTime(String secondsTime)
    {
        String result = "Unknown";
        int iTimes = Integer.parseInt((secondsTime != null && secondsTime.trim().length() > 0) ? secondsTime : "0");
        if (iTimes < 60)
        { // 如果时长小于60秒
            result = iTimes + "秒";
        }
        else if (iTimes >= 60 && iTimes < 60 * 60)
        { // 如果时长超过60秒小于60分钟
            int minutes = iTimes / 60; // 求分钟
            int seconds = iTimes % 60; // 求秒
            result = minutes + "分" + seconds + "秒";
        }
        else if (iTimes >= 60 * 60)
        { // 如果时长超过60分钟
            int hours = iTimes / (60 * 60); // 求小时
            int minutes = 0;
            int seconds = 0;
            int leftTimes = iTimes % (60 * 60); // 求小时后剩余的时间
            // 根据求小时后剩余的时间继续求分和秒
            if (leftTimes < 60)
            {
                seconds = leftTimes;
            }
            else if (leftTimes >= 60 && leftTimes < 60 * 60)
            {
                minutes = leftTimes / 60; // 求分
                seconds = leftTimes % 60; // 求秒
            }
            result = hours + "时" + minutes + "分" + seconds + "秒";
            
        }
        return result;
    }
    
    /**
     * 如果value的长度小于size，在左侧补充空格
     * 
     * @param value
     * @param size
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String fillLeftBlank(String value, int size)
    {
        if (null == value)
        {
            value = "";
        }
        
        if (value.getBytes().length >= size)
        {
            return value;
        }
        
        int len = size - value.getBytes().length;
        while (len > 0)
        {
            value = " ".concat(value);
            len--;
        }
        
        return value;
    }
    
    /**
     * 如果value的长度小于size，在右侧补充空格
     * 
     * @param value
     * @param size
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String fillRightBlank(String value, int size)
    {
        if (null == value)
        {
            value = "";
        }
        
        if (value.getBytes().length >= size)
        {
            return value;
        }
        
        int len = size - value.getBytes().length;
        while (len > 0)
        {
            value = value.concat(" ");
            len--;
        }
        
        return value;
    }
    
    /**
     * 格式化电话号码，如果号码不超过11位，则用HTML空格补齐11位
     * 
     * @param servNumber
     * @return
     */
    public static String formatServnumber(String servNumber)
    {
        String result = (servNumber != null) ? servNumber : "";
        StringBuffer sbuf = new StringBuffer(result);
        if (result.trim().length() < 11)
        {
            int size = 11 - result.trim().length();
            for (int i = 0; i < size; i++)
            {
                sbuf.append(" ");
            }
        }
        else if (result.trim().length() > 11)
        {
            // 如果号码大于11位，则换行打印(用空格来控制换行)
            for (int i = 0; i < 49; i++)
            {
                sbuf.append(" ");
            }
        }
        result = sbuf.toString();
        return result;
    }
    
    /**
     * 按传入的开始位置开始截取日期字符串
     * 
     * @param dateTime
     * @param beginIndex
     * @return
     */
    public static String subDateTime(String dateTime, int beginIndex)
    {
        String tmp = (dateTime != null) ? dateTime : "";
        
        String result = "";
        if (tmp.length() > beginIndex)
        {
            result = tmp.substring(beginIndex);
        }
        
        return result.replace(' ', '-');
    }
    
	/**
	 * 补充指定大小的空格，但是如果不超过maxSize则不补充
	 * 
	 * @param value
	 * @param size
	 * @param maxSize
	 * @return
	 */
	public static String fillBlank(String value, int size, int maxSize) {
		String result = value != null ? value : "";
		if (result.trim().length() <= maxSize)
			return result;
		int fillSize = size - result.trim().length();
		StringBuffer sbuf = new StringBuffer(result);
		for (int i = 0; i < fillSize; i++) {
		    sbuf.append(" ");
		}
		result = sbuf.toString();
		return result;
	}
    
    /**
     * 在value的右侧补充size个空格
     * 
     * @param value
     * @param size
     * @return
     */
    public static String fillBlank(String value, int size)
    {
        String result = (value != null) ? value : "";
        
        int fillSize = size - result.trim().length();
        StringBuffer sbuf = new StringBuffer(result);
        for (int i = 0; i < fillSize; i++)
        {
            sbuf.append(" ");
        }
        result = sbuf.toString();
        return result;
    }
    
    /**
     * 如果时长长短不一，则以空格补齐。 只控制到4位数的时长
     * 
     * @param secondsTime
     * @return
     */
    public static String fillBlank(String secondsTime)
    {
        String result = (secondsTime != null) ? secondsTime : "";
        
        int size = 4 - result.trim().length();
        StringBuffer sbuf = new StringBuffer(result);
        for (int i = 0; i < size; i++)
        {
            sbuf.append(" ");
        }
        result = sbuf.toString();
        return result;
    }
    
    //判断字符串是否为空
    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str.trim());
    }
    
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
    public static boolean isNotEmpty(String str)
    {
        return null != str && !"".equals(str.trim());
    }
    
    /**
     * 取得当前时间(格式:20080109151259)
     * 
     * 
     * @return
     */
    public static String getCurrentTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar theday = Calendar.getInstance();
        String currTime = df.format(theday.getTime());
        
        return currTime;
    }
    
    /**
     * 为当前日期增加指定的秒
     * 
     * @param baseDate 基础日期
     * @param addNum 增加的秒
     * @return String 增加秒后yyyyMMddHHmmss格式的日期
     * @throws ParseException 日期格式异常
     */
    public static String addDate(String baseDate, int addNum) throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = df.parse(baseDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, addNum);
        return df.format(cal.getTime());
    }
    
	/**
	 * 转换比特为字节(不满1024默认为1K)
	 * 
	 * @param sByte
	 * @return
	 */
	public static String calByteToK(String sByte) {
		int iByte = Integer
				.parseInt((sByte != null && sByte.trim().length() > 0) ? sByte
						: "0");
		int iResult = (iByte % 1024 != 0) ? iByte / 1024 + 1 : iByte / 1024;
		return String.valueOf(iResult);
	}
	
	/**
	 * 通过crset名字获取改crset中名字为key的值
	 * @param crset
	 * @param key
	 * @return
	 */
	public static String getBillValueByKey(CRSet crset, String key) 
	{
        if (crset == null || (key == null || "".equals(key.trim())))
        {
            return "";
        }
        
        int cnt = crset.GetRowCount();
        
        for(int i=0; i<cnt; i++){
        	if(key.equals(crset.GetValue(i, 2)))
        	{
        		try
        		{
        			if(new Double(crset.GetValue(i, 3)).doubleValue() == 0){
        			    return "";
        			}
        			else
        			{
        				return crset.GetValue(i, 3);
        			}
        		}
        		catch(NumberFormatException ex)
        		{
            		return crset.GetValue(i, 3);
        		}
        	}
        }
        return "";
	}
	
	/**
     * 格式化字符串
     * 
     * @param resource 原字符串
     * @param align left or right，长度不够时左侧补或者右侧补
     * @param sign 长度不够时的填充值
     * @param count 指定长度
     * @return
     * @remark create g00140516 2011/12/09 R003C11L12n01 详单查询实现socket协议
     */
    public static String formatStr(String resource, String align, String sign, int count)
    {       
        if (null == resource)
        {
            resource = "";
        }
        
        if (resource.length() >= count)
        {
            return resource;
        }
        
        StringBuffer str = new StringBuffer(1024);
        for (int i = 0; i < count - resource.length(); i++)
        {
            str.append(sign);
        }
        
        if ("left".equalsIgnoreCase(align))
        {
            str.append(resource);
        }
        else if ("right".equalsIgnoreCase(align))
        {
            str.insert(0, resource);
        }
        
        return str.toString();        
    }
    
    /**
     * 将秒转换为XX分XX秒
     * 
     * @param seconds
     * @return
     * @see 
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     */
    public static String formatSeconds(String seconds)
    {
        if (null == seconds || "".equals(seconds.trim()))
        {
            return "0";
        }
        
        // 非整数
        if (-1 != seconds.indexOf("."))
        {
            return seconds;
        }
        
        int nMin = Integer.parseInt(seconds) / 60;
        int nSec = Integer.parseInt(seconds) % 60;
        
        String info = "";
        if (0 < nMin)
        {
            info = nMin + "分";
        }
        
        info += (nSec + "秒");
        
        return info;
    }
    
    /**
     * 将K转换为XX(M)XX(K)
     * 
     * @param kBytes
     * @return
     * @see 
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     */
    public static String calKToM(String kBytes) 
    {
        String info = "";
        
        if (null == kBytes || "".equals(kBytes.trim()))
        {
            info = "0(K)";
        }
        else
        {
            int mBytes = Integer.parseInt(kBytes) / 1024;
            
            if (0 < mBytes)
            {
                info = mBytes + "(M)";
            }
            
            info += (Integer.parseInt(kBytes) % 1024 + "(K)");
        }
        
        return info;
    }
    
    /**
     * 将秒转换为XX:XX:XX
     * 
     * @param seconds
     * @return
     * @see 
     * @remark create g00140516 2012/02/16 R003C12L02n01 OR_huawei_201112_953
     */
    public static String formatSecondsSD(String seconds)
    {
        if (null == seconds || "".equals(seconds.trim()))
        {
            return "0";
        }
        
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(seconds);
        
        // 非整数
        if (!m.matches())
        {
            return seconds;
        }
        
        int nSec = Integer.parseInt(seconds);
        
        int nHour = nSec / 3600;
        int nMin = (nSec - nHour * 3600) / 60;
        nSec = nSec % 60;
        
        String info = "";        
        if (10 < nHour)
        {
            info = nHour + ":";
        }
        else 
        {
            info = "0" + nHour + ":";
        }
        
        if (10 < nMin)
        {
            info += (nMin + ":");
        }
        else
        {
            info += ("0" + nMin + ":");
        }
        
        if (10 < nSec)
        {
            info += nSec;
        }
        else
        {
            info += ("0" + nSec);
        }
        
        return info;
    }
    
    /**
     * 将B转换为XXMBXXKBXXB
     * 
     * @param bytes
     * @return
     * @see 
     * @remark create g00140516 2012/02/16 R003C12L02n01 OR_huawei_201112_953
     */
    public static String calBToK(String strBytes) 
    {
        if (null == strBytes || "".equals(strBytes.trim()))
        {
            return "0B";
        }
        
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(strBytes);
        
        // 非整数
        if (!m.matches())
        {
            return strBytes;
        }
        
        String info = "";
        
        int bytes = Integer.parseInt(strBytes);
        
        int mBytes = bytes / (1024 * 1024);
        int kBytes = (bytes - mBytes * 1024 * 1024) / 1024;
        bytes = bytes % 1024;
        
        if (0 < mBytes)
        {
            info = mBytes + "MB";
        }
        
        if (0 < kBytes)
        {
            info += (kBytes + "KB");
        }
        
        info += (bytes + "B");
        
        return info;
    }
    
    /**
     * 如果value的长度小于size，在左侧补充空格
     * 
     * @param value 原字符串
     * @param minSize 最小长度
     * @param blank " "或&nbsp;
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public static String fillLeftBlank(String value, int minSize, String blank)
    {
        if (null == value)
        {
            value = "";
        }
        
        if (value.getBytes().length >= minSize)
        {
            return value;
        }
        
        int len = minSize - value.getBytes().length;
        while (len > 0)
        {
            value = blank.concat(value);
            len--;
        }
        
        return value;
    }
    
    /**
     * 计算两日期之间的年数月数，不满一个月的舍去
     * 
     * @param startDate 格式为yyyyMMdd
     * @param endDate 格式为yyyyMMdd
     * @return
     * @remark create lKF60882 2012-6-6 
     */
    public static HashMap<String, Integer> getYearsMonths(Date startDate, Date endDate)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int iMonth = 0;
        int flag = 0; // 标志是否存在非整月，这部分要舍去
        try
        {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDate);
            
            if (endCalendar.equals(startCalendar))
            {
                map.put("years", 0);
                map.put("months", 0);
                return map;
            }
            if (startCalendar.after(endCalendar))
            {
                Calendar temp = startCalendar;
                startCalendar = endCalendar;
                endCalendar = temp;
            }
            
            if (endCalendar.get(Calendar.DAY_OF_MONTH) < startCalendar.get(Calendar.DAY_OF_MONTH))
            {
                flag = 1;
            }
            
            if (endCalendar.get(Calendar.YEAR) > startCalendar.get(Calendar.YEAR))
            {
                iMonth = ((endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12
                        + endCalendar.get(Calendar.MONTH) - flag)
                        - startCalendar.get(Calendar.MONTH);
            }
            else
            {
                iMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH) - flag;
            }
            
            int years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            
            // 月数>=12才算年
            if(12 <= iMonth)
            {
                iMonth = iMonth - years * 12;
            }
            else
            {
                years = 0;
            }
            map.put("years", years);
            map.put("months", iMonth);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }
    
    /** 
     * 获得随机数字符串
     *
     * @param iLen 随机数的长度
     * @param iType 随机数的类型，0:表示仅获得数字随机数；1：表示仅获得字符随机数；2：表示获得数字字符混合随机数 
     *  
     */ 
    public static final String createRandom(int iLen, int iType)
    { 
        StringBuffer buffer = new StringBuffer(""); 
        Random rnd = new Random();
        
        // 默认8位
        if (iLen < 0)
        { 
           iLen = 8; 
        }
        
        // 默认数字随机数
        if (iType > 2 || iType < 0)
        { 
            iType = 0; 
        } 
        
        switch (iType)
        { 
            case 0: 
                for (int iLoop = 0; iLoop < iLen; iLoop++)
                { 
                    buffer.append(Integer.toString(rnd.nextInt(10)));
                } 
                break; 
            case 1: 
                for (int iLoop = 0; iLoop < iLen; iLoop++)
                { 
                    buffer.append(Integer.toString(35 - rnd.nextInt(10), 36));
                } 
                break; 
            case 2: 
                for (int iLoop = 0; iLoop < iLen; iLoop++)
                { 
                    buffer.append(Integer.toString(rnd.nextInt(36), 36));
                } 
               break; 
        } 
        
        return buffer.toString(); 
    }
    
    /**
     * 取得当前时间(格式:20080109151259)
     * @param patternType 时间格式
     * 
     * @return
     */
    public static String getCurrentTime(String patternType)
    {
        SimpleDateFormat df = new SimpleDateFormat(patternType);
        
        Calendar theday = Calendar.getInstance();
        
        String currTime = df.format(theday.getTime());
        
        return currTime;
    }
    
    /**
     * 计算可选认证方式
     * 如果用户已认证方式与菜单对应的认证方式有交集，则返回0000，即不再需要认证；如果没有交集，直接返回菜单对应的认证方式，但有一种情况例外：宁夏的农广星用户不能接收短信，所以需要特殊判断。
     * @param customerSimp 用户信息
     * @param currMenuID 当前菜单
     * @param menuAuthCode 菜单对应的认证方式
     * @return 可选认证方式
     * @remark create g00140516 2013/02/20 V200R003C13L02n0 OR_NX_201302_600
     */
    public static String getIntersectionCode(NserCustomerSimp customerSimp, String currMenuID, String menuAuthCode)
    {
    	// 用户尚未登录或者认证方式为0000，直接返回菜单对应的认证方式
    	if (customerSimp == null || "0000".equals(customerSimp.getLoginMark()))
    	{
    		return menuAuthCode;
    	}
    	
    	String loginMark = customerSimp.getLoginMark();
    	if (loginMark.length() == menuAuthCode.length())
    	{
    		for (int i = 0; i < loginMark.length(); i++)
    		{
    			// 用户已认证方式与菜单对应的认证方式有交集，返回0000
    			if (loginMark.charAt(i) == menuAuthCode.charAt(i) && (menuAuthCode.charAt(i) == '1'))
    			{
    				return "0000";
    			}
    		}
    	}
    	
    	// 宁夏的农广星用户不能接收短信
    	String menuId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_MENU);
        String ProId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_PRO);
        
        //获取当前用户的产品id
        String usrProId = customerSimp.getProductID();
        
        // 去掉随机密码验证
    	if (menuAuthCode.charAt(1) == '1' && filterMenu(menuId, currMenuID) && filterProd(ProId, usrProId))
    	{
    		menuAuthCode = menuAuthCode.substring(0, 1) + "0" + menuAuthCode.substring(2);
    	}
        
    	return menuAuthCode;
    }
    
    /**
     * 判断当前菜单需不需要过滤
     * @param str 参数表中配置的需要过滤的菜单串
     * @param menuid 当前菜单
     * @return true：需要过滤
     * @remark create m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
     */
    public static boolean filterMenu(String str, String menuid)
    {
    	boolean flag = false;
    	
    	if (str == null || "".equals(str.trim()) || menuid == null || "".equals(menuid.trim()))
    	{
    		return flag;
    	}
    	
    	String[] menus = str.split(",");
    	for (int i = 0; i < menus.length; i++)
    	{
    		if (menus[i].equalsIgnoreCase(menuid))
    		{
    			flag = true;
    			break;
    		}
    	}
    	
    	return flag;
    }
    
    /**
     * 判断当前产品需不需要过滤
     * @param str 参数表中需要过滤的产品id
     * @param prodid 当前产品id
     * @return true：当前产品需要过滤 false：当前产品不需要过滤
     * @remark create m00227318 2012/08/20 V200R003C12L08n0 OR_NX_201207_1184 
     */
    public static boolean filterProd(String str, String prodid)
    {
    	boolean flag = false;
    	
    	if (str == null || "".equals(str.trim()) || prodid == null || "".equals(prodid.trim()))
    	{
    		return flag;
    	}
    	
    	String[] prods=str.split(",");
    	for (int i = 0; i < prods.length; i++)
    	{
    		if (prods[i].equalsIgnoreCase(prodid))
    		{
    			flag = true;
    			break;
    		}
    	}
    	
    	return flag;
    }
    
    /**
     * 将客户名称模糊化
     * 两个或三个字最多保留1个字，大于三个字的最多保留2个字,其余字用*代替
     * @param custname 参数表中需要模糊化的客户名称
     * @return custname
     * @remark create yWX163692 2013/11/21 OR_NX_201310_1186 客户界面信息模糊化展示 
     */
    public static String getVagueName(String custname)
    {
        if (null == custname)
        {
            custname = "";
        }
        else
        {
            custname = custname.trim();
        }
        
        int len = custname.length();
        if (len > 1)
        {
            String subsname2 = "";
            if (len > 3)
            {
                subsname2 = custname.substring(0, 2);
            }
            else
            {
                subsname2 = custname.substring(0, 1);
            }
            
            StringBuffer buffer = new StringBuffer(subsname2);
            for (int i = 0; i < len - subsname2.length(); i++)
            {
                buffer.append("*");
            }
            
            custname = buffer.toString();
        }
        
        return custname;
    }
    
    /**
     * 客户信息模糊化
     * create by lwx439898 2017-06-26 OR_SD_201706_196_自助终端部分菜单“客户名称”模糊化改造
     */
    public static String getUserLastName(String fullName)
    {
        // 当全名不存在为空时,返回"未知用户"
        if (StringUtils.isBlank(fullName) || "".equals(fullName.trim()))
        {
            fullName = "未知用户";
        }
        // 如果客户名称多于2个字 ：张*峰
        else if (fullName.length() > 2)
        {
            StringBuffer lastName = new StringBuffer(fullName.substring(0, 1));
            for (int i = 1; i < fullName.length() - 1; i++)
            {
                lastName.append("*");
            }
            fullName = lastName.toString() + fullName.substring(fullName.length() - 1);
        }
        // 如果客户名称是2个字 ：张*
        else if (fullName.length() == 2)
        {
            fullName = fullName.substring(0, 1) + "*";
        }
        return fullName;
    }
 
    /**
     * 获取一个年月的天数数组
     * @param date YYYYMM 或者YYYYMMdd
     * @return
     */
    public static List<String> getMonDays(String date) 
    {
        List<String> monDays = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));  
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) -1);  
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);    
        int[] days = new int[maxDay];    
        for(int i = 1; i <= maxDay; i++)
        {   
            days[i-1] = i;
            monDays.add(String.valueOf(days[i-1]));
        }    
        
       return monDays;
    }
    
    /**
     * 获取一个年月的月
     * @param date YYYYMM 或者YYYYMMdd
     * @return
     */
    public static String getMonth(String date) 
    {
        int month = Integer.parseInt(date.substring(4, 6));
        return String.valueOf(month);
    }
    
    /**
     * 一个数字返回一个占bits位的数字
     * @param date 或者 dd
     * @return
     */
    public static String getDay(String date, int bits) 
    {
        StringBuffer dateStr = new StringBuffer(date);
        if(bits > date.length())
        {
            for(int i = 0; i < bits-date.length(); i++)
            {
                dateStr.append("0");
            }
        }
        return dateStr.toString();
    }
    
    /**
     * 获取用户品牌名称
     * @param brand 品牌 （一个大写字母的情况）
     * @return
     */
    public static String getNameByBrandLetter(String brand)
    {
        String brandName = "";
        if("M".equals(brand))
        {
            brandName = "动感地带";
        }
        else if("G".equals(brand))
        {
            brandName = "全球通";
        }
        else if("E".equals(brand))
        {
            brandName = "神州行";
        }
        return brandName;
    }
    
    /**
     * 获取用户品牌名称
     * @param brand 品牌 （一个全拼单词的情况）
     * @return
     */
    public static String getNameByBrandWord(String brand)
    {
        String brandName = "";
        if("BrandMzone".equals(brand))
        {
            brandName = "动感地带";
        }
        else if("BrandGotone".equals(brand))
        {
            brandName = "全球通";
        }
        else
        {
            brandName = "神州行";
        }
        return brandName;
    }
    
    /** 
     * 生成ReturnWrap
     * 
     * @param status 状态
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create by zKF69263 2014/07/24 OR_huawei_201407_369 代码重复率_自助终端
     */
    public static ReturnWrap getReturnWrap(int status)
    {
        return getReturnWrap(status, "");
    }
    
    /** 
     * 生成ReturnWrap
     * 
     * @param status 状态
     * @param message 返回信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: create by zKF69263 2014/07/24 OR_huawei_201407_369 代码重复率_自助终端
     */
    public static ReturnWrap getReturnWrap(int status, String message)
    {
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(status);
        rw.setReturnMsg(message);
        return rw;
    }
    
    /**
     * <从缓存中获取sh_param_value中的参数>
     * <功能详细描述>
     * @param paramId
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-8-6 10:26:04 OR_SD_201408_20_ISSS平台对接自助终端的需求 
     */
    public static String getParamValue(String paramKey)
    {
    	return (String) PublicCache.getInstance().getCachedData(paramKey);
    }
    
    /**
     * <从缓存中获取sh_param_value中的参数>
     * <若缓存中没有该参数，或该参数值为空，则返回默认值>
     * @param paramKey
     * @param defaultValue 该参数的默认值
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-20 15:56:03 OR_NX_201501_1030 跨省异地缴费
     */
    public static String getParamValue(String paramKey, String defaultValue)
    {
        String paramValue = (String) PublicCache.getInstance().getCachedData(paramKey);
        
        if(StringUtils.isBlank(paramValue))
        {
            paramValue = StringUtils.isEmpty(defaultValue) ? "" : defaultValue;
        }
        return paramValue;
    }
    
    /**
     * 依据dictId和groupId获取字典表信息
     * 
     * @param dictID String
     * @param groupID String
     * @return
     * @remark create by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static String getDictNameById(String dictId, String groupId)
    {
        DictItemPO dictItemPO = getDictItemById(dictId, groupId);
        
        if (null == dictItemPO)
        {
            return "";
        }
        
        return dictItemPO.getDictname();
    }
    
    /**
     * 根据dictid和groupid获取字典表信息，如果没有的话返回默认值defaultVal
     * 
     * @param dictId
     * @param groupId
     * @param defaultVal
     * @return
     * @remark create by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static String getDictNameById(String dictId, String groupId, String defaultVal)
    {
        DictItemPO dictItemPO = getDictItemById(dictId, groupId);
        if (null == dictItemPO)
        {
            return defaultVal;
        }
        return dictItemPO.getDictname();
    }
    
    /**
     * 依据dictId和groupId获取字典表信息
     * 
     * @param dictId
     * @param groupId
     * @return
    * @remark create by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static DictItemPO getDictItemById(String dictId, String groupId)
    {
        if (null == dictId || null == groupId)
        {
            return null;
        }
        
        List<DictItemPO> itemList = getDictItems(groupId);
        if (null != itemList)
        {
            for (DictItemPO dictItem : itemList)
            {
                if (dictId.equals(dictItem.getDictid()))
                {
                    logger.info("groupId为" + groupId + ",dictId为" + dictId + ",取得参数值为：" + dictItem);
                    
                    return dictItem;
                }
            }
        }
        
        logger.info("groupId为" + groupId + ",dictId为" + dictId + ",取得参数值为：null");
        return null;
    }
    
    /**
     * 依据groupId获取字典表信息
     * 
     * @param groupId String
     * @return List<DictItemPO>
     * @remark create by lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    @SuppressWarnings("unchecked")
    public static List<DictItemPO> getDictItems(String groupId)
    {
        if (null == groupId)
        {
            return new ArrayList<DictItemPO>();
        }
        List<DictItemPO> itemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(groupId);
        if (itemList == null)
        {
            itemList = new ArrayList<DictItemPO>();
        }
        return itemList;
    }
    
    /**
     * 模糊化名称
     * 三个字以内的用户名（包含三个字）只模糊掉姓保留名字，三个字以上的用户名直接模糊掉前两个字。
     * @param name 参数表中需要模糊化的名称
     * @return String
     * @remark create by hWX5316476 2014-8-25 V200R003C10LG0801 OR_NX_201407_722_宁夏_关于客户信息模糊化的补充需求
     */
    public static String blurName(String name)
    {
        // 名称为空或去除两边空格后为空串的直接返回""
        if(StringUtils.isEmpty(name) || "".equals(name.trim()))
        {
            return "";
        }
        
        // 去掉名称两边空格
        name = name.trim();
        
        int len = name.length();
        String subsname;
        
        // 三个字以上的用户名直接模糊掉前两个字。
        if(3 < len)
        {
            subsname = "**" + name.substring(2, len);
        }
        // 三个字以内的用户名（包含三个字）只模糊掉姓保留名字
        else if(1 < len )
        {
            subsname = "*" + name.substring(1, len);
        }
        // 一个字保留，不做模糊化
        else
        {
            subsname = name;
        }

        return subsname;
    }
    
    /**
     * 拼凑字符串数组 将字符串格式为"SD.LA.07.xxx"转换为"'SD','SD.LA','SD.LA.07','SD.LA.07.xxx'"的字符串
     * 
     * @param orgidString
     * @return
     */
    public static String splitOrgid(String orgidString)
    {
        String[] shuzu = orgidString.split("\\.");
        
        //modify begin g00140516 2011/09/30 重构
        StringBuffer org = null;
        
        StringBuffer orgids = new StringBuffer(1024);
        
        for (int i = 1; i <= shuzu.length; i++)
        {
            org = new StringBuffer(1024);
            
            for (int j = 0; j < i; j++)
            {
                org.append(shuzu[j]).append(".");
            }
            
            orgids.append("'").append(org.substring(0, org.length() - 1)).append("',");
        }
        //modify end g00140516 2011/09/30 重构
        
        return orgids.substring(0, orgids.length() - 1);
    }
    
    /**
     * 将厘转换成分（不带小数点）
     * 
     * @param strMoney 金额
     * @param roundMode 转换模式 BigDecimal.ROUND_UP，大于0就入1，BigDecimal.ROUND_HALF_EVEN 四舍五入 等
     * @return
     * @see
     */
    public static String liToFen(String strMoney, int roundMode)
    {
        if (strMoney == null || "".equals(strMoney.trim()))
        {
            return "0";
        }
        long l = Long.parseLong(strMoney);
        BigDecimal bd1 = new BigDecimal(String.valueOf(l));
        BigDecimal bd2 = new BigDecimal("10");
        return bd1.divide(bd2, 0, roundMode).toString();
    }

    /**
     * 将分转换成厘
     * 
     * @param strMoney
     * @return
     * @see
     * @remark create by sWX219697 2015-4-8 18:10:50 OR_NX_201501_1030 跨省异地缴费
     */
    public static String fenToLi(String strMoney)
    {
    	String li = "0";
    	
        if (StringUtils.isNotBlank(strMoney))
        {
        	BigDecimal bd1 = new BigDecimal(strMoney);
            BigDecimal bd2 = new BigDecimal("10");
            li = bd1.multiply(bd2).toString();
        }
        
        return li;
    }
    
    /**
     * <关闭流>
     * <功能详细描述>
     * @param closeable
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-3-31 15:58:44 OR_NX_201501_1030 跨省异地缴费
     */
    public static void closeStream(Closeable closeable)
    {
        if (null != closeable)
        {
            try
            {
                closeable.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * <湖北姓名模糊化>
     * <姓名：两个字，模糊第一个；超过两个字，仅保留最后两个前面的均模糊化>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String blurNameHUB(String custName)
    {
    	String blurName = "";
    	
    	if(StringUtils.isNotEmpty(custName))
    	{
    		int len = custName.length();
    		
    		if(len == 2)
    		{
    			blurName = "*" + custName.substring(1, 2);
    		}
    		
    		if(len > 2)
    		{
    			blurName = (len == 3 ? "*" : "**") + custName.substring(len - 2, len);
    		}
    	}
    	
    	return blurName;
    }
    
    /**
     * 重复交费判断（宁夏用）
     * 
     * @param termInfo 终端机信息
     * @return true，未重复；false：重复
     * @since 
     * @remark create hWX5316476 2015-4-15 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知 
     */
    public static boolean checkCashFeeNX(TerminalInfoPO termInfo, String terminalSeq, String servnumber, String tMoney)
    {        
        String cashChargeFlag = getParamValue("SH_CASHCHARGE_SEQLOG_FLAG");
        if ("1".equalsIgnoreCase(cashChargeFlag))
        {
            String seq = termInfo.getTermid().concat("_").concat(terminalSeq).concat("_").concat(servnumber).concat("_").concat(tMoney);
                        
            synchronized(RefreshCacheNX.cashChargeRecords)
            {
                if (RefreshCacheNX.cashChargeRecords.containsKey(seq))
                {
                    String tmpErrorMsg = "重复缴费:手机号[".concat(servnumber).concat("], 投币金额[").concat(tMoney)
                            .concat("]元, 操作员[").concat(termInfo.getOperid()).concat("], 流水号[").concat(terminalSeq).concat("]");
                    
                    logger.error(tmpErrorMsg);

                    return false;
                }
                else
                {
                    logger.info("向缓存中插入缴费信息：" + seq);
                    
                    RefreshCacheNX.cashChargeRecords.put(seq, DateUtilHub.curOnlyTime());
                }
            }
        }
        return true;
    }
    
    /**
     * 获取可用缴费方式
     * @param groupID 终端组
     * @return
     */
    public static List qryUsablePayTypes(String termGroupId, String curMenuId)
    {
        List<MenuInfoPO> menuList = null;
        
        if (termGroupId != null && !"".equals(termGroupId))
        {
            menuList = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(termGroupId);
        }
        
        // 获取可选缴费方式
        List usableTypes = getChildrenMenuInfo(menuList, curMenuId, "");

        return usableTypes;
    }
    
    /**
     * 格式化数字字符串
     * 例如：00010，格式化为10
     * <功能详细描述>
     * @param numStr
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-4-17 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知 
     */
    public static String formatNumberStr(String numStr)
    {
        String formattedNumStr = "0";
        if(StringUtils.isNotBlank(numStr))
        {
            BigDecimal bd1 = new BigDecimal(numStr);
            formattedNumStr = bd1.toString();
        }
        return formattedNumStr;
    }
    
    /** 替换错误信息中的特殊字符(湖北)
     * <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by qWX279398 2015-8-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
     */
    public static String errorMessage(String message)
    {
        // 入参为null或空字符串，返回""
        if(message == null || "".equals(message))
        {
            return "";
        }
        
        // 替换错误信息中的特殊字符开关 0:关闭 1:开启
        String errorFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERROR_FLAG);
        
        // 开关未开启或参数表没有配置（0或""、null）
        if (!"1".equals(errorFlag))
        {
            return message;   
        }
        else
        {
            // 特殊字符替换字典项
            List<DictItemPO> dictItemList = ((SelfSvcService)ApplicationContextUtil.getBean("selfSvcService")).getDictItemByGrp("errorConvertGroup");
            for(DictItemPO dictItemPO : dictItemList)
            {
                if (message.contains(dictItemPO.getDictid()))
                {
                    return dictItemPO.getDictname();
                }
            }
        }
        
        return message;
    }
    
    /** 当前日期与传入日期之差(单位:毫秒)
     * <功能详细描述>
     * @param lastLoginTime
     * @return
     * @see [类、类#方法、类#成员]
     * @remark created by by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
     */
    public static long compareCurrTime(String lastLoginTime)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期
        Calendar calendar = Calendar.getInstance();// 获取日期
        String currLoginTime = simpleDateFormat.format(calendar.getTime());// 当前时间
        
        long currLoginDate = 0;// 当前时间
        long lastLoginDate = 0;// 传入时间
        
        try
        {
            currLoginDate = simpleDateFormat.parse(currLoginTime).getTime();
            lastLoginDate = simpleDateFormat.parse(lastLoginTime).getTime();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return currLoginDate-lastLoginDate;
    }
    
    /** 将受理时长插入数据库
     * <功能详细描述>
     * @param busiAcceptTimePO
     * @see [类、类#方法、类#成员]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 线程插入受理时长表日志
     */
    public static void insertBusiAcceptTime(final BusiAcceptTimePO busiAcceptTimePO)
    {
        final ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool.execute(new Runnable()
        {
            public void run()
            {
                BusiAcceptTimeServiceImpl busiAcceptTimeServiceImpl = (BusiAcceptTimeServiceImpl)ApplicationContextUtil.getBean("busiAcceptTimeServiceImpl");
                try
                {
                    busiAcceptTimeServiceImpl.insertBusiAcceptTime(busiAcceptTimePO);
                }
                catch (SQLException e)
                {
                    threadPool.shutdown();
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * 生成长度为length的随机数字码
     * 生成长度为length的随机数字码用来验证信息
     * 
     * @param length 验证码的长度
     * @return 结果集，返回String类型的验证码
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017-1-3 OR_HUB_201609_640 自助终端用户登录验证方式
     */
    public static String createRandomCode(int length)
    {
    	StringBuffer sf = new StringBuffer();
    	SecureRandom sr = new SecureRandom();
    	for (int i = 0; i < length; i ++) 
    	{
    		sf.append(sr.nextInt(10));
    	}
    	return sf.toString();
    }
    
    /**
     * 生成图片验证码干扰线
     * @param g
     * @param nums 干扰线条数
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017-1-6 OR_HUB_201609_640 自助终端用户登录验证方式
     */
    public static void drawRandomLines(Graphics2D g,int nums)
	{
    	SecureRandom sr = new SecureRandom();
    	
    	int width = 60;
    	int height = 20;
    	
		for (int i = 0; i < nums; i ++) 
		{
			int x1 = sr.nextInt(width);
			int y1 = sr.nextInt(height);
			int x2 = sr.nextInt(12);
			int y2 = sr.nextInt(12);
			
			g.drawLine(x1, y1, x2, y2);
		}
	}
    
    /**
     * 把随机码写到图片上返回，并画上干扰线
     * @param randomPassword //随机码
     * @return 结果集，返回验证码图片
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017-1-3 OR_HUB_201609_640 自助终端用户登录验证方式
     */
    public static BufferedImage createBufferedImage (String randomPassword) 
    {
    	
    	int width = 60;
    	int height = 20;
    	
    	BufferedImage big = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	
    	Graphics2D g_2d = big.createGraphics();   	   	
        g_2d.clearRect(0, 0, width, height);
        g_2d.setColor(Color.white);
        g_2d.fillRect(0, 0, width, 20);
        g_2d.setColor(Color.BLUE);
        g_2d.setFont(new Font("", 1, 15));
        g_2d.drawString(randomPassword, 2, 15);
        //生成干扰线4条
        drawRandomLines(g_2d,4);
        g_2d.dispose();
        return big;
    }
    
    /**
     * xml格式转为json
     * 
     * @param xmlInfo
     * @return
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static String convertXmlToJson(String xmlInfo)
    {
        logger.info("XmlToJson,转换前xml：" + xmlInfo);
        
        XMLSerializer xmlSerializer = new XMLSerializer();
        
        // 设置不添加前缀
        xmlSerializer.setTypeHintsCompatibility(false);
        xmlSerializer.setTypeHintsEnabled(false);
        
        // 忽略命名空间
        xmlSerializer.setSkipNamespaces(true);
        
        JSON json = xmlSerializer.read(xmlInfo);
        
        String jsonStr = json.toString();
        
        logger.info("XmlToJson,转换后json：" + jsonStr);
        
        return jsonStr;
    }
    
    /**
     * json格式转为xml
     * 
     * @param jsonStr
     * @param processCode 接口processcode，用于区分是否需要对json进行特殊处理
     * @return
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static String convertJsonToXml(String jsonStr, String processCode)
    {
        logger.info("JsonToXml,转换前json：" + jsonStr);
        
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsCompatibility(false);
        xmlSerializer.setTypeHintsEnabled(false);
        
        if(null != processCode)
        {
            // 将报文中的result节点改成content节点，res_code改成resp_result，res_desc改成resp_desc
            jsonStr = jsonStr.replace("\"result\"", "\"message_content\"").replace("\"res_code\"", "\"retcode\"").replace("\"res_desc\"", "\"retmsg\"");
            
            // 设置根节点为result
            xmlSerializer.setRootName("message_body");
            
            // 设置接口类型为list对应的返回节点名称
            DictItemPO dict = CommonUtil.getDictItemById(processCode, "openEbusInvokeList");
            if(null != dict.getDescription())
            {
                String[] expandableProperties = dict.getDescription().split(",");
                xmlSerializer.setExpandableProperties(expandableProperties);
            }
        }
        JSON json = JSONSerializer.toJSON(jsonStr);
        
        String xml = xmlSerializer.write(json);
        
        logger.info("JsonToXml,转换后xml：" + xml);
        
        return xml;
    }
    
    /**
     * 是否调用能力开放平台
     * 
     * @param processCode 接口processCode
     * @return
     * @remark lKF60882 OR_huawei_201704_4_【山东移动接口迁移专题】-自助终端接口调用配套工具类改造 2017-4-1
     */
    public static boolean isInvokeOpenEbus(String processCode)
    {
        // 总开关
        String switchConf = CommonUtil.getDictNameById("isInvoke", "openEbusConf");
        
        // 具体的接口配置
        DictItemPO dict = CommonUtil.getDictItemById(processCode, "openEbusInvokeList");
        
        // 总开关开启，且相关接口存在配置，则走openEbus，返回true。
        return ("true".equalsIgnoreCase(switchConf)) && (null != dict);
    }
    
    /**
     * 将tagset中的key都转为小写
     * 
     * @param srcTag
     * @return
     * @remark lKF60882 2017-4-14
     */
    public static CTagSet lowerTagKey(CTagSet srcTag)
    {
        CTagSet desTag = new CTagSet();
        
        Iterator it = srcTag.iterator();
        while (it.hasNext())
        {
            String key = (String)it.next();
            desTag.put(key.toLowerCase(Locale.ENGLISH), srcTag.get(key));
        }
        
        return desTag;
    }
    
    /**
     * 将单列CRSet转换成CTagSet
     * @param attrs CRSet所有属性顺序数组
     * @param rw
     * @remark lWX298507 OR_huawei_201704_376_【山东移动接口迁移专题】-自助终端业务办理1 2017-5-8
     */
    public static void cRSetToTagSet(String[] attrs, ReturnWrap rw)
    {
        CRSet crSet = (CRSet)rw.getReturnObject();
        if (crSet == null || crSet.GetRowCount() <= 0)
        {
            return;
        }
        CTagSet tagSet = new CTagSet();
        for (int i = 0; i < attrs.length; i++)
        {
            tagSet.SetValue(attrs[i], crSet.GetValue(0, i));
        }
        rw.setReturnObject(tagSet);
    }
    
    /**
     * 调能开出参tagset的键值转换
     * 
     * @param srcTag
     * @param businessId
     * @param openEbusRtn 能开返回的参数名
     * @param destRtn 目标参数名
     * @return
     * @remark lKF60882 2017-4-19
     */
    public static CTagSet rtnConvert(CTagSet srcTag, String businessId, String[] openEbusRtn, String[] destRtn)
    {
        // 1、出参转小写
        CTagSet desTag = lowerTagKey(srcTag);
        
        // 2、参数名转换
        if(null != openEbusRtn && null != destRtn)
        {
            // 原与目标长度需一致
            int len = openEbusRtn.length;
            
            if(len != destRtn.length)
            {
                throw new ReceptionException("转换参数"+businessId+"有误，请联系管理员。");
            }
            
            // 逐个转换
            for(int i = 0;  i < len; i++)
            {
                // 添加新的
                desTag.SetValue(destRtn[i], desTag.GetValue(openEbusRtn[i]));
                
                // 删掉旧的
                desTag.remove(openEbusRtn[i]);
            }
        }
        
        return desTag;
    }
    
    public static String readFileByLines(String fileName)
    {
        File file = new File(fileName);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                sb.append(tempString);
                line++;
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args)
    {
        String src = readFileByLines("E:/test.txt");
        //convertJsonToXml(src, "");
        convertXmlToJson(src);
    }
    
    /**
     * 获取能开token
     * <功能详细描述>
     * @param token
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017-09-22 OR_huawei_201708_809_【山东移动接口迁移专题】-能开token获取机制优化 
     */
    public static String getOpenEbusToken()
    {
        String token = "";
        try
        {
            // 是否开启auth2鉴权
            String isOpenAuth = CommonUtil.getDictNameById("isOpenAuth", "openEbusConf");
            
            // 应用id
            String client_id = CommonUtil.getDictNameById("client_id", "openEbusConf");
            
            // 需要鉴权，则调用接口查询token
            if("true".equals(isOpenAuth))
            {
                // 鉴权接口url
                String url = CommonUtil.getDictNameById("CMCVS_OpenEbusAuthAddress", "openEbusConf");
                
                // 分配的secret
                String client_secret = CommonUtil.getDictNameById("client_secret", "openEbusConf");
                
                // 组装请求报文
                String content = "grant_type=client_credentials&client_id=" + client_id + "&client_secret=" + client_secret;
                
                // 调用接口
                String response = HttpURLConnectionUtil.sendByPost(url, content, null);
                
                // 接口返回信息是json格式，将其转为xml格式
                String xml = CommonUtil.convertJsonToXml(response, null);
                
                Document doc = DocumentHelper.parseText(xml);
                
                // 得到token
                token = doc.getRootElement().element("access_token").getText();
            }
            // 不用鉴权，则token传应用id
            else
            {
                token = client_id;
            }
            
            // 存入缓存
            PublicCache.getInstance().cache("openEbusTokenDate", DateUtil.getTodaytime());
            PublicCache.getInstance().cache("openEbusToken", token);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        } 
        return token;
    }
}
