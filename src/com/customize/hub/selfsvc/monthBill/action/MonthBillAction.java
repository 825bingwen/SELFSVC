package com.customize.hub.selfsvc.monthBill.action;



import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customize.hub.selfsvc.bean.MonthBillBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.waf.service.menu.Menu;

/**
 * 湖北月账单查询功能
 * 
 * @author xkf29026
 * 
 */
public class MonthBillAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 调用接口bean
    private MonthBillBean monthBillBean;
    
    // 当前菜单
    private String curMenuId;
    
    // 账单集合
    private CRSet billItems;
    
    // 月份
    private String month;
    
    // 查询类型，按账户0；按用户1
    private String qryType;
    // 增加月份导航信息
    private String[] months;

    /**
     * 转到选择账单月份页面
     * 
     * @return
     */
    public String selectBillMonth()
    {
        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyyMM";
        this.setMonth(CommonUtil.getLastMonth(pattern, 0));
        String month1 = CommonUtil.getLastMonth(pattern, 1);
        String month2 = CommonUtil.getLastMonth(pattern, 2);
        String month3 = CommonUtil.getLastMonth(pattern, 3);
        String month4 = CommonUtil.getLastMonth(pattern, 4);
        String month5 = CommonUtil.getLastMonth(pattern, 5);
        
        HttpServletRequest request = this.getRequest();
        request.setAttribute("month", month);
        request.setAttribute("month1", month1);
        request.setAttribute("month2", month2);
        request.setAttribute("month3", month3);
        request.setAttribute("month4", month4);
        request.setAttribute("month5", month5);
        
        return "selectBillMonth";
    }
    
    /**
     * 月账单查询
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
	public String queryMonthBill()
    {
        
        HttpServletRequest request = this.getRequest();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //modified by xkf57421 for 新版账单查询增加条件判断
        String dispatchFlag = "";
        dispatchFlag = getDispFlag(month,customerSimp.getBrandID());
        // 可查询当前月和前五个月的详单信息
        String[] monthsBak = new String[6];
        months = CommonUtil.getLastMonths(Constants.DATE_PATTERN_YYYYMM, 6);
        for (int i = 0; i <= 5; i ++)
        {
            monthsBak[5-i] = months[i];
        }
        months = monthsBak;
        
        if("queryMonthBill".equals(dispatchFlag))
        {
        	// 调用接口进行月账单查询
            billItems = monthBillBean.qryMonthBill(customerSimp, terminalInfo, month, curMenuId, qryType);
            if (billItems != null && billItems.GetRowCount() > 0)
            {
                for (int i = 0; i < billItems.GetRowCount(); i++)
                {
                    billItems.SetValue(i, 2, CommonUtil.fenToYuan(billItems.GetValue(i, 2).trim()));
                }
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "账单查询成功！");
                return "monthBillDetail";
            }
            else
            {
                String resultMsg = getConvertMsg("未查询到" + month + "月份的账单信息", "zz_04_17_000003", null, formatParams(month));
                
                request.setAttribute("errormessage", resultMsg);
                
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", resultMsg);
                
                return "error";
            }
        }
        else
        {
    		try
    		{
    			Vector resVec = monthBillBean.qryBillCustInfo(customerSimp, terminalInfo, month, curMenuId);
    			
    			CTagSet custSet = (CTagSet)resVec.get(0);
    			
    			String isUnitFlag = "0";
    			String unitenumc="";
    			
    			if(resVec.size() > 1)
    			{
    				CRSet cyclst = (CRSet)resVec.get(1);
    				
    				if(cyclst.GetRowCount()>0 && cyclst.GetColumnCount()> 4)
    				{
    					isUnitFlag = cyclst.GetValue(0, 4);
    					unitenumc=cyclst.GetValue(0, 5);
    				}
    			}
    			//modify begin yKF73963 2012-08-09 R00043250
    	        CRSet crset = new CRSet(6);
    			crset.AddRow();
    			//-------------------------
    			if(IntMsgUtil.isTransEBUS("CTCSQryBillCycleCustInfo"))
                {
    				crset.SetValue(0, 0, custSet.GetValue("custName"));
        			crset.SetValue(0, 1, customerSimp.getServNumber());
        			crset.SetValue(0, 2, "湖北" + custSet.GetValue("regionName"));
        			crset.SetValue(0, 3, custSet.GetValue("brandnm"));
        			crset.SetValue(0, 4, getBillCycle(month));
                }
    			else
    			{
    				crset.SetValue(0, 0, custSet.GetValue("CUSTNAME"));
        			crset.SetValue(0, 1, customerSimp.getServNumber());
        			crset.SetValue(0, 2, "湖北" + custSet.GetValue("REGIONNAME"));
        			crset.SetValue(0, 3, custSet.GetValue("BRANDNM"));
        			crset.SetValue(0, 4, getBillCycle(month));
        			//crset.SetValue(0, 5, custSet.GetValue("UNITENUM"));
    			}
    			//-------------------------
    			
    			
    			 // modify end yKF73963 2012-08-09 R00043250
    			String custInfo = toStrCust(crset);
    			request.setAttribute("custInfo", custInfo);
    			request.setAttribute("custType", isUnitFlag);
    			request.setAttribute("unitenumc", unitenumc);
    			this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "0", "账单查询成功！");
    		}
    		catch(Exception e)
    		{    
    			 request.setAttribute("errormessage", "未查询到客户" + customerSimp.getServNumber() + "的信息");
    			 this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", "未查询到客户" + customerSimp.getServNumber() + "的信息！");
                 return "error";
    		}
    		if("qryHisMonthBill".equals(dispatchFlag))
    		{
    			return "hisMonBillDetail";
    		}
    		else if("qryCurMonthBill".equals(dispatchFlag))
    		{
    			return "curMonBillDetail";
    		}
    		else
    		{
    		    String resultMsg = getConvertMsg("未查询到" + month + "月份的账单信息", "zz_04_17_000003", null, formatParams(month));
                this.createRecLog(Constants.BUSITYPE_SUBSQRYBILLITEM, "0", "0", "1", resultMsg);
    			request.setAttribute("errormessage", resultMsg);
    			
                return "error";
    		}
        }       
    }
    
    /**
     * 提示信息改造
     * 
     * @param strMonth 查询月份
     * @return
     * @see 
     * @remark: create g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
     */
    private String[] formatParams(String strMonth)
    {
        String[] params = new String[]{"", ""};
        
        if (strMonth != null && strMonth.length() == 6)
        {
            params[0] = strMonth.substring(0, 4);
            params[1] = strMonth.substring(4);
        }
        
        return params;
    }
    
    public String getQryType()
    {
        return qryType;
    }
    
    public void setQryType(String qryType)
    {
        this.qryType = qryType;
    }
    
    public MonthBillBean getMonthBillBean()
    {
        return monthBillBean;
    }
    
    public void setMonthBillBean(MonthBillBean monthBillBean)
    {
        this.monthBillBean = monthBillBean;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CRSet getBillItems()
    {
        return billItems;
    }
    
    public void setBillItems(CRSet billItems)
    {
        this.billItems = billItems;
    }
   
    
    
    public String[] getMonths()
    {
        return months;
    }

    public void setMonths(String[] months)
    {
        this.months = months;
    }

    public String getDispFlag(String month,String brand)
    {
    	 String dispatcher = "";
    	
    	 //第一个配置：交割月
    	 String monthCfg = "";
         monthCfg = (String) PublicCache.getInstance().getCachedData("SH_BILLMONTH_CFG");
         //当前月
         String curMonth = CommonUtil.getLastMonth("yyyyMM", 0);
         
         //第二个配置：分用户群依次配置
         String brandGotone = "";
         brandGotone = (String) PublicCache.getInstance().getCachedData("SH_BRANDGOTONE_BRAND_CFG");
         
         String brandMzone = "";
         brandMzone = (String) PublicCache.getInstance().getCachedData("SH_BRANDMZONE_BRAND_CFG");
         
         String brandSzx = "";
         brandSzx = (String) PublicCache.getInstance().getCachedData("SH_BRANDSZX_BRAND_CFG");
         
         String brandOther = "";
         brandOther = (String) PublicCache.getInstance().getCachedData("SH_BRANDOTHER_BRAND_CFG");
         
         String brandMonCfg = "BrandGotone".equals(brand) ? brandGotone 
        		 			  : ("BrandMzone".equals(brand) ? brandMzone 
        		 			  : ("BrandSzx".equals(brand) ? brandSzx : brandOther));
         
         String curDateStr = CommonUtil.getLastMonth("yyyyMMdd", 0);
         
         //如果配置月为空或者品牌群配置为空显示old
         if(null == monthCfg || "".equals(monthCfg) || 
        	null == brandMonCfg || "".equals(brandMonCfg))
         {
            return "queryMonthBill";
         }
         else
         {
        	 //如果参数月份小于配置月则显示old
        	 if(month.compareTo(monthCfg) < 0)
             {
        		 dispatcher = "queryMonthBill";
             }
        	 //如果参数月份大于配置月并且小于当前月
             if((month.compareTo(monthCfg)>=0)&&(month.compareTo(curMonth)<0))
             {
            	 //显示newOld
            	 dispatcher = "qryHisMonthBill";
             }
             //如果参数月份等于当前月
             if(month.equals(curMonth))
             {
            	 //显示new
            	 dispatcher = "qryCurMonthBill";
             }
             
             //如果品牌配置yyyymmdd大于当前日期则还是显示old
        	 if(brandMonCfg.compareTo(curDateStr) > 0)
        	 {
        		 dispatcher = "queryMonthBill";
        	 }
         }
        
    	 return dispatcher;
    }
    
    private String getBillCycle(String month)
	{
		StringBuffer sb = new StringBuffer();
		
		try
		{
			sb.append(month.substring(0, 4)).append("年").
				append(month.substring(4, 6)).append("月").
				append("01").append("日");
			
			String curMonth = CommonUtil.getLastMonth("yyyyMM", 0);
			
			if(curMonth.equals(month))
			{
				sb.append("起截止目前");
				return sb.toString();
			}
		
			GregorianCalendar greCalendar = new GregorianCalendar();
			
			DateFormat df = new SimpleDateFormat("yyyyMM");
			
			Date date = df.parse(month);
			
			greCalendar.setTime(date);
			
			int lastDay = greCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			sb.append("至").append(month.substring(0, 4)).append("年").
				append(month.substring(4, 6)).append("月").append(lastDay).append("日");
			
			return sb.toString();
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	private String toStrCust(CRSet crset)
	{
		
		StringBuffer strBuff = new StringBuffer();
		
		if(null == crset || crset.GetRowCount() == 0)
		{
			return strBuff.toString();
		}
		else
		{
			
			int colLen = crset.GetColumnCount();
			
			for(int i=0; i<colLen;i++)
			{
				strBuff.append(crset.GetValue(0, i));
				if(i<colLen - 1)
				{
					strBuff.append("%");
				}
			}
			
			return strBuff.toString();
		}
	}
	  /**
     * 资助终端账单协同查询之139email
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
	public void  billColQuery139Email() throws IOException
    {
	   
	    StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
	    resBuff.append("<info>");
	    Writer writer = null;
        HttpServletRequest request = this.getRequest();
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        try
            { HttpServletResponse response = getResponse();
              response.setContentType("text/xml;charset=GBK");
              request.setCharacterEncoding("GBK");
              writer = response.getWriter();
              String isCurrString=getRequest().getParameter("isCurr");
              String monString=null;
              String menuString=null;
              
              if("yes".equals(isCurrString)){
                  monString=(String)(getRequest().getSession().getAttribute("BILL_MONTH"));
                  menuString=(String)(getRequest().getSession().getAttribute("BILL_MENUID"));
              }else{
                  monString=(String)(getRequest().getSession().getAttribute("HIS_BILL_MONTH"));
                  menuString=(String)(getRequest().getSession().getAttribute("HIS_BILL_MENUID"));
                  
              }
             
                CTagSet res = monthBillBean.billColQuery139Email(customerSimp, terminalInfo, monString,menuString);
                String resStatus=res.GetValue("restatus");
                if("0".equals(resStatus)){
                    resBuff.append("139邮箱接收账单信息成功！");
                    //writer.write("139邮箱接收账单信息成功！");
                    this.createRecLog(Constants.billColQuery139Email, "0", "0", "0", "账单协同查询 139邮箱接收账单信息成功！");
                    
                }else{
                    resBuff.append("139邮箱接收账单信息失败！");
                    //writer.write("139邮箱接收账单信息失败！");
                    this.createRecLog(Constants.billColQuery139Email, "0", "0", "0", "账单协同查询 139邮箱接收账单信息失败！");
                    
                }
               }
            catch(Exception e)
            {    this.createRecLog(Constants.billColQuery139Email, "0", "0", "1", "账单协同查询 139邮箱接收账单信息异常！");
                   resBuff.append("139邮箱接收账单信息出现异常！");
                     //writer.write("139邮箱接收账单信息出现异常！");
              
                 
                
            }finally{
                
                if(null != writer)
                    {   
                    resBuff.append("</info>");
                    writer.write(resBuff.toString());
                    writer.flush();
                    writer.close();
                }
                
            }
            return ;    
        }
	  /**
     * 资助终端账单协同查询之短信
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
	public void  billColQueryMessage() throws IOException
    {     StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
          resBuff.append("<info>");
	    Writer writer = null;
        HttpServletRequest request = this.getRequest();
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        try
            {
            String isCurrString=getRequest().getParameter("isCurr");
            String monString=null;
            String menuString=null;
            
            if("yes".equals(isCurrString)){
                monString=(String)(getRequest().getSession().getAttribute("BILL_MONTH"));
                menuString=(String)(getRequest().getSession().getAttribute("BILL_MENUID"));
            }else{
                monString=(String)(getRequest().getSession().getAttribute("HIS_BILL_MONTH"));
                menuString=(String)(getRequest().getSession().getAttribute("HIS_BILL_MENUID"));
                
            }
                int resStatus=monthBillBean.billColQueryMessage(customerSimp, terminalInfo, monString,menuString);
                HttpServletResponse response = getResponse();
                response.setContentType("text/xml;charset=GBK");
                request.setCharacterEncoding("GBK");
                writer = response.getWriter();
                if(resStatus==1){
                    resBuff.append("短信接收账单信息成功！");
                    this.createRecLog(Constants.billColQueryMessage, "0", "0", "0", "短信接收账单信息成功！");
                    
                }else{
                    resBuff.append("短信接收账单信息失败！");
                    this.createRecLog(Constants.billColQueryMessage, "0", "0", "0", "短信接收账单信息失败！");
                    
                }
               }
            catch(Exception e)
            {     this.createRecLog(Constants.billColQueryMessage, "0", "0", "1", "账单协同查询 短信接收账单信息异常！");
            
             
                     resBuff.append("短信接收账单信息出现异常！");
               
                
            }finally{
                
                if(null != writer)
                    
                {   resBuff.append("</info>");
                     writer.write(resBuff.toString());
                    writer.flush();
                    writer.close();
                }
                
            }
            return ;    
        }
	  /**
     * 资助终端账单协同查询之彩信
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
     */
	   public void  billColQueryColorMessage()  throws IOException
	    {    StringBuffer resBuff = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
             resBuff.append("<info>");
	       Writer writer = null;
	        HttpServletRequest request = this.getRequest();
	        // 用户信息
	        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
	        // 终端机信息
	        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
	        try
	            {
	            String isCurrString=getRequest().getParameter("isCurr");
	            String monString=null;
	            String menuString=null;
	            
	            if("yes".equals(isCurrString)){
	                monString=(String)(getRequest().getSession().getAttribute("BILL_MONTH"));
	                menuString=(String)(getRequest().getSession().getAttribute("BILL_MENUID"));
	            }else{
	                monString=(String)(getRequest().getSession().getAttribute("HIS_BILL_MONTH"));
	                menuString=(String)(getRequest().getSession().getAttribute("HIS_BILL_MENUID"));
	                
	            }
	                int resStatus=monthBillBean.billColQueryColorMessage(customerSimp, terminalInfo, monString,menuString);
	                HttpServletResponse response = getResponse();
                    response.setContentType("text/xml;charset=GBK");
                    request.setCharacterEncoding("GBK");
                    writer = response.getWriter();
	                if(resStatus==1){
	                   
	                    resBuff.append("彩信接收账单信息成功！");
	                    this.createRecLog(Constants.billColQueryColorMessage, "0", "0", "0", "彩信接收账单信息成功！");
	                    
	                }else{
	                    resBuff.append("彩信接收账单信息失败！");
	                    this.createRecLog(Constants.billColQueryColorMessage, "0", "0", "0", "彩信接收账单信息失败！");
	                    
	                }
	               }
	            catch(Exception e)
	            {    this.createRecLog(Constants.billColQueryColorMessage, "0", "0", "1", "账单协同查询 彩信接收账单信息异常！");
	               
	                     resBuff.append("彩信接收账单信息出现异常！");
	              
	                 
	                
	            }finally{
                    
                    if(null != writer)
                    {  
                        resBuff.append("</info>");
                        writer.write(resBuff.toString());
                        writer.flush();
                        writer.close();
                    }
                    
                }
	            return ;    
	        }
	
}

