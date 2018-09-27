package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * �˵�����bean
 * 
 * @author xkf29026
 * 
 */
public class BillSendBean extends BaseBeanHubImpl
{
    /**
     * �ж��û��Ƿ�ͨ139�ֻ����书��
     * 
     * @param termInfo �ն˻���Ϣ
     * @param customer �û���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     * @remark create by sWX219697 2014-9-19 09:04:13 OR_huawei_201409_430 �����ն˽���EBUS_�˵�����
     */
    public String emailService(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
            List<DictItemPO> itemList)
    {
    	//��װ��Ϣͷ
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = this.getSelfSvcCallHub().queryService(msgHead);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // �ж��û��Ƿ�ͨ139����
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_HUB.equals(province))
            {
                return emailService(rw, itemList);
            }
        }

        return "0";
    }
    
    /**
     * �˵����ͷ�ʽ�ύ����
     * 
     * @param termInfo �ն˻���Ϣ
     * @param customer �û���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param billSendType �˵����ͷ�ʽ
     * @param mailAddr �����ַ
     * @return
     * @remark modify by sWX219697 2014-9-9 09:57:15 OR_huawei_201409_430 �����ն˽���EBUS_�˵�����
     */
    public Map billSendCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
            String billSendType, String mailAddr)
    {
        
    	//��װ��Ϣͷ
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = this.getSelfSvcCallHub().billSendCommit(msgHead, billSendType, mailAddr);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("successFlag", "1");
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
            
        }
        else if(rw != null)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
       return null;
    }
}
