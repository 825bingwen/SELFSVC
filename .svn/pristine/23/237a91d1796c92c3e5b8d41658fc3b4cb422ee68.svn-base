package com.customize.hub.selfsvc.feeservice.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.BillAnalysisBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class BillAnalysisAction extends BaseAction{
	
    private static final long serialVersionUID = -3950351941316700610L;
    
    private static Log logger = LogFactory.getLog(BillAnalysisAction.class);
    
    private String curMenuId = "";
    
    private String month = "";
    
    /**账单分析**/
    private CRSet billAnalysisItems;
    // 错误信息
    private String errormessage;
    
    private BillAnalysisBean billAnalysisBean;

    /**
     * 显示所查询的月份
     * 
     * @return
     * @throws Exception
     */
    public String qryCurMonthBillAy() throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurMonthBillAy Starting ...");
        }
        
        
        // 可查询当前月和前五个月的账单信息
        String pattern = "yyyyMM";
        setMonth(CommonUtil.getLastMonth(pattern, 0));
        String month1 = CommonUtil.getLastMonth(pattern, 1);
        String month2 = CommonUtil.getLastMonth(pattern, 2);
        String month3 = CommonUtil.getLastMonth(pattern, 3);
        String month4 = CommonUtil.getLastMonth(pattern, 4);
        String month5 = CommonUtil.getLastMonth(pattern, 5);
        
        HttpServletRequest request = this.getRequest();
        request.setAttribute("month1", month1);
        request.setAttribute("month2", month2);
        request.setAttribute("month3", month3);
        request.setAttribute("month4", month4);
        request.setAttribute("month5", month5);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryCurMonthBillAy End");
        }
        
        return "qryCurMonthBillAy";
    }
    
    /**
     * 查询月份的账单信息 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String qryMonthBillAy() throws Exception{
    	
    	String forward ="error";
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO)getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        Map result = billAnalysisBean.qryMonthBillAy(customer, terminalInfo, month, curMenuId);
        
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        if (result != null && result.size() > 0){
        	
        	CTagSet tagSet =(CTagSet)result.get("returnObj");
        	String reStatus = "";
        	if(IntMsgUtil.isTransEBUS("CTCSQryBillAnalysis"))
            {
        		reStatus = tagSet.GetValue("RECCODE");
            }
        	else
        	{
        		reStatus = tagSet.GetValue("restatus");
        	}
        	
        	if("0".equals(reStatus)){
        		String allBillAy = "";
        		if(IntMsgUtil.isTransEBUS("CTCSQryBillAnalysis"))
                {
        			allBillAy = tagSet.GetValue("RECSTR");
                }
            	else
            	{
            		allBillAy = tagSet.GetValue("billAnalysisInfo");
            	}
        		
        		String[] billArray = allBillAy.split(";");
        		CRSet crset1 = new CRSet(billArray.length);
        		for(int i=0;i<billArray.length ;i++){
        			String[] itemArray = billArray[i].split(":");
        			crset1.AddRow();
        			for(int j=0;j<itemArray.length;j++){
        				if(j==0){
        					crset1.SetValue(i, 2,"2");
        				}
        			    if(itemArray[j].startsWith("#")&&itemArray[j].endsWith("#")&&itemArray[j].length()>6){
	    			    	crset1.SetValue(i, j, itemArray[j].substring(3,itemArray[j].length() - 3));
	    			    	crset1.SetValue(i, 2,"0");
	    			    }else if(itemArray[j].startsWith("#")&&itemArray[j].length()==3){
	    			    	crset1.SetValue(i, j,"");
	    			    }else if(itemArray[j].startsWith("#")&&(!itemArray[j].endsWith("#"))){
	    			    	crset1.SetValue(i, j,itemArray[j].substring(3,itemArray[j].length()));
	    			    	crset1.SetValue(i, 2,"1");
	    			    }else{
	    			    	crset1.SetValue(i, j,itemArray[j]);
	    			    }
        			}
        		}
        		setBillAnalysisItems(crset1);
        		forward = "qryMonthBill";
        	}
        	else
        	{
        		forward = "error";
        		
        		String resultMsg = getConvertMsg("未查询到" + month + "月份的账单信息", "zz_04_17_000003", null, formatParams(month));
        		
        		setErrormessage(resultMsg);
        		this.createRecLog("SHQryBilAnalysis", "0", "0", "1", resultMsg);
        	}
        }
        else
        {
            String resultMsg = getConvertMsg("未查询到" + month + "月份的账单信息", "zz_04_17_000003", null, formatParams(month));
           
            setErrormessage(resultMsg);
            
            this.createRecLog("SHQryBilAnalysis", "0", "0", "1", resultMsg);
            
            forward = "error";
        }
    	
    	return forward;
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
    
	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public BillAnalysisBean getBillAnalysisBean() {
		return billAnalysisBean;
	}

	public void setBillAnalysisBean(BillAnalysisBean billAnalysisBean) {
		this.billAnalysisBean = billAnalysisBean;
	}

	public CRSet getBillAnalysisItems() {
		return billAnalysisItems;
	}

	public void setBillAnalysisItems(CRSet billAnalysisItems) {
		this.billAnalysisItems = billAnalysisItems;
	}
}
