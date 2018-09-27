package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;

//add begin g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
//add end g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24

import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ����ҵ���ѯ�˶�
 * 
 * @author xkf29026
 * 
 */
public class SPBean extends BaseBeanImpl
{
    /**
     * ����ҵ���ѯ
     * 
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param sn ���
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn)
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
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���
        paramMap.put("sn", sn);
        
        ReturnWrap rw = selfSvcCall.queryService(paramMap);
        Map map = new HashMap();
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
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
        	
        	// ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            return map;
        }
    }
    
    /**
     * ҵ��ͳһ�˶��ӿ�
     * 
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param operType ��������
     * @param cancelType �˶�����
     * @param dealType
     * @param domain
     * @param spId
     * @param spBizCode
     * @param bizType
     * @param effectType
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map cancelService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String operType, String cancelType, String dealType, String domain, String spId, String spBizCode,
            String bizType, String effectType)
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
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        paramMap.put("operType", operType);
        
        paramMap.put("cancelType", cancelType);
        
        paramMap.put("dealType", dealType);
        
        paramMap.put("domain", domain);
        
        paramMap.put("spId", spId);
        
        paramMap.put("spBizCode", spBizCode);
        
        paramMap.put("bizType", bizType);
        
        paramMap.put("effectType", effectType);
        
        ReturnWrap rw = selfSvcCall.cancelService(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", tagSet);
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        } 
        else if(rw != null)
        {
        	Map map = new HashMap();
        	map.put("returnMsg", rw.getReturnMsg());
        	map.put("errcode", rw.getErrcode());
        	return map;
        }
		return null;
    }
    
    /**
     * ����ҵ�񶩹��ӿ�
     * 
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer �û���Ϣ
     * @param curMenuId �˵�
     * @param operType ��������
     * @param cancelFlag �˶���־
     * @param domain ƽ̨
     * @param spId �������
     * @param spBizCode ҵ�����
     * @param bizType ҵ������
     * @param effectType ��Ч��ʽ
     * @return �������
     * @see 
     * @remark create g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
     */
    public Map orderSPService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String operType, String cancelFlag, String domain, String spId, String spBizCode, String bizType, String effectType)
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
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        paramMap.put("operType", operType);
        
        paramMap.put("cancelFlag", cancelFlag);
        
        paramMap.put("domain", domain);
        
        paramMap.put("spId", spId);
        
        paramMap.put("spBizCode", spBizCode);
        
        paramMap.put("bizType", bizType);
        
        paramMap.put("effectType", effectType);
        
        ReturnWrap rw = selfSvcCall.orderSPService(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet tagSet = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", tagSet);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        } 
        else if(rw != null)
        {
            Map map = new HashMap();
            map.put("returnMsg", rw.getReturnMsg());
            map.put("errcode", rw.getErrcode());
            return map;
        }
        return null;
    }
    
    /**
     * ��ѯ�û��Ѷ�������ҵ���б�
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param sn
     * @param ignoreFlag trueʱ����̨����192Ҳ��Ϊ�ǳɹ��ģ�����192��Ϊʧ��
     * @return
     */
    public Map queryService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn, boolean ignoreFlag)
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
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���
        paramMap.put("sn", sn);
        
        ReturnWrap rw = selfSvcCall.queryService(paramMap);
        Map map = new HashMap();
        
        // ��̨����192Ҳ��Ϊ�ǳɹ���
        if (ignoreFlag)
        {
        	// begin zKF66389 2015-09-15 9�·�findbugs�޸�
        	//if (rw != null && (rw.getStatus() == SSReturnCode.SUCCESS || rw.getErrcode() == 192))
        	if (rw.getStatus() == SSReturnCode.SUCCESS || rw.getErrcode() == 192)
            {
                CRSet v = (CRSet)rw.getReturnObject();
                String returnMsg = rw.getReturnMsg();
                
                // ���÷��ؽ��
                map.put("returnObj", v);
                
                // ���÷�����Ϣ
                map.put("returnMsg", returnMsg);
                
                return map;
            }
            else
            {
            	String returnMsg = rw.getReturnMsg();
            	
            	// ���÷�����Ϣ
                map.put("returnMsg", returnMsg);
                return map;
            }
        }
        
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
        	String returnMsg = rw.getReturnMsg();
        	
        	// ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            return map;
        }
    }
}
