package com.customize.hub.selfsvc.feeservice.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 新版账单查询
 * @author xkf57421
 * @version  [版本号, Feb 10, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("unchecked")
public class ParseBillUtils
{
	private static final String NODE_NAME_FEE = "fee";
	private static final String TREND_NODE_NAME_BILL = "bill";
	private static final String NODE_NAME_FEEGROUP = "feegroup";
	private static final String NODE_NAME_SP = "sp";
	
	private static final String NODE_NAME_PKG = "pkg";
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public String parseBillDetail(String servNumber,String billInfoXml,String areaFlag,String custType)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billInfoXml = billInfoXml.replaceAll("<!\\[CDATA\\[", "")
									 .replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billInfoXml);
			Element billInfo = doc.getRootElement();
			
			
			Element billUnite = null;
			Element billUniteFixed = null;
			List<Element> uniteList = null;
			if("1".equals(custType))
			{
				billUnite = billInfo.element("billunite");
				billUniteFixed = billUnite.element("billfixed");
				uniteList = billUniteFixed.elements();
			}
			
			Element billNoUnite = billInfo.element("billnounite");
			
			Element billFixed = null;
			if(null != billNoUnite)
			{
				billFixed = billNoUnite.element("billfixed");
			}
			
			List<Element> list = new ArrayList<Element>();
			if(null != billFixed)
			{
				list = billFixed.elements();
			}
			if(null == list || list.isEmpty()){
				return createWarnMsg("您当期账期未发生账单，未查询到账单信息!");
			}
			
			int i=0;
			String nodeName = "row" + i;
			xmlBuff.append("<billdetail>");
			
			if("1".equals(custType))
			{
			    // modify begin yKF73963 2012-08-03 OR_HUB_201202_1192 
			    xmlBuff.append("<").append(nodeName).append(">").append("费用信息%合户账户，含本机在内共2个号码%append")
                .append("</").append(nodeName).append(">");
			    i++;
			    nodeName = "row" + i;
				xmlBuff.append("<").append(nodeName).append(">").append("费用项目%合户总消费(元)%本机消费(元)")
					.append("</").append(nodeName).append(">");
				 // modify end yKF73963 2012-08-03 OR_HUB_201202_1192 
			}
			else
			{
				xmlBuff.append("<").append(nodeName).append(">").append("费用项目%本机消费(元)")
					.append("</").append(nodeName).append(">");
			}
			
			int uniteIndex = 0;
			
			for(Element e : list)
			{
				if(NODE_NAME_FEE.equalsIgnoreCase(e.getName()))
				{
					String value = formatFee(e.elementText("value"));
					String uniteFee = "0";
					if("1".equals(custType))
					{
						uniteFee = getUniteFee(uniteIndex,uniteList);
						uniteFee = formatFee(uniteFee);
					}
					if("他人代付".equals(e.elementText("name")) || 
							   "集团代付".equals(e.elementText("name")))
					{
						if(null == value || 0d == Double.parseDouble(value))
						{
							uniteIndex++;
							continue;
						}
						uniteFee = "/";
					}
					i++;
					nodeName = "row" + i;
					xmlBuff.append("<").append(nodeName).append(">");
					
					if("1".equals(custType))
					{
							xmlBuff.append(e.elementText("name"))
								   .append("%").append(uniteFee)
								   .append("%").append(value);
					}
					else
					{
						xmlBuff.append(e.elementText("name"))
							   .append("%").append(value);
					}
					xmlBuff.append("</").append(nodeName).append(">");
					
				}
				else if(NODE_NAME_FEEGROUP.equalsIgnoreCase(e.getName()))
				{
					
					String name = e.elementText("name");
					i++;
					nodeName = "row" + i;
					xmlBuff.append("<").append(nodeName).append(">");
					xmlBuff.append(name);
					xmlBuff.append("</").append(nodeName).append(">");
					Element subFee = e.element("subfee");
					List<Element> feeList = subFee.elements();
					
					int subFeeIndex = 0;
					for(Element fee : feeList)
					{
							i++;
							nodeName = "row" + i;
							xmlBuff.append("<").append(nodeName).append(">");
							
							//#标示居中显示且前面加黑点
							if("1".equals(custType))
							{
								String uniteSubFee = getUniteSubFee(uniteIndex,subFeeIndex,uniteList);
								
								uniteSubFee = formatFee(uniteSubFee);
								
								String value = formatFee(fee.elementText("value"));
								
								xmlBuff.append("#").append(fee.elementText("name"))
								   .append("%").append(uniteSubFee)
								   .append("%").append(value);
							}
							else
							{
								String value = formatFee(fee.elementText("value"));
								
								xmlBuff.append("#").append(fee.elementText("name"))
							       .append("%").append(value);
							}
							subFeeIndex++;
							xmlBuff.append("</").append(nodeName).append(">");
					}
					
				}
				else
				{
					return createErrorMsg(servNumber,areaFlag);
				}
				uniteIndex++;
				
			}
			xmlBuff.append("</billdetail>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		
		System.out.println(xmlBuff.toString());
		
		return xmlBuff.toString();
	}
	
	public String parseBillTrend(String servNumber,String billTrendXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billTrendXml = billTrendXml.replaceAll("<!\\[CDATA\\[", "")
										.replaceAll("\\]\\]>", "");

			Document doc = DocumentHelper.parseText(billTrendXml);
			Element billTrend = doc.getRootElement();
			
			List<Element> list = new ArrayList<Element>();
			if(null != billTrend)
			{
				list = billTrend.elements();
			}
			
			if(null == list || list.isEmpty())
			{
				return createWarnMsg("您当前账期未发生账单，无法查询消费趋势图!");
			}
			
			int i=0;
			String nodeName = "";
			xmlBuff.append("<billhalfyeartrend>");
			for(Element e : list)
			{
				if(TREND_NODE_NAME_BILL.equalsIgnoreCase(e.getName()))
				{
					i++;
					nodeName = "row" + i;
					xmlBuff.append("<").append(nodeName).append(">");
					xmlBuff.append(e.elementText("month")).append("%").
						append(e.elementText("money"));
					xmlBuff.append("</").append(nodeName).append(">");
				}
				else
				{
					return createErrorMsg(servNumber,areaFlag);
				}
				
			}
			xmlBuff.append("</billhalfyeartrend>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		return xmlBuff.toString();
	}
	
	public String parseBillMvalue(String servNumber,String billMXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billMXml = billMXml.replaceAll("<!\\[CDATA\\[", "")
							   .replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billMXml);
			Element scoreInfo = doc.getRootElement();
			
			List<Element> list = scoreInfo.elements();
			List<String> rowList = new ArrayList<String>(); 
			int i=0;
			String nodeName = "";
			xmlBuff.append("<scoreinfo>");
			for(Element e : list)
			{
				String needNode = transMvName(e.getName());
				if(null != needNode && !"".equals(needNode))
				{
					rowList.add(needNode + "%" + e.getText());
				}
				continue;
			}
			rowList = sortRowList(rowList);
			for(String rowInfo : rowList)
			{
				int index = rowInfo.indexOf("%");
				rowInfo = rowInfo.substring(0, index -1) + rowInfo.substring(index);
				i++;
				nodeName = "row" + i;
				xmlBuff.append("<").append(nodeName).append(">");
				xmlBuff.append("<![CDATA[").append(rowInfo).append("]]>");
				xmlBuff.append("</").append(nodeName).append(">");
			}
			
			xmlBuff.append("</scoreinfo>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	
	public String parseBillSelfSv(String servNumber,String billInfoXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billInfoXml = billInfoXml.replaceAll("<!\\[CDATA\\[", "")
			 .replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billInfoXml);
			Element billInfo = doc.getRootElement();
			
			Element billNoUnite = billInfo.element("billnounite");
			
			Element feeDetails = null;
			if(null != billNoUnite)
			{
				feeDetails = billNoUnite.element("feedetails");
			}
			
			List<Element> list = new ArrayList<Element>();
			if(null != feeDetails)
			{
				list = feeDetails.elements();
			}
			if(null == list || list.isEmpty()){
				return createWarnMsg("您当前账期未发生账单，未查询到自有业务费用明细!");
			}
			
			xmlBuff.append("<billselfsv>");
			int i=0;
			String nodeName = "";
			double allValue = 0.00;
			for(Element e : list)
			{
				if(NODE_NAME_FEEGROUP.equalsIgnoreCase(e.getName()))
				{
					    String name = e.elementText("name");
						i++;
						nodeName = "row" + i;
						xmlBuff.append("<").append(nodeName).append(">");
						//@标示大结点
						xmlBuff.append("@").append(name);
						xmlBuff.append("</").append(nodeName).append(">");
						Element subFee = e.element("subfee");
						List<Element> feeList = subFee.elements();
						for(Element fee : feeList)
						{
							
							if(checkIsEmptyFee(fee.elementText("value")))
							{
								i++;
								nodeName = "row" + i;
								xmlBuff.append("<").append(nodeName).append(">");
								
								//#标示子结点
								String value = formatFee(fee.elementText("value"));
								xmlBuff.append("#").append(fee.elementText("name"))
										.append("%").append(value);
								
								xmlBuff.append("</").append(nodeName).append(">");
							}
							
						}
						i++;
						nodeName = "row" + i;
						xmlBuff.append("<").append(nodeName).append(">");
						Element feeElement = e.element("fee");
						
						String value = formatFee(feeElement.elementText("value"));
						
						allValue += Double.parseDouble(value);
						
						xmlBuff.append("#").append(feeElement.elementText("name"))
							   .append("%").append(value);
						
						xmlBuff.append("</").append(nodeName).append(">");
				}
				else
				{
					return createErrorMsg(servNumber,areaFlag);
				}
			}
			
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append("费用合计")
			   .append("%").append(df.format(allValue));
		    xmlBuff.append("</").append(nodeName).append(">");
			
			xmlBuff.append("</billselfsv>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	private List<String> sortRowList(List<String> list)
	{
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) {
				
				int tag = getOrderValue(s1);
				int tagAnother = getOrderValue(s2);
				if (tag > tagAnother) 
				{
					return 1;
				}
				else if (tag == tagAnother)
				{
					return 0;
				} 
				else 
				{
					return -1;
				}
			}
		});
		return list;
	}
	
	private int getOrderValue(String s1)
	{
		int index = s1.indexOf("%");
		
		String sort = s1.substring(index-1,index);
		
		return Integer.parseInt(sort);
	}

	public String transMvName(String nodeName)
	{
		Map<String,String> mvMap = new HashMap<String,String>();
		mvMap.put("lastavail", "上期可用M值2");
		mvMap.put("consume", " + 本期新增M值3");
		mvMap.put("award", " + 本期奖励M值4");
		mvMap.put("transin", " - 本期转入M值5");
		mvMap.put("exchange", " - 本期兑换M值6");
		mvMap.put("transout", " - 本期转出7");
		mvMap.put("clear", " - 本期作废8");
		mvMap.put("thisavail", "可用M值 = 1");
		
		return mvMap.get(nodeName);
		
	}


	public String createErrorMsg(String servNumber, String areaFlag)
	{
		StringBuffer errBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		errBuff.append("<error>").append(servNumber).append("%")
			.append(areaFlag).append("</error>");
		
		return errBuff.toString();
	}
	
	public String createWarnMsg(String warnMsg)
	{
		StringBuffer warnBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		warnBuff.append("<warn>").append(warnMsg).append("</warn>");
		return warnBuff.toString();
	}
	
	public String parseBillStruct(String servNumber,String billInfoXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billInfoXml = billInfoXml.replaceAll("<!\\[CDATA\\[", "")
			 .replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billInfoXml);
			Element billInfo = doc.getRootElement();

			Element billNoUnite = billInfo.element("billnounite");
			Element billFixed = null;
			if(null != billNoUnite)
			{
				billFixed = billNoUnite.element("billfixed");
			}
			
			List<Element> list = new ArrayList<Element>();
			if(null != billFixed)
			{
				list = billFixed.elements();
			}
			
			if(null == list || list.isEmpty())
			{
				return createWarnMsg("您当前账期未发生账单，无法查询消费结构图!");
			}
			
			xmlBuff.append("<billstruct>");
			int i=0;
			String nodeName = "";
			for(Element e : list)
			{
				if(NODE_NAME_FEE.equalsIgnoreCase(e.getName()))
				{
						i++;
						nodeName = "row" + i;
						xmlBuff.append("<").append(nodeName).append(">");
						xmlBuff.append(e.elementText("name")).append("%").
							append(e.elementText("value"));
						xmlBuff.append("</").append(nodeName).append(">");
					
				}
				else if(NODE_NAME_FEEGROUP.equalsIgnoreCase(e.getName()))
				{
						
						Element subFee = e.element("subfee");
						List<Element> feeList = subFee.elements();
						for(Element fee : feeList)
						{
							i++;
							nodeName = "row" + i;
							xmlBuff.append("<").append(nodeName).append(">");
							xmlBuff.append(fee.elementText("name")).append("%").
								append(fee.elementText("value"));
							xmlBuff.append("</").append(nodeName).append(">");
						}
				}
				else
				{
					return createErrorMsg(servNumber,areaFlag);
				}
				
			}
			xmlBuff.append("</billstruct>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
		
	}
	
	
	public String parseBillAgentSv(String servNumber,String billAsvXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billAsvXml = billAsvXml.replaceAll("<!\\[CDATA\\[", "")
			   						.replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billAsvXml);
			Element spbill = doc.getRootElement();
			
			List<Element> list = spbill.elements();
			int i=0;
			String nodeName = "";
			xmlBuff.append("<billagentsv>");
			Double totalFee = 0.00;
			DecimalFormat df = new DecimalFormat("0.00");
			for(Element e : list)
			{
				if(NODE_NAME_SP.equalsIgnoreCase(e.getName()))
				{
						i++;
						nodeName = "row" + i;
						xmlBuff.append("<").append(nodeName).append(">");
						
						String value = formatFee(e.elementText("fee"));
						
						xmlBuff.append(e.elementText("spcode")).append("%")
							.append(e.elementText("spname")).append("%")
							.append(e.elementText("servcode")).append("%")
							.append(e.elementText("usetype")).append("%")
							.append(e.elementText("feetype")).append("%")
							.append(value);
						
						xmlBuff.append("</").append(nodeName).append(">");
						
						totalFee += Double.parseDouble(value);
					
				}
				else
				{
					return createErrorMsg(servNumber,areaFlag);
				}
			}
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append(df.format(totalFee));
			xmlBuff.append("</").append(nodeName).append(">");
			xmlBuff.append("</billagentsv>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	public String parseBillComm(String servNumber,String billCommXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billCommXml = billCommXml.replaceAll("<!\\[CDATA\\[", "")
				.replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billCommXml);
			Element pkginfo = doc.getRootElement();
			
			List<Element> list = pkginfo.elements();
			int i=0;
			String nodeName = "";
			xmlBuff.append("<pkginfo>");
			
			StringBuffer totalUsageDesc = new StringBuffer("总通信量");
			for(Element e : list)
			{
				if(NODE_NAME_PKG.equalsIgnoreCase(e.getName()))
				{
						
						if("999".equals(e.elementText("pkgid")))
						{
							
							totalUsageDesc.append("%").append(e.elementText("usagedesc"));
						}
						else
						{
							i++;
							nodeName = "row" + i;
							xmlBuff.append("<").append(nodeName).append(">");
							
							xmlBuff.append(e.elementText("pkgname")).append("%")
							   .append(e.elementText("pkgdesc")).append("%")
						       .append(e.elementText("usagedesc"));
							
							xmlBuff.append("</").append(nodeName).append(">");
						}
				}
				else
				{
					return createErrorMsg(servNumber,areaFlag);
				}
			}
			
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append(totalUsageDesc.toString());
			xmlBuff.append("</").append(nodeName).append(">");
			
			xmlBuff.append("</pkginfo>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	
	public String parseBillBalance(String servNumber,String billBalXml,String areaFlag,String custType)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billBalXml = billBalXml.replaceAll("<!\\[CDATA\\[", "")
				.replaceAll("\\]\\]>", "");
			
			String[] xmlArr = billBalXml.split("\\|");
			
			billBalXml = xmlArr[0];
			
			String scoreXml = xmlArr[1];
			
			String billInfoXml = xmlArr[2];
			
			Document doc = DocumentHelper.parseText(billBalXml);
			Element balanceinfo = doc.getRootElement();
			
			List<Element> list = balanceinfo.elements();
			int i=0;
			String nodeName = "";
			xmlBuff.append("<balance>");
			for(Element e : list)
			{
				i++;
				String fee = formatFee(String.valueOf(e.getData()));
				//modified by xkf57421 for OR_HUB_201204_399 话费账户余额 begin
				if(1 == i)
				{
					if(Double.parseDouble(fee) <= 0)
					{
						fee = "0.00";
					}
				}
				//modified by xkf57421 for OR_HUB_201204_399 话费账户余额 end
				
				//modified by xkf57421 如果专款，返回账户金额为0则不显示整栏 begin
				if(2 == i || 3 == i)
				{
					if(Double.parseDouble(fee) == 0d)
					{
						fee = "EMPTY";
					}
				}
				//modified by xkf57421 end
				
				nodeName = "row" + i;
				xmlBuff.append("<").append(nodeName).append(">");
				xmlBuff.append(fee);
				xmlBuff.append("</").append(nodeName).append(">");
				
			}
			
			//当期消费，分合户与非合户
			doc = DocumentHelper.parseText(billInfoXml);
			
			Element billFixed = null;
			
			if("1".equals(custType))
			{
				Element billunite = findElement("billunite", doc);
				billFixed = findElementByParent("billfixed", billunite);
			}
			else
			{
				Element noUnite = findElement("billnounite", doc);
				billFixed = findElementByParent("billfixed", noUnite);
			}
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append(getBillFixedTotal(billFixed));
			xmlBuff.append("</").append(nodeName).append(">");
			
			//个人积分余额
			doc = DocumentHelper.parseText(scoreXml);
			Element thisAvail = findElement("thisavail", doc);
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append(getNodeValue(thisAvail));
			xmlBuff.append("</").append(nodeName).append(">");
			
			xmlBuff.append("</balance>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	
	public String parseHllBalance(String servNumber,String billBalXml,String areaFlag,String custType)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			
			billBalXml = billBalXml.replaceAll("<!\\[CDATA\\[", "")
				.replaceAll("\\]\\]>", "");
			
			String[] xmlArr = billBalXml.split("\\|");
			
			billBalXml = xmlArr[0];
			
			String scoreXml = xmlArr[1];
			
			String billInfoXml = xmlArr[2];
			
			Document doc = DocumentHelper.parseText(billBalXml);
			Element balanceinfo = doc.getRootElement();
			
			int i=0;
			String nodeName = "";
			String[] limitArr = new String[]{"thisval","specialfee","contractval","debtfee"};
			xmlBuff.append("<balance>");
			
			for(String limit : limitArr)
			{
				Element e = findElementByParent(limit, balanceinfo);
				String fee = formatFee(String.valueOf(e.getData()));
				
				//modified by xkf57421 for OR_HUB_201204_399 话费账户余额 begin
				if("thisval".equals(limit))
				{
					if(Double.parseDouble(fee) <= 0)
					{
						fee = "0.00";
					}
				}
				//modified by xkf57421 for OR_HUB_201204_399 话费账户余额 end
				
				//modified by xkf57421 如果专款，返回账户金额为0则不显示整栏 begin
				if("specialfee".equals(limit) || "contractval".equals(limit))
				{
					if(Double.parseDouble(fee) == 0d)
					{
						fee = "EMPTY";
					}
				}
				//modified by xkf57421 end
				
				i++;
				nodeName = "row" + i;
				xmlBuff.append("<").append(nodeName).append(">");
				xmlBuff.append(fee);
				xmlBuff.append("</").append(nodeName).append(">");
			}
			
			//当期消费，分合户与非合户
			doc = DocumentHelper.parseText(billInfoXml);
			
			Element billFixed = null;
			
			if("1".equals(custType))
			{
				Element billunite = findElement("billunite", doc);
				billFixed = findElementByParent("billfixed", billunite);
			}
			else
			{
				Element noUnite = findElement("billnounite", doc);
				billFixed = findElementByParent("billfixed", noUnite);
			}
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append(getBillFixedTotal(billFixed));
			xmlBuff.append("</").append(nodeName).append(">");
			
			//个人积分余额
			doc = DocumentHelper.parseText(scoreXml);
			Element thisAvail = findElement("thisavail", doc);
			i++;
			nodeName = "row" + i;
			xmlBuff.append("<").append(nodeName).append(">");
			xmlBuff.append(getNodeValue(thisAvail));
			xmlBuff.append("</").append(nodeName).append(">");
			
			xmlBuff.append("</balance>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	public String parseBillRecommend(String servNumber,String billRecXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billRecXml = billRecXml.replaceAll("<!\\[CDATA\\[", "")
										.replaceAll("\\]\\]>", "");

			Document doc = DocumentHelper.parseText(billRecXml);
			Element custbill = doc.getRootElement();
			
			int i=0;
			String nodeName = "";
			xmlBuff.append("<custbill>");
			
			List<Element> recList = custbill.elements();
			
			for(Element e: recList)
			{
				i++;
				nodeName = "row" + i;
				xmlBuff.append("<").append(nodeName).append(">");
				xmlBuff.append(e.getData());
				xmlBuff.append("</").append(nodeName).append(">");
			}
			
			xmlBuff.append("</custbill>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		return xmlBuff.toString();
	}
	
	public String parseBillAcc(String servNumber,String billAccXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billAccXml = billAccXml.replaceAll("<!\\[CDATA\\[", "")
							   .replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billAccXml);
			Element acctbalance = doc.getRootElement();
			
			//pay otherpay - reverse abs
			String[] nodeArray = new String[]{"accttype","lastavail","income","reverse","quitfee","usable"
											 ,"pay","otherpay","currentavail"};
			
			List<Element> accList = acctbalance.elements();
			List<String> rowList = new ArrayList<String>();
			int i=0;
			String nodeName = "";
			for(Element acc : accList)
			{
				StringBuffer tempBuff = new StringBuffer();
				i++;
				nodeName = "row" + i;
				tempBuff.append("<").append(nodeName).append(">");
				
				boolean emptyFlag = false;
				String acctype = "1";
				String lastAvail = "0.00";
				String incomeAvail = "0.00";
				String reverseAvail = "0.00";
				String quitfeeAvail = "0.00";
				String usableAvail = "0.00";
				String payAvail = "0.00";
				String otherpayAvail = "0.00";
				String curAvail = "0.00";
				Element[] eleArr = new Element[nodeArray.length];
				int k=0;
				for(String node : nodeArray)
				{	
					eleArr[k] = findElementByParent(node, acc);
					k++;
				}
				acctype = String.valueOf(getNodeValue(eleArr[0]));
				lastAvail = formatFee(String.valueOf(getNodeValue(eleArr[1])));
				incomeAvail = formatFee(String.valueOf(getNodeValue(eleArr[2])));
				reverseAvail = formatFee(String.valueOf(getNodeValue(eleArr[3])));
				quitfeeAvail = formatFee(String.valueOf(getNodeValue(eleArr[4])));
				usableAvail = formatFee(String.valueOf(getNodeValue(eleArr[5])));
				payAvail = formatFee(String.valueOf(getNodeValue(eleArr[6])));
				otherpayAvail = formatFee(String.valueOf(getNodeValue(eleArr[7])));
				curAvail = formatFee(String.valueOf(getNodeValue(eleArr[8])));
				
				double temp4 = Math.abs(Double.parseDouble(reverseAvail));
				reverseAvail = formatFee(String.valueOf(temp4));
				
				if(!payAvail.equals("0.00"))
				{
					double temp5 = -Double.parseDouble(payAvail);
					payAvail = formatFee(String.valueOf(temp5));
				}
				if(!otherpayAvail.equals("0.00"))
				{
					double temp6 = -Double.parseDouble(otherpayAvail);
					otherpayAvail = formatFee(String.valueOf(temp6));
				}
				
				if("1".equals(acctype))
				{
					emptyFlag = true;
				}
				acctype = transAcctType(acctype);
				if(Double.parseDouble(lastAvail) < 0d)
				{
					double temp1 = Double.parseDouble(usableAvail) - Double.parseDouble(lastAvail);
					usableAvail = formatFee(String.valueOf(temp1));
					double temp2 = Double.parseDouble(payAvail) - Double.parseDouble(lastAvail);
					payAvail = formatFee(String.valueOf(temp2));
					lastAvail = "0.00";
				}
				if(Double.parseDouble(curAvail) < 0d)
				{
					double temp3 = Double.parseDouble(payAvail) + Double.parseDouble(curAvail);
					payAvail = formatFee(String.valueOf(temp3));
					curAvail = "0.00";
				}
				
				if(emptyFlag)
				{
					quitfeeAvail = "";
					usableAvail = "";
					payAvail = "";
				}
				
				tempBuff.append(acctype).append("%").append(lastAvail).append("%")
						.append(incomeAvail).append("%").append(reverseAvail).append("%")
						.append(quitfeeAvail).append("%").append(usableAvail).append("%")
						.append(payAvail).append("%").append(otherpayAvail).append("%")
						.append(curAvail);
				
				tempBuff.append("</").append(nodeName).append(">");
				rowList.add(tempBuff.toString());
			} 
			rowList = sortRowList(rowList);
			xmlBuff.append("<acctbalance>");
			for(String rowInfo: rowList)
			{
				rowInfo = rowInfo.replace("话费账户1", "话费账户");
				rowInfo = rowInfo.replace("返还账户3", "返还账户");
				rowInfo = rowInfo.replace("专款账户2", "专款账户");
				xmlBuff.append(rowInfo);
			}
			xmlBuff.append("</acctbalance>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	
	public String parseBillIoDetail(String servNumber,String billAccXml,String areaFlag)
	{
		StringBuffer xmlBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		
		try
		{
			billAccXml = billAccXml.replaceAll("<!\\[CDATA\\[", "")
							   .replaceAll("\\]\\]>", "");
			
			Document doc = DocumentHelper.parseText(billAccXml);
			Element iodetail = doc.getRootElement();
			
			List<Element> ioList = iodetail.elements();
			List<String> sortedList = new ArrayList<String>();
			for(Element io : ioList)
			{
				Element eleBillType = findElementByParent("billtype", io); 
				String billtype = String.valueOf(getNodeValue(eleBillType));
				//只显示入账
				if(!"1".equals(billtype))
				{
					continue;
				}
				
				StringBuffer strBuff = new StringBuffer();
				
				Element eleIntType = findElementByParent("intype", io);
				Element eleBillTime = findElementByParent("billtime", io); 
				Element eleWay = findElementByParent("way", io); 
				Element eleFee = findElementByParent("fee", io); 
				Element eleRemark = findElementByParent("payreason", io);
				
				String intType = String.valueOf(getNodeValue(eleIntType));
				strBuff.append(transIoDeType(intType)).append("%");
				
				String billTime = String.valueOf(getNodeValue(eleBillTime));
				strBuff.append(billTime).append("%");
				
				String way = String.valueOf(eleWay.getData());
				strBuff.append(way).append("%");
				
				String fee = String.valueOf(getNodeValue(eleFee));
				fee = formatFee(fee);
				strBuff.append(fee).append("%");
				
				String remark = String.valueOf(eleRemark.getData());
				//add by xkf57421 for OR_HUB_201204_655 begin
				int istart = remark.indexOf(",");
				if(istart > -1)
				{
					remark = remark.substring(istart + 1);
				}
				//add by xkf57421 for OR_HUB_201204_655 end
				
				strBuff.append(remark);
				
				sortedList.add(strBuff.toString());
			}
			
			sortedList = sortIoDetailList(sortedList);
			
			Map<String,String> indexMap = new HashMap<String,String>();
			int i=0;
			String nodeName = "";
			xmlBuff.append("<iodetail>");
			for(String rowInfo: sortedList)
			{
				int sIndex = rowInfo.indexOf("#");
				if(1 == sIndex)
				{
					String intType = rowInfo.substring(0,1);
					if(null == indexMap.get(intType))
					{
						i++;
						nodeName = "row" + i;
						xmlBuff.append("<").append(nodeName).append(">");
						xmlBuff.append("---------------------------------------");
						xmlBuff.append("</").append(nodeName).append(">");
					}
					indexMap.put(intType, intType);
					rowInfo = rowInfo.substring(2);
				}
				i++;
				nodeName = "row" + i;
				xmlBuff.append("<").append(nodeName).append(">");
				xmlBuff.append(rowInfo);
				xmlBuff.append("</").append(nodeName).append(">");
			}
			xmlBuff.append("</iodetail>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return createErrorMsg(servNumber,areaFlag);
		}
		System.out.println(xmlBuff.toString());
		return xmlBuff.toString();
	}
	
	
	
	public String getTotalComm(List<Element> list)
	{
		StringBuffer sb = new StringBuffer("总通信量：");
		
		Map<String,String> itemMap = new HashMap<String,String>();
		
		for(Element pkg : list)
		{
			Element privlist = pkg.element("privlist");
			
			List<Element> privs = privlist.elements();
			
			for(Element priv : privs)
			{
				String feeName = priv.elementText("feename");
				
				String unittype = priv.elementText("unittype");
				
				String usetotal = priv.elementText("usetotal");
				
				if(itemMap.containsKey(feeName))
				{
					String oldValue = itemMap.get(feeName);
					String[] values = oldValue.split("#");
					int oldUseTotal = Integer.parseInt(values[0]);
					int newUseTotal = oldUseTotal + Integer.parseInt(usetotal);
					itemMap.put(feeName, newUseTotal + "#" + unittype);
				}
				else
				{
					itemMap.put(feeName, usetotal + "#" + unittype);
				}
			}
		}
		
		Set mapSet = itemMap.entrySet();
		Iterator iter = mapSet.iterator();
		boolean flag = false;
		while(iter.hasNext())
		{
			flag = true;
			Map.Entry<String, String> entry = (Entry<String, String>) iter.next();
			
			sb.append(entry.getKey()).append("：").append(entry.getValue()).append("、");
		}
		if(flag)
		{
			sb = sb.deleteCharAt(sb.lastIndexOf("、"));
		}
		
		String initStr = sb.toString();
		
		initStr = initStr.replaceAll("#", "");
		
		return initStr;
	}
	

	private String getUniteFee(int uniteIndex, List<Element> uniteList)
	{
		
		if(uniteIndex < 0 || uniteIndex > (uniteList.size() -1))
		{
			return "";
		}
		
		return uniteList.get(uniteIndex).elementText("value");
		
	}
	
	private String getUniteSubFee(int uniteIndex, int subFeeIndex, List<Element> uniteList)
	{
		if(uniteIndex < 0 || uniteIndex > (uniteList.size() -1))
		{
			return "";
		}
		
		Element subFee = uniteList.get(uniteIndex).element("subfee");
		
		List<Element> feeList = subFee.elements();
		
		if(subFeeIndex < 0 || subFeeIndex > (feeList.size() - 1))
		{
			return "";
		}
		
		return feeList.get(subFeeIndex).elementText("value");
	}
	
	private boolean checkIsEmptyFee(String value)
	{
		try
		{
			if(null == value || Double.parseDouble(value) == 0d)
			{
				return false;
			}
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	private  Element findElement(String ename, Document document) throws Exception
    {

	    Element root = document.getRootElement();
	    Element legend = null;
	    for (Iterator i = root.elementIterator(ename); i.hasNext();)
	    {
	        legend = (Element)i.next();
	    }
        return legend;
    }
	
	private  Element findElementByParent(String ename, Element parent) throws Exception
    {
	    Element legend = null;
	    for (Iterator i = parent.elementIterator(ename); i.hasNext();)
	    {
	        legend = (Element)i.next();
	    }
        return legend;
    }
	
	private Object getNodeValue(Element e)
	{
		if(null == e || null == e.getData() || "".equals(e.getData()))
		{
			return "0";
		}
		
		return e.getData();
	}
	
	private String getBillFixedTotal(Element billFixed)
	{
		if(null == billFixed)
		{
			return "0";
		}
		
		List<Element> list = billFixed.elements();
		
		for(Element e: list)
		{
			if(NODE_NAME_FEE.equalsIgnoreCase(e.getName()))
			{
				if("合计".equals(e.elementText("name")))
				{
					String value = formatFee(e.elementText("value"));
					
					return value;
				}
			}
		}
		
		return "0";
	}
	
	
	private String formatFee(String fee)
	{
		try
		{
			if(null == fee || "".equals(fee.trim()))
			{
				fee = "0";
			}
			
			double value = Double.parseDouble(fee);
			
			if(String.valueOf(fee).indexOf(".") == -1)
			{
				value = value/100;
			}
			
			return df.format(value);
		}
		catch(Exception e)
		{
			return "0";
		}
	}
	
	private String transAcctType(String nodeValue)
	{
		if("0".equals(nodeValue))
		{
			return "话费账户1";
		}
		
		if("1".equals(nodeValue))
		{
			return "返还账户3";
		}
		
		if("2".equals(nodeValue))
		{
			return "专款账户2";
		}
		
		return nodeValue + "4";
	}
	
	/*private String transIoBlType(String billType,String fee)
	{
		if("1".equals(billType))
		{
			return fee + "%";
		}
		else if("2".equals(billType))
		{
			return "%" + fee;
		}
		else
		{
			return "%";
		}
	}*/

	private String transIoDeType(String intType)
	{
		if("1".equals(intType))
		{
			return "1#话费账户入账明细";
		}
		else if("2".equals(intType))
		{
			return "3#返还账户入账明细";
		}
		else if("3".equals(intType))
		{
			return "2#专款账户入账明细";
		}
		else
		{
			return intType;
		}
	}
	
	private int getIoDetailOrder(String s1)
	{
		int index = s1.indexOf("#");
		
		if(index < 0 || index > s1.length() -1)
		{
			return 6;
		}
		
		String sort = s1.substring(index-1,index);
		
		return Integer.parseInt(sort);
	}
	
	private List<String> sortIoDetailList(List<String> list)
	{
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) {
				
				int tag = getIoDetailOrder(s1);
				int tagAnother = getIoDetailOrder(s2);
				if (tag > tagAnother) 
				{
					return 1;
				}
				else if (tag == tagAnother)
				{
					return 0;
				} 
				else 
				{
					return -1;
				}
			}
		});
		return list;
	}
}
