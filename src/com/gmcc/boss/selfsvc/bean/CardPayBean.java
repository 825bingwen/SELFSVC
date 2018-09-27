package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * ��ֵ����ֵ
 * 
 * @author cKF48754
 * 
 */
public class CardPayBean extends BaseBeanImpl
{
    
    /**
     * �ж��û���ǰ״̬
     * 
     * @return
     */
    public String qryUserState(TerminalInfoPO termInfo, String curMenuId, String telnum)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
     
        // ���õ�ǰ�˵�
        paramMap.put("menuid", curMenuId);
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnum", telnum);
        
        // ���ò���Աid
        paramMap.put("operid", termInfo.getOperid());
        
        // �����ն˻�id
        paramMap.put("termid", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.queryCurrentStatus(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
           
            //modify begin fwx439896 2017-6-20  OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            String userState=null;

        	//�ܿ����ؽ���� 20  99��������
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERSTATE))
            {
            	if("20".equals(v.GetValue("STATUS"))||"99".equals(v.GetValue("STATUS")))
            	{
            		userState="����";
            	}
            		
            }
            else
            {
            	userState=v.GetValue("state");
            }	
            return userState;
            //modify end fwx439896 2017-6-20  OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
        }
        return null;
    }
    
    /**
     * ��ֵ����ֵ
     * 
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param telnum �ֻ�����
     * @param cardpwd ��ֵ������
     * @return
     */
    public boolean cardPayCommit(TerminalInfoPO termInfo, String curMenuId, String telnum, String cardpwd)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // ���õ�ǰ�˵�
        paramMap.put("menuid", curMenuId);
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnum", telnum);
        
        // ���ò���Աid
        paramMap.put("operid", termInfo.getOperid());
        
        // �����ն˻�id
        paramMap.put("termid", termInfo.getTermid());
        
        // ���ÿͻ���ֵ������
        paramMap.put("cardpwd", cardpwd);
        
        ReturnWrap rw = selfSvcCall.cardPayCommit(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        return false;
        
    }
    
}
