/*
 * 文件名：CurrencyUtil
 * 版权：(c)版权所有 2006-2008 华为技术有限公司
 * 描述：金额工具类
 * 修改人：
 * 修改时间：
 * 修改内容：
 */
package com.gmcc.boss.selfsvc.util;

import java.math.BigDecimal;

/**
 * 提供金额加减乘除的运算，四舍五入，大小写转化以及格式化
 * 
 * @author 1654565
 * @version 1.0,2007-2-8
 * @since 1.0
 */
public class CurrencyUtil
{
    /**
     * 默认小数位数(标度)
     */
    private static final int DEFAULT_SCALE = 2;
    
    /**
     * 存储数字中文大写
     */
    private static final String[] chnNumerics = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    
    /**
     * 存储金额单位
     */
    private static final String[] units = {"拾", "佰", "仟", "元", "万", "亿", "角", "分", "厘"};
    
    public static void main(String[] args)
    {
        System.out.println("======"+upperMoney("512445.03"));
        String s = add("0.00", "0.00");
        System.out.println("add:" + s);
        System.out.println("");
        minus("132.3233", "33.312");
        System.out.println("");
        multiply("22.123232", "-2.1");
        System.out.println("");
        divide("2.881", "100");
        
    }
    
    /**
     * 加法运算，默认保留2位小数
     * 
     * @param s1 金额1
     * @param s2 金额2
     * @return 金额相加结果的字符串形式
     */
    public static String add(String s1, String s2)
    {
        if (null == s1 || "".equals(s1.trim()))
        {
            s1 = "0";
        }
        
        if (null == s2 || "".equals(s2.trim()))
        {
            s2 = "0";
        }
        
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return round(bd1.add(bd2).toString());
    }
    
