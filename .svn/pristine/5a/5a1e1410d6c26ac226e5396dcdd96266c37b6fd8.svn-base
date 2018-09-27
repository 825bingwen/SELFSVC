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
 * –¬∞Ê’Àµ•≤È—Ø
 * @author xkf57421
 * @version 1.0
 * @since  201202
 */
public class BillQueryHubAction extends BaseAction
{
	private static final long serialVersionUID = 1321321321L;
	
	private static final String BILLINFO = "billinfo";
	
	private static final String SCOREINFO = "scoreinfo";
	
	private static final String BALANCEINFO = "balanceinfo";
	
	private static final String SPBILL = "spbill";
	
	private static final String PKGINFO = "pkginfo";
	
	private static NserCustomerSimp customerSimp = null;
	
	private BillQueryHubBean billQueryHubBean;
	
	private int i = 0;
	
	public String toCurBillDetail() throws Exception
	{
		customerSimp = (NserCustomerSimp)getRequest().getSession().getAttribute(Constants.USER_INFO);
		
		return "curBillDetail";
	}
	
	public String curBillDetail() throws IOException
	{
		i++;
		System.out.println("curBillDetail()" + i);
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
			System.out.println("curBillDetail()" + getMonth() + "|" + getCurMenuid() + "|" + getCustType());
			
			
			CTagSet billInfo = billQueryHubBean.qryCurMonBillInfo(getNserCustomer(), getTerminalInfo(), 
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
	
	public String curBillBalance() throws IOException
	{
		i++;
		System.out.println("curBillBalance()" + i);
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
			
			CTagSet billBalance = billQueryHubBean.qryCurMonMvalue(customer, getTerminalInfo(), 
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
				
				billBalanceInfo = pb.parseBillBalance(customer.getServNumber(),billBalXml,BALANCEINFO,getCustType());
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
	
	public String toCurBillSelfSv() throws Exception
	{
		return "curBillSelfSv";
	}
	
	public String curBillSelfSv() throws IOException
	{
		i++;
		System.out.println("curBillSelfSv()" + i);
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
			CTagSet billInfo = billQueryHubBean.qryCurMonBillInfo(getNserCustomer(), getTerminalInfo(), 
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
	
	public String toCurBillAgentSv() throws Exception
	{
		return "curBillAgentSv";
	}
	
	public String curBillAgentSv() throws IOException
	{
		i++;
		System.out.println("curBillAgentSv()" + i);
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
			
			CTagSet billAsv = billQueryHubBean.qryCurMonMvalue(getNserCustomer(), getTerminalInfo(), 
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
	
	public String toCurBillCommDetail() throws Exception
	{
		return "curBillCommDetail";
	}
	
	public String curBillCommDetail() throws Exception
	{
		i++;
		System.out.println("curBillCommDetail()" + i);
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
			
			CTagSet billComm = billQueryHubBean.qryCurMonMvalue(getNserCustomer(), getTerminalInfo(), 
					getMonth(), getCurMenuid(),getCustType(),PKGINFO);
			
			if(null == billComm)
			{
				billCommDetail = pb.createErrorMsg(getNserCustomer().getServNumber(),PKGINFO);
			}
			else
			{
				String billCommXml = billComm.GetValue(PKGINFO);
				billCommDetail = pb.parseBillComm(getNserCustomer().getServNumber(),billCommXml,PKGINFO);
			}

			
			*/
			String billCommDetail = billQueryHubBean.arQryBillCommuHuBExcelEbus(getNserCustomer(), getTerminalInfo(), 
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
	
	public NserCustomerSimp getNserCustomer()
	{
		return customerSimp;
	}
	
	public TerminalInfoPO getTerminalInfo()
	{
		TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		return terminalInfo;
	}
	
	public void setBillQueryHubBean(BillQueryHubBean billQueryHubBean)
	{
		this.billQueryHubBean = billQueryHubBean;
	}
	
	public String getMonth()
	{
		return (String)getRequest().getSession().getAttribute("BILL_MONTH");
	}
	
	public String getCurMenuid()
	{
		return (String)getRequest().getSession().getAttribute("BILL_MENUID");
	}
	
	public String getQryType()
	{
		return (String)getRequest().getSession().getAttribute("BILL_QRYTYPE");
	}
	
	public String getCustType()
	{
		return (String)getRequest().getSession().getAttribute("BILL_CUSTTYPE");
	}
}
