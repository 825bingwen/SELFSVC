package com.gmcc.boss.selfsvc.feeservice.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.bean.MonthDeductBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 月初扣款查询Action
 * <功能详细描述>
 * 
 * @author  user
 * @version  [版本号, Dec 10, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonthDeductAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(MonthDeductAction.class);
    
    private MonthDeductBean monthDeductBean;
    
    private String curMenuId = "";

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * 月初扣款查询
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryMonthDeduct() 
    {
        logger.debug("queryMonthDeduct Starting...");
        String forward = "";
        
        HttpSession session = this.getRequest().getSession();
        
        // 用户信息
        NserCustomerSimp customerInfo = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

        // 执行查询
        ReturnWrap rw = monthDeductBean.queryMonthDeduct(customerInfo, termInfo, this.getCurMenuId());
        
        if (rw.getStatus() == 1)
        {
            Vector vec = (Vector)rw.getReturnObject();
            this.getRequest().setAttribute("query", vec);
            
            // 成功日志
            this.createRecLog(Constants.BUSITYPE_MONTHFEE, "0", "0", "0", "话费查询：月初扣款查询成功！");
            
            forward = "success";
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "错误消息：" + rw.getReturnMsg());
            
            // 失败日志
            this.createRecLog(Constants.BUSITYPE_MONTHFEE, "0", "0", "1", "话费查询：月初扣款查询失败！");
            
            forward = "error";
        }
        logger.debug("queryMonthDeduct end!");
        return forward;
    }

    /**
     * spring注入
     * <功能详细描述>
     * @param monthDeductBean
     * @see [类、类#方法、类#成员]
     */
    public void setMonthDeductBean(MonthDeductBean monthDeductBean)
    {
        this.monthDeductBean = monthDeductBean;
    }
    
    
}
