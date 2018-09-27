package com.customize.hub.selfsvc.feeservice.action;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.customize.hub.selfsvc.bean.FeeBalanceBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 账户余额查询
 * @author cKF48754
 *
 */

public class FeeBalanceAction extends BaseAction
{
    
	// 序列化
    private static final long serialVersionUID = 1L;
   
    // 当前菜单id
    private String curMenuId = "";
    
    // 错误消息
    private String error;
   
    // 结果列表标题
    private String[] serviceTitle;
    
    // 设置余额信息
    private String[] balanceStr;
    
    // 日志
    private static Log logger = LogFactory.getLog(FeeBalanceAction.class);
    
    // 调用接口Bean
    private FeeBalanceBean feeBalanceBean;
   
    /**
     * 账户余额查询
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    
    public String qryAccBalance()
    {
        if (logger.isDebugEnabled())
        {
        	logger.debug("queryBalance Starting...");
        }
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 定义错误页面转向
        String forward = "error";
        
        // 余额查询
		Map result = feeBalanceBean.queryBalance(terminalInfoPO,customer,curMenuId);
		 
		if (result != null && result.size() > 0)
		{
			CTagSet tagSet = (CTagSet)result.get("returnObj");
		     
		    String[] titles = {"话费余额", "现金", "充值卡", "分月抵扣话费", "赠送话费", "分月赠送话费", "存入分月抵扣总额",
		    				   "赠送分月抵扣总额", "可用余额", "信用度", "实时话费", "历史欠费"};
		     
		    // 设置标题
		    setServiceTitle(titles);
		    
		    String[] balances = {tagSet.GetValue("balance"), tagSet.GetValue("cashBalance"),
		    					 tagSet.GetValue("cardBalance"), tagSet.GetValue("monDeduction"),
		    					 tagSet.GetValue("presentBalance"), tagSet.GetValue("monPresentBalance"),
		     					 tagSet.GetValue("DKBalance"), tagSet.GetValue("preDKBalance"),
		     					 tagSet.GetValue("availableBalance"), tagSet.GetValue("credit"),
		     					 tagSet.GetValue("realTimeFee"), tagSet.GetValue("hisArrears")};
		    
		    //将分转换成元
		    for(int i = 0;i < balances.length; i++)
		    {
		    	balances[i] = CommonUtil.fenToYuan(balances[i]);
		    }
		    
		    // 设置余额
		    setBalanceStr(balances);
		     
		    forward = "qryBalance";
		     
		    // 记录成功日志
		    this.createRecLog( Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "0", "账户余额查询:余额查询成功!");
		}
		else
		{
		    String errMsg = "";
		    if (null != result)
		    {
		        errMsg = (String) result.get("returnMsg");
		    }
		    
		    if (null == errMsg || "".equals(errMsg))
		    {
		        errMsg = "账户余额查询失败";
		    }
		    
		    getRequest().setAttribute("errormessage", errMsg);
		     
		    // 记录错误日志
		    this.createRecLog(Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "1", errMsg);
		}
        
		if (logger.isDebugEnabled())
        {
			logger.debug("queryBalance End!");
        }
		
		return forward;
    }

    public void setFeeBalanceBean(FeeBalanceBean feeBalanceBean)
    {
        this.feeBalanceBean = feeBalanceBean;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String[] getServiceTitle() 
	{
		return serviceTitle;
	}
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setServiceTitle(String[] serviceTitle) 
	{
		this.serviceTitle = serviceTitle;
	}

	public String[] getBalanceStr() 
	{
		return balanceStr;
	}

	public void setBalanceStr(String[] balanceStr) 
	{
		this.balanceStr = balanceStr;
	}

}
