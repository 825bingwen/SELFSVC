package com.gmcc.boss.selfsvc.util;

import java.text.NumberFormat; 
import java.util.HashMap; 

/**
 * 将阿拉伯数字金额转换为大写金额的类
 * 
 * @author hWX5316476
 * @remark OR_SD_201402_278 申请为所有自助系统打印的发票添加大写发票金额
 */
public class MoneySimpleToCapitalUtil { 
	 
	public static final String EMPTY = ""; 
	 
	public static final String ZERO = "零"; 
	 
	public static final String ONE = "壹"; 
	 
	public static final String TWO = "贰"; 
	 
	public static final String THREE = "叁"; 
	 
	public static final String FOUR = "肆"; 
	 
	public static final String FIVE = "伍"; 
	 
	public static final String SIX = "陆"; 
	 
	public static final String SEVEN = "柒"; 
	 
	public static final String EIGHT = "捌"; 
	 
	public static final String NINE = "玖"; 
	 
	public static final String TEN = "拾";  
	 
	public static final String HUNDRED = "佰";  
	 
	public static final String THOUSAND = "仟";  
	 
	public static final String TEN_THOUSAND = "万"; 
	 
	public static final String HUNDRED_MILLION = "亿"; 
	 
	public static final String YUAN = "圆"; 
	 
	public static final String JIAO = "角"; 
	 
	public static final String FEN = "分";  
	 
	public static final String DOT = ".";  
	 
	private static MoneySimpleToCapitalUtil formatter = null;
	 
	private HashMap chineseNumberMap = new HashMap();
	
	private HashMap chineseMoneyPattern = new HashMap();
	  
	private NumberFormat numberFormat = NumberFormat.getInstance(); 
	
	/**
	 * 构造方法
	 */
	private MoneySimpleToCapitalUtil() 
	{  
		// numberFormat设置最大小数位数
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(0);
		numberFormat.setGroupingUsed(false);
		chineseNumberMap.put("0", ZERO); 
		chineseNumberMap.put("1", ONE); 
		chineseNumberMap.put("2", TWO); 
		chineseNumberMap.put("3", THREE); 
		chineseNumberMap.put("4", FOUR); 
		chineseNumberMap.put("5", FIVE); 
		chineseNumberMap.put("6", SIX); 
		chineseNumberMap.put("7", SEVEN);  
		chineseNumberMap.put("8", EIGHT); 
		chineseNumberMap.put("9", NINE); 
		chineseNumberMap.put(DOT, DOT);
		
		chineseMoneyPattern.put("1", TEN);  
		chineseMoneyPattern.put("2", HUNDRED);
		chineseMoneyPattern.put("3", THOUSAND);
		chineseMoneyPattern.put("4", TEN_THOUSAND);
		chineseMoneyPattern.put("5", TEN);
		chineseMoneyPattern.put("6", HUNDRED);
		chineseMoneyPattern.put("7", THOUSAND);
		chineseMoneyPattern.put("8", HUNDRED_MILLION);
	}  
	
	/**
	 * MoneySimpleToCapitalUtil静态实例化方法
	 * 
	 * @return
	 */
	//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi 需求编号:OR_huawei_201407_373_静态检查_自助终端
	public static synchronized MoneySimpleToCapitalUtil getInstance() 
	{ 
		if (formatter == null)
		{
			formatter = new MoneySimpleToCapitalUtil(); 
		}
		return formatter;  
	}  
	//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi 需求编号:OR_huawei_201407_373_静态检查_自助终端
	/**
	 * 格式化阿拉伯数字字符串转换为大写金额
	 * 
	 * @param moneyStr
	 * @return String
	 */
	public String format(String moneyStr) 
	{ 
		// 检查字符串小数位是否超过2位，如果超过2位，格式化
		moneyStr = checkPrecision(moneyStr);  
		
		String result;
		
		// 将字符串转换为大写
		result = convertToChineseNumber(moneyStr);
		
		// 为大写字符串添加单位并按读写习惯输出
		result = addUnitsToChineseMoneyString(result); 
		
		return result; 
	}  
	 
