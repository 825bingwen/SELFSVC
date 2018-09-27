package com.customize.cq.selfsvc.feeService.action;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.FeeBalanceBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 充值卡充值
 * @author cKF48754
 *
 */

public class FeeBalanceAction extends BaseAction
{
    
//	// 序列化
//    private static final long serialVersionUID = 1L;
//   
//    // begin zKF66389 findbus清零
//    // 当前菜单id
//    private String curMenuId = "";
//    // end zKF66389 findbus清零
//    
//    // 错误消息
//    private String error;
//   
//    // 结果列表标题
//    private String[] serviceTitle;
//    
//    // 设置余额信息
//    private String[] balanceStr;
//    
//    // 日志
//    private static Log logger = LogFactory.getLog(FeeBalanceAction.class);
//    
//    // 调用接口Bean
//    private FeeBalanceBean feeBalanceBean;
//   
//    /**
//     * 账户余额查询
//     * 
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//    
//    public String qryAccBalance()
//    {
//        if (logger.isDebugEnabled())
//        {
//        	logger.debug("queryBalance Starting...");
//        }
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // 定义错误页面转向
//        String forward = "error";
//        
//        // 余额查询
//		Map result = feeBalanceBean.queryBalance(terminalInfoPO,customer,curMenuId);
//		 
//		if (result != null && result.size() > 0)
//		{
//			CTagSet tagSet = (CTagSet)result.get("returnObj");
//		     
//		    String[] titles = {"可用余额"};
//		     
//		    // 设置标题
//		    setServiceTitle(titles);
//		     
//		    String[] balances = {tagSet.GetValue("lasttimebalance"), 
//		            tagSet.GetValue("shouldpay"), tagSet.GetValue("newbalance")};
//		     
//		    // 设置余额
//		    setBalanceStr(balances);
//		     
//		    forward = "qryBalance";
//		     
//		    // 记录成功日志
//		    this.createRecLog( Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "0", "账户余额查询:余额查询成功!");
//		}
//		else
//		{
//		    setError("错误消息：" + result.get("returnMsg"));
//		     
//		    // 记录错误日志
//		    this.createRecLog(Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "1", "账户余额查询:余额查询失败!");
//		}
//        
//		if (logger.isDebugEnabled())
//        {
//			logger.debug("queryBalance End!");
//        }
//		
//		return forward;
//    }
//
//    public void setFeeBalanceBean(FeeBalanceBean feeBalanceBean)
//    {
//        this.feeBalanceBean = feeBalanceBean;
//    }
//
//    // begin zKF66389 findbus清零
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//	// end zKF66389 findbus清零
//
//	public String getError()
//    {
//        return error;
//    }
//
//    public void setError(String error)
//    {
//        this.error = error;
//    }
//
//	public String[] getServiceTitle() {
//		return serviceTitle;
//	}
//
//	public void setServiceTitle(String[] serviceTitle) {
//		this.serviceTitle = serviceTitle;
//	}
//
//	public String[] getBalanceStr() {
//		return balanceStr;
//	}
//
//	public void setBalanceStr(String[] balanceStr) {
//		this.balanceStr = balanceStr;
//	}

}
