package com.customize.sd.selfsvc.feeService.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.customize.sd.selfsvc.bean.FeeBalanceBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 账户余额查询
 * 
 * @author cKF48754
 * 
 */

//modify begin cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
public class FeeBalanceAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单id
    private String curMenuId = "";
    
    // 错误消息
    private String error;
    
    // 结果列表标题
    private List<String> serviceTitle = new ArrayList();
    
    // 设置余额信息
    private List<String> balanceList = new ArrayList();
    
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
        logger.debug("queryBalance Starting...");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 定义错误页面转向
        String forward = "error";
        
        // 余额查询
        Map result = feeBalanceBean.queryBalance(terminalInfoPO, customer, curMenuId);
        
        //modify begin g00140516 2011/10/26 R003C11L10n01 山东余额查询返回的是元，直接显示
        if (result != null && result.size() > 0)
        {
            CTagSet tagSet = (CTagSet)result.get("returnObj");
            
            // 账户付费类型
            String prepayType = tagSet.GetValue("prepayType");
            
            // modify begin hWX5316476  2014-7-23 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
            // 后付费用户
            if (prepayType.equals("pptpPost"))
            {
                getFeeDetailPost(tagSet);                
            }
            // 预付费用户
            else if (prepayType.equals("pptpPre"))
            {
                getFeeDetailPre(tagSet);
            }
            // modify end hWX5316476  2014-7-23 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
            //modify end g00140516 2011/10/26 R003C11L10n01 山东余额查询返回的是元，直接显示
            
            forward = "qryBalance";
            
            // 记录成功日志
            this.createRecLog(Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "0", "账户余额查询:余额查询成功!");
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "余额查询失败!");
            
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_WBQRYBALANCE, "0", "0", "1", "账户余额查询:余额查询失败!");
        }
        
        logger.debug("queryBalance End!");
        
        return forward;
    }

    /**
     * 预付费用户的费用明细
     * @param tagSet
     * @remark add by hWX5316476 2014-7-23 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
     */
    private void getFeeDetailPre(CTagSet tagSet)
    {
        // 当前账期账单费用，单位：分
        String currBillFee = tagSet.GetValue("currBillFee");
        // 最新余额，单位：分
        String balance = tagSet.GetValue("balance");
        // 有效期，格式：yyyyMMdd
//                String expireDate = tagSet.GetValue("expireDate");
        // 协议款余额，单位：分
        String contractBalance = tagSet.GetValue("contractBalance");
        // 协议款本期可用余额，单位：分
        String contractCanUse = tagSet.GetValue("contractCanUse");
        // 协议款本期已用金额，单位：分
        String contractThisUsed = tagSet.GetValue("contractThisUsed");
        // 本月可用协议款额度，单位：分
        String contractDrawamt = tagSet.GetValue("contract_drawamt");
        // 赠送款余额，单位：分
        String presentBalance = tagSet.GetValue("present_balance");
        // 赠送款本期可用余额，单位：分
        String presentCanuse = tagSet.GetValue("present_canuse");
        // 赠送款本期已用金额，单位：分
        String presentThisused = tagSet.GetValue("present_thisused");
        // 本月可用赠送款额度，单位：分
        String presentDrawamt = tagSet.GetValue("present_drawamt");
        
        serviceTitle.clear();
        balanceList.clear();
        
        serviceTitle.add("最新余额");
        balanceList.add(balance);
        
        if(isNotZero(currBillFee))    
        {
            serviceTitle.add("当前账期账单费用");
            balanceList.add(currBillFee);
        }

        if(isNotZero(contractBalance))    
        {
            serviceTitle.add("协议款余额");
            balanceList.add(contractBalance);
        }

        if(isNotZero(contractCanUse))    
        {
            serviceTitle.add("协议款本期可用余额");
            balanceList.add(contractCanUse);
        }

        if(isNotZero(contractThisUsed)) 
        {
            serviceTitle.add("协议款本期已用金额");
            balanceList.add(contractThisUsed);
        }

        if(isNotZero(contractDrawamt)) 
        {
            serviceTitle.add("本月可用协议款额度");
            balanceList.add(contractDrawamt);
        }

        if(isNotZero(presentBalance)) 
        {
            serviceTitle.add("赠送款余额");
            balanceList.add(presentBalance);
        }
        
        if(isNotZero(presentCanuse)) 
        {
            serviceTitle.add("赠送款本期可用余额");
            balanceList.add(presentCanuse);
        }
        
        if(isNotZero(presentThisused))
        {
            serviceTitle.add("赠送款本期已用余额");
            balanceList.add(presentThisused);
        }
        
        if(isNotZero(presentDrawamt))
        {
            serviceTitle.add("本月可用赠送款额度");
            balanceList.add(presentDrawamt);
        }
    }

    /**
     * 后付费用户的费用明细
     * @param tagSet
     * @remark add by hWX5316476 2014-7-23 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
     */
    private void getFeeDetailPost(CTagSet tagSet)
    {
        // 最新余额，单位：分
        String cashBalance = tagSet.GetValue("cash_balance");
        // 协议款余额，单位：分
        String contractBalance = tagSet.GetValue("contractBalance");
        // 赠送款余额，单位：分
        String presentBalance = tagSet.GetValue("present_balance");
        // 本月可用协议款额度，单位：分
        String contractDrawamt = tagSet.GetValue("contract_drawamt");
        // 本月可用赠送款额度，单位：分
        String presentDrawamt = tagSet.GetValue("present_drawamt");
        // 历史账单总费用，欠款。单位：分
        String hisBillFee = tagSet.GetValue("hisBillFee");
        
        serviceTitle.clear();
        balanceList.clear();
        
        // 话费余额必须显示
        serviceTitle.add("话费余额");
        balanceList.add(cashBalance);
        
        if(isNotZero(contractBalance))
        {
            serviceTitle.add("协议款余额");
            balanceList.add(contractBalance);
        }
        
        if(isNotZero(presentBalance))
        {
            serviceTitle.add("赠款余额");
            balanceList.add(presentBalance);
        }
        
        if(isNotZero(contractDrawamt))
        {
            serviceTitle.add("本月可用的协议款额度");
            balanceList.add(contractDrawamt);
        }
        
        if(isNotZero(contractDrawamt))
        {
            serviceTitle.add("本月可用的赠款额度");
            balanceList.add(presentDrawamt);
        }
        
        if(isNotZero(hisBillFee))
        {
            serviceTitle.add("往月未缴帐单话费");
            balanceList.add(hisBillFee);
        }
    }
    
    /**
     * 判断金额是否不为0
     * @param amount
     * @return
     * @remark add by hWX5316476 2014-7-23 V200R003C10LG0701 OR_huawei_201407_371_圈复杂度_自助终端
     */
    private boolean isNotZero(String amount)
    {
        if (!"".equals(amount) && Double.parseDouble(amount) != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
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
    
    public List getServiceTitle()
    {
        return serviceTitle;
    }
    
    public void setServiceTitle(List serviceTitle)
    {
        this.serviceTitle = serviceTitle;
    }
    
    public List getBalanceList()
    {
        return balanceList;
    }
    
    public void setBalanceList(List balanceList)
    {
        this.balanceList = balanceList;
    }
    // modify end cKF48754 2011/10/19 OR_SD_201106_95 根据接口协议 V3.6修改山东余额查询
}