	/**
	 * 参数为double类型格式化方法
	 * 
	 * @param moneyDouble
	 * @return String
	 */
	public String format(double moneyDouble) 
	{  
		return format(numberFormat.format(moneyDouble)); 
	}  
	 
	/**
	 * 参数为整数类型格式化方法
	 * @param moneyInt
	 * @return String
	 */
	public String format(int moneyInt) 
	{  
		return format(numberFormat.format(moneyInt)); 
	}  
	 
	/**
	 * 参数为long类型格式化方法
	 * 
	 * @param moneyLong
	 * @return String
	 */
	public String format(long moneyLong) 
	{  
		return format(numberFormat.format(moneyLong));
	}  
	 
	/**
	 * 参数为字符串类型格式化方法
	 * @param moneyNum
	 * @return String
	 */
	public String format(Number moneyNum) 
	{ 
		return format(numberFormat.format(moneyNum)); 
	}  
	 
	/**
	 * 转换为大写中文方法
	 * 
	 * @param moneyStr
	 * @return String
	 */
	private String convertToChineseNumber(String moneyStr) 
	{ 
		String result;
		
		StringBuffer cMoneyStringBuffer = new StringBuffer();
		
		// 循环将数字替换为大写
		for (int i = 0; i < moneyStr.length(); i++) 
		{  
			cMoneyStringBuffer.append(chineseNumberMap.get(moneyStr.substring(i, i + 1))); 
		}  
		
		// 小数点不存在，追加到字符串中".零零"
		if(cMoneyStringBuffer.indexOf(DOT) == -1)
		{
			cMoneyStringBuffer.append(".零零");
		}
		
		// 获取字符串中小数点索引
		int indexOfDot = cMoneyStringBuffer.indexOf(DOT);
		
		//　设置位数游标
		int moneyPatternCursor = 1; 
		
		// 循环处理整数位，一直到十位
		for (int i = indexOfDot - 1; i > 0; i--) 
		{  
			cMoneyStringBuffer.insert(i, chineseMoneyPattern.get(EMPTY 	+ moneyPatternCursor));  
			moneyPatternCursor = moneyPatternCursor == 8 ? 1 : moneyPatternCursor + 1;  
		}
		
		// 截取小数部分
		 String	fractionPart = cMoneyStringBuffer.substring(cMoneyStringBuffer.indexOf(".")); 
		
		// 删除字符串缓冲区小数点后面的字符串
		cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("."),cMoneyStringBuffer.length());  
	
		// 将"零拾"替换为"零"
		while (cMoneyStringBuffer.indexOf("零拾") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零拾"),cMoneyStringBuffer.indexOf("零拾") + 2, ZERO); 
		}  	
		
		// 将"零拾"替换为"零"
		while (cMoneyStringBuffer.indexOf("零拾") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零拾"),cMoneyStringBuffer.indexOf("零拾") + 2, ZERO); 
		}  

		// 只要字符按串中有"零佰"，则将"零佰"替换为"零"
		while (cMoneyStringBuffer.indexOf("零佰") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零佰"),cMoneyStringBuffer.indexOf("零佰") + 2, ZERO);
		}
		
		// 只要字符按串中有"零仟"，则将"零仟"替换为"零"
		while (cMoneyStringBuffer.indexOf("零仟") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零仟"), cMoneyStringBuffer.indexOf("零仟") + 2, ZERO); 
		}  
		
		// 只要字符按串中有"零万"，则将"零万"替换为"万"
		while (cMoneyStringBuffer.indexOf("零万") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零万"),cMoneyStringBuffer.indexOf("零万") + 2, TEN_THOUSAND); 
		}  
		
		// 只要字符按串中有"零亿"，则将"零亿"替换为"亿"
		while (cMoneyStringBuffer.indexOf("零亿") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零亿"),cMoneyStringBuffer.indexOf("零亿") + 2, HUNDRED_MILLION); 
		}  
	 
