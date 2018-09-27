package com.customize.cq.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.customize.cq.selfsvc.reception.model.ScoreExchangeInfoVO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ���ֶһ�
 * @author l00190940
 * @date   2011/11/03
 */

public class ScoreExchangeBean extends BaseBeanCQImpl
{

	/**
     * ���ֲ�ѯ
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryScoreValue(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
        Map paramMap = new HashMap();
        
        //���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallCQ().queryScoreValue(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String v = ((CTagSet)rw.getReturnObject()).GetValue("scoreinfo");
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", v);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	Map map = new HashMap();
            
            //���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        	
        return null;
    }

	public Map queryScoreExchangeInfo(TerminalInfoPO terminalInfoPO,
			NserCustomerSimp customer, String curMenuId, String acttype) 
	{
        Map paramMap = new HashMap();
        
        //���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        //���û����
        paramMap.put("acttype", acttype);
        
        ReturnWrap rw = getSelfSvcCallCQ().queryScoreExchangeInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = ((CRSet)rw.getReturnObject());
            String returnMsg = rw.getReturnMsg();
            List resultList = new ArrayList();
            
            for(int i = 0 ; i <= v.GetRowCount() - 1 ; i++)
            {
            	ScoreExchangeInfoVO scoreExchangeInfoVO = new ScoreExchangeInfoVO();
            	
            	//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
//            	scoreExchangeInfoVO.setScore(v.GetValue(i, 0));
//            	scoreExchangeInfoVO.setNlevel(v.GetValue(i, 1));
//            	scoreExchangeInfoVO.setNote(v.GetValue(i, 2));
//            	scoreExchangeInfoVO.setItemid(v.GetValue(i, 3));
//            	scoreExchangeInfoVO.setActiveno(v.GetValue(i, 4));
            	//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            	
            	resultList.add(scoreExchangeInfoVO);
            }

            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj",resultList);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	Map map = new HashMap();
            
            //���÷�����Ϣ
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
		return null;
	}

	public Map exchangeBalance(TerminalInfoPO terminalInfoPO,
			NserCustomerSimp customer, String curMenuId, String activeno,
			String nlevel, String serviceid) 
	{
        Map paramMap = new HashMap();
        
        //���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        //���û���
        paramMap.put("activeno", activeno);
        
        //���û����
        paramMap.put("nlevel", nlevel);
        
        //���ý�Ʒ����
        paramMap.put("serviceid", serviceid);
        
        ReturnWrap rw = getSelfSvcCallCQ().exchangeBalance(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        	if(Constants.PROOPERORGID_CQ.equals(province))
        	{
        		CTagSet result = (CTagSet)rw.getReturnObject();
        		String returnMsg = rw.getReturnMsg();
                Map map = new HashMap();
                
                // ���óɹ���־
                map.put("successFlag", "1");
                
                // ���÷��ؽ��
                map.put("returnObj", result);
                
                // ���÷�����Ϣ
                map.put("returnMsg", returnMsg);
                
                return map;
        	}
        }
        else if(rw != null)
        {
        	String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
		return null;
	}
	
}
