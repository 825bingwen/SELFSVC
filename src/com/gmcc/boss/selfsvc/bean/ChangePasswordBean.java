package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * �����޸�
 * @author xkf29026
 *
 */
public class ChangePasswordBean extends BaseBeanImpl
{
    /**
     * �����޸�
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param oldPasswd ������
     * @param newPasswd ������
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map recChangePassword(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,String oldPasswd,String newPasswd, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���þ�����
        paramMap.put("oldPasswd", oldPasswd);
        
        // ����������
        paramMap.put("newPasswd", newPasswd);
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.recChangePassword(paramMap);
        Map map = new HashMap();
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        else
        {
        	String returnMsg = rw.getReturnMsg();
        	map.put("returnMsg", returnMsg);
        	map.put("errcode", rw.getErrcode());
        	
        	return map;
        }
    }

    /**
     * �����޸�(����)��
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param oldPasswd ������
     * @param newPasswd ������
     * @param curMenuId ��ǰ�˵�
     * @return Map
     * @remark create by hWX5316476 2014-2-20 OR_NX_201402_306 ���������ն��Ż�����_�������������
     */
    @SuppressWarnings("unchecked")
    public Map recChangePasswordNew(TerminalInfoPO termInfo, NserCustomerSimp customer,String oldPasswd,String newPasswd, String curMenuId)
    {
    	Map paramMap = new HashMap();
    	
    	// �ֻ���
    	paramMap.put("telnum",customer.getServNumber());
    	
    	// �������(���Բ���) �ֻ���
    	paramMap.put("callernum","");
    	
    	// �Ƿ񲦴򱾻�  0������1�Ǳ���
    	paramMap.put("flag", "0");
    	
    	// �ӿ�������  0 ����������֤  1:�����޸� 2����������
    	paramMap.put("subcmdid","1");
    	
    	// ������
    	paramMap.put("old_passwd",oldPasswd);
    	
    	// ������
    	paramMap.put("new_passwd",newPasswd);
    	
    	// ��֤��ʽ AuthCheckB :����������֤ 
    	paramMap.put("chktype", "AuthCheckB");
    	
    	// newpwdcount ������λ��У�飬У���Ƿ���ϴ���λ������0�򲻴���У�顣
    	paramMap.put("newpwdcount","0");
    	
    	// �ն�ID
    	paramMap.put("termid", termInfo.getTermid());
    	
    	// ��ǰ�˵�ID
    	paramMap.put("menuid", curMenuId);
    	
    	// ����ID
    	paramMap.put("touchoid", "");
    	
    	// ����ԱID
    	paramMap.put("operid", termInfo.getOperid());
        
        ReturnWrap rw = selfSvcCall.resetPwdNew(paramMap);
        Map map = new HashMap();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        else if( null != rw)
        {
        	String returnMsg = rw.getReturnMsg();
        	map.put("returnMsg", returnMsg);
        	map.put("errcode", rw.getErrcode());
        	
        	return map;
        }
        
        return null;
    }    
}