		// 只要字符按串中有"零零"，则将"零零"替换为"零"
		while (cMoneyStringBuffer.indexOf("零零") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零零"),cMoneyStringBuffer.indexOf("零零") + 2, ZERO); 
		}  
		
		// 如果最后一个"零"的字符索引等于字符串长度-1，也就是说最后一个字符为零并且不是唯一的一个零的话，删除最后一个字符"零"，追加小数部分
		if (cMoneyStringBuffer.lastIndexOf(ZERO) == cMoneyStringBuffer.length() - 1 && cMoneyStringBuffer.length() != 1)
		{
			cMoneyStringBuffer.delete(cMoneyStringBuffer.length() - 1,cMoneyStringBuffer.length()); 
		} 
		
		cMoneyStringBuffer.append(fractionPart); 
		result = cMoneyStringBuffer.toString();  
		return result; 
	}
	 
	/**
	 * 为大写字符串添加单位
	 * 
	 * @param moneyStr
	 * @return String
	 */
	private String addUnitsToChineseMoneyString(String moneyStr) 
	{  
		String result; 
		
		// 将传入的字符串存入StringBuffer
		StringBuffer cMoneyStringBuffer = new StringBuffer(moneyStr); 
		
		// 找到小数点的索引
		int indexOfDot = cMoneyStringBuffer.indexOf(DOT);
		
		// 加上单位"圆"
		cMoneyStringBuffer.replace(indexOfDot, indexOfDot + 1, YUAN);
		
		// 如果只有一位小数，加上单位"角"
		if(cMoneyStringBuffer.length()-1 == indexOfDot+1)
		{
			cMoneyStringBuffer.insert(cMoneyStringBuffer.length(), JIAO);
		}
		// 如果有两位小数，加上单位"角"、"分"
		else if(cMoneyStringBuffer.length()-1 == indexOfDot+2)
		{
			cMoneyStringBuffer.insert(cMoneyStringBuffer.length() - 1, JIAO); 
			cMoneyStringBuffer.insert(cMoneyStringBuffer.length(), FEN);
		}
		
		// 没有零头，加整
		if (cMoneyStringBuffer.indexOf("零角零分") != -1)
		{
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零角零分"),cMoneyStringBuffer.length(), "整");  
		}
		// 有零分，加整
		else if (cMoneyStringBuffer.indexOf("零分") != -1)
		{
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零分"),cMoneyStringBuffer.length(), "整"); 
		}
		// 没有零角，去掉
		else if (cMoneyStringBuffer.indexOf("零角") != -1)
		{  
				cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("零角"), cMoneyStringBuffer.indexOf("零角") + 2); // tmpBuffer.append("整"); 
		}
		// 最后精确到角，添加整
		else if(cMoneyStringBuffer.indexOf("角") != -1 && cMoneyStringBuffer.indexOf("角")== cMoneyStringBuffer.length()-1 )
		{
			cMoneyStringBuffer.append("整");
		}
		
		// 最后精确到圆，加整
		if(cMoneyStringBuffer.indexOf("圆") != -1 && cMoneyStringBuffer.indexOf("圆")== cMoneyStringBuffer.length()-1 )
		{
			cMoneyStringBuffer.append("整");
		}
		
		result = cMoneyStringBuffer.toString(); 
		return result; 
	}  
	
	/**
	 * 检验字符串包含2位以内的小数，否则抛出异常
	 * 
	 * @param moneyStr
	 */
	private String checkPrecision(String moneyStr) 
	{
		// 如果有小数点
		if(moneyStr.indexOf(DOT) != -1)
		{
			// 获取阿拉伯数字字符串小数部分的位数
			int fractionDigits = moneyStr.length() - moneyStr.indexOf(DOT) - 1; 
			
			// 如果小数部分位数大于2 ,格式化
			if (fractionDigits > 2)
			{
				moneyStr = numberFormat.format(Double.parseDouble(moneyStr)); //精度不能比分低
			}  
		}
		
		return  moneyStr;
	}  

}  



