/*
 * @filename: CurrBillFeeAction.java
 * @  All Right Reserved (C), 2013-2113, HUAWEI TECO CO.
 * @brif:  话费总额查询，同CRM的余额查询中的未出帐话费
 * @author: g00140516
 * @de:  2013/02/22
 * @description: 
 * @remark: create g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
 */
package com.gmcc.boss.selfsvc.feeservice.action;

import javax.servlet.http.HttpSession;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.CurrBillFeeBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 话费总额查询
 * @author g00140516
 *
 */
public class CurrBillFeeAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前菜单
	 */
	private String curMenuId = "";
	
	/**
	 * 未出帐话费
	 */
	private String currBillFee = "";
	
	private transient CurrBillFeeBean currBillFeeBean = null;
	
	/**
	 * 话费总额查询
	 * @return
	 */
	public String qryCurrBillFee()
	{
		String forward = "";
        
        HttpSession session = this.getRequest().getSession();
        
        // 用户信息
        NserCustomerSimp customerInfo = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

        // 执行查询
        ReturnWrap rw = currBillFeeBean.qryCurrBillFee(customerInfo, termInfo, curMenuId);
        
        if (rw.getStatus() == 1)
        {
            // {currbillfee=0.00}
        	CTagSet tagSet = (CTagSet) rw.getReturnObject();
        	currBillFee = tagSet.GetValue("currbillfee");
        	
            // 成功日志
            this.createRecLog(Constants.BUSITYPE_CURRBILLFEE, "0", "0", "0", "话费总额查询成功！");
            
            forward = "success";
        }
        else
        {
            this.getRequest().setAttribute("errormessage", rw.getReturnMsg());
            
            // 失败日志
            this.createRecLog(Constants.BUSITYPE_CURRBILLFEE, "0", "0", "1", "话费总额查询失败！");
            
            forward = "error";
        }
        
        return forward;
	}


	public String getCurMenuId() {
		return curMenuId;
	}


	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}


	public String getCurrBillFee() 
	{
		return currBillFee;
	}

	public void setCurrBillFee(String currBillFee) 
	{
		this.currBillFee = currBillFee;
	}

	public CurrBillFeeBean getCurrBillFeeBean() 
	{
		return currBillFeeBean;
	}

	public void setCurrBillFeeBean(CurrBillFeeBean currBillFeeBean) 
	{
		this.currBillFeeBean = currBillFeeBean;
	}

}
