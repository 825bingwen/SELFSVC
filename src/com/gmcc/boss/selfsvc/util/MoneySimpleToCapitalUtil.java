package com.gmcc.boss.selfsvc.util;

import java.text.NumberFormat; 
import java.util.HashMap; 

/**
 * �����������ֽ��ת��Ϊ��д������
 * 
 * @author hWX5316476
 * @remark OR_SD_201402_278 ����Ϊ��������ϵͳ��ӡ�ķ�Ʊ��Ӵ�д��Ʊ���
 */
public class MoneySimpleToCapitalUtil { 
	 
	public static final String EMPTY = ""; 
	 
	public static final String ZERO = "��"; 
	 
	public static final String ONE = "Ҽ"; 
	 
	public static final String TWO = "��"; 
	 
	public static final String THREE = "��"; 
	 
	public static final String FOUR = "��"; 
	 
	public static final String FIVE = "��"; 
	 
	public static final String SIX = "½"; 
	 
	public static final String SEVEN = "��"; 
	 
	public static final String EIGHT = "��"; 
	 
	public static final String NINE = "��"; 
	 
	public static final String TEN = "ʰ";  
	 
	public static final String HUNDRED = "��";  
	 
	public static final String THOUSAND = "Ǫ";  
	 
	public static final String TEN_THOUSAND = "��"; 
	 
	public static final String HUNDRED_MILLION = "��"; 
	 
	public static final String YUAN = "Բ"; 
	 
	public static final String JIAO = "��"; 
	 
	public static final String FEN = "��";  
	 
	public static final String DOT = ".";  
	 
	private static MoneySimpleToCapitalUtil formatter = null;
	 
	private HashMap chineseNumberMap = new HashMap();
	
	private HashMap chineseMoneyPattern = new HashMap();
	  
	private NumberFormat numberFormat = NumberFormat.getInstance(); 
	
	/**
	 * ���췽��
	 */
	private MoneySimpleToCapitalUtil() 
	{  
		// numberFormat�������С��λ��
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
	 * MoneySimpleToCapitalUtil��̬ʵ��������
	 * 
	 * @return
	 */
	//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi ������:OR_huawei_201407_373_��̬���_�����ն�
	public static synchronized MoneySimpleToCapitalUtil getInstance() 
	{ 
		if (formatter == null)
		{
			formatter = new MoneySimpleToCapitalUtil(); 
		}
		return formatter;  
	}  
	//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi ������:OR_huawei_201407_373_��̬���_�����ն�
	/**
	 * ��ʽ�������������ַ���ת��Ϊ��д���
	 * 
	 * @param moneyStr
	 * @return String
	 */
	public String format(String moneyStr) 
	{ 
		// ����ַ���С��λ�Ƿ񳬹�2λ���������2λ����ʽ��
		moneyStr = checkPrecision(moneyStr);  
		
		String result;
		
		// ���ַ���ת��Ϊ��д
		result = convertToChineseNumber(moneyStr);
		
		// Ϊ��д�ַ�����ӵ�λ������дϰ�����
		result = addUnitsToChineseMoneyString(result); 
		
		return result; 
	}  
	 
	/**
	 * ����Ϊdouble���͸�ʽ������
	 * 
	 * @param moneyDouble
	 * @return String
	 */
	public String format(double moneyDouble) 
	{  
		return format(numberFormat.format(moneyDouble)); 
	}  
	 
	/**
	 * ����Ϊ�������͸�ʽ������
	 * @param moneyInt
	 * @return String
	 */
	public String format(int moneyInt) 
	{  
		return format(numberFormat.format(moneyInt)); 
	}  
	 
	/**
	 * ����Ϊlong���͸�ʽ������
	 * 
	 * @param moneyLong
	 * @return String
	 */
	public String format(long moneyLong) 
	{  
		return format(numberFormat.format(moneyLong));
	}  
	 
	/**
	 * ����Ϊ�ַ������͸�ʽ������
	 * @param moneyNum
	 * @return String
	 */
	public String format(Number moneyNum) 
	{ 
		return format(numberFormat.format(moneyNum)); 
	}  
	 
	/**
	 * ת��Ϊ��д���ķ���
	 * 
	 * @param moneyStr
	 * @return String
	 */
	private String convertToChineseNumber(String moneyStr) 
	{ 
		String result;
		
		StringBuffer cMoneyStringBuffer = new StringBuffer();
		
		// ѭ���������滻Ϊ��д
		for (int i = 0; i < moneyStr.length(); i++) 
		{  
			cMoneyStringBuffer.append(chineseNumberMap.get(moneyStr.substring(i, i + 1))); 
		}  
		
		// С���㲻���ڣ�׷�ӵ��ַ�����".����"
		if(cMoneyStringBuffer.indexOf(DOT) == -1)
		{
			cMoneyStringBuffer.append(".����");
		}
		
		// ��ȡ�ַ�����С��������
		int indexOfDot = cMoneyStringBuffer.indexOf(DOT);
		
		//������λ���α�
		int moneyPatternCursor = 1; 
		
		// ѭ����������λ��һֱ��ʮλ
		for (int i = indexOfDot - 1; i > 0; i--) 
		{  
			cMoneyStringBuffer.insert(i, chineseMoneyPattern.get(EMPTY 	+ moneyPatternCursor));  
			moneyPatternCursor = moneyPatternCursor == 8 ? 1 : moneyPatternCursor + 1;  
		}
		
		// ��ȡС������
		 String	fractionPart = cMoneyStringBuffer.substring(cMoneyStringBuffer.indexOf(".")); 
		
		// ɾ���ַ���������С���������ַ���
		cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("."),cMoneyStringBuffer.length());  
	
		// ��"��ʰ"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("��ʰ") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("��ʰ"),cMoneyStringBuffer.indexOf("��ʰ") + 2, ZERO); 
		}  	
		
