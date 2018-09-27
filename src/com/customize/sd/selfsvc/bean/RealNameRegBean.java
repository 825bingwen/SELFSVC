/*
* @filename: RealNameRegAction.java
* @  All Right Reserved (C), 2014-2018, HUAWEI TECO CO.
* @brif:  ʵ����֤����
* @author: hWX5316476
* @de:  2014-06-10 
* @description: ����ʵ����֤bean
* @remark: create hWX5316476 2014-06-11 V200R003C10LG0601 OR_SD_201405_849
*/
package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.realNameReg.model.ChargeRecordPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class RealNameRegBean extends BaseBeanSDImpl
{
    /**
     * ��ȡ��������룬�����Ͷ�������뵽�ֻ�
     * @param termInfoPO  �ն˻���Ϣ
     * @param curMenuId  ��ǰ�˵�
     * @param telnum �ֻ���
     * @return
     */
	public Map<String, String> sendRandomPwd(TerminalInfoPO termInfoPO,String curMenuId, String telnum)
	{
	    // ����
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // ����Աid
        paramMap.put("operID", termInfoPO.getOperid());
        
        // �ն˻�id
        paramMap.put("termID", termInfoPO.getTermid());
        
        // ��ǰ�˵�
        paramMap.put("menuID", curMenuId);
        
        // ����id
        paramMap.put("touchOID", "");
        
        // �ֻ���
        paramMap.put("telnum", telnum);
        
        // ҵ������
        paramMap.put("dorectype", "ʵ���Ǽ�����");
        
        // ���� 4�����ɲ����Ͷ��������
        paramMap.put("subcmdid", "4");
        
        // �������ɲ����Ͷ�����֤��ӿ�
        ReturnWrap rw = getSelfSvcCallSD().getRandomPwd(paramMap);
        Map<String, String> map = new HashMap<String, String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // �µ��������
            map.put("new_passwd", v.GetValue("new_passwd"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // ���÷�����Ϣ
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"�Բ��𣬶�������뷢��ʧ�ܣ����Ժ�����!":retMsg);
        }
        return map;
	}
    
	/**
	 * ��֤������֤�� 
	 * @param termInfoPO  �ն˻���Ϣ
	 * @param curMenuId  ��ǰ�˵�
	 * @param telnum  �ֻ���
	 * @param randomPwd   ������֤��
	 * @return
	 */
    public Map<String, String> verifyRandomPwd(TerminalInfoPO termInfoPO,String curMenuId, String telnum, String randomPwd)
    {
        // ����
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // ����Աid
        paramMap.put("operID", termInfoPO.getOperid());
        
        // �ն˻�id
        paramMap.put("termID", termInfoPO.getTermid());
        
        // ��ǰ�˵�
        paramMap.put("menuID", curMenuId);
        
        // ����id
        paramMap.put("touchOID", "");
        
        // �ֻ���
        paramMap.put("telnum", telnum);
        
        // ������֤��
        paramMap.put("randompwd", randomPwd);
        
        // ���ö�����֤����֤�ӿ�
        ReturnWrap rw = getSelfSvcCallSD().checkRandomPwd(paramMap);
        
        Map<String, String> map = new HashMap<String, String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // ��֤���0 ʧ��  1 �ɹ�
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // ��֤�����Ϣ
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // ���÷�����Ϣ
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"�Բ��𣬶�����֤����֤ʧ��!":retMsg);
        }
        return map;
    }
    
    /**
     * ���˷���������֤
     * @param termInfoPO
     * @param curMenuId
     * @param telnum
     * @param password
     * @return
     */
    public Map<String, String> verifyServerPwd(TerminalInfoPO termInfoPO,String curMenuId,String telnum,String password)
    {
        // ����
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // ����Աid
        paramMap.put("operID", termInfoPO.getOperid());
        
        // �ն˻�id
        paramMap.put("termID", termInfoPO.getTermid());
        
        // ��ǰ�˵�
        paramMap.put("menuID", curMenuId);
        
        // ����id
        paramMap.put("touchOID", "");
        
        // �ֻ���
        paramMap.put("telnum", telnum);
        
        // ����
        paramMap.put("passwd", password);
        
        // ���ø��˷���������֤�ӿ�
        ReturnWrap rw = getSelfSvcCallSD().checkUserPwd(paramMap);
        
        Map<String, String> map = new HashMap<String, String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // ��֤���0 ʧ��  1 �ɹ�
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // ��֤�����Ϣ
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // ���÷�����Ϣ
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"�Բ��𣬸���������֤ʧ��!":retMsg);
        }
        return map;
    }
    /**
	 * SIM����֤
	 * @param termInfoPO �ն˻���Ϣ
	 * @param curMenuId ��ǰ�˵�
	 * @param telnum �ֻ���
	 * @param cardNum ����
	 * @return map
	 */
	public Map<String,String> chkSIMCardNo(TerminalInfoPO termInfoPO,String curMenuId,String telnum,String cardNum)
	{
		// ����
		Map<String,String> paramMap = new HashMap<String, String>();
		
		// ����Աid
		paramMap.put("operId", termInfoPO.getOperid());
		
		// �ն˻�id
		paramMap.put("termId", termInfoPO.getTermid());
		
		// ��ǰ�˵�
		paramMap.put("menuId", curMenuId);
		
		// ����id
		paramMap.put("touchId", "");
		
		// �ֻ���
		paramMap.put("telnum", telnum);
		
		// ����
		paramMap.put("cardno", cardNum);
		
		// SIM����֤
		ReturnWrap rw = getSelfSvcCallSD().chkSIMCardNo(paramMap);
		Map<String,String> map = new HashMap<String,String>();
		if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // ��֤���0 ʧ��  1 �ɹ�
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // ��֤�����Ϣ
            map.put("authchkmsg", v.GetValue("authchkmsg"));
		}
		else
		{
		    String retMsg = rw.getReturnMsg();
		    
		    // ���÷�����Ϣ
		    map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"�Բ���SIM����֤�쳣�����Ժ�����!":retMsg);
		}
		
		return map;
	}
	
	/**
	 * ��ֵ��¼��֤
	 * @param termInfoPO �ն˻���Ϣ
	 * @param curMenuId ��ǰ�˵�
	 * @param telnum  �ֻ���
	 * @param chargeRecordPO �ɷ���־
	 * @return
	 */
	public Map<String,String> chkChargeRecord(TerminalInfoPO termInfoPO, String curMenuId, String telnum, ChargeRecordPO chargeRecordPO)
	{
	    // ����
        Map<String,Object> paramMap = new HashMap<String, Object>();
        
        // ����Աid
        paramMap.put("operId", termInfoPO.getOperid());
        
        // �ն˻�id
        paramMap.put("termId", termInfoPO.getTermid());
        
        // ��ǰ�˵�
        paramMap.put("menuId", curMenuId);
        
        // ����id
        paramMap.put("touchId", "");
        
        // �ֻ���
        paramMap.put("telnum", telnum);
        
        // �ɷ���־
        paramMap.put("chargeRecordPO", chargeRecordPO);
        
        // ��ֵ��¼��֤
        ReturnWrap rw = getSelfSvcCallSD().chkChargeRecord(paramMap);
        
        Map<String,String> map = new HashMap<String,String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // ��֤���0 ʧ��  1 �ɹ�
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // ��֤�����Ϣ
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // ���÷�����Ϣ
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"�Բ��𣬳�ֵ��¼��֤�쳣�����Ժ�����!":retMsg);
        }
        
        return map;
	}
	
	/**
	 * ͨ����¼��֤
	 * @param termInfoPO �ն˻���Ϣ
	 * @param curMenuId  ��ǰ�˵�
	 * @param telnum   �ֻ�����
	 * @param calledNum  ���к���
	 * @return
	 */
	public Map<String, String> chkCallRecord(TerminalInfoPO termInfoPO, String curMenuId, String telnum, String calledNum)
	{
	    // ����
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // ����Աid
        paramMap.put("operId", termInfoPO.getOperid());
        
        // �ն˻�id
        paramMap.put("termId", termInfoPO.getTermid());
        
        // ��ǰ�˵�
        paramMap.put("menuId", curMenuId);
        
        // ����id
        paramMap.put("touchId", "");
        
        // �ֻ���
        paramMap.put("telnum", telnum);
        
        // ���к���
        paramMap.put("calledNum", calledNum);
        
        // ͨ����¼��֤
        ReturnWrap rw = getSelfSvcCallSD().chkCallRecord(paramMap);
        
        Map<String,String> map = new HashMap<String,String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // ��֤���0 ʧ��  1 �ɹ�
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // ��֤�����Ϣ
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // ���÷�����Ϣ
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"�Բ���ͨ����¼��֤�쳣�����Ժ�����!":retMsg);
        }
        
        return map;
	}
	
	/**
     * ��¼ʵ������֤������־
     * @param termInfoPO �ն˻���Ϣ
     * @param curMenuId �˵�id
     * @param telnum �ֻ���
     * @param chkMethod  ��֤��ʽ
     * @param chkValue  ��֤����
     * @return
     * @remark modify gWX301560 2015-08-12 OR_SD_201506_971_ɽ��_���ڴ�����ʵ���ƿͻ����Ǽ����ϵͳ֧�ŵ�����
     */
    public ReturnWrap saveRealNameChkRecLog(TerminalInfoPO termInfoPO, String curMenuId, String telnum, Map<String,String> map)
    {
        // ����
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // ����Աid
        paramMap.put("operId", termInfoPO.getOperid());
        
        // �ն˻�id
        paramMap.put("termId", termInfoPO.getTermid());
        
        // ��ǰ�˵�
        paramMap.put("menuId", curMenuId);
        
        // ����id
        paramMap.put("touchId", "");
        
        // �ֻ���
        paramMap.put("telnum", telnum);
        
//        // У�鷽ʽ ���� 
//        paramMap.put("attrid", chkMethod);
//        
//        // У������
//        paramMap.put("newattrvalue", chkValue);
        
        // SIM����֤
        ReturnWrap rw = getSelfSvcCallSD().saveRealNameChkRecLog(paramMap,map);
        
        return rw;
    }
}
