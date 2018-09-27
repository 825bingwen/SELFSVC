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
 * ��ѡ�ײ�����bean
 * 
 * 
 * @author  cKF76106
 * @version  [�汾��, May 14, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class TelProdDiyBean extends BaseBeanHubImpl
{
    /**
     * ����ͨ������
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param ncode
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by jWX216858 2014-11-11 OR_huawei_201410_867_HUB_��ѡ�ײ���ˮ�߲���EBUS����
     */
    public ReturnWrap mainProductChangeSubmit(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,
            String curMenuId, String ncode)
    {
    	// ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        return getSelfSvcCallHub().mainProductChangeSubmit(msgHeader, ncode);
        
    }
    
    /**
     * ������������
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param productset
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap gprsWlanRec(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String productset)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // ������������
        return getSelfSvcCallHub().gprsWlanRec(msgHeader, productset);
    }
    
    /**
     * ����ҵ������
     * 
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param spbizid
     * @param spid
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap spRec(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String spbizid,
            String spid)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // ����ҵ������
        return getSelfSvcCallHub().spRec(msgHeader, spbizid, spid);
    }
    
}
