package com.customize.hub.selfsvc.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 09�����������ɿ���������ѯ������
 * 
 * @author l00190940
 * 
 */
public class FamilyNumberBean extends BaseBeanHubImpl
{

	public Map<String, Object> qryFamilyNumber(TerminalInfoPO termInfo, String curMenuId,
			NserCustomerSimp customer) 
	{
	    // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // ��ѯ�������
        ReturnWrap rw = getSelfSvcCallHub().qryFamilyNumber(msgHeader);
        
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            
            //���÷��ؽ��
            map.put("returnObj", rw.getReturnObject());
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
		return null;
	}

	/**
	 * ���á��޸Ļ�ȡ���������
	 * @param terminalInfoPO �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param curMenuId �˵�id
	 * @param stype ��������
	 * @param sn  �������λ�á�Ŀǰֻ��1��2��3
	 * @param sregion ������룬�����ȡ����������
	 * @return
	 * @remark modify by jWX216858 2014-11-12 OR_huawei_201410_867_HUB_��ѡ�ײ���ˮ�߲���EBUS����
	 */
	public Map<String, Serializable> setFamilyNumber(TerminalInfoPO terminalInfoPO,
			NserCustomerSimp customer, String curMenuId, String stype,
			String sn, String sregion) 
	{
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), 
        		terminalInfoPO.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = this.getSelfSvcCallHub().setFamilyNumber(msgHeader, sn, sregion, stype);
        
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
    		CTagSet result = (CTagSet)rw.getReturnObject();
    		String returnMsg = rw.getReturnMsg();
            Map<String, Serializable> map = new HashMap<String, Serializable>();
            
            // ���÷��ؽ��
            map.put("returnObj", result);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
		return null;
	}

}
