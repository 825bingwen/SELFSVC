/*
 * �ļ�����MonthFeeAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ����������˵���ѯ��Ӧ��action��
 */
package com.customize.cq.selfsvc.feeService.action;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.customize.cq.selfsvc.bean.MonthFeeBean;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.SendMailUtil;

/**
 * ���˵���ѯ
 * 
 * @author xkf29026
 * 
 */
public class MonthFeeAction extends BaseAction
{
//    private static Log logger = LogFactory.getLog(MonthFeeAction.class);
//    
//    private static final long serialVersionUID = -3950351941316700610L;
//    
//    private transient MonthFeeBean monthFeeBean = null;
//    
//    // begin zKF66389 findbus����
//    private String curMenuId = "";
//    // end zKF66389 findbus����
//    
//    private String month = "";
//    
//    private String billcycle;
//    
//    private String telnum;
//    
//    private String custName;
//    
//    private String brandID;
//    
//    private String needCheckMail; //  �Ƿ���Ҫ���û��Ѿ���ͨ�ֻ�������м��.1,��Ҫ��0,����Ҫ
//    
//    /**
//     * ��ѯ��ǰ���˵���Ϣ
//     * 
//     * @return
//     * @throws Exception
//     */
//    public String qryCurrentMonthBill() throws Exception
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("qryCurrentMonthBill Starting ...");
//        }
//        
//        // �ɲ�ѯ��ǰ�º�ǰ����µ��˵���Ϣ
//        String pattern = "yyyyMM";
//        this.setMonth(CommonUtil.getLastMonth(pattern, 0));
//        String month1 = CommonUtil.getLastMonth(pattern, 1);
//        String month2 = CommonUtil.getLastMonth(pattern, 2);
//        String month3 = CommonUtil.getLastMonth(pattern, 3);
//        String month4 = CommonUtil.getLastMonth(pattern, 4);
//        String month5 = CommonUtil.getLastMonth(pattern, 5);
//        
//        HttpServletRequest request = this.getRequest();
//        request.setAttribute("month", month);
//        request.setAttribute("month1", month1);
//        request.setAttribute("month2", month2);
//        request.setAttribute("month3", month3);
//        request.setAttribute("month4", month4);
//        request.setAttribute("month5", month5);
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("qryCurrentMonthBill End");
//        }
//        
//        return "qryCurrentMonthBill";
//    }
//    
//    /**
//     * ��ѯ�ǵ�ǰ�·ݵ��˵���Ϣ <������ϸ����>
//     * 
//     * @return
//     * @throws Exception
//     * @see [�ࡢ��#��������#��Ա]
//     */
//    public String qryMonthBill() throws Exception
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("qryMonthBill Starting ...");
//        }
//        
//        HttpServletRequest request = this.getRequest();
//        
//        // �û���Ϣ
//        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
//        
//        // �ն˻���Ϣ
//        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
//        
//        ReturnWrap rw = monthFeeBean.qryMonthBill(customerSimp.getServNumber(), terminalInfo, month, curMenuId,billcycle);
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//        	CRSet billData = (CRSet)rw.getReturnObject();
//        	
//        	String startDate = "";
//    		String endDate = "";
//    		CRSet brief = new CRSet(billData.GetColumnCount());			//ժҪ��Ϣ
//    		CRSet billfixed = new CRSet(billData.GetColumnCount());		//�ʵ�������Ϣ
//    		CRSet billflex = new CRSet(billData.GetColumnCount());		//�ʵ�������Ϣ
//    		CRSet acct = new CRSet(billData.GetColumnCount());			//�ʻ���Ϣ
//    		CRSet score = new CRSet(billData.GetColumnCount());			//������Ϣ
//    		CRSet sp = new CRSet(billData.GetColumnCount());			//���շ���Ϣ
//    		int rowCnt = billData.GetRowCount();
//        	int colCnt = billData.GetColumnCount();
//        	int briefCnt = 0;
//        	int infoCnt = 0;
//        	int feeCnt = 0;
//        	int acctCnt = 0;
//        	int scoreCnt = 0;
//        	int spCnt = 0;
//        	
//        	for(int i=0; i<rowCnt; i++)
//        	{
//        		if("brief".equals(billData.GetValue(i, 1)))
//        		{
//        			brief.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				brief.SetValue(briefCnt, j, billData.GetValue(i, j));
//        			}
//        			briefCnt ++;
//        		}
//        		else if("billfixed".equals(billData.GetValue(i, 1)))
//        		{
//        			billfixed.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				billfixed.SetValue(infoCnt, j, billData.GetValue(i, j));
//        			}
//        			infoCnt ++;
//        		}
//        		else if("billflex".equals(billData.GetValue(i, 1)))
//        		{
//        			billflex.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				billflex.SetValue(feeCnt, j, billData.GetValue(i, j));
//        			}
//        			feeCnt ++;
//        		}
//        		else if("acct".equals(billData.GetValue(i, 1)))
//        		{
//        			acct.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				acct.SetValue(acctCnt, j, billData.GetValue(i, j));
//        			}
//        			acctCnt ++;
//        		}
//        		else if("score".equals(billData.GetValue(i, 1)))
//        		{
//        			score.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				score.SetValue(scoreCnt, j, billData.GetValue(i, j));
//        			}
//        			scoreCnt ++;
//        		}
//        		else if("sp".equals(billData.GetValue(i, 1)))
//        		{
//        			sp.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				sp.SetValue(spCnt, j, billData.GetValue(i, j));
//        			}
//        			spCnt ++;
//        		}
//        	}
//        	
//            request.setAttribute("brief", brief);
//            request.setAttribute("billfixed", billfixed);
//            request.getSession().setAttribute("billflex", billflex);
//            request.setAttribute("acct", acct);
//            request.setAttribute("score", score);
//            request.setAttribute("sp", sp);
//            request.setAttribute("startDate", startDate);
//            request.setAttribute("endDate", endDate);
//            
//            request.setAttribute("printFlag", "1");
//            
//            this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "��ѯ" + month + "�·ݵ��˵���Ϣ�ɹ�");
//            
//            if (logger.isInfoEnabled())
//            {
//                logger.info("�û�" + customerSimp.getServNumber() + "��" + month + "�·ݵ��˵���Ϣ��ѯ�ɹ�");
//            }
//        }
//        else 
//        {
//        	request.setAttribute("errormessage", "δ��ѯ��" + month + "�·ݵ��˵���Ϣ");
//            
//        	this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "δ��ѯ��" + month + "�·ݵ��˵���Ϣ");
//            
//            logger.error("δ��ѯ���û�" + customerSimp.getServNumber() + "��" + month + "�·ݵ��˵���Ϣ");
//            
//            return "error";
//        }
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("qryMonthBill End");
//        }
//        
//        return "qryMonthBill";
//    }
//    
//    /**
//     * �˵�����
//     * @return 1:���ͳɹ���2:δ��ͨ139����
//     */
//    public void sendmail() throws IOException
//    {
//    	if (logger.isDebugEnabled())
//        {
//            logger.debug("MonthFeeAction - sendmail Starting ...");
//        }
//
//    	HttpServletRequest request = this.getRequest();
//    	request.setCharacterEncoding("GBK");
//        this.getResponse().setContentType("text/html;charset=GBK");
//    	
//        // �ն˻���Ϣ
//        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
//        
//        // �˵���������
//        String mailContent = "";
//        
//        ReturnWrap rw = monthFeeBean.qryMonthBill(telnum, terminalInfo, month, curMenuId,billcycle);
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//        	CRSet billData = (CRSet)rw.getReturnObject();
//        	
//        	String startDate = "";
//    		String endDate = "";
//    		CRSet brief = new CRSet(billData.GetColumnCount());			//ժҪ��Ϣ
//    		CRSet billfixed = new CRSet(billData.GetColumnCount());		//�ʵ�������Ϣ
//    		CRSet billflex = new CRSet(billData.GetColumnCount());		//�ʵ�������Ϣ
//    		CRSet acct = new CRSet(billData.GetColumnCount());			//�ʻ���Ϣ
//    		CRSet score = new CRSet(billData.GetColumnCount());			//������Ϣ
//    		CRSet sp = new CRSet(billData.GetColumnCount());			//���շ���Ϣ
//    		int rowCnt = billData.GetRowCount();
//        	int colCnt = billData.GetColumnCount();
//        	int briefCnt = 0;
//        	int infoCnt = 0;
//        	int feeCnt = 0;
//        	int acctCnt = 0;
//        	int scoreCnt = 0;
//        	int spCnt = 0;
//        	
//        	for(int i=0; i<rowCnt; i++)
//        	{
//        		if("brief".equals(billData.GetValue(i, 1)))
//        		{
//        			brief.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				brief.SetValue(briefCnt, j, billData.GetValue(i, j));
//        			}
//        			briefCnt ++;
//        		}
//        		else if("billfixed".equals(billData.GetValue(i, 1)))
//        		{
//        			billfixed.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				billfixed.SetValue(infoCnt, j, billData.GetValue(i, j));
//        			}
//        			infoCnt ++;
//        		}
//        		else if("billflex".equals(billData.GetValue(i, 1)))
//        		{
//        			billflex.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				billflex.SetValue(feeCnt, j, billData.GetValue(i, j));
//        			}
//        			feeCnt ++;
//        		}
//        		else if("acct".equals(billData.GetValue(i, 1)))
//        		{
//        			acct.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				acct.SetValue(acctCnt, j, billData.GetValue(i, j));
//        			}
//        			acctCnt ++;
//        		}
//        		else if("score".equals(billData.GetValue(i, 1)))
//        		{
//        			score.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				score.SetValue(scoreCnt, j, billData.GetValue(i, j));
//        			}
//        			scoreCnt ++;
//        		}
//        		else if("sp".equals(billData.GetValue(i, 1)))
//        		{
//        			sp.AddRow();
//        			for(int j=0; j<colCnt; j++)
//        			{
//        				sp.SetValue(spCnt, j, billData.GetValue(i, j));
//        			}
//        			spCnt ++;
//        		}
//        	}
//        	// �˴�ƴ�շ�������  
//        	mailContent = getMailContent(telnum,custName,brandID,brief,billfixed,billflex,acct,score,sp);
//        }
//        
//        String mailFlag = "";
//        
//        // �˵�����ʱ��
//        int time = 0;
//        if("1".equals(needCheckMail))
//        {
//        	/**
//             * �ж��û��Ƿ��Ѿ���ͨ�ֻ����䣬�û��Ѿ���ͨ�ֻ����䣬����з����ʼ���
//             */
//        	mailFlag = monthFeeBean.qrymailBox(telnum,terminalInfo,curMenuId);
//        }
//        else 
//        {
//        	mailFlag = "1";
//        	time = Integer.parseInt((String)PublicCache.getInstance().getCachedData("SH_MAIL_DELAY"));
//        }
//        
//        PrintWriter out = this.getResponse().getWriter();
//        if("1".equals(mailFlag))
//        {
//        	Timer timer = new Timer();
//        	timer.schedule(new SendMailUtil(telnum+"@139.com", "��"+month+"�·ݵĻ����˵��ѵ�������ա�",mailContent), new Date(System.currentTimeMillis() + time*60L*1000)); 
//        	
//        	if("1".equals(needCheckMail))
//        	{
//        		out.write("1");
//                out.flush();
//        	}
//        }
//        /**
//         * ����û�δ��ͨ������ʾ�û����п�ͨ���139���䡣���ڿ�ͨ���139����ģ��Ժ󼸷��ӷ����ʼ���
//         */
//        else
//        {
//        	// �û�û�п�ͨ�ֻ�����
//            out.write("2");
//            out.flush();
//        }
//    }
//    
//    /**
//     * ���췢���˵�����
//     * @param brief
//     * @param billfixed
//     * @param billflex
//     * @param acct
//     * @param score
//     * @param sp
//     * @return
//     */
//    public String getMailContent(String telnum,String custName,String brandID,CRSet brief,CRSet billfixed,CRSet billflex,CRSet acct,CRSet score,CRSet sp)
//    {
//    	// ���ݲ�ͬƷ����ʾ��ͬ����ʾ��Ϣ
//    	String logoTitle="";
//    	if("BrandGotone".equals(brandID))
//    	{
//    		logoTitle = "1";
//    	}
//    	else if ("BrandMzone".equals(brandID))
//    	{
//    		logoTitle = "2";
//    	}
//    	else // if ("BrandSzx".equals(brandID))
//    	{
//    		logoTitle = "3";
//    	}
//    	
//    	// ��ʾ�ͻ���Ϣ
//    	String startDate = CommonUtil.getBillValueByKey(billfixed,"startdate");
//    	String endDate = CommonUtil.getBillValueByKey(billfixed, "enddate");
//    	
//    	String custItem = "";
//    	custItem+="<tr><td width=\"13%\" rowspan=\"2\"></td><td style=\"width: 34%\"><span class=\"title02\">�ͻ����ƣ�</span>"+custName+"</td>";
//    	custItem+="<td width=\"35%\"><span class=\"title02\">�ֻ����룺</span>"+telnum+"</td><td width=\"23%\" rowspan=\"2\"></td></tr>";
//    	custItem+="<tr><td style=\"width: 34%\"><span class=\"title02\">�Ʒ����ڣ�</span>"+startDate.substring(0, 4)+"��"+startDate.substring(4, 6)+"��"+startDate.substring(6, 8)+"����"+endDate.substring(0, 4)+"��"+endDate.substring(4, 6)+"��"+endDate.substring(6, 8)+"��</td></tr>";
//    	
//    	// ������Ϣ��ʼ
//        String billContent1 ="";
//        String billContent2 ="";
//        String billTotalFee ="";
//        if (billflex != null) 
//		{
//        	int count = billflex.GetRowCount();
//			int num = count/2;
//			if(count/2 == 1)
//			{
//				num += 1;
//			}
//			StringBuffer buf1 = new StringBuffer(billContent1);
//			StringBuffer buf2 = new StringBuffer(billContent2);
//			for (int i = 0; i < num; i++) 
//			{
//			    buf1.append("<tr>").append(toStrong(billflex, i)).append("</tr>");
//				if (i + num < count - 1) 
//				{
//				    buf2.append("<tr>").append(toStrong(billflex, i + num)).append("</tr>");
//				}
//			}
//			billContent1 = buf1.toString();
//			billContent2 = buf2.toString();
//			
//			billTotalFee += "<tr><td colspan=\"2\" valign=\"top\"   class=\"paddingLeft12 bold bgEAEAEA\"><span class=\"STYLE18\">���úϼƣ� " + billflex.GetValue(billflex.GetRowCount() - 1,3) + "Ԫ</span></td></tr>";
//		}
//
//        // �˻�������Ϣ��ʼ
//        String acctItem = "";
//        if (acct != null) 
//		{
//        	acctItem+="<tr><td>���³�����</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"lastval"), "Ԫ") + "</tr>" +
//            "<tr><td>�˷�</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"backfee"), "Ԫ") + "</tr>" +
//            "<tr><td>����ת�����</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"transferout"), "Ԫ") + "</tr>" +
//            "<tr><td>����ת�����</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"transferin"), "Ԫ") + "</td></tr>" +
//            "<tr><td >Э�����·���</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"contractused"), "Ԫ") + "</td></tr>" +
//            "<tr><td>�����ۼ��ѽ���</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct, "income"), "Ԫ") + "</td></tr>" +
//            "<tr><td>���úϼ�</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"totalfee"), "Ԫ") + "</td></tr>" +
//            "<tr><td>ΥԼ��</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"latefee"), "Ԫ") + "</td></tr>" +
//            "<tr><td class='title02'>����ĩ����</td><td class='title02' align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"thisval"), "Ԫ") + "</td></tr>" +
//            "<tr><td>���ͷ�ʣ���</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"presentval"), "Ԫ") + "</td></tr>" +
//            "<tr><td>Э���ʣ���</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(acct,"contractval"), "Ԫ") + "</td></tr>";
//		}
//        
//        String scoreItem = "";
//        if(scoreItem != null)
//        {
//        	scoreItem+="<tr><td>����ĩʣ��ɶһ�����</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"lastval"), "��") + "</td></tr>" +
//            "<tr><td>������������</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"income"), "��")+ "</td></tr>"+
//            "<tr><td>���У����ѻ���</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"consume"), "��") + "</td></tr>"+
//            "<tr><td>���У�����ʱ�佱������</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"innet"), "��") + "</td></tr>"+
//            "<tr><td>���У�������������</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"otheraward"), "��") + "</td></tr>"+
//            "<tr><td >�����Ѷһ�����</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"thisused"), "��") + "</td></tr>"+
//            "<tr><td>����ĩʣ��ɶһ�����</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"thisval"), "��") + "</td></tr>"+
//            "<tr><td>��׽��������</td><td align='right'>" + fomartStr(CommonUtil.getBillValueByKey(score,"resetval"), "��") + "</td></tr>"+
//            "<tr><td>&nbsp;</td><td >&nbsp;</td></tr>"+
//            "<tr><td>&nbsp;</td><td >&nbsp;</td></tr>"+
//            "<tr><td>&nbsp;</td><td >&nbsp;</td></tr>"+
//            "<tr><td>&nbsp;</td><td >&nbsp;</td></tr>";
//        }
//        
//        // ������Ϣ����ϸ��ʼ
//        String spItem = "";
//        if (sp != null && sp.GetRowCount() > 0) 
//		{
//			String[] strSP;
//			BigDecimal fee = new BigDecimal("0");
//			int spCnt = sp.GetRowCount();
//			StringBuffer sbuf = new StringBuffer(spItem);
//			for (int i = 0; i < spCnt-1; i++) 
//			{
//				if (i < 3) 
//				{
//					strSP = sp.GetValue(i, 2).split("  ");
//					
//					sbuf.append("<tr><td width=\"13%\" align=\"center\">").append(strSP[0]).append("</td><td width=\"10%\" align=\"center\">").append(strSP[1]).append("</td><td width=\"13%\" align=\"center\">").append(strSP[2]).append("</td><td width=\"12%\" align=\"center\" >").append(sp.GetValue(i, 3)).append("</td></tr>");
//				} 
//				else 
//				{
//					fee = fee.add(new BigDecimal(sp.GetValue(i, 3)));
//				}
//			}
//			spItem = sbuf.toString();
//			if (fee.doubleValue() > 0) 
//			{
//
//				spItem+="<tr><td width=\"13%\" align=\"center\">����</td><td width=\"10%\" align=\"center\"></td><td width=\"13%\" align=\"center\"></td><td width=\"12%\" align=\"center\">" + fee.toString() + "</td></tr>";
//			}
//			
//			spItem+="<tr><td width=\"13%\" align=\"center\">�ϼ�</td><td width=\"10%\" align=\"center\"></td><td width=\"13%\" align=\"center\"></td><td width=\"12%\" align=\"center\">" + sp.GetValue(spCnt - 1, 3) + "</td></tr>";
//		}
//        
//		String mailContent ="";
//        mailContent+="<html>";
//    	mailContent+="<head>";
//    	mailContent+="    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\">";
//    	mailContent+="    <style type=\"text/css\" media=\"screen\"><!--";
//    	mailContent+="        body {";
//    	mailContent+="            background-color:#ffffff;";
//    	mailContent+="            font-size:14px;";
//    	mailContent+="            font-family:'';";
//    	mailContent+="            overflow-x:auto;";
//    	mailContent+="            overflow-y:hidden;";
//    	mailContent+="        }";
//    	mailContent+="    //--></style>";
//    	mailContent+="    <base target=\"_blank\" />";
//    	mailContent+="    <title></title>";
//    	mailContent+="    <script type=\"text/javascript\">";
//    	mailContent+="        document.domain = window.location.host.substring(window.location.host.substring(0,window.location.host.lastIndexOf(\".\")).lastIndexOf(\".\")+1);";
//    	mailContent+="    </script>";
//    	mailContent+="</head>";
//    	mailContent+="<body>";
//    	mailContent+="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
//    	mailContent+="<html xmlns=\"http://www.w3.org/1999/xhtml\">";
//    	mailContent+="<head>";
//    	mailContent+="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />";
//    	mailContent+="<title>�й��ƶ�ͨ�ſͻ��˵�</title>";
//    	mailContent+="<style type=\"text/css\">";
//    	mailContent+="";
//    	mailContent+="body, td, th {";
//    	mailContent+="	font-family:\"����\", Arial, Helvetica, sans-serif;";
//    	mailContent+="	font-size: 12px;";
//    	mailContent+="	color: #333333;";
//    	mailContent+="	line-height:21px;";
//    	mailContent+="}";
//    	mailContent+="body {";
//    	mailContent+="	background-color: #CCCCCC;";
//    	mailContent+="	margin-left: 0px;";
//    	mailContent+="	margin-top: 0px;";
//    	mailContent+="	margin-right: 0px;";
//    	mailContent+="	margin-bottom: 0px;";
//    	mailContent+="}";
//    	mailContent+=".padding5 {";
//    	mailContent+="	padding:5px;";
//    	mailContent+="}";
//    	mailContent+=".STYLE14 {";
//    	mailContent+="	color: #336600;";
//    	mailContent+="}";
//    	mailContent+=".paddingLeftRight35 {";
//    	mailContent+="	padding:15px 35px;";
//    	mailContent+="}";
//    	mailContent+=".bold {";
//    	mailContent+="	font-weight:bold";
//    	mailContent+="}";
//    	mailContent+=".paddingLeft12 {";
//    	mailContent+="	padding-left:12px;";
//    	mailContent+="}";
//    	mailContent+=".paddingLeft25 {";
//    	mailContent+="	padding-left:25px;";
//    	mailContent+="}";
//    	mailContent+=".red {";
//    	mailContent+="	color:#f00";
//    	mailContent+="}";
//    	mailContent+=".title01 {";
//    	mailContent+="	background:#EAEAEA;";
//    	mailContent+="	border-bottom:solid 1px #666666;";
//    	mailContent+="	font-weight:bold";
//    	mailContent+="}";
//    	mailContent+=".title02 {";
//    	mailContent+="	color:#006699;";
//    	mailContent+="	font-weight:bold";
//    	mailContent+="}";
//    	mailContent+=".content td {";
//    	mailContent+="	padding-left:12px;";
//    	mailContent+="}";
//    	mailContent+=".margintop10 {";
//    	mailContent+="	margin-top:10px";
//    	mailContent+="}";
//    	mailContent+=".STYLE92 {";
//    	mailContent+="	color: #006699;";
//    	mailContent+="}";
//    	mailContent+=".STYLE21 {";
//    	mailContent+="	color: #CC3300;";
//    	mailContent+="}";
//    	mailContent+=".STYLE7 {";
//    	mailContent+="	font-size: 12px;";
//    	mailContent+="	line-height: 20px;";
//    	mailContent+="	font-weight: bold;";
//    	mailContent+="	color: #006699;";
//    	mailContent+="}";
//    	mailContent+=".bgWhite{ background:#fff;}";
//    	mailContent+=".bg666{ background:#666;}";
//    	mailContent+=".bgEAEAEA{ background:#EAEAEA}";
//    	mailContent+="a img{ border:none} ";
//    	mailContent+="";
//    	mailContent+="</style>";
//    	mailContent+="</head>";
//    	mailContent+="<body  style=\"overflow:scroll\">";
//    	mailContent+="<table width=\"760\" height=\"257\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td colspan=\"2\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index_title" + logoTitle + ".jpg\" width=\"760\" height=\"67\" alt=\"\" /></td>";
//    	mailContent+="  </tr>";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td colspan=\"2\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_02.gif\" width=\"760\" height=\"55\" alt=\"\" /></td>";
//    	mailContent+="  </tr>";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_03.gif\" width=\"759\" height=\"58\" alt=\"\" /></td>";
//    	mailContent+="    <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_04.gif\" width=\"1\" height=\"58\" alt=\"\" /></td>";
//    	mailContent+="  </tr>";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td width=\"760\" height=\"42\" colspan=\"2\" background=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_05.gif\">";
//    	mailContent+="    	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
//    	mailContent+=       custItem;
//    	mailContent+="      </table>";
//    	mailContent+="    </td>";
//    	mailContent+="  </tr>";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td colspan=\"2\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_06.gif\" width=\"760\" height=\"35\" alt=\"\" /></td>";
//    	mailContent+="  </tr>";
//    	mailContent+="</table>";
//    	mailContent+="<table width=\"760\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td width=\"540\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
//    	mailContent+="        <tr>";
//    	mailContent+="          <td background=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_07.gif\" class=\"paddingLeftRight35\">";
//    	mailContent+="          	<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\"  class=\"bg666\">";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td colspan=\"2\" align=\"center\"  class=\"bold bgWhite\">������Ϣ����λ��Ԫ��</td>";
//    	mailContent+="              </tr>";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td width=\"33%\" valign=\"top\" class=\"bgWhite\">";
//    	mailContent+="                	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">";
//    	mailContent+="                    <tr>";
//    	mailContent+="                      <td class=\"title01 paddingLeft12\" style=\"width: 65%\">������Ŀ</td>";
//    	mailContent+="                      <td  width=\"35%\" class=\"title01\"><strong>����</strong>���</td>";
//    	mailContent+="                    </tr>";
//    	mailContent+=                     billContent1;
//    	mailContent+="                  </table>";
//    	mailContent+="                </td>";
//    	mailContent+="                <td width=\"33%\" valign=\"top\" class=\"bgWhite\">";
//    	mailContent+="                	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">";
//    	mailContent+="                    <tr>";
//    	mailContent+="                      <td class=\"title01 paddingLeft12\" style=\"width: 65%\">������Ŀ</td>";
//    	mailContent+="                      <td  width=\"35%\" class=\"title01\"><strong>����</strong>���</td>";
//    	mailContent+="                    </tr>";
//    	mailContent+=                     billContent2;
//    	mailContent+="                  </table>";
//    	mailContent+="                </td>";
//    	mailContent+="              </tr>";
//    	mailContent+=                     billTotalFee;
//    	mailContent+="            </table>";
//    	mailContent+="            <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"margintop10\" >";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td width=\"50%\" valign=\"top\">";
//    	mailContent+="                	<table width=\"98%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\"  class=\"bg666\">";
//    	mailContent+="                    <tr>";
//    	mailContent+="                      <td align=\"center\"   class=\"bold bgWhite\">�ʻ���Ϣ</td>";
//    	mailContent+="                    </tr>";
//    	mailContent+="                    <tr>";
//    	mailContent+="                      <td width=\"33%\" valign=\"top\" class=\"bgWhite\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">";
//    	mailContent+="                          <tr>";
//    	mailContent+="                            <td class=\"title01 paddingLeft12\" style=\"width: 64%\">�ʻ���Ŀ</td>";
//    	mailContent+="                            <td width=\"42%\" class=\"title01\">���/Ԫ</td>";
//    	mailContent+="                          </tr>";
//    	mailContent+=                             acctItem;
//    	mailContent+="                        </table>";
//    	mailContent+="                      </td>";
//    	mailContent+="                    </tr>";
//    	mailContent+="                  </table>";
//    	mailContent+="                </td>";
//    	mailContent+="                <td width=\"50%\" valign=\"top\"><table width=\"98%\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"1\"  class=\"bg666\">";
//    	mailContent+="                    <tr>";
//    	mailContent+="                      <td align=\"center\"  class=\"bold bgWhite\">������Ϣ</td>";
//    	mailContent+="                    </tr>";
//    	mailContent+="                    <tr>";
//    	mailContent+="                      <td width=\"33%\" valign=\"top\" class=\"bgWhite\">";
//    	mailContent+="                      	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"content\">";
//    	mailContent+="                          <tr>";
//    	mailContent+="                            <td class=\"title01 paddingLeft12\" style=\"width: 65%\">������Ŀ</td>";
//    	mailContent+="                            <td width=\"35%\" class=\"title01\">����ֵ</td>";
//    	mailContent+="                          </tr>";
//    	mailContent+=                           scoreItem;
//    	mailContent+="                        </table>";
//    	mailContent+="                      </td>";
//    	mailContent+="                    </tr>";
//    	mailContent+="                  </table>";
//    	mailContent+="                </td>";
//    	mailContent+="              </tr>";
//    	mailContent+="            </table>";
//    	mailContent+="            <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\"  class=\"margintop10 bg666\">";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td width=\"66%\" colspan=\"3\" align=\"center\" class=\"bgWhite\"><span class=\"bold\">������Ϣ����ϸ</span></td>";
//    	mailContent+="              </tr>";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td colspan=\"3\" class=\"bgWhite\"><table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">";
//    	mailContent+="                    <tr class=\"bgEAEAEA\">";
//    	mailContent+="                      <td width=\"13%\" align=\"center\"  class=\"title01\">�����̣�SP��</td>";
//    	mailContent+="                      <td width=\"10%\" align=\"center\"  class=\"title01\">�������</td>";
//    	mailContent+="                      <td width=\"13%\" align=\"center\"  class=\"title01\">����ҵ������</td>";
//    	mailContent+="                      <td width=\"12%\" align=\"center\"  class=\"title01\">����/Ԫ</td>";
//    	mailContent+="                    </tr>";
//    	mailContent+=                     spItem;
//    	mailContent+="                  </table>";
//    	mailContent+="                </td>";
//    	mailContent+="              </tr>";
//    	mailContent+="            </table>";
//    	mailContent+="            <table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"margintop10\">";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td width=\"11%\" align=\"right\" valign=\"top\" class=\"STYLE13\">��ע��</td>";
//    	mailContent+="                <td width=\"89%\"><p class=\"STYLE21\">1���ʵ���������ݽ����ο���ʵ�ʽɷ��������й��ƶ�ͨ�ż���ɽ�����޹�˾���շ�ƾ֤Ϊ׼��<br />";
//    	mailContent+="                  2�����ڸ��ʼ����߱��ظ����ܣ��벻Ҫ�ظ����ʼ��������н����������벦��ͻ���������10086��ӳ��<br />";
//    	mailContent+="                  <span lang=\"EN-US\" xml:lang=\"EN-US\">3</span>����ӭȫ��ͨ�û���¼<span lang=\"EN-US\" xml:lang=\"EN-US\"><a href=\"http://jf.10086.cn/\" target=\"_blank\">jf.10086.cn</a></span>�������һ���<br />";
//    	mailContent+="                  <br />";
//    	mailContent+="                  �ͷ����ߣ�10086 ��ַ��<a href=\"http://sd.10086.cn/\">sd.10086.cn</a><br />";
//    	mailContent+="������Ժ������յ����ʵ������¼ɽ���ƶ���վ���ڡ�<a href=\"http://www.sd.10086.cn/newecare/common/index.jsp?menuID=sendEmail&randnum=9354.912194773477\">����Ӫҵ�������ѷ��񣭶��ƻ�����Ϣ</a>��ҳ���˶����˵���<br /><span lang=\"EN-US\" xml:lang=\"EN-US\">4</span>��ɽ���ƶ���ܰ��ʾ���ֻ�����<span lang=\"EN-US\" xml:lang=\"EN-US\"><U><a href=\"wap.sd.10086.cn\" target=\"_blank\">wap.sd.10086.cn</a></U></span>����Ӫҵ������ʱ��ز�ѯ���ѡ�����ҵ����<span lang=\"EN-US\" xml:lang=\"EN-US\">GPRS</span>�����ѣ������Ϊ<span lang=\"EN-US\" xml:lang=\"EN-US\">cmwap</span>������ӭʹ�ã�<br />";
//    	mailContent+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�й��ƶ�ͨ�ż���ɽ�����޹�˾ ����</p>";
//    	mailContent+="                </td>";
//    	mailContent+="              </tr>";
//    	mailContent+="              <tr>";
//    	mailContent+="                <td colspan=\"2\" align=\"left\" valign=\"top\" class=\"STYLE13\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/logo.jpg\" width=\"102\" height=\"33\" /></td>";
//    	mailContent+="              </tr>";
//    	mailContent+="            </table></td>";
//    	mailContent+="        </tr>";
//    	mailContent+="        <tr>";
//    	mailContent+="          <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_09.gif\" width=\"540\" height=\"18\" /></td>";
//    	mailContent+="        </tr>";
//    	mailContent+="      </table></td>";
//    	mailContent+="    <td width=\"220\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
//    	mailContent+="      <tr>";
//    	mailContent+="        <td><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_08.gif\" width=\"220\" height=\"49\" /></td>";
//    	mailContent+="      </tr>";
//    	mailContent+="      ";
//    	mailContent+="    </table>";
//    	mailContent+="      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style=\"border:solid 2px #fff; \">";
//    	mailContent+="      <tr>";
//    	mailContent+="        <td height=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"homemail\" clicklog=\"true\" thingid=\"80001\">";
//    	mailContent+="<img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/205133.jpg\" width=\"205\" height=\"133\" /></a></td>";
//    	mailContent+="      </tr>";
//    	mailContent+="      <tr>";
//    	mailContent+="        <td height=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"greetingcard\" clicklog=\"true\" thingid=\"80002\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/huodong1.jpg\" width=\"205\" height=\"133\" /></a></td>";
//    	mailContent+="      </tr>";
//    	mailContent+="      <tr>";
//    	mailContent+="        <td eight=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"weblink\" clicklog=\"true\" thingid=\"80003\"><img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/wlsq.jpg\" width=\"205\" height=\"133\" /></a></td>";
//    	mailContent+="      </tr>";
//    	mailContent+="      <tr>";
//    	mailContent+="        <td height=\"140\"><a id=\"139Command_LinksShow\" href=\"http://mail.10086.cn\" rel=\"calendar\" clicklog=\"true\" thingid=\"80006\">";
//    	mailContent+="";
//    	mailContent+="<img src=\"http://fun.mail.10086.cn/sd/1007/0578/images/rctx5.jpg\" width=\"205\" height=\"133\" /></a></td>";
//    	mailContent+="      </tr>";
//    	mailContent+="    </table></td>";
//    	mailContent+="  </tr>";
//    	mailContent+="</table>";
//    	mailContent+="<table width=\"760\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" background=\"http://fun.mail.10086.cn/sd/1007/0578/images/index3003_109.gif\">";
//    	mailContent+="  <tr>";
//    	mailContent+="    <td height=\"35\" align=\"center\" class=\"paddingLeft12\"><a href=\"http://www.miibeian.gov.cn/\"> ";
//    	mailContent+="	��ICP��05002571��</a> �й��ƶ�ͨ�Ű�Ȩ���� �ͷ��绰��10086</td>";
//    	mailContent+="  </tr>";
//    	mailContent+="</table>";
//    	mailContent+="<img width=0 height=0 src=\"http://interface.mail.10086.cn/UrlRedirect/UserReplay.aspx?BatchLot=1282011083&UserNumber=15864500526&OtherInfo=\" />";
//    	mailContent+="</body>";
//    	mailContent+="</html>";
//    	mailContent+="";
//    	mailContent+="</body>";
//    	mailContent+="</html>";
//    	mailContent+="<!-- CM 3.0.x -->";
//        
//		return mailContent;
//    }
//    
//    /**
//     * ��ͨ139�ֻ�������Ѱ�
//     * @return
//     */
//    public void provideMailService() throws IOException
//    {
//    	HttpServletRequest request = this.getRequest();
//    	request.setCharacterEncoding("GBK");
//        this.getResponse().setContentType("text/html;charset=GBK");
//        
//        // �ն˻���Ϣ
//        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
//        
//        // �Ƿ�ͨ��־ 1����ͨ�ɹ���0����ͨʧ��
//        String addMailFlag = "";
//        
//        // ���ýӿڿ�ͨ139�ֻ�����
//        String addMailStatus = monthFeeBean.add139Mail(telnum, terminalInfo, curMenuId);
//        if ("1".equals(addMailStatus))
//        {
//        	addMailFlag = "1";
//        }
//        else
//        {
//        	addMailFlag = "0";
//        }
//        PrintWriter out = this.getResponse().getWriter();
//        out.write(addMailFlag);
//        out.flush();
//    }
//    
//    /**
//     * ���ѷ���ͼ
//     */
//    public String billPieChart()
//    {
//    	HttpServletRequest request = this.getRequest();
//    	HttpServletResponse response = this.getResponse();
//    	
//    	CRSet myConsume = (CRSet)request.getSession().getAttribute("billflex");
//        if(myConsume == null)
//        {
//        	return null;
//        }
//		Vector vDetailFee = new Vector();
//        for(int i=0; i<myConsume.GetRowCount(); i++)
//        {
//            if(myConsume.GetValue(i,1).indexOf(" ")!=0)
//            {
//                vDetailFee.add(new CEntityString(myConsume.GetValue(i,2)));
//                vDetailFee.add(new CEntityString(myConsume.GetValue(i,3)));
//            }
//        }
//       request.getSession().removeAttribute("billflex");
//    	
//       try 
//       {
//			response.setContentType("image/jpeg");
//			DefaultPieDataset data = new DefaultPieDataset();
//			// ��÷�����ϸ
//
//			String fee = null;
//			String[] feeName = {"�»�����","����ͨ�ŷ�","��ֵҵ���","���շ�","���շ�"};
//			Arrays.sort(feeName);
//			for (int ix = 0; ix < vDetailFee.size() - 1; ix += 2) 
//			{
//				if(Arrays.binarySearch(feeName,((CEntityString)vDetailFee.get(ix)).EntityString) < 0)
//				{
//					continue;
//				}
//				// ȡ������
//				fee = ((CEntityString) vDetailFee.get(ix + 1)).EntityString;
//
//				if (!fee.equals("0")) 
//				{
//					data.setValue(((CEntityString) vDetailFee.get(ix)).EntityString,Float.parseFloat(fee));
//				}
//			}
//
//			PiePlot plot = new PiePlot(data);// ��ͼ
//			JFreeChart chart = new JFreeChart("",
//					JFreeChart.DEFAULT_TITLE_FONT, plot, false);
//			chart.setBackgroundPaint(java.awt.Color.white);// ��ѡ������ͼƬ����ɫ
//			plot.setStartAngle(180);
//			
//			plot.setLabelBackgroundPaint(java.awt.Color.white);//���ñ�ǩ����ɫΪ��ɫ
//			plot.setShadowPaint(java.awt.Color.white);//��ȥ��ͼ�����Ӱ
//			plot.setLabelGenerator(new StandardPieSectionLabelGenerator( "{0}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance() ));//ȥ����ǩ������� 
//			plot.setLabelOutlinePaint(java.awt.Color.white);//��ȥ��ǩ�߿�
//			plot.setOutlinePaint(java.awt.Color.white);//��ȥͼƬ�߿�
//			plot.setLabelShadowPaint(java.awt.Color.white);//��ȥ��ǩ����Ӱ
//			plot.setLabelFont(new Font("����", Font.TRUETYPE_FONT, 11));
//		
//			//ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 100,chart, 300, 120, null);
//			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 300, 150, null);
//			
//			return null;
//		} 
//       catch (Exception e) 
//       {
//			e.printStackTrace();
//		}
//       return null;
//    }
//    
//    public String toStrong(CRSet crset, int i) 
//    {
//		String rtn = "";
//		String src = crset.GetValue(i, 2);
//		if (src.indexOf(" ") == 0) 
//		{
//			rtn="<td>" + src.replaceAll(" ", "&nbsp;") + "</td><td align='right'>" +(src.substring(0,src.lastIndexOf(" "))).replaceAll(" ","&nbsp;")+ crset.GetValue(i, 3).replaceAll(" ", "")+"</td>";
//		} 
//		else 
//		{
//			rtn ="<td class='title02'>" + src.trim() + "</td><td class='title02' align='right'>" + crset.GetValue(i, 3).trim() + "</td>";
//		}
//		return rtn;
//    }
//    
//    public String fomartStr(String src, String unit) 
//	{
//		if (src == null || "".equals(src.trim())) 
//		{
//			return "";
//		}
//
//		try 
//		{
//			if (new Double(src).doubleValue() > 0) 
//			{
//				return src + unit;
//			} else 
//			{
//				return "";
//			}
//		} 
//		catch (NumberFormatException ex) 
//		{
//			return "";
//		}
//	}
//    
//    public String getMonth()
//    {
//        return month;
//    }
//    
//    public void setMonth(String month)
//    {
//        this.month = month;
//    }
//    
//    public MonthFeeBean getMonthFeeBean()
//    {
//        return monthFeeBean;
//    }
//    
//    public void setMonthFeeBean(MonthFeeBean monthFeeBean)
//    {
//        this.monthFeeBean = monthFeeBean;
//    }
//    
//    public String getBillcycle()
//    {
//        return billcycle;
//    }
//    
//    public void setBillcycle(String billcycle)
//    {
//        this.billcycle = billcycle;
//    }
//
//    public String getNeedCheckMail() 
//    {
//		return needCheckMail;
//	}
//
//	public void setNeedCheckMail(String needCheckMail) 
//	{
//		this.needCheckMail = needCheckMail;
//	}
//
//	// begin zKF66389 findbus����
//	public String getCurMenuId() {
//		return curMenuId;
//	}
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//	// end zKF66389 findbus����
//
//	public String getTelnum() 
//	{
//		return telnum;
//	}
//
//	public void setTelnum(String telnum) 
//	{
//		this.telnum = telnum;
//	}
//
//	public String getCustName() 
//	{
//		return custName;
//	}
//
//	public void setCustName(String custName) 
//	{
//		try 
//		{
//			this.custName = URLDecoder.decode(custName, "UTF-8");
//		} 
//		catch (UnsupportedEncodingException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public String getBrandID() 
//	{
//		return brandID;
//	}
//
//	public void setBrandID(String brandID) 
//	{
//		this.brandID = brandID;
//	}
}
