package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 自选套餐受理bean
 * 
 * 
 * @author  cKF76106
 * @version  [版本号, May 14, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TelProdDiyBean extends BaseBeanHubImpl
{
    /**
     * 语音通话受理
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param ncode
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by jWX216858 2014-11-11 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
     */
    public ReturnWrap mainProductChangeSubmit(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,
            String curMenuId, String ncode)
    {
    	// 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        return getSelfSvcCallHub().mainProductChangeSubmit(msgHeader, ncode);
        
    }
    
    /**
     * 上网流量受理
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param productset
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap gprsWlanRec(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String productset)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // 上网流量受理
        return getSelfSvcCallHub().gprsWlanRec(msgHeader, productset);
    }
    
    /**
     * 数据业务受理
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param spbizid
     * @param spid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap spRec(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String spbizid,
            String spid)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // 数据业务受理
        return getSelfSvcCallHub().spRec(msgHeader, spbizid, spid);
    }
    
}
