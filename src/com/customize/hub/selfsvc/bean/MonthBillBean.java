package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 湖北月账单查询功能bean
 * 
 * @author xkf29026
 * 
 */
public class MonthBillBean extends BaseBeanHubImpl
{
    /**
     * 非当前月账单查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @param qryType，查询类型
     * @return 当前月账单查询
     * @see
     */
    public CRSet qryMonthBill(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfo, String month,
            String curMenuId, String qryType)
    {
        Map map = new HashMap();
        map.put("telnumber", customerSimp.getServNumber());
        map.put("month", month);
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        map.put("type", qryType);
        
        ReturnWrap rw = getSelfSvcCallHub().qryMonthBill(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet crset = (CRSet)rw.getReturnObject();
            
            if (crset != null && crset.GetRowCount() > 0)
            {
                CRSet crset1 = new CRSet(crset.GetRowCount());
                int k = 0;
                for (int j = 1; j <= 5; j++)
                {
                    crset1.AddRow();
                    String trueValue = null;
                    // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    trueValue = getTrueValue(j);
                    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    crset1.SetValue(k, 0, trueValue);
                    k++;
                    for (int i = 0; i < crset.GetRowCount(); i++)
                    {
                    	//过滤掉费用为0的账单信息
                        if (crset.GetValue(i, 0).equals(String.valueOf(j)) && !crset.GetValue(i, 2).equals("0"))
                        {
                            crset1.AddRow();
                            crset1.SetValue(k, 1, crset.GetValue(i, 1));
                            crset1.SetValue(k, 2, crset.GetValue(i, 2));
                            k++;
                        }
                    }
                }
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    if (crset.GetValue(i, 0).equals("TotalFee"))
                    {
                        crset1.AddRow();
                        crset1.SetValue(k, 0, "本月合计");
                        k++;
                        crset1.AddRow();
                        crset1.SetValue(k, 1, crset.GetValue(i, 1));
                        crset1.SetValue(k, 2, crset.GetValue(i, 2));
                    }
                }
                return crset1;
            }
        }
        
        return null;
    }

    /**
     * 取名称
     * <功能详细描述>
     * @param j
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getTrueValue(int j)
    {
        String trueValue = "";
        if (j == 1)
        {
            trueValue = "月基本费";
        }
        else if (j == 2)
        {
            trueValue = "增值业务费";
        }
        else if (j == 3)
        {
            trueValue = "通信费";
        }
        else if (j == 4)
        {
            trueValue = "代收费";
        }
        else if (j == 5)
        {
            trueValue = "其他费";
        }
        return trueValue;
    }
    
    //新版账单查询 Add by XKF57421 Begin
    /**
	 * 账单查询--查询客户信息
	 * @param customerSimp，用户信息
	 * @param month，月份信息
	 * @return Vector，CTagSet+CRset
	 * @see [类、类#方法、类#成员]
	 */
	public Vector qryBillCustInfo(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,String month,String curMenuId)
	{
		Map map = new HashMap();
        map.put("SERVNUM", customerSimp.getServNumber());
        map.put("CYCLEMONTH", month);
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = getSelfSvcCallHub().qryBillCustInfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return (Vector)rw.getReturnObject();
        }
        
        return null;
		
	}
	//新版账单查询 Add by XKF57421 end
	  /**
	    * 资助终端账单协同查询之139email
	    * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
	    */
	   public CTagSet billColQuery139Email(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,String month,String curMenuId)
	    {
	        Map map = new HashMap();
	        map.put("SERVNUM", customerSimp.getServNumber());
	        map.put("CYCLEMONTH", month);
	        map.put("menuID", curMenuId);
	        map.put("touchOID", customerSimp.getContactId());
	        map.put("operID", terminalInfo.getOperid());
	        map.put("termID", terminalInfo.getTermid());
	        map.put("region", customerSimp.getRegionID());
	        
	        ReturnWrap rw = getSelfSvcCallHub().billColQuery139Email(map);
	        
	        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
	        {
	            return (CTagSet)rw.getReturnObject();
	        }
	        
	        return null;
	        
	    }
	   /**
        * 资助终端账单协同查询之短信
        * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
        */
       public int billColQueryMessage(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,String month,String curMenuId)
        {
            Map map = new HashMap();
            map.put("SERVNUM", customerSimp.getServNumber());
            map.put("CYCLEMONTH", month);
            map.put("menuID", curMenuId);
            map.put("touchOID", customerSimp.getContactId());
            map.put("operID", terminalInfo.getOperid());
            map.put("termID", terminalInfo.getTermid());
            ReturnWrap rw = getSelfSvcCallHub().billColQueryMessage(map);
            return rw.getStatus();
           }
       /**
        * 资助终端账单协同查询之彩信
        * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 关于在自助终端及网厅上实现多渠道协同查询账单功能
        */
       public int billColQueryColorMessage(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,String month,String curMenuId)
       {
           Map map = new HashMap();
           map.put("SERVNUM", customerSimp.getServNumber());
           map.put("CYCLEMONTH", month);
           map.put("menuID", curMenuId);
           map.put("touchOID", customerSimp.getContactId());
           map.put("operID", terminalInfo.getOperid());
           map.put("termID", terminalInfo.getTermid());
           ReturnWrap rw = getSelfSvcCallHub().billColQueryColorMessage(map);
           
           return rw.getStatus();
           
       }
}
