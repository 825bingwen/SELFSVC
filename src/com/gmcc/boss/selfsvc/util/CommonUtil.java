/*
 * �ļ�����TerminalInfoServiceImpl.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������ͨ�ù�����
 * ������:w69551
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
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
 * ͨ�ù�����
 * @author w69551
 * @version 1.0��2010-11-30
 * @see
 * @since
 */
public class CommonUtil
{
    /**
     * ��־
     */
    private static Log logger = LogFactory.getLog(CommonUtil.class);
	
	/**
     * Ĭ��С��λ��(���)
     */
    private static final int DEFAULT_SCALE = 2;
	
	/**
     * �������룬Ĭ�ϱ���2λС��
     * 
     * @param s
     *            Ҫ��������Ľ��
     * @return ����������
     */
    public static String round(String s) 
    {
        BigDecimal bd1 = new BigDecimal(s.trim());
        BigDecimal bd2 = new BigDecimal("1");
        return bd1.divide(bd2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_EVEN)
                .toString();
    }
	
    /**
     * ���ݵ�ǰ�˵�ID��ȡ��һ�������б�
     * 
     * @param allMenu�������Ĳ˵��б�
     * @param menuIDList���ն˿��õĲ˵�ID�б�
     * @param currMenuID����ǰ�Ĳ˵�ID
     * @param brandID��Ʒ��
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
            
            // �ȸ����û���brandIDɸѡ������˵��Ȳ�����������Ʒ��Ҳ���������û�����Ʒ�ƣ�����
            // begin zKF66389 findbus����
            if (brandID != null && !"".equals(brandID.trim()) && !"ALL".equalsIgnoreCase(menu.getBrandID())
                    && !brandID.equalsIgnoreCase(menu.getBrandID()))
            // end zKF66389 findbus����
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
     * ���ݵ�ǰ�˵�ID��ȡ��һ�������б�
     * 
     * @param menus���ն˻����õĲ˵��б�
     * @param currMenuID����ǰ�Ĳ˵�ID
     * @param brandID��Ʒ��
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
            
            // �ȸ����û���brandIDɸѡ������˵��Ȳ�����������Ʒ��Ҳ���������û�����Ʒ�ƣ�����
            // begin zKF66389 findbus����
            if (brandID != null && !"".equals(brandID.trim()) && !"ALL".equalsIgnoreCase(menu.getBrandID())
                    && !brandID.equalsIgnoreCase(menu.getBrandID()))
            // end zKF66389 findbus����
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
     * ���ݵ�ǰ�˵�ID��ȡ���˵�ID
     * 
     * @param allMenu�������Ĳ˵��б�
     * @param currMenuID����ǰ�Ĳ˵�ID
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
     * ���ݵ�ǰ�˵�ID��ȡ�����Ȳ˵�ID
     * 
     * @param currMenuID
     * @return
     * @see 
     */
    public static String getAncestorMenuInfo(HttpServletRequest request,String currMenuID)
    {
    	// �ն���Ϣ
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
     * ���ݵ�ǰ�˵�ID��ȡ������URL
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
     * ���ݵ�ǰ�˵�ID��ȡ������
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
     * �����û���ǰ����֤��ʽ�͹���Ҫ�����֤��ʽ�������û�������е���֤��ʽ
     * 
     * @param s1���û���ǰ����֤��ʽ
     * @param s2������Ҫ�����֤��ʽ
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
     * �ۻ������û���������֤��ʽ
     * 
     * @param s1��֮ǰ����֤��ʽ
     * @param s2��������֤��ʽ
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
     * �ӵ�ǰʱ�䵹��lastseq���£�����pattern��ʽ����ʱ��
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
     * �������������ڵģ���monthNum���µ��·���Ϣ����ָ����ʽ����
     * 
     * @param pattern����ʽ����yyyyMM
     * @param monthNum���·ݸ����������ؽ�������ֳ���
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
     * ����ת����Ԫ
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
     * ����ָ����ʽ�������
     * 
     * @param date������
     * @param srcPattern�����ڸ�ʽ����yyyyMMdd
     * @param destPattern��������ڸ�ʽ����yyyy-MM-dd
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
     * ����ָ��λ���������
     * 
     * @param len ������λ��
     * @return �����
     */
    public static String getRandomNum(int len)
    {
        StringBuffer randomNum = new StringBuffer(len);
        
        // modify begin zKF66389 2015-12-14 V200R003C10LG1201 OR_huawei_201509_857_��ȫ������㷨����
//        Random rd = new Random();
//        
//        int i = 0;
//        while (i < len)
//        {
//            randomNum.append(String.valueOf(rd.nextInt(10)));
//            
//            i++;
//        }
		// �����������
		SecureRandom random = new SecureRandom();
		// �����byte����
		byte[] randomBytes = new byte[10];
		// ���������
		random.nextBytes(randomBytes);
		// 0~9֮�������
		for (int i = 0; i < len; i++)
		{
			randomNum.append(Math.abs(randomBytes[i] % 10));
		}
		// modify end zKF66389 2015-12-14 V200R003C10LG1201 OR_huawei_201509_857_��ȫ������㷨����
        
        return randomNum.toString();
    }
    
    /**
     * ��String�͵����ڴ�ת��Date
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
     * �����ڰ���ָ����ʽת���ַ���
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
    // // �����������������е��쳣
    // }
    // }
    // }
    // }
    /**
     * ����ַ����Ƿ�Ϊ�մ�
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
     * �ж�һ���ַ����Ƿ�����������
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            char tmp = str.charAt(i);
            // ASCII�Ƚ�
            if (tmp < '0' || tmp > '9')
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * �ж�һ���ַ����Ƿ����ֻ����루ֻ�ж�11λ�����ֻ����룩
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
    
    // // JAVA�Ķ�ά�����ΪJS�Ķ�ά����
    // public static String javaArray2JSArray(String[][] srcData)
    // {
    //        
    // if (srcData == null || srcData.length == 0)
    // {
    // return "new Array(new Array(\"û����ص�����!\"))";
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
    // * ��&nbsp;�滻�ո��nullֵ
    // *
    // * @param s
    // * @return
    // */
    // public static String replaceBlank(String s)
    // {
    // return s == null || "".equals(s) ? "&nbsp;" : s;
    // }
    
    /**
     * ���ת��,Ԫת��Ϊ��
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
     * ��ת��ΪԪ
     * 
     * @param ���� : ����
     * @return String
     * @exception
     * @author x60003349 <br>
     *         �������ƣ� <br>
     *         ����ʱ�䣺May 29, 2006 5:36:59 PM
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
        
        //����һ�֣���һ�ּ���
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
     * ��ʽ������Ϊ��λ��ʱ��Ϊ ʱ���� �ĸ�ʽ
     * 
     * @param secondsTime
     * @return
     */
    public static String formatSecondsTime(String secondsTime)
    {
        String result = "Unknown";
        int iTimes = Integer.parseInt((secondsTime != null && secondsTime.trim().length() > 0) ? secondsTime : "0");
        if (iTimes < 60)
        { // ���ʱ��С��60��
            result = iTimes + "��";
        }
        else if (iTimes >= 60 && iTimes < 60 * 60)
        { // ���ʱ������60��С��60����
            int minutes = iTimes / 60; // �����
            int seconds = iTimes % 60; // ����
            result = minutes + "��" + seconds + "��";
        }
        else if (iTimes >= 60 * 60)
        { // ���ʱ������60����
            int hours = iTimes / (60 * 60); // ��Сʱ
            int minutes = 0;
            int seconds = 0;
            int leftTimes = iTimes % (60 * 60); // ��Сʱ��ʣ���ʱ��
            // ������Сʱ��ʣ���ʱ�������ֺ���
            if (leftTimes < 60)
            {
                seconds = leftTimes;
            }
            else if (leftTimes >= 60 && leftTimes < 60 * 60)
            {
                minutes = leftTimes / 60; // ���
                seconds = leftTimes % 60; // ����
            }
            result = hours + "ʱ" + minutes + "��" + seconds + "��";
            
        }
        return result;
    }
    
    /**
     * ���value�ĳ���С��size������ಹ��ո�
     * 
     * @param value
     * @param size
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
     * ���value�ĳ���С��size�����Ҳಹ��ո�
     * 
     * @param value
     * @param size
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
     * ��ʽ���绰���룬������벻����11λ������HTML�ո���11λ
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
            // ����������11λ�����д�ӡ(�ÿո������ƻ���)
            for (int i = 0; i < 49; i++)
            {
                sbuf.append(" ");
            }
        }
        result = sbuf.toString();
        return result;
    }
    
    /**
     * ������Ŀ�ʼλ�ÿ�ʼ��ȡ�����ַ���
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
	 * ����ָ����С�Ŀո񣬵������������maxSize�򲻲���
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
     * ��value���Ҳಹ��size���ո�
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
     * ���ʱ�����̲�һ�����Կո��롣 ֻ���Ƶ�4λ����ʱ��
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
    
    //�ж��ַ����Ƿ�Ϊ��
    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str.trim());
    }
    
    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param str
     * @return
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   Ȧ���Ӷ�_�����նˣ����׶Σ�
     */
    public static boolean isNotEmpty(String str)
    {
        return null != str && !"".equals(str.trim());
    }
    
    /**
     * ȡ�õ�ǰʱ��(��ʽ:20080109151259)
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
     * Ϊ��ǰ��������ָ������
     * 
     * @param baseDate ��������
     * @param addNum ���ӵ���
     * @return String �������yyyyMMddHHmmss��ʽ������
     * @throws ParseException ���ڸ�ʽ�쳣
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
	 * ת������Ϊ�ֽ�(����1024Ĭ��Ϊ1K)
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
	 * ͨ��crset���ֻ�ȡ��crset������Ϊkey��ֵ
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
     * ��ʽ���ַ���
     * 
     * @param resource ԭ�ַ���
     * @param align left or right�����Ȳ���ʱ��ಹ�����Ҳಹ
     * @param sign ���Ȳ���ʱ�����ֵ
     * @param count ָ������
     * @return
     * @remark create g00140516 2011/12/09 R003C11L12n01 �굥��ѯʵ��socketЭ��
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
     * ����ת��ΪXX��XX��
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
        
        // ������
        if (-1 != seconds.indexOf("."))
        {
            return seconds;
        }
        
        int nMin = Integer.parseInt(seconds) / 60;
        int nSec = Integer.parseInt(seconds) % 60;
        
        String info = "";
        if (0 < nMin)
        {
            info = nMin + "��";
        }
        
        info += (nSec + "��");
        
        return info;
    }
    
    /**
     * ��Kת��ΪXX(M)XX(K)
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
     * ����ת��ΪXX:XX:XX
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
        
        // ������
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
     * ��Bת��ΪXXMBXXKBXXB
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
        
        // ������
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
     * ���value�ĳ���С��size������ಹ��ո�
     * 
     * @param value ԭ�ַ���
     * @param minSize ��С����
     * @param blank " "��&nbsp;
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
     * ����������֮�����������������һ���µ���ȥ
     * 
     * @param startDate ��ʽΪyyyyMMdd
     * @param endDate ��ʽΪyyyyMMdd
     * @return
     * @remark create lKF60882 2012-6-6 
     */
    public static HashMap<String, Integer> getYearsMonths(Date startDate, Date endDate)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int iMonth = 0;
        int flag = 0; // ��־�Ƿ���ڷ����£��ⲿ��Ҫ��ȥ
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
            
            // ����>=12������
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
     * ���������ַ���
     *
     * @param iLen ������ĳ���
     * @param iType ����������ͣ�0:��ʾ����������������1����ʾ������ַ��������2����ʾ��������ַ��������� 
     *  
     */ 
    public static final String createRandom(int iLen, int iType)
    { 
        StringBuffer buffer = new StringBuffer(""); 
        Random rnd = new Random();
        
        // Ĭ��8λ
        if (iLen < 0)
        { 
           iLen = 8; 
        }
        
        // Ĭ�����������
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
     * ȡ�õ�ǰʱ��(��ʽ:20080109151259)
     * @param patternType ʱ���ʽ
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
     * �����ѡ��֤��ʽ
     * ����û�����֤��ʽ��˵���Ӧ����֤��ʽ�н������򷵻�0000����������Ҫ��֤�����û�н�����ֱ�ӷ��ز˵���Ӧ����֤��ʽ������һ��������⣺���ĵ�ũ�����û����ܽ��ն��ţ�������Ҫ�����жϡ�
     * @param customerSimp �û���Ϣ
     * @param currMenuID ��ǰ�˵�
     * @param menuAuthCode �˵���Ӧ����֤��ʽ
     * @return ��ѡ��֤��ʽ
     * @remark create g00140516 2013/02/20 V200R003C13L02n0 OR_NX_201302_600
     */
    public static String getIntersectionCode(NserCustomerSimp customerSimp, String currMenuID, String menuAuthCode)
    {
    	// �û���δ��¼������֤��ʽΪ0000��ֱ�ӷ��ز˵���Ӧ����֤��ʽ
    	if (customerSimp == null || "0000".equals(customerSimp.getLoginMark()))
    	{
    		return menuAuthCode;
    	}
    	
    	String loginMark = customerSimp.getLoginMark();
    	if (loginMark.length() == menuAuthCode.length())
    	{
    		for (int i = 0; i < loginMark.length(); i++)
    		{
    			// �û�����֤��ʽ��˵���Ӧ����֤��ʽ�н���������0000
    			if (loginMark.charAt(i) == menuAuthCode.charAt(i) && (menuAuthCode.charAt(i) == '1'))
    			{
    				return "0000";
    			}
    		}
    	}
    	
    	// ���ĵ�ũ�����û����ܽ��ն���
    	String menuId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_MENU);
        String ProId = (String) PublicCache.getInstance().getCachedData(Constants.NO_RANDOMPWD_PRO);
        
        //��ȡ��ǰ�û��Ĳ�Ʒid
        String usrProId = customerSimp.getProductID();
        
        // ȥ�����������֤
    	if (menuAuthCode.charAt(1) == '1' && filterMenu(menuId, currMenuID) && filterProd(ProId, usrProId))
    	{
    		menuAuthCode = menuAuthCode.substring(0, 1) + "0" + menuAuthCode.substring(2);
    	}
        
    	return menuAuthCode;
    }
    
    /**
     * �жϵ�ǰ�˵��費��Ҫ����
     * @param str �����������õ���Ҫ���˵Ĳ˵���
     * @param menuid ��ǰ�˵�
     * @return true����Ҫ����
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
     * �жϵ�ǰ��Ʒ�費��Ҫ����
     * @param str ����������Ҫ���˵Ĳ�Ʒid
     * @param prodid ��ǰ��Ʒid
     * @return true����ǰ��Ʒ��Ҫ���� false����ǰ��Ʒ����Ҫ����
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
     * ���ͻ�����ģ����
     * ��������������ౣ��1���֣����������ֵ���ౣ��2����,��������*����
     * @param custname ����������Ҫģ�����Ŀͻ�����
     * @return custname
     * @remark create yWX163692 2013/11/21 OR_NX_201310_1186 �ͻ�������Ϣģ����չʾ 
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
     * �ͻ���Ϣģ����
     * create by lwx439898 2017-06-26 OR_SD_201706_196_�����ն˲��ֲ˵����ͻ����ơ�ģ��������
     */
    public static String getUserLastName(String fullName)
    {
        // ��ȫ��������Ϊ��ʱ,����"δ֪�û�"
        if (StringUtils.isBlank(fullName) || "".equals(fullName.trim()))
        {
            fullName = "δ֪�û�";
        }
        // ����ͻ����ƶ���2���� ����*��
        else if (fullName.length() > 2)
        {
            StringBuffer lastName = new StringBuffer(fullName.substring(0, 1));
            for (int i = 1; i < fullName.length() - 1; i++)
            {
                lastName.append("*");
            }
            fullName = lastName.toString() + fullName.substring(fullName.length() - 1);
        }
        // ����ͻ�������2���� ����*
        else if (fullName.length() == 2)
        {
            fullName = fullName.substring(0, 1) + "*";
        }
        return fullName;
    }
 
    /**
     * ��ȡһ�����µ���������
     * @param date YYYYMM ����YYYYMMdd
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
     * ��ȡһ�����µ���
     * @param date YYYYMM ����YYYYMMdd
     * @return
     */
    public static String getMonth(String date) 
    {
        int month = Integer.parseInt(date.substring(4, 6));
        return String.valueOf(month);
    }
    
    /**
     * һ�����ַ���һ��ռbitsλ������
     * @param date ���� dd
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
     * ��ȡ�û�Ʒ������
     * @param brand Ʒ�� ��һ����д��ĸ�������
     * @return
     */
    public static String getNameByBrandLetter(String brand)
    {
        String brandName = "";
        if("M".equals(brand))
        {
            brandName = "���еش�";
        }
        else if("G".equals(brand))
        {
            brandName = "ȫ��ͨ";
        }
        else if("E".equals(brand))
        {
            brandName = "������";
        }
        return brandName;
    }
    
    /**
     * ��ȡ�û�Ʒ������
     * @param brand Ʒ�� ��һ��ȫƴ���ʵ������
     * @return
     */
    public static String getNameByBrandWord(String brand)
    {
        String brandName = "";
        if("BrandMzone".equals(brand))
        {
            brandName = "���еش�";
        }
        else if("BrandGotone".equals(brand))
        {
            brandName = "ȫ��ͨ";
        }
        else
        {
            brandName = "������";
        }
        return brandName;
    }
    
    /** 
     * ����ReturnWrap
     * 
     * @param status ״̬
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: create by zKF69263 2014/07/24 OR_huawei_201407_369 �����ظ���_�����ն�
     */
    public static ReturnWrap getReturnWrap(int status)
    {
        return getReturnWrap(status, "");
    }
    
    /** 
     * ����ReturnWrap
     * 
     * @param status ״̬
     * @param message ������Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: create by zKF69263 2014/07/24 OR_huawei_201407_369 �����ظ���_�����ն�
     */
    public static ReturnWrap getReturnWrap(int status, String message)
    {
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(status);
        rw.setReturnMsg(message);
        return rw;
    }
    
    /**
     * <�ӻ����л�ȡsh_param_value�еĲ���>
     * <������ϸ����>
     * @param paramId
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-8-6 10:26:04 OR_SD_201408_20_ISSSƽ̨�Խ������ն˵����� 
     */
    public static String getParamValue(String paramKey)
    {
    	return (String) PublicCache.getInstance().getCachedData(paramKey);
    }
    
    /**
     * <�ӻ����л�ȡsh_param_value�еĲ���>
     * <��������û�иò�������ò���ֵΪ�գ��򷵻�Ĭ��ֵ>
     * @param paramKey
     * @param defaultValue �ò�����Ĭ��ֵ
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-4-20 15:56:03 OR_NX_201501_1030 ��ʡ��ؽɷ�
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
     * ����dictId��groupId��ȡ�ֵ����Ϣ
     * 
     * @param dictID String
     * @param groupID String
     * @return
     * @remark create by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
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
     * ����dictid��groupid��ȡ�ֵ����Ϣ�����û�еĻ�����Ĭ��ֵdefaultVal
     * 
     * @param dictId
     * @param groupId
     * @param defaultVal
     * @return
     * @remark create by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
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
     * ����dictId��groupId��ȡ�ֵ����Ϣ
     * 
     * @param dictId
     * @param groupId
     * @return
    * @remark create by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
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
                    logger.info("groupIdΪ" + groupId + ",dictIdΪ" + dictId + ",ȡ�ò���ֵΪ��" + dictItem);
                    
                    return dictItem;
                }
            }
        }
        
        logger.info("groupIdΪ" + groupId + ",dictIdΪ" + dictId + ",ȡ�ò���ֵΪ��null");
        return null;
    }
    
    /**
     * ����groupId��ȡ�ֵ����Ϣ
     * 
     * @param groupId String
     * @return List<DictItemPO>
     * @remark create by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
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
     * ģ��������
     * ���������ڵ��û��������������֣�ֻģ�����ձ������֣����������ϵ��û���ֱ��ģ����ǰ�����֡�
     * @param name ����������Ҫģ����������
     * @return String
     * @remark create by hWX5316476 2014-8-25 V200R003C10LG0801 OR_NX_201407_722_����_���ڿͻ���Ϣģ�����Ĳ�������
     */
    public static String blurName(String name)
    {
        // ����Ϊ�ջ�ȥ�����߿ո��Ϊ�մ���ֱ�ӷ���""
        if(StringUtils.isEmpty(name) || "".equals(name.trim()))
        {
            return "";
        }
        
        // ȥ���������߿ո�
        name = name.trim();
        
        int len = name.length();
        String subsname;
        
        // ���������ϵ��û���ֱ��ģ����ǰ�����֡�
        if(3 < len)
        {
            subsname = "**" + name.substring(2, len);
        }
        // ���������ڵ��û��������������֣�ֻģ�����ձ�������
        else if(1 < len )
        {
            subsname = "*" + name.substring(1, len);
        }
        // һ���ֱ���������ģ����
        else
        {
            subsname = name;
        }

        return subsname;
    }
    
    /**
     * ƴ���ַ������� ���ַ�����ʽΪ"SD.LA.07.xxx"ת��Ϊ"'SD','SD.LA','SD.LA.07','SD.LA.07.xxx'"���ַ���
     * 
     * @param orgidString
     * @return
     */
    public static String splitOrgid(String orgidString)
    {
        String[] shuzu = orgidString.split("\\.");
        
        //modify begin g00140516 2011/09/30 �ع�
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
        //modify end g00140516 2011/09/30 �ع�
        
        return orgids.substring(0, orgids.length() - 1);
    }
    
    /**
     * ����ת���ɷ֣�����С���㣩
     * 
     * @param strMoney ���
     * @param roundMode ת��ģʽ BigDecimal.ROUND_UP������0����1��BigDecimal.ROUND_HALF_EVEN �������� ��
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
     * ����ת������
     * 
     * @param strMoney
     * @return
     * @see
     * @remark create by sWX219697 2015-4-8 18:10:50 OR_NX_201501_1030 ��ʡ��ؽɷ�
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
     * <�ر���>
     * <������ϸ����>
     * @param closeable
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-3-31 15:58:44 OR_NX_201501_1030 ��ʡ��ؽɷ�
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
     * <��������ģ����>
     * <�����������֣�ģ����һ�������������֣��������������ǰ��ľ�ģ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
     * �ظ������жϣ������ã�
     * 
     * @param termInfo �ն˻���Ϣ
     * @return true��δ�ظ���false���ظ�
     * @since 
     * @remark create hWX5316476 2015-4-15 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ 
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
                    String tmpErrorMsg = "�ظ��ɷ�:�ֻ���[".concat(servnumber).concat("], Ͷ�ҽ��[").concat(tMoney)
                            .concat("]Ԫ, ����Ա[").concat(termInfo.getOperid()).concat("], ��ˮ��[").concat(terminalSeq).concat("]");
                    
                    logger.error(tmpErrorMsg);

                    return false;
                }
                else
                {
                    logger.info("�򻺴��в���ɷ���Ϣ��" + seq);
                    
                    RefreshCacheNX.cashChargeRecords.put(seq, DateUtilHub.curOnlyTime());
                }
            }
        }
        return true;
    }
    
    /**
     * ��ȡ���ýɷѷ�ʽ
     * @param groupID �ն���
     * @return
     */
    public static List qryUsablePayTypes(String termGroupId, String curMenuId)
    {
        List<MenuInfoPO> menuList = null;
        
        if (termGroupId != null && !"".equals(termGroupId))
        {
            menuList = (List<MenuInfoPO>)PublicCache.getInstance().getCachedData(termGroupId);
        }
        
        // ��ȡ��ѡ�ɷѷ�ʽ
        List usableTypes = getChildrenMenuInfo(menuList, curMenuId, "");

        return usableTypes;
    }
    
    /**
     * ��ʽ�������ַ���
     * ���磺00010����ʽ��Ϊ10
     * <������ϸ����>
     * @param numStr
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-4-17 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ 
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
    
    /** �滻������Ϣ�е������ַ�(����)
     * <������ϸ����>
     * @param message
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by qWX279398 2015-8-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
     */
    public static String errorMessage(String message)
    {
        // ���Ϊnull����ַ���������""
        if(message == null || "".equals(message))
        {
            return "";
        }
        
        // �滻������Ϣ�е������ַ����� 0:�ر� 1:����
        String errorFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERROR_FLAG);
        
        // ����δ�����������û�����ã�0��""��null��
        if (!"1".equals(errorFlag))
        {
            return message;   
        }
        else
        {
            // �����ַ��滻�ֵ���
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
    
    /** ��ǰ�����봫������֮��(��λ:����)
     * <������ϸ����>
     * @param lastLoginTime
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark created by by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
     */
    public static long compareCurrTime(String lastLoginTime)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ������
        Calendar calendar = Calendar.getInstance();// ��ȡ����
        String currLoginTime = simpleDateFormat.format(calendar.getTime());// ��ǰʱ��
        
        long currLoginDate = 0;// ��ǰʱ��
        long lastLoginDate = 0;// ����ʱ��
        
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
    
    /** ������ʱ���������ݿ�
     * <������ϸ����>
     * @param busiAcceptTimePO
     * @see [�ࡢ��#��������#��Ա]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 �̲߳�������ʱ������־
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
     * ���ɳ���Ϊlength�����������
     * ���ɳ���Ϊlength�����������������֤��Ϣ
     * 
     * @param length ��֤��ĳ���
     * @return �����������String���͵���֤��
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017-1-3 OR_HUB_201609_640 �����ն��û���¼��֤��ʽ
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
     * ����ͼƬ��֤�������
     * @param g
     * @param nums ����������
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017-1-6 OR_HUB_201609_640 �����ն��û���¼��֤��ʽ
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
     * �������д��ͼƬ�Ϸ��أ������ϸ�����
     * @param randomPassword //�����
     * @return �������������֤��ͼƬ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017-1-3 OR_HUB_201609_640 �����ն��û���¼��֤��ʽ
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
        //���ɸ�����4��
        drawRandomLines(g_2d,4);
        g_2d.dispose();
        return big;
    }
    
    /**
     * xml��ʽתΪjson
     * 
     * @param xmlInfo
     * @return
     * @remark lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
     */
    public static String convertXmlToJson(String xmlInfo)
    {
        logger.info("XmlToJson,ת��ǰxml��" + xmlInfo);
        
        XMLSerializer xmlSerializer = new XMLSerializer();
        
        // ���ò�����ǰ׺
        xmlSerializer.setTypeHintsCompatibility(false);
        xmlSerializer.setTypeHintsEnabled(false);
        
        // ���������ռ�
        xmlSerializer.setSkipNamespaces(true);
        
        JSON json = xmlSerializer.read(xmlInfo);
        
        String jsonStr = json.toString();
        
        logger.info("XmlToJson,ת����json��" + jsonStr);
        
        return jsonStr;
    }
    
    /**
     * json��ʽתΪxml
     * 
     * @param jsonStr
     * @param processCode �ӿ�processcode�����������Ƿ���Ҫ��json�������⴦��
     * @return
     * @remark lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
     */
    public static String convertJsonToXml(String jsonStr, String processCode)
    {
        logger.info("JsonToXml,ת��ǰjson��" + jsonStr);
        
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsCompatibility(false);
        xmlSerializer.setTypeHintsEnabled(false);
        
        if(null != processCode)
        {
            // �������е�result�ڵ�ĳ�content�ڵ㣬res_code�ĳ�resp_result��res_desc�ĳ�resp_desc
            jsonStr = jsonStr.replace("\"result\"", "\"message_content\"").replace("\"res_code\"", "\"retcode\"").replace("\"res_desc\"", "\"retmsg\"");
            
            // ���ø��ڵ�Ϊresult
            xmlSerializer.setRootName("message_body");
            
            // ���ýӿ�����Ϊlist��Ӧ�ķ��ؽڵ�����
            DictItemPO dict = CommonUtil.getDictItemById(processCode, "openEbusInvokeList");
            if(null != dict.getDescription())
            {
                String[] expandableProperties = dict.getDescription().split(",");
                xmlSerializer.setExpandableProperties(expandableProperties);
            }
        }
        JSON json = JSONSerializer.toJSON(jsonStr);
        
        String xml = xmlSerializer.write(json);
        
        logger.info("JsonToXml,ת����xml��" + xml);
        
        return xml;
    }
    
    /**
     * �Ƿ������������ƽ̨
     * 
     * @param processCode �ӿ�processCode
     * @return
     * @remark lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1
     */
    public static boolean isInvokeOpenEbus(String processCode)
    {
        // �ܿ���
        String switchConf = CommonUtil.getDictNameById("isInvoke", "openEbusConf");
        
        // ����Ľӿ�����
        DictItemPO dict = CommonUtil.getDictItemById(processCode, "openEbusInvokeList");
        
        // �ܿ��ؿ���������ؽӿڴ������ã�����openEbus������true��
        return ("true".equalsIgnoreCase(switchConf)) && (null != dict);
    }
    
    /**
     * ��tagset�е�key��תΪСд
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
     * ������CRSetת����CTagSet
     * @param attrs CRSet��������˳������
     * @param rw
     * @remark lWX298507 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1 2017-5-8
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
     * ���ܿ�����tagset�ļ�ֵת��
     * 
     * @param srcTag
     * @param businessId
     * @param openEbusRtn �ܿ����صĲ�����
     * @param destRtn Ŀ�������
     * @return
     * @remark lKF60882 2017-4-19
     */
    public static CTagSet rtnConvert(CTagSet srcTag, String businessId, String[] openEbusRtn, String[] destRtn)
    {
        // 1������תСд
        CTagSet desTag = lowerTagKey(srcTag);
        
        // 2��������ת��
        if(null != openEbusRtn && null != destRtn)
        {
            // ԭ��Ŀ�곤����һ��
            int len = openEbusRtn.length;
            
            if(len != destRtn.length)
            {
                throw new ReceptionException("ת������"+businessId+"��������ϵ����Ա��");
            }
            
            // ���ת��
            for(int i = 0;  i < len; i++)
            {
                // �����µ�
                desTag.SetValue(destRtn[i], desTag.GetValue(openEbusRtn[i]));
                
                // ɾ���ɵ�
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
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
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
     * ��ȡ�ܿ�token
     * <������ϸ����>
     * @param token
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by lWX431760 2017-09-22 OR_huawei_201708_809_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�ܿ�token��ȡ�����Ż� 
     */
    public static String getOpenEbusToken()
    {
        String token = "";
        try
        {
            // �Ƿ���auth2��Ȩ
            String isOpenAuth = CommonUtil.getDictNameById("isOpenAuth", "openEbusConf");
            
            // Ӧ��id
            String client_id = CommonUtil.getDictNameById("client_id", "openEbusConf");
            
            // ��Ҫ��Ȩ������ýӿڲ�ѯtoken
            if("true".equals(isOpenAuth))
            {
                // ��Ȩ�ӿ�url
                String url = CommonUtil.getDictNameById("CMCVS_OpenEbusAuthAddress", "openEbusConf");
                
                // �����secret
                String client_secret = CommonUtil.getDictNameById("client_secret", "openEbusConf");
                
                // ��װ������
                String content = "grant_type=client_credentials&client_id=" + client_id + "&client_secret=" + client_secret;
                
                // ���ýӿ�
                String response = HttpURLConnectionUtil.sendByPost(url, content, null);
                
                // �ӿڷ�����Ϣ��json��ʽ������תΪxml��ʽ
                String xml = CommonUtil.convertJsonToXml(response, null);
                
                Document doc = DocumentHelper.parseText(xml);
                
                // �õ�token
                token = doc.getRootElement().element("access_token").getText();
            }
            // ���ü�Ȩ����token��Ӧ��id
            else
            {
                token = client_id;
            }
            
            // ���뻺��
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