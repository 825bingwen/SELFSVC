package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ��������
 * 
 * @author xkf29026
 * 
 */
public class PasswordResetBean extends BaseBeanImpl
{
    /**
     * У�����֤��
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public ReturnWrap checkIDCard(TerminalInfoPO terminalInfoPO, String IDCard, String servnumbvr, String curMenuId)
    {
        Map map = new HashMap();
        map.put("telnum", servnumbvr);
        map.put("IDCard", IDCard);
        map.put("operid", terminalInfoPO.getOperid());
        map.put("termid", terminalInfoPO.getTermid());
        map.put("touchoid", "");
        map.put("menuid", curMenuId);
        
        ReturnWrap returnWrap = selfSvcCall.checkIDCard(map);
        return returnWrap;
    }
    
    /**
     * ���û���������������
     * 
     * @param termInfo���ն˻���Ϣ
     * @param shortMessage����������
     * @param CurMenuid����ǰ�˵�
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public boolean sendRandomPwd(TerminalInfoPO termInfo, String shortMessage, String servnumber, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * ��������
     * 
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @param newPasswd ������
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean resetPassword(TerminalInfoPO termInfo, String servnumber, String curMenuId, String newPasswd)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("subcmdid", "2");
        paramMap.put("oldpwd", "");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("newpwd", newPasswd);
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.resetPassword(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        // �׳��쳣
        String msg = "��������ʧ��";
        if(null != rw)
        {
            msg = rw.getReturnMsg();
        }
        
        throw new ReceptionException(msg);
    }

    /**
     * ����������(����)
     * 
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @param newPasswd ������
     * @return boolean 
     * @remark create by hWX5316476 2014-2-20 OR_NX_201402_306 ���������ն��Ż�����_�������������
     */
    @SuppressWarnings("unchecked")
    public Map resetPasswordNew(TerminalInfoPO termInfo, String servnumber, String curMenuId, String newPasswd)
    {
    	Map paramMap = new HashMap();
    	
    	// �ֻ���
    	paramMap.put("telnum",servnumber);
    	
    	// �������(���Բ���) �ֻ���
    	paramMap.put("callernum","");
    	
    	// �Ƿ񲦴򱾻�  0������1�Ǳ���
    	paramMap.put("flag", "0");
    	
    	// �ӿ�������  2��������(����������������������)
    	paramMap.put("subcmdid","2");
    	
    	// ������
    	paramMap.put("old_passwd","");
    	
    	// ������
    	paramMap.put("new_passwd",newPasswd);
    	
    	// ��֤��ʽ AuthCheckA :�������������֤ 
    	paramMap.put("chktype", "AuthCheckA");
    	
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
        
        // ���ýӿڳɹ����Ұ���ɹ�
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet)rw.getReturnObject();
            
            // ���÷��ؽ��
            map.put("returnObj", cout);
            
            // ���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            // ������
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        // ���ýӿڳɹ������ǰ����ɹ�
        else if(rw != null)
        {
        	// ���÷�����Ϣ
        	map.put("returnMsg", rw.getReturnMsg());
        	
        	//��������
        	map.put("errcode", rw.getErrcode());
        	
        	return map;
        }
        
        return null;
    }
    
    /**
     * ���û���������������(����SA_DB_SMTEMPLATE�����õ�ģ����)
     * 
     * @param termInfo
     * @param smsparam
     * @param servnumber
     * @param templateno
     * @param curMenuId
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    @SuppressWarnings("unchecked")
    public boolean sendRandomPwdHub(TerminalInfoPO termInfo, String smsparam, String servnumber, String templateno, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("smsparam", smsparam);
        paramMap.put("templateno", templateno);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.sendSmsHub(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
}
