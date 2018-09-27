/*
 * 文 件 名:  BalanceDetailAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  wWX217192
 * 修改时间:  2016-3-31
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.balanceDetail.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.balanceDetail.model.BalanceDetailPO;
import com.customize.hub.selfsvc.bean.BalanceDetailBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wWX217192
 * @version  [版本号, 2016-3-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BalanceDetailAction extends BaseAction 
{

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -1763242091892115726L;

	private static Log logger = LogFactory.getLog(BalanceDetailAction.class);
	
	/**
	 * 账单总费用
	 */
	private String curFee;
	
	/**
	 * 当前余额
	 */
	private String leftBalance;
	
	/**
	 * 信用余额
	 */
	private String credit;
	
	/**
	 * 余额明细
	 */
	private List<BalanceDetailPO> balanceList = new ArrayList<BalanceDetailPO>();
	
	// Bean
	private BalanceDetailBean balanceDetailBean;
	
	@SuppressWarnings("unchecked")
	public String showBalanceDetail()
	{
		HttpServletRequest request = this.getRequest();
    	
    	// 获取session信息
    	HttpSession session = this.getRequest().getSession();
    	
    	// 获取客户信息
    	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    	
		// 获取终端机信息
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
    	// 调用余额明细查询接口
		Map<String, Object> map = balanceDetailBean.showBalanceDetail(termInfo, customer.getServNumber(), getCurMenuId());
		
		if(map.containsKey("returnMsg"))
		{
			request.setAttribute("errormessage", "查询余额明细失败");
			logger.error("接口调用失败，失败原因是" + (String) map.get("returnMsg"));
			return "error";
		}
		else
		{
			balanceList = (List<BalanceDetailPO>) map.get("balanceList");
			curFee = (String) map.get("CURFEE");
			leftBalance = (String) map.get("LEFTBALANCE");
			credit = (String) map.get("CREDIT");
		}
		
		return SUCCESS;
	}

	public String getCurFee() {
		return curFee;
	}

	public void setCurFee(String curFee) {
		this.curFee = curFee;
	}

	public String getLeftBalance() {
		return leftBalance;
	}

	public void setLeftBalance(String leftBalance) {
		this.leftBalance = leftBalance;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public List<BalanceDetailPO> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(List<BalanceDetailPO> balanceList) {
		this.balanceList = balanceList;
	}

	public BalanceDetailBean getBalanceDetailBean() {
		return balanceDetailBean;
	}

	public void setBalanceDetailBean(BalanceDetailBean balanceDetailBean) {
		this.balanceDetailBean = balanceDetailBean;
	}
	
}