		// ��"��ʰ"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("��ʰ") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("��ʰ"),cMoneyStringBuffer.indexOf("��ʰ") + 2, ZERO); 
		}  

		// ֻҪ�ַ���������"���"����"���"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("���") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("���"),cMoneyStringBuffer.indexOf("���") + 2, ZERO);
		}
		
		// ֻҪ�ַ���������"��Ǫ"����"��Ǫ"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("��Ǫ") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("��Ǫ"), cMoneyStringBuffer.indexOf("��Ǫ") + 2, ZERO); 
		}  
		
		// ֻҪ�ַ���������"����"����"����"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("����") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("����"),cMoneyStringBuffer.indexOf("����") + 2, TEN_THOUSAND); 
		}  
		
		// ֻҪ�ַ���������"����"����"����"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("����") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("����"),cMoneyStringBuffer.indexOf("����") + 2, HUNDRED_MILLION); 
		}  
	 
		// ֻҪ�ַ���������"����"����"����"�滻Ϊ"��"
		while (cMoneyStringBuffer.indexOf("����") != -1) 
		{  
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("����"),cMoneyStringBuffer.indexOf("����") + 2, ZERO); 
		}  
		
		// ������һ��"��"���ַ����������ַ�������-1��Ҳ����˵���һ���ַ�Ϊ�㲢�Ҳ���Ψһ��һ����Ļ���ɾ�����һ���ַ�"��"��׷��С������
		if (cMoneyStringBuffer.lastIndexOf(ZERO) == cMoneyStringBuffer.length() - 1 && cMoneyStringBuffer.length() != 1)
		{
			cMoneyStringBuffer.delete(cMoneyStringBuffer.length() - 1,cMoneyStringBuffer.length()); 
		} 
		
		cMoneyStringBuffer.append(fractionPart); 
		result = cMoneyStringBuffer.toString();  
		return result; 
	}
	 
	/**
	 * Ϊ��д�ַ�����ӵ�λ
	 * 
	 * @param moneyStr
	 * @return String
	 */
	private String addUnitsToChineseMoneyString(String moneyStr) 
	{  
		String result; 
		
		// ��������ַ�������StringBuffer
		StringBuffer cMoneyStringBuffer = new StringBuffer(moneyStr); 
		
		// �ҵ�С���������
		int indexOfDot = cMoneyStringBuffer.indexOf(DOT);
		
		// ���ϵ�λ"Բ"
		cMoneyStringBuffer.replace(indexOfDot, indexOfDot + 1, YUAN);
		
		// ���ֻ��һλС�������ϵ�λ"��"
		if(cMoneyStringBuffer.length()-1 == indexOfDot+1)
		{
			cMoneyStringBuffer.insert(cMoneyStringBuffer.length(), JIAO);
		}
		// �������λС�������ϵ�λ"��"��"��"
		else if(cMoneyStringBuffer.length()-1 == indexOfDot+2)
		{
			cMoneyStringBuffer.insert(cMoneyStringBuffer.length() - 1, JIAO); 
			cMoneyStringBuffer.insert(cMoneyStringBuffer.length(), FEN);
		}
		
		// û����ͷ������
		if (cMoneyStringBuffer.indexOf("������") != -1)
		{
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("������"),cMoneyStringBuffer.length(), "��");  
		}
		// ����֣�����
		else if (cMoneyStringBuffer.indexOf("���") != -1)
		{
			cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("���"),cMoneyStringBuffer.length(), "��"); 
		}
		// û����ǣ�ȥ��
		else if (cMoneyStringBuffer.indexOf("���") != -1)
		{  
				cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("���"), cMoneyStringBuffer.indexOf("���") + 2); // tmpBuffer.append("��"); 
		}
		// ���ȷ���ǣ������
		else if(cMoneyStringBuffer.indexOf("��") != -1 && cMoneyStringBuffer.indexOf("��")== cMoneyStringBuffer.length()-1 )
		{
			cMoneyStringBuffer.append("��");
		}
		
		// ���ȷ��Բ������
		if(cMoneyStringBuffer.indexOf("Բ") != -1 && cMoneyStringBuffer.indexOf("Բ")== cMoneyStringBuffer.length()-1 )
		{
			cMoneyStringBuffer.append("��");
		}
		
		result = cMoneyStringBuffer.toString(); 
		return result; 
	}  
	
	/**
	 * �����ַ�������2λ���ڵ�С���������׳��쳣
	 * 
	 * @param moneyStr
	 */
	private String checkPrecision(String moneyStr) 
	{
		// �����С����
		if(moneyStr.indexOf(DOT) != -1)
		{
			// ��ȡ�����������ַ���С�����ֵ�λ��
			int fractionDigits = moneyStr.length() - moneyStr.indexOf(DOT) - 1; 
			
			// ���С������λ������2 ,��ʽ��
			if (fractionDigits > 2)
			{
				moneyStr = numberFormat.format(Double.parseDouble(moneyStr)); //���Ȳ��ܱȷֵ�
			}  
		}
		
		return  moneyStr;
	}  

}  



