package com.customize.hub.selfsvc.feeservice.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customize.hub.selfsvc.bean.BillQueryHubBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 历史账单查询
 * @author  xkf57421
 * @version  [版本号, Mar 2, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HisBillQryHubAction extends BaseAction
{
	private static final long serialVersionUID = 21312312L;
	
	private static final String BILLINFO = "billinfo";
	
	private static final String BILLTREND = "billtrend";
	
	private static final String SCOREINFO = "scoreinfo";
	
	private static final String BALANCEINFO = "balanceinfo";
	
	private static final String RECOMMEND = "recommend";
	
	private static final String ACCTBALANCE = "acctbalance";
	
	private static final String SPBILL = "spbill";
	
	private static final String PKGINFO = "pkginfo";
	
	private static final String IODETAIL = "iodetail";
	
	private static NserCustomerSimp customerSimp = null;
	
	private BillQueryHubBean hisBillQryHubBean;
	
	private int i = 0;
	
	public String hisBillBalance() throws IOException
	{
		i++;
		System.out.println("hisBillBalance()" + i);
		Writer writer = null;
		
		NserCustomerSimp customer = 
					(NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billBalanceInfo = "";
			
			System.out.println("hisBillBalance()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billBalance = hisBillQryHubBean.qryCurMonMvalue(customer, getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(), SCOREINFO + "," + BALANCEINFO + "," + BILLINFO);
			
			if(null == billBalance)
			{
				billBalanceInfo = pb.createErrorMsg(customer.getServNumber(),BALANCEINFO);
			}
			else
			{
				String billBalXml = billBalance.GetValue(BALANCEINFO);
				
				String scoreInfoXml = billBalance.GetValue(SCOREINFO);
				
				String billInfoXml = billBalance.GetValue(BILLINFO);
				
				billBalXml += "|" + scoreInfoXml + "|" + billInfoXml;
				
				billBalanceInfo = pb.parseHllBalance(customer.getServNumber(),billBalXml,BALANCEINFO,getCustType());
			}
			writer.write(billBalanceInfo);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	
	public String toHisBillDetail() throws Exception
	{
		customerSimp = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
		
		return "hisBillDetail";
	}
	
	public String toHisBillTrendImg() throws Exception
	{
		return "hisBillTrendImg";
	}
	
	public String hisBillDetail() throws IOException
	{
		i++;
		System.out.println("hisBillDetail()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billDetail = "";
			System.out.println("hisBillDetail()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			
			CTagSet billInfo = hisBillQryHubBean.qryCurMonBillInfo(getNserCustomer(), getTerminalInfo(), 
						getMonth(), getCurMenuid(),getCustType(),BILLINFO);
				
			if(null == billInfo)
			{
				billDetail = pb.createErrorMsg(getNserCustomer().getServNumber(),BILLINFO);
			}
			else
			{
				String billInfoXml = billInfo.GetValue(BILLINFO);
				billDetail = pb.parseBillDetail(getNserCustomer().getServNumber(),billInfoXml,BILLINFO,getCustType());
			}
			
			writer.write(billDetail);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	public String hisBillTrendImg() throws IOException
	{
		i++;
		System.out.println("hisBillTrendImg()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billTrendImg = "";
			System.out.println("hisBillTrendImg()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			CTagSet billTrend = hisBillQryHubBean.qryCurMonBillTrend(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),BILLTREND);
			
			if(null == billTrend)
			{
				billTrendImg = pb.createErrorMsg(getNserCustomer().getServNumber(),BILLTREND);
			}
			else
			{
				String billTrendXml = billTrend.GetValue(BILLTREND);
				billTrendImg = pb.parseBillTrend(getNserCustomer().getServNumber(),
							billTrendXml,BILLTREND);
			}
			writer.write(billTrendImg);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			writer.close();
		}
		return null;
	}
	
	public String toHisBillStructImg() throws Exception
	{
		return "hisBillStructImg";
	}
	
	public String hisBillStructImg() throws IOException
	{
		i++;
		System.out.println("hisBillStructImg()" + i);
		Writer writer = null;
		
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billStuctImg = "";
			String billInfoXml = "";
			System.out.println("hisBillStructImg()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			CTagSet billInfo = hisBillQryHubBean.qryCurMonBillInfo(getNserCustomer(), getTerminalInfo(), 
						getMonth(), getCurMenuid(),getCustType(),BILLINFO);
			
			if(null == billInfo)
			{
				billStuctImg = pb.createErrorMsg(getNserCustomer().getServNumber(),"billstruct");
			}
			else
			{
				billInfoXml = billInfo.GetValue(BILLINFO);
				billStuctImg = pb.parseBillStruct(getNserCustomer().getServNumber(),
						billInfoXml,"billstruct");
			}
			writer.write(billStuctImg);
			writer.flush();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	public String toHisBillValuate() throws Exception
	{
		return "hisBillValuate";
	}
	
	public String hisBillValuate() throws Exception
	{
		i++;
		System.out.println("hisBillValuate()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billValuate = "";
			System.out.println("hisBillValuate()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billValSet = hisBillQryHubBean.qryBillValuate(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),RECOMMEND);
			
			if(null == billValSet)
			{
				billValuate = pb.createErrorMsg(getNserCustomer().getServNumber(),RECOMMEND);
			}
			else
			{
				String billRXml = billValSet.GetValue(RECOMMEND);
				billValuate = pb.parseBillRecommend(getNserCustomer().getServNumber(),billRXml,RECOMMEND);
			}
			writer.write(billValuate);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	
	public String toHisBillAccInfo() throws Exception
	{
		return "hisBillAccInfo";
	}
	
	public String hisBillAccInfo() throws Exception
	{
		i++;
		System.out.println("hisBillAccInfo()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billAccInfo = "";
			System.out.println("hisBillAccInfo()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billAccSet = hisBillQryHubBean.qryCurMonMvalue(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),ACCTBALANCE);
			
			if(null == billAccSet)
			{
				billAccInfo = pb.createErrorMsg(getNserCustomer().getServNumber(),ACCTBALANCE);
			}
			else
			{
				String billAccXml = billAccSet.GetValue(ACCTBALANCE);
				billAccInfo = pb.parseBillAcc(getNserCustomer().getServNumber(),billAccXml,ACCTBALANCE);
			}
			writer.write(billAccInfo);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	
	public String toHisBillMvalue() throws Exception
	{
		return "hisBillMvalue";
	}
	
	public String hisBillMvalue() throws IOException
	{
		i++;
		System.out.println("hisBillMvalue()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billMvalue = "";
			System.out.println("hisBillMvalue()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billM = hisBillQryHubBean.qryCurMonMvalue(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),SCOREINFO);
			
			if(null == billM)
			{
				billMvalue = pb.createErrorMsg(getNserCustomer().getServNumber(),SCOREINFO);
			}
			else
			{
				String billMXml = billM.GetValue(SCOREINFO);
				billMvalue = pb.parseBillMvalue(getNserCustomer().getServNumber(),billMXml,SCOREINFO);
			}
			writer.write(billMvalue);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	public String toHisBillSelfSv() throws Exception
	{
		return "hisBillSelfSv";
	}
	
	public String hisBillSelfSv() throws Exception
	{
		i++;
		System.out.println("hisBillSelfSv()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billSelfSv = "";
			String billInfoXml = "";
			
			System.out.println("hisBillSelfSv()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billInfo = hisBillQryHubBean.qryCurMonBillInfo(getNserCustomer(), getTerminalInfo(), 
						getMonth(), getCurMenuid(),getCustType(),BILLINFO);
			
			if(null == billInfo)
			{
				billSelfSv = pb.createErrorMsg(getNserCustomer().getServNumber(),"billselfsv");
			}
			else
			{
				billInfoXml = billInfo.GetValue(BILLINFO);
				billSelfSv = pb.parseBillSelfSv(getNserCustomer().getServNumber(),
						billInfoXml,"billselfsv");
			}
			writer.write(billSelfSv);
			writer.flush();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	
	public String toHisBillAgentSv() throws Exception
	{
		return "hisBillAgentSv";
	}
	
	public String hisBillAgentSv() throws IOException
	{
		i++;
		System.out.println("hisBillAgentSv()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billAgentSv = "";
			
			System.out.println("hisBillAgentSv()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billAsv = hisBillQryHubBean.qryCurMonMvalue(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),SPBILL);
			
			if(null == billAsv)
			{
				billAgentSv = pb.createErrorMsg(getNserCustomer().getServNumber(),SPBILL);
			}
			else
			{
				String billAsvXml = billAsv.GetValue(SPBILL);
				billAgentSv = pb.parseBillAgentSv(getNserCustomer().getServNumber(),billAsvXml,SPBILL);
			}
			writer.write(billAgentSv);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	public String toHisBillCommDetail() throws Exception
	{
		return "hisBillCommDetail";
	}
	
	
	public String hisBillCommDetail() throws Exception
	{
		i++;
		System.out.println("hisBillCommDetail()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			/*
			String billCommDetail = "";
			System.out.println("hisBillCommDetail()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billComm = hisBillQryHubBean.qryBillValuate(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),PKGINFO);
			
			if(null == billComm)
			{
				billCommDetail = pb.createErrorMsg(getNserCustomer().getServNumber(),PKGINFO);
			}
			else
			{
				String billCommXml = billComm.GetValue(PKGINFO);
				billCommDetail = pb.parseBillComm(getNserCustomer().getServNumber(),billCommXml,PKGINFO);
			}*/
			String billCommDetail = hisBillQryHubBean.arQryBillCommuHuBExcelEbus(getNserCustomer(), getTerminalInfo(), 
                    getMonth(), getCurMenuid(),getCustType(),PKGINFO);
            if(billCommDetail==null)
            {
                billCommDetail = pb.createErrorMsg(getNserCustomer().getServNumber(),PKGINFO);
            }
			writer.write(billCommDetail);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	
	public String toHisBillAccDetail() throws Exception
	{
		return "hisBillAccDetail";
	}
	
	
	public String hisBillAccDetail() throws Exception
	{
		i++;
		System.out.println("hisBillAccDetail()" + i);
		Writer writer = null;
		try
		{
			ParseBillUtils pb = new ParseBillUtils();
			HttpServletResponse response = getResponse();
			HttpServletRequest request = getRequest();
			response.setContentType("text/xml;charset=GBK");
	        request.setCharacterEncoding("GBK");
			writer = response.getWriter();
			String billAccDetail = "";
			System.out.println("hisBillAccDetail()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			CTagSet billAccSet = hisBillQryHubBean.qryBillValuate(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),IODETAIL);
			
			if(null == billAccSet)
			{
				billAccDetail = pb.createErrorMsg(getNserCustomer().getServNumber(),IODETAIL);
			}
			else
			{
				String billAccXml = billAccSet.GetValue(IODETAIL);
				billAccDetail = pb.parseBillIoDetail(getNserCustomer().getServNumber(),billAccXml,IODETAIL);
			}
			writer.write(billAccDetail);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(null != writer)
			{
				writer.close();
			}
		}
		return null;
	}
	
	public void setHisBillQryHubBean(BillQueryHubBean hisBillQryHubBean)
	{
		this.hisBillQryHubBean = hisBillQryHubBean;
	}

	public NserCustomerSimp getNserCustomer()
	{
		return customerSimp;
	}
	
	public TerminalInfoPO getTerminalInfo()
	{
		TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		return terminalInfo;
	}

	public String getMonth()
	{
		return (String)getRequest().getSession().getAttribute("HIS_BILL_MONTH");
	}
	
	public String getCurMenuid()
	{
		return (String)getRequest().getSession().getAttribute("HIS_BILL_MENUID");
	}
	
	public String getQryType()
	{
		return (String)getRequest().getSession().getAttribute("HIS_BILL_QRYTYPE");
	}
	
	public String getCustType()
	{
		return (String)getRequest().getSession().getAttribute("HIS_BILL_CUSTTYPE");
	}
}
