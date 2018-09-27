package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 月初扣款查询
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Dec 10, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonthDeductBean extends BaseBeanImpl
{
    /**
     * 月初扣款查询
     * <功能详细描述>
     * @param customerInfo 客户信息
     * @param termInfo 终端机信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryMonthDeduct(NserCustomerSimp customerInfo,TerminalInfoPO termInfo,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("touchoid", customerInfo.getContactId());// 统一接触流水
        paramMap.put("telnumber", customerInfo.getServNumber());// 手机号码
        paramMap.put("menuid", menuid);// 菜单ID
        paramMap.put("operID", termInfo.getOperid());// 操作员
        paramMap.put("termID", termInfo.getTermid());//终端编号
        
        ReturnWrap returnWrap = selfSvcCall.queryMonthDeduct(paramMap);
        
        return returnWrap;
    }
}