    /**
     * 减法运算，默认保留2位小数
     * 
     * @param s1 减数
     * @param s2 被减数
     * @return 金额相减结果的字符串形式
     */
    public static String minus(String s1, String s2)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return round(bd1.subtract(bd2).toString());
    }
    
    /**
     * 将以元为单位的金额转化为以分为单位的金额
     * 
     * @param s1 以元为单位的金额
     * @return 转化后的金额字符串
     */
    public static String multiply(String s1)
    {
        return multiply(s1.trim(), "100", 0);
    }
    
    /**
     * 乘法运算，默认保留2位小数
     * 
     * @param s1 乘数
     * @param s2 被乘数
     * @return 金额相乘结果的字符串形式
     */
    public static String multiply(String s1, String s2)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return round(bd1.multiply(bd2).toString());
    }
    
    /**
     * 将以分为单位的金额转化成以元为单位的金额
     * 
     * @param s1 金额字符串
     * @return
     */
    public static String divide(String s1)
    {
        return divide(s1.trim(), "100");
    }
    
    /**
     * 除法运算，默认保留2位小数
     * 
     * @param s1 除数
     * @param s2 被除数
     * @return 金额相除结果的字符串形式
     */
    public static String divide(String s1, String s2)
    {
        if (s1 == null || "".equals(s1.trim()))
        {
            return "";         
        }
        
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return bd1.divide(bd2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
    }
    
    /**
     * 加法运算，保留scale位小数
     * 
     * @param s1 加数
     * @param s2 被加数
     * @param scale 保留小数位数
     * @return 金额相加结果的字符串形式
     */
    public static String add(String s1, String s2, int scale)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return round(bd1.add(bd2).toString(), scale);
    }
    
    /**
     * 减法运算，保留scale位小数
     * 
     * @param s1 减数
     * @param s2 被减数
     * @param scale 保留小数位数
     * @return 金额相减结果的字符串形式
     */
    public static String minus(String s1, String s2, int scale)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return round(bd1.subtract(bd2).toString(), scale);
    }
    
    /**
     * 乘法运算，保留scale位小数
     * 
     * @param s1 乘数
     * @param s2 被乘数
     * @param scale 保留小数位数
     * @return 金额相乘结果的字符串形式
     */
    public static String multiply(String s1, String s2, int scale)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return round(bd1.multiply(bd2).toString(), scale);
    }
    
    /**
     * 除法运算，保留scale位小数
     * 
     * @param s1 除数
     * @param s2 被除数
     * @param scale 保留小数位数
     * @return 金额相除结果的字符串形式
     */
    public static String divide(String s1, String s2, int scale)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_EVEN).toString();
    }
    
    /**
     * 四舍五入，默认保留2位小数
     * 
     * @param s 要四舍五入的金额
     * @return 四舍五入结果
     */
    public static String round(String s)
    {
        BigDecimal bd1 = new BigDecimal(s.trim());
        BigDecimal bd2 = new BigDecimal("1");
        return bd1.divide(bd2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
    }
    
    /**
     * 四舍五入，保留scale位小数
     * 
     * @param s 要四舍五入的金额
     * @param scale 保留小数位数
     * @return 四舍五入后的金额字符串
     */
    public static String round(String s, int scale)
    {
        if (scale < 0)
            throw new ArithmeticException("保留小数位数小于0!");
        BigDecimal bd1 = new BigDecimal(s.trim());
        BigDecimal bd2 = new BigDecimal("1");
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_EVEN).toString();
    }
    
    /**
     * 转换金额数字为中文大写
     * 
     * @param moneyAmount 金额数字字符串
     * @return 转换后的金额字符串
     */
    public static String transform(String moneyAmount)
    {
        StringBuffer result = new StringBuffer();
        int radixPoint = moneyAmount.indexOf(".");
        String intDigit = null; // 存储金额整数部分
        String decimalDigit = null; // 存储金额小数部分
        if (radixPoint < 0)
        {
            intDigit = moneyAmount;
        }
        else
        {
            intDigit = moneyAmount.substring(0, radixPoint);
            decimalDigit = moneyAmount.substring(radixPoint + 1, moneyAmount.length());
        }
        
        int m = 0; // 单个金额数字所在金额字符串位置%4值
        int d = 0; // 单个金额数字所在金额字符串位置/4值
        char c = 0; // 存储单个金额数字字符
        int num = 0; // 存储单个金额数字整型值
        int pos = 0;
        
        // 处理金额整数部分
        for (int i = intDigit.length() - 1; i >= 0; i--)
        {
            c = intDigit.charAt(pos);
            num = Integer.parseInt(String.valueOf(c));
            m = i % 4;
            d = i < 12 ? i / 4 : 2; // 如果位数<12,即金额不超过亿亿,则取余，否则都取2,只能接"亿"单位
            if (m == 0)
            { // 如果取模等于0表示此数字后面的位置应该接"元", "万", "亿"三个单位
                result.append(chnNumerics[num] + units[d + 3]);
            }
            else if (m != 0)
            { // 否则接"拾", "佰", "仟"三个单位
                result.append(chnNumerics[num] + units[m - 1]);
            }
            pos++;
        }
        
        // 处理金额小数部分,超过3位不处理后面的
        if (decimalDigit != null)
        {
            int length = decimalDigit.length() <= 3 ? decimalDigit.length() : 3;
            for (int i = 0; i < length; i++)
            {
                c = decimalDigit.charAt(i);
                num = Integer.parseInt(String.valueOf(c));
                result.append(chnNumerics[num] + units[i + 6]);
            }
        }
        
        return result.toString();
    }
    
    public static String addInt(String s1, String s2)
    {
        return String.valueOf(Integer.parseInt(s1 != null ? s1 : "0") + Integer.parseInt(s2 != null ? s2 : "0"));
    }
    
    /**
     * 两个long型数据相加
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static String addLong(String s1, String s2)
    {
        return String.valueOf(Long.parseLong(s1 != null ? s1 : "0") + Long.parseLong(s2 != null ? s2 : "0"));
    }
    
    /**
     * 转换比特为字节(不满1024默认为1K)
     * 
     * @param sByte
     * @return
     */
    public static String calByteToK(String sByte)
    {
        int iByte = Integer.parseInt((sByte != null && sByte.trim().length() > 0) ? sByte : "0");
        int iResult = (iByte % 1024 != 0) ? iByte / 1024 + 1 : iByte / 1024;
        return String.valueOf(iResult);
    }
    
    
    /**
     * 该函数将人民币金额转换为大写数字<br>
     * 
     * @param dMoney为金额的阿拉伯数字形式<br>
     * @return 返回人民币汉字大写金额 <br>
     * 
     * 移植 by wmk 2009-03-02<br>
     */
    public static String upperMoney(String strMoney)
    {
        if (strMoney == null || "".equals(strMoney.trim()))
        {
            return "";
        }
        
        double dMoney = Double.parseDouble(strMoney);
        // 分以下数额的四舍五入
        if (dMoney < 0.01 && dMoney >= 0.005)
        {
            dMoney = 0.01;
        }
        else if (dMoney < 0.005)
        {
            dMoney = 0.00;
        }
        
        String chineseNumber[] = new String[] {"零", "壹", "贰", "叁", "肆", "伍",
                "陆", "柒", "捌", "玖"};
        
        // 不超万部分金额的单位
        String unitBelowWAN[] = new String[] {"元", "拾", "佰", "仟", "万"};
        
        // 超万部分金额的单位
        String unitBeyondWAN[] = new String[] {"万", "拾", "佰", "仟"};
        
        // 金额的绝对值
        double absMoneyNumber = 0;
        
        // 金额的绝对值的字符串
        String absMoneyString = "";
        
        // 金额的绝对值的字符串的整数部分
        String absMoneyStringInt = "";
        
        // 金额的绝对值的字符串的小数部分
        String absMoneyStringFraction = "";
        
        // 金额的绝对值的字符串的整数部分的大写
        String absMoneyStringZchineseNumber = "";
        
        // 金额的绝对值的字符串的小数部分的大写
        StringBuffer absMoneyStringXchineseNumber = new StringBuffer(1024);
        
        String sTemp = "";
        
        // 金额的绝对值的字符串的整数部分的大写的超万部分 */
        String sFirst = "";
        
        // 金额的绝对值的字符串的整数部分的大写的不超万部分 */
        String sSecond = "";
        
        // 返回值
        String sReturn = "";
        
        int iLength = 0;
        
        if (dMoney == 0)
            return "零元整";
        
        // 取得金额的绝对值并转化为“######.##”形式的字串
        absMoneyNumber = Math.abs(dMoney);
        java.text.DecimalFormat nfMoney = new java.text.DecimalFormat("0.00");
        absMoneyString = nfMoney.format(absMoneyNumber);
        
        // 取得金额绝对值的整数和小数部分
        absMoneyStringInt = absMoneyString.substring(0,
                absMoneyString.length() - 3);
        absMoneyStringFraction = absMoneyString.substring(absMoneyString.length() - 2,
                absMoneyString.length());
        
        // 先将小数部分转化为中文大写
        // ---------------------------------------------------------------------------------------------------------------------
        if (!(absMoneyStringFraction.substring(0, 1).equals("0")))
        {
            absMoneyStringXchineseNumber.delete(0, absMoneyStringXchineseNumber.length());
            absMoneyStringXchineseNumber.append(chineseNumber[(new Integer(absMoneyStringFraction.substring(0, 1))).intValue()]).append("角");
            if (!(absMoneyStringFraction.substring(1, 2).equals("0")))
            {
                absMoneyStringXchineseNumber.append(chineseNumber[(new Integer(absMoneyStringFraction.substring(1, 2))).intValue()]).append("分整");
            }
            else
            {
                absMoneyStringXchineseNumber.append("整");
            }
        }
        else
        {
            if (!(absMoneyStringFraction.substring(1, 2).equals("0"))
                    && ((new Long(absMoneyStringInt)).longValue() != 0))
            {
                absMoneyStringXchineseNumber.delete(0, absMoneyStringXchineseNumber.length());
                absMoneyStringXchineseNumber.append("零").append(chineseNumber[(new Integer(absMoneyStringFraction.substring(1, 2))).intValue()]).append("分整");
            }
            else if (!(absMoneyStringFraction.substring(1, 2).equals("0")))
            {
                absMoneyStringXchineseNumber.delete(0, absMoneyStringXchineseNumber.length());
                absMoneyStringXchineseNumber.append(chineseNumber[(new Integer(absMoneyStringFraction.substring(1, 2))).intValue()]).append("分整");
            }
            else
            {
                absMoneyStringXchineseNumber.delete(0, absMoneyStringXchineseNumber.length());
                absMoneyStringXchineseNumber.append("整");
            }
        }
        // ---------------------------------------------------------------------------------------------------------------------
        
        // 将整数部分转化为中文大写
        // ---------------------------------------------------------------------------------------------------------------------
        if (absMoneyStringInt.equals("0")
                && !(absMoneyStringFraction.equals("00")))
        {
            absMoneyStringZchineseNumber = "";
        }
        else
        {
            iLength = absMoneyStringInt.length();
            if (iLength > 8)
            {
                return "位数太长，不能显示!";
            }
            if (iLength < 5)
            {
                sSecond = absMoneyStringInt;
                StringBuffer sbuf = new StringBuffer(absMoneyStringZchineseNumber);
                // 此for 结构的内容与下同
                for (int iCount = 0; iCount < iLength; iCount++)
                {
                    sTemp = sSecond.substring(iCount, iCount + 1);
                    if (!(sTemp.equals("0")))
                    {
                        sbuf.append(chineseNumber[(new Integer(sTemp)).intValue()]).append(unitBelowWAN[iLength - iCount - 1]);
                    }
                    if ((sTemp.equals("0"))
                            && (iCount + 1 != iLength)
                            && !(sSecond.substring(iCount + 1, iCount + 2).equals("0")))
                    {
                        sbuf.append("零");
                    }
                    if ((sTemp.equals("0")) && (iCount + 1 == iLength))
                    {
                        sbuf.append("元");
                    }
                    if ((sTemp.equals("0"))
                            && (iCount + 1 == iLength)
                            && !(absMoneyStringFraction.substring(0, 1).equals("0")))
                    {
                        sbuf.append("零");
                    }
                }
                absMoneyStringZchineseNumber = sbuf.toString();
            }
            
            if (iLength > 4)
            {
                sFirst = absMoneyStringInt.substring(0,
                        absMoneyStringInt.length() - 4);
                sSecond = absMoneyStringInt.substring(absMoneyStringInt.length() - 4,
                        absMoneyStringInt.length());
                iLength = sFirst.length();
                StringBuffer sbuf = new StringBuffer(absMoneyStringZchineseNumber);
                for (int iCount = 0; iCount < iLength; iCount++)
                {
                    sTemp = sFirst.substring(iCount, iCount + 1);
                    if (!(sTemp.equals("0")))
                    {
                        sbuf.append(chineseNumber[(new Integer(sTemp)).intValue()]).append(unitBeyondWAN[iLength - iCount - 1]);
                    }
                    if ((sTemp.equals("0")) && (iCount + 1 == iLength))
                    {
                        sbuf.append("万");
                    }
                    if ((sTemp.equals("0"))
                            && !(absMoneyStringInt.substring(iCount + 1,
                                    iCount + 2).equals("0")))
                    {
                        sbuf.append("零");
                    }
                }
                absMoneyStringZchineseNumber = sbuf.toString();
                iLength = 4;
                //此for 结构的内容与上同
                for (int iCount = 0; iCount < iLength; iCount++)
                {
                    sTemp = sSecond.substring(iCount, iCount + 1);
                    if (!(sTemp.equals("0")))
                    {
                        sbuf.append(chineseNumber[(new Integer(sTemp)).intValue()]).append(unitBelowWAN[iLength - iCount - 1]);
                    }
                    if ((sTemp.equals("0"))
                            && (iCount + 1 != iLength)
                            && !(sSecond.substring(iCount + 1, iCount + 2).equals("0")))
                    {
                        sbuf.append("零");
                    }
                    if ((sTemp.equals("0")) && (iCount + 1 == iLength))
                    {
                        sbuf.append("元");
                    }
                    if ((sTemp.equals("0"))
                            && (iCount + 1 == iLength)
                            && !(absMoneyStringFraction.substring(0, 1).equals("0")))
                    {
                        sbuf.append("零");
                    }
                }
                absMoneyStringZchineseNumber = sbuf.toString();
            }
        }
        //---------------------------------------------------------------------------------------------------------------------
        
        // 合并整数部分和小数部分的中文表示
        sReturn = absMoneyStringZchineseNumber + absMoneyStringXchineseNumber.toString();
        if (dMoney < 0)
            sReturn = "（负）" + sReturn;
        return sReturn;
    }
    
    /**
     * 减法运算，默认保留2位小数，如果存在负数则将其修改为0
     * 
     * @param s1 减数
     * @param s2 被减数
     * @return 金额相减结果的字符串形式
     * @remark create by wWX217192 2014-10-13 由于剩余流量中包含负值，修改此方法
     */
    public static String minusTo0(String s1, String s2)
    {
        BigDecimal bd1 = new BigDecimal(s1.trim());
        BigDecimal bd2 = new BigDecimal(s2.trim());
        String tempValue = round(bd1.subtract(bd2).toString());
        
        boolean b = tempValue.matches("^\\d+(.?\\d{1,2})?$");
        if(b)
        {
        	return tempValue;
        }
        else
        {
        	return "0.00";  
        }
    }
}
